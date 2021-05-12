package com.epam.gameshop.action;

import com.epam.gameshop.DAO.GameDAO;
import com.epam.gameshop.DAO.GameDAOImpl;
import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import com.mysql.cj.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteGameAction implements Action, Constants {
    private final ConnectionPool pool;
    private final GameDAO gameDAO;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        Game game = new Game(Integer.parseInt(request.getParameter(DELETED_GAME)));

        try {
            connection.setAutoCommit(false);
            gameDAO.deleteLinks(game, connection);
            gameDAO.delete(game, connection);
            connection.commit();
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
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
