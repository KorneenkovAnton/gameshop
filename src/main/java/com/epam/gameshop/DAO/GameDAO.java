package com.epam.gameshop.DAO;


import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GameDAO extends DAO<Game> {
    void deleteLinks(Game game, Connection connection) throws SQLException;
    List<Game> getUserGames(User user, Connection connection) throws SQLException;
    void deleteAllUserGames(User user, Connection connection) throws  SQLException;
    void deleteUserGame(User user,long gameID, Connection connection) throws SQLException;
    List<Game> getAll(int page,int recordsPerPage,Connection connection) throws SQLException;
    void addGameToUser(User user,Game game, Connection connection) throws SQLException;
    List<Game> getGameByName(int page, int recordsPerPage, String gameName, Connection connection) throws SQLException;
}

