package com.epam.gameshop.action;

import com.epam.gameshop.DAO.AddressDAOImpl;
import com.epam.gameshop.DAO.DAO;
import com.epam.gameshop.DAO.UserDAO;
import com.epam.gameshop.DAO.UserDAOImpl;
import com.epam.gameshop.entity.Address;
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

public class ShowUsersAction implements Action, Constants {

    private static final Logger logger = Logger.getLogger(ShowUsersAction.class);

    private final ConnectionPool pool;
    private final UserDAO userDAO;
    private final DAO<Address> addressDAO;

    {
        pool = ConnectionPool.getInstance();
        userDAO = new UserDAOImpl();
        addressDAO = new AddressDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        List<User> users;

        try {
            users = userDAO.getAll(user, connection);
            if (users != null) {
                for (User userTemp : users) {
                    userTemp.setAddress(addressDAO.getById(userTemp.getId(), connection));
                }
            }

            session.setAttribute(USERS_ATTRIBUTE, users);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            throw new SQLException(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }

        return ALL_USERS_PAGE_JSP;
    }
}
