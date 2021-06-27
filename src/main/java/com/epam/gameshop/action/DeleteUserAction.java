package com.epam.gameshop.action;


import com.epam.gameshop.DAO.*;
import com.epam.gameshop.entity.Address;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteUserAction implements Action, Constants {

    private static final Logger logger = Logger.getLogger(DeleteUserAction.class);

    private final ConnectionPool pool;
    private final UserFriendDAO userFriendDAO;
    private final GameDAO gameDAO;
    private final UserDAO userDAO;

    {
        pool = ConnectionPool.getInstance();
        userFriendDAO = new UserFriendDAOImpl();
        gameDAO = new GameDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int idOfDeletedUser = Integer.parseInt(request.getParameter(DELETED_USER));
        int idOfAddress = Integer.parseInt(request.getParameter(DELETED_ADDRESS));
        Connection connection = pool.getConnection();
        User deletedUser = new User();

        deletedUser.setId(idOfDeletedUser);
        deletedUser.setAddress(new Address());
        deletedUser.getAddress().setId(idOfAddress);

        try {
            connection.setAutoCommit(false);
            userFriendDAO.deleteAllFriendsOfUser(deletedUser, connection);
            gameDAO.deleteAllUserGames(deletedUser, connection);
            userDAO.delete(deletedUser, connection);
            connection.commit();
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException(e.getMessage());
        }
        try {
            new AddressDAOImpl().delete(deletedUser.getAddress(), connection);
        } catch (SQLException e) {
            logger.warn("address is used");
        } finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
