package com.epam.gameshop.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.gameshop.util.constants.Constants.*;

public interface DAO <T> {

    void addToDatabase(T t, Connection connection) throws SQLException;
    void update(T t, Connection connection) throws SQLException;
    void delete(T t, Connection connection) throws SQLException;
    T getById(long id, Connection connection) throws SQLException;

    default void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    default void closeResultSet(ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    default int countOfRecords(Connection connection,String query) throws SQLException {
        int countOfRecords = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            countOfRecords = resultSet.getInt(COUNT);
        }
        return countOfRecords;
    }

    default int countOfRecordsInSearch(Connection connection,String query,String keyWord) throws SQLException {
        int countOfRecords = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,keyWord);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            countOfRecords = resultSet.getInt(COUNT);
        }
        return countOfRecords;
    }

}
