package com.epam.gameshop.action;

import com.epam.gameshop.DAO.UserDAO;
import com.epam.gameshop.DAO.UserDAOImpl;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class SetAdminAction implements Action, Constants {
    private final ConnectionPool pool;
    private final UserDAO userDAO;

    {
        pool = ConnectionPool.getInstance();
        userDAO = new UserDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int newAdminID = Integer.parseInt(request.getParameter(NEW_ADMIN_ID));
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute(USER_ATTRIBUTE);
        User newAdmin = new User(newAdminID);

        if (admin.isAdmin()) {
            try {
                userDAO.setAdmin(newAdmin, connection);
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
                throw new SQLException(e.getMessage());
            } finally {
                pool.closeConnection(connection);
            }
        }

        return MAIN_PAGE_ACTION;
    }
}
