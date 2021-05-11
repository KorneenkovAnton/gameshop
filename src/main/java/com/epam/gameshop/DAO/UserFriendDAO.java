package com.epam.gameshop.DAO;

import com.epam.gameshop.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserFriendDAO {
    void addFriendToDataBase(User user, long friendID, Connection connection) throws SQLException;
    List<User> selectAllFriendsOfUser(User user, Connection connection) throws SQLException;
    List<User> selectAllFriendRequestsOfUser(User user,Connection connection) throws SQLException;
    void deleteAllFriendsOfUser(User user, Connection connection) throws SQLException;
    void deleteFriend(User user,long friendId,Connection connection) throws SQLException;
    void confirmRel(User user, long friendID, Connection connection) throws SQLException;


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

    List<User> getAllAvailableFriends(User user, Connection connection) throws SQLException;
}
