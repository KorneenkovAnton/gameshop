package com.epam.gameshop.action;

import com.epam.gameshop.DAO.GameDAO;
import com.epam.gameshop.DAO.GameDAOImpl;
import com.epam.gameshop.DAO.UserDAO;
import com.epam.gameshop.DAO.UserDAOImpl;
import com.epam.gameshop.entity.Game;
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

public class AddGameToUserAction implements Action, Constants {

    private static final Logger logger = Logger.getLogger(AddGameToUserAction.class);

    private final ConnectionPool pool;
    private final GameDAO gameDAO;
    private final UserDAO userDAO;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        List<Game> cart = (List<Game>) session.getAttribute(CART_ATTRIBUTE);

        try {
            user.setOwnedGames(gameDAO.getUserGames(user, connection));
            Game game = gameDAO.getById(Integer.parseInt(request.getParameter(ADDED_GAME)), connection);
            if ((user.getOwnedGames() == null || !user.getOwnedGames().contains(game)) &&
                    (cart == null || !cart.contains(game))) {
                if (user.getMoney() >= game.getCost()) {
                    connection.setAutoCommit(false);
                    userDAO.buyGame(user, game, connection);
                    gameDAO.addGameToUser(user, game, connection);
                    user.setMoney(user.getMoney() - game.getCost());
                    connection.commit();
                    request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
                } else {
                    request.setAttribute(OPERATION_STATUS, NOT_ENOUGH_MONEY);
                }
            } else {
                request.setAttribute(OPERATION_STATUS, GAME_AVAILABLE);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            connection.rollback();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_ACTION;
    }
}
