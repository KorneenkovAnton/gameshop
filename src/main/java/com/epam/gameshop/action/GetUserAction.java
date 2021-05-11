package com.epam.gameshop.action;

import com.epam.gameshop.DAO.AddressDAOImpl;
import com.epam.gameshop.DAO.DAO;
import com.epam.gameshop.DAO.UserDAO;
import com.epam.gameshop.DAO.UserDAOImpl;
import com.epam.gameshop.entity.Address;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import com.epam.gameshop.util.validator.UserValidator;
import com.epam.gameshop.util.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.gameshop.util.validator.UserValidator.*;


public class GetUserAction implements Action, Constants {
    private final ConnectionPool pool;
    private final UserDAO userDAO;
    private final DAO<Address> addressDAO;
    private final Validator<User> validator;

    {
        pool = ConnectionPool.getInstance();
        userDAO = new UserDAOImpl();
        addressDAO = new AddressDAOImpl();
        validator = new UserValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        String answer = LOGIN_JSP;
        User user;

        try {
            if (validator.validateString(request.getParameter(LOGIN), LOGIN_REGEX) &&
                    validator.validateString(request.getParameter(PASSWORD), PASSWORD_REGEX)) {
                user = userDAO.getByLoginAndPassword(request.getParameter(LOGIN), request.getParameter(PASSWORD),
                        connection);
                if (user != null) {
                    user.setAddress(addressDAO.getById(user.getId(), connection));
                    session.setAttribute(USER_ATTRIBUTE, user);
                    answer = MAIN_PAGE_ACTION;
                } else {
                    request.setAttribute(OPERATION_STATUS, WRONG_LOGIN_OR_PASSWORD);
                }
            } else {
                request.setAttribute(OPERATION_STATUS, VALIDATION_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }

        return answer;
    }
}
