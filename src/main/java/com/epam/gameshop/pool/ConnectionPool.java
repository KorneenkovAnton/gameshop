package com.epam.gameshop.pool;


import com.epam.gameshop.util.constants.Constants;
import com.epam.gameshop.util.creator.UserCreator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool implements Constants {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final int COUNT_OF_CONNECTIONS;
    public static ConnectionPool instance;
    private static ResourceBundle rb;
    private BlockingQueue<Connection> pool;

    {
        rb = ResourceBundle.getBundle(PROPERTIES_NAME);
        URL = rb.getString(URL_PROP);
        USER = rb.getString(USER_ATTRIBUTE);
        PASSWORD = rb.getString(PASSWORD_PROP);
        COUNT_OF_CONNECTIONS = Integer.parseInt(rb.getString(COUNT_OF_CONNECTION_PROP));
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

            for (int i = 0; i < COUNT_OF_CONNECTIONS; i++) {
                try {
                    pool.add(DriverManager.getConnection(URL, USER, PASSWORD));
                } catch (SQLException e) {
                    logger.error("Connection failed...");
                    logger.error(e.getMessage());
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
    }

    public synchronized Connection getConnection() {
        Connection con = null;

        try {
            con = pool.take();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
        return con;
    }


    public synchronized void closeConnection(Connection c) {
        if (c != null) {
            try {
                pool.put(c);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
                logger.error(e.getStackTrace());
            }
        }
    }

    public ConnectionPool() {
        this.createPool();
    }
}
