package com.epam.gameshop.action;

import com.epam.gameshop.DAO.UserDAOImpl;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class DonateAction implements Action, Constants {
    private final ConnectionPool pool;
    private final UserDAOImpl userDAO;

    {
        pool = ConnectionPool.getInstance();
        userDAO = new UserDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int money = Integer.parseInt(request.getParameter(DONATE_ATTRIBUTE));
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        try {
            userDAO.donate(user, money, connection);
            user.setMoney(user.getMoney() + money);
            session.setAttribute(USER_ATTRIBUTE, user);
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
        } finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_ACTION;
    }
}
