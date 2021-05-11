package com.epam.gameshop.DAO;

import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface UserDAO extends DAO<User> {
    List<User> getAll(User user, Connection connection) throws SQLException;
    User getByLoginAndPassword(String login, String password , Connection connection) throws SQLException;
    void donate(User user, int money, Connection connection) throws SQLException;
    void buyGame(User user , Game game, Connection connection)throws SQLException;
    void setAdmin(User user, Connection connection) throws SQLException;
}
