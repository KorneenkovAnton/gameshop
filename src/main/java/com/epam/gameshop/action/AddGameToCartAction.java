package com.epam.gameshop.action;

import com.epam.gameshop.DAO.DAO;
import com.epam.gameshop.DAO.FileDAOImpl;
import com.epam.gameshop.DAO.GameDAO;
import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.Poster;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import com.epam.gameshop.DAO.GameDAOImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddGameToCartAction implements Action, Constants {

    private static final Logger logger = Logger.getLogger(AddGameToCartAction.class);

    private final ConnectionPool pool;
    private final GameDAO gameDAO;
    private final DAO<Poster> fileDAO;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
        fileDAO = new FileDAOImpl();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        long gameID = Long.parseLong(request.getParameter(ADDED_GAME));
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        List<Game> cart = (List<Game>) session.getAttribute(CART_ATTRIBUTE);
        Game game;


        if (cart == null) {
            cart = new ArrayList<>();
        }

        try {
            game = gameDAO.getById(gameID, connection);
            game.setPoster(fileDAO.getById(game.getPoster().getId(), connection));
            if (!cart.contains(game) && gameDAO.getUserGames(user, connection) == null ||
                    !gameDAO.getUserGames(user, connection).contains(game)) {
                cart.add(game);
                session.setAttribute(CART_ATTRIBUTE, cart);
            } else {
                request.setAttribute(OPERATION_STATUS, GAME_AVAILABLE);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            throw new SQLException(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
