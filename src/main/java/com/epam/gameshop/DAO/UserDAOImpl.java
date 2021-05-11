package com.epam.gameshop.DAO;

import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import com.epam.gameshop.util.constants.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO, Constants {


    @Override
    public void addToDatabase(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, DigestUtils.md2Hex(user.getPassword()));
        preparedStatement.setDate(3, new Date(user.getDateOfBirthday().getTime()));
        preparedStatement.setString(4, user.geteMail());
        preparedStatement.setString(5, user.getName());
        preparedStatement.setString(6, user.getsName());
        preparedStatement.setLong(7, user.getAddress().getId());
        preparedStatement.execute();
        try (ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
            if (generatedKey.next()) {
                user.setId(generatedKey.getInt(1));
                closeResultSet(generatedKey);
                closePrepareStatement(preparedStatement);
            } else {
                closeResultSet(generatedKey);
                closePrepareStatement(preparedStatement);
                throw new SQLException("Creating user failed");
            }
        }
    }

    @Override
    public void update(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPADTE_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getsName());
        preparedStatement.setString(3, DigestUtils.md2Hex(user.getPassword()));
        preparedStatement.setDate(4, new Date(user.getDateOfBirthday().getTime()));
        preparedStatement.setString(5, user.geteMail());
        preparedStatement.setLong(6, user.getId());

        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    @Override
    public void delete(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    @Override
    public User getById(long id, Connection connection) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.setName(resultSet.getString(NAME_COLUMN));
            user.setsName(resultSet.getString(SNAME_COLUMN));
            user.seteMail(resultSet.getString(EMAIL_COLUMN));
            user.setDateOfBirthday(resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN));
            user.setMoney(resultSet.getInt(MONEY_COLUMN));
        }
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return user;
    }

    @Override
    public User getByLoginAndPassword(String login, String password, Connection connection) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, DigestUtils.md2Hex(password));
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new User(resultSet.getBoolean(ADMIN_COLUMN));
            user.setLogin(login);
            user.setPassword(password);
            user.setId(resultSet.getInt(USERID_COLUMN));
            user.setName(resultSet.getString(NAME_COLUMN));
            user.setsName(resultSet.getString(SNAME_COLUMN));
            user.seteMail(resultSet.getString(EMAIL_COLUMN));
            user.setDateOfBirthday(resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN));
            user.setMoney(resultSet.getInt(MONEY_COLUMN));
        }
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return user;
    }

    @Override
    public void donate(User user, int money, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DONATE);
        preparedStatement.setInt(1, money);
        preparedStatement.setLong(2, user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    @Override
    public void buyGame(User user, Game game, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(BUY_GAME);
        preparedStatement.setLong(1, game.getId());
        preparedStatement.setLong(2, user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    @Override
    public void setAdmin(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_ROLE);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    @Override
    public List<User> getAll(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
        preparedStatement.setLong(1, user.getId());
        List<User> users = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            users = new ArrayList<>();
            do {
                users.add(new User(resultSet.getLong(USERID_COLUMN), resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN),
                        resultSet.getString(EMAIL_COLUMN), resultSet.getString(NAME_COLUMN), resultSet.getString(SNAME_COLUMN),
                        resultSet.getBoolean(ADMIN_COLUMN)));

            } while (resultSet.next());
        }
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return users;
    }
}
