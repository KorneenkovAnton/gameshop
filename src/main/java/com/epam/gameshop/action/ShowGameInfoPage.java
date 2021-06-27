package com.epam.gameshop.action;

import com.epam.gameshop.DAO.DAO;
import com.epam.gameshop.DAO.GameDAO;
import com.epam.gameshop.DAO.FileDAOImpl;
import com.epam.gameshop.DAO.GameDAOImpl;
import com.epam.gameshop.DAO.SystemRequirementsDAOImpl;
import com.epam.gameshop.DAO.CommentDAOImpl;
import com.epam.gameshop.DAO.CommentDAO;
import com.epam.gameshop.entity.*;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class ShowGameInfoPage implements Action, Constants {

    private static final Logger logger = Logger.getLogger(ShowGameInfoPage.class);

    private final ConnectionPool pool;
    private final GameDAO gameDAO;
    private final DAO<SystemRequirements> systemRequirementsDAO;
    private final DAO<Poster> fileDAO;
    private final CommentDAO commentDAO;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
        systemRequirementsDAO = new SystemRequirementsDAOImpl();
        fileDAO = new FileDAOImpl();
        commentDAO = new CommentDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        Long gameId = Long.valueOf(request.getParameter(GAME_INFO_PARAMETER));

        try {
            Game game = gameDAO.getById(gameId, connection);
            game.setMinimalSystemRequirements(systemRequirementsDAO.getById(
                    game.getMinimalSystemRequirements().getId(), connection));
            game.setRecommendedSystemRequirements(systemRequirementsDAO.getById(
                    game.getRecommendedSystemRequirements().getId(), connection));
            game.setPoster(fileDAO.getById(game.getPoster().getId(),connection));
            game.setComments(commentDAO.getByGame(gameId, connection));

            session.setAttribute(GAME_INFO_PARAMETER, game);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            throw new SQLException(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }


        return SHOW_GAME_INFO_JSP;
    }
}
