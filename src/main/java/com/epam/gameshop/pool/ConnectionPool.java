package com.epam.gameshop.pool;


import com.epam.gameshop.util.constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool implements Constants {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final int COUNT_OF_CONNECTIONS;
    public static ConnectionPool instance;
    private static ResourceBundle rb;
    private BlockingQueue<Connection> pool;

    {
        rb = ResourceBundle.getBundle(PROPERTIES_NAME);
        URL = "jdbc:mysql://localhost:3306/site_of_games?useUnicode=true&characterEncoding=utf8";
        USER = "root";
        PASSWORD = "1234";
        COUNT_OF_CONNECTIONS = 4;
        pool = new ArrayBlockingQueue<>(COUNT_OF_CONNECTIONS);
    }


    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void createPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            if (rb.getKeys().hasMoreElements()) {
                createPoolWithBundle();
            } else {
                createPoolWithConstants();
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void createPoolWithConstants() {
        for (int i = 0; i < COUNT_OF_CONNECTIONS; i++) {
            try {
                pool.add(DriverManager.getConnection(URL, USER, PASSWORD));
            } catch (SQLException e) {
                System.out.println("Connection failed...");
                e.printStackTrace();
            }
        }
    }

    private void createPoolWithBundle() {
        for (int i = 0; i < Integer.parseInt(rb.getString("count_of_connections")); i++) {
            try {
                pool.add(DriverManager.getConnection(rb.getString(URL_PROP), rb.getString(USER_ATTRIBUTE),
                        rb.getString(PASSWORD_PROP)));
            } catch (SQLException e) {
                System.out.println("Connection failed...");
                e.printStackTrace();
            }
        }
    }

    public synchronized Connection getConnection() {
        Connection con = null;

        try {
            con = pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return con;
    }


    public synchronized void closeConnection(Connection c) {
        if (c != null) {
            try {
                pool.put(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ConnectionPool() {
        this.createPool();
    }
}
