package com.epam.gameshop.DAO;


import com.epam.gameshop.entity.User;
import com.epam.gameshop.util.constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFriendDAOImpl implements UserFriendDAO, Constants {

    @Override
    public void addFriendToDataBase(User user, long friendID, Connection connection) throws SQLException {
        if (!isFriends(user.getId(), friendID, connection)) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_FRIEND);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, friendID);
            preparedStatement.execute();
            closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public List<User> selectAllFriendsOfUser(User user, Connection connection) throws SQLException {
        List<User> friends;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER_FRIENDS);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, user.getId());
        preparedStatement.setLong(3, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        friends = makeUserList(resultSet);
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);

        return friends;
    }

    @Override
    public List<User> selectAllFriendRequestsOfUser(User user, Connection connection) throws SQLException {
        List<User> requests;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER_FRIEND_REQUESTS);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        requests = makeUserList(resultSet);
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);

        return requests;
    }

    @Override
    public void deleteAllFriendsOfUser(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_FRIENDS);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);

    }

    @Override
    public void deleteFriend(User user, long friendId, Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FRIEND);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, friendId);
        preparedStatement.setLong(3, friendId);
        preparedStatement.setLong(4, user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    @Override
    public void confirmRel(User user, long friendID, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CONFIRM_FRIEND);
        preparedStatement.setLong(1, friendID);
        preparedStatement.setLong(2, user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    private boolean isFriends(long userID, long friendID, Connection connection) throws SQLException {
        boolean isFriend = false;
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_REL);
        preparedStatement.setLong(1, friendID);
        preparedStatement.setLong(2, userID);
        preparedStatement.setLong(3, userID);
        preparedStatement.setLong(4, friendID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            isFriend = true;
        }
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return isFriend;
    }

    @Override
    public List<User> getAllAvailableFriends(User user, Connection connection) throws SQLException {
        List<User> friends;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AVAILABLE_FRIENDS);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, user.getId());
        preparedStatement.setLong(3, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        friends = makeUserList(resultSet);
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return friends;
    }

    private List<User> makeUserList(ResultSet resultSet) throws SQLException {
        List<User> list = null;
        if (resultSet.next()) {
            list = new ArrayList<>();
            do {
                list.add(new User(resultSet.getLong(USERID_COLUMN), resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN),
                        resultSet.getString(EMAIL_COLUMN), resultSet.getString(NAME_COLUMN), resultSet.getString(SNAME_COLUMN)));
            } while (resultSet.next());
        }
        return list;
    }
}
