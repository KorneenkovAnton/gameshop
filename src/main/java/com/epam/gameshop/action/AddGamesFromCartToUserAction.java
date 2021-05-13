package com.epam.gameshop.action;

import com.epam.gameshop.DAO.GameDAO;
import com.epam.gameshop.DAO.GameDAOImpl;
import com.epam.gameshop.DAO.UserDAO;
import com.epam.gameshop.DAO.UserDAOImpl;
import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddGamesFromCartToUserAction implements Action, Constants {
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
        List<Game> games = (List<Game>) session.getAttribute(CART_ATTRIBUTE);
        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        int gamesCostSum = games.stream().map(Game::getCost).reduce(Integer::sum).orElse(0);

        try {
            if (user.getMoney() >= gamesCostSum) {
                connection.setAutoCommit(false);
                for (Game game : games) {
                    gameDAO.addGameToUser(user, game, connection);
                    userDAO.buyGame(user, game, connection);
                    user.setMoney(user.getMoney() - game.getCost());
                }
                session.removeAttribute(CART_ATTRIBUTE);
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
                request.setAttribute(USER_ATTRIBUTE, user);
                connection.commit();
            } else {
                request.setAttribute(OPERATION_STATUS, NOT_ENOUGH_MONEY);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
