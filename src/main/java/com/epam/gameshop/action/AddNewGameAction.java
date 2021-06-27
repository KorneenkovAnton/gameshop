package com.epam.gameshop.action;

import com.epam.gameshop.DAO.DAO;
import com.epam.gameshop.DAO.GameDAO;
import com.epam.gameshop.DAO.GameDAOImpl;
import com.epam.gameshop.DAO.SystemRequirementsDAOImpl;
import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.SystemRequirements;
import com.epam.gameshop.entity.User;
import org.apache.commons.fileupload.FileUploadException;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import com.epam.gameshop.util.uploader.Uploader;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class AddNewGameAction implements Action, Constants {

    private static final Logger logger = Logger.getLogger(AddNewGameAction.class);

    private final ConnectionPool pool;
    private final GameDAO gameDAO;
    private final DAO<SystemRequirements> systemRequirementsDAO;
    private final Uploader posterUploader;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
        systemRequirementsDAO = new SystemRequirementsDAOImpl();
        posterUploader = new Uploader();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute(ADDED_GAME);

        try {
            game.setPoster(posterUploader.uploadFile(request));
            connection.setAutoCommit(false);
            systemRequirementsDAO.addToDatabase(game.getMinimalSystemRequirements(), connection);
            systemRequirementsDAO.addToDatabase(game.getRecommendedSystemRequirements(), connection);
            gameDAO.addToDatabase(game, connection);
            connection.commit();
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException(e.getMessage());
        } catch (FileUploadException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
        } finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
