package com.epam.gameshop.DAO;


import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.Poster;
import com.epam.gameshop.entity.SystemRequirements;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.util.constants.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImpl implements GameDAO, Constants {

    @Override
    public void addToDatabase(Game game, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GAME, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, game.getName());
        preparedStatement.setInt(2, game.getCost());
        preparedStatement.setDate(3, new Date(game.getReleaseDate().getTime()));
        preparedStatement.setString(4, game.getDescription());
        preparedStatement.setString(5, game.getDeveloper());
        preparedStatement.setLong(6, game.getMinimalSystemRequirements().getId());
        preparedStatement.setLong(7, game.getRecommendedSystemRequirements().getId());
        preparedStatement.setLong(8, game.getPoster().getId());
        preparedStatement.executeUpdate();
        try (ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
            if (generatedKey.next()) {
                game.setId(generatedKey.getLong(1));
                closeResultSet(generatedKey);
            } else {
                closeResultSet(generatedKey);
                closePrepareStatement(preparedStatement);
                throw new SQLException("Creating game failed");
            }
        }
        closePrepareStatement(preparedStatement);
    }


    @Override
    public void update(Game game, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GAME);
        preparedStatement.setString(1, game.getName());
        preparedStatement.setInt(2, game.getCost());
        preparedStatement.setDate(3, (Date) game.getReleaseDate());
        preparedStatement.setString(4, game.getDescription());
        preparedStatement.setString(5, game.getDeveloper());
        preparedStatement.setLong(6, game.getPoster().getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    @Override
    public void delete(Game game, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GAME);
        preparedStatement.setLong(1, game.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    public void deleteLinks(Game game, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LINKS_1);
        preparedStatement.setLong(1, game.getId());
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(DELETE_LINKS_2);
        preparedStatement.setLong(1, game.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    @Override
    public Game getById(long id, Connection connection) throws SQLException {
        Game gameTemp = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_GAME);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            gameTemp = getGame(resultSet);
        }
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return gameTemp;
    }


    public List<Game> getUserGames(User user, Connection connection) throws SQLException {
        List<Game> games;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_GAMES);
        preparedStatement.setLong(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        games = getGameLIst(resultSet);
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return games;
    }

    @Override
    public void deleteAllUserGames(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_USER_GAMES);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    public void deleteUserGame(User user, long gameID, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_GAME);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, gameID);
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    public List<Game> getAll(int page, int recordsPerPage, Connection connection) throws SQLException {
        List<Game> games;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_GAMES);
        preparedStatement.setInt(1, (page - 1) * recordsPerPage);
        preparedStatement.setInt(2, recordsPerPage);
        ResultSet resultSet = preparedStatement.executeQuery();
        games = getGameLIst(resultSet);
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return games;
    }


    public void addGameToUser(User user, Game game, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GAME_TO_USER);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, game.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    @Override
    public List<Game> getGameByName(int page, int recordsPerPage, String gameName, Connection connection) throws SQLException {
        List<Game> games;
        PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_GAME);
        preparedStatement.setString(1, "%" + gameName + "%");
        preparedStatement.setInt(2, (page - 1) * recordsPerPage);
        preparedStatement.setInt(3, recordsPerPage);
        ResultSet resultSet = preparedStatement.executeQuery();
        games = getGameLIst(resultSet);
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return games;
    }


    private List<Game> getGameLIst(ResultSet resultSet) throws SQLException {
        List<Game> games = new ArrayList<>();
        if (resultSet.next()) {
            games = new ArrayList<>();
            do {
                games.add(getGame(resultSet));
            } while (resultSet.next());
        }
        return games;
    }

    private Game getGame(ResultSet resultSet) throws SQLException {
        Game gameTemp = new Game();
        gameTemp.setId(resultSet.getLong(GAME_ID_COLUMN));
        gameTemp.setName(resultSet.getString(GAME_NAME_COLUMN));
        gameTemp.setCost(resultSet.getInt(COAST_COLUMN));
        gameTemp.setReleaseDate(resultSet.getDate(RELEASE_DATE_COLUMN));
        gameTemp.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        gameTemp.setDeveloper(resultSet.getString(DEVELOPER_COLUMN));
        gameTemp.setMinimalSystemRequirements(new SystemRequirements());
        gameTemp.setRecommendedSystemRequirements(new SystemRequirements());
        gameTemp.getMinimalSystemRequirements().setId(resultSet.getLong(MIN_SYS_REQ_COLUMN));
        gameTemp.getRecommendedSystemRequirements().setId(resultSet.getLong(REC_SYS_REQ_COLUMN));
        gameTemp.setPoster(new Poster());
        gameTemp.getPoster().setId(resultSet.getLong(FILE_ID));
        return gameTemp;
    }


}

