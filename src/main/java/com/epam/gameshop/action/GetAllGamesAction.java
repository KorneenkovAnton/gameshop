package com.epam.gameshop.action;

import com.epam.gameshop.DAO.*;
import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.Poster;
import com.epam.gameshop.entity.SystemRequirements;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GetAllGamesAction implements Action, Constants {

    private static final Logger logger = Logger.getLogger(GetAllGamesAction.class);

    private final ConnectionPool pool;
    private final ResourceBundle rb;
    private final GameDAO gameDAO;
    private final DAO<SystemRequirements> systemRequirementsDAO;
    private final DAO<Poster> fileDAO;

    {
        pool = ConnectionPool.getInstance();
        rb = ResourceBundle.getBundle(PROPERTIES_NAME);
        gameDAO = new GameDAOImpl();
        systemRequirementsDAO = new SystemRequirementsDAOImpl();
        fileDAO = new FileDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int currentPage = 1;
        int countOfRecords = Integer.parseInt(rb.getString(COUNT_OF_RECORDS));
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();

        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        try {
            List<Game> games = gameDAO.getAll(currentPage, countOfRecords, connection);
            if (games != null) {
                for (Game game : games) {
                    game.setMinimalSystemRequirements(systemRequirementsDAO.getById(
                            game.getMinimalSystemRequirements().getId(), connection));
                    game.setRecommendedSystemRequirements(systemRequirementsDAO.getById(
                            game.getRecommendedSystemRequirements().getId(), connection));
                    game.setPoster(fileDAO.getById(game.getPoster().getId(),connection));
                }
            }

            session.setAttribute(GAMES_ATTRIBUTE, games);
            request.setAttribute(CURRENT_PAGE, currentPage);

            int countOfPages = gameDAO.countOfRecords(connection, COUNT_OF_RECORDS_IN_GAME_TABLE);
            if (countOfPages % countOfRecords == 0) {
                countOfPages = countOfPages / countOfRecords;
            } else {
                countOfPages = countOfPages / countOfRecords + 1;
            }

            request.setAttribute(COUNT_OF_PAGES_ATTRIBUTE, countOfPages);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            throw new SQLException(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return SHOW_GAMES_JSP;
    }
}
