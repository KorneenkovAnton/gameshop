package com.epam.gameshop.action;

import com.epam.gameshop.DAO.AddressDAOImpl;
import com.epam.gameshop.DAO.DAO;
import com.epam.gameshop.DAO.UserDAO;
import com.epam.gameshop.DAO.UserDAOImpl;
import com.epam.gameshop.entity.Address;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import com.epam.gameshop.util.creator.Creator;
import com.epam.gameshop.util.creator.UserCreator;
import com.epam.gameshop.util.validator.AddressValidator;
import com.epam.gameshop.util.validator.UserValidator;
import com.epam.gameshop.util.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterUserAction implements Action, Constants {

    private static final Logger logger = Logger.getLogger(RegisterUserAction.class);

    private final ConnectionPool pool;
    private final Creator<User> creator;
    private final DAO<Address> addressDAOImpl;
    private final UserDAO userDAOImpl;
    private final Validator<Address> addressValidator;
    private final Validator<User> userValidator;

    {
        pool = ConnectionPool.getInstance();
        creator = new UserCreator();
        addressDAOImpl = new AddressDAOImpl();
        userDAOImpl = new UserDAOImpl();
        addressValidator = new AddressValidator();
        userValidator = new UserValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        User user = creator.create(request);
        String answer = REGISTER_PAGE_JSP;

        try {

            if (addressValidator.isValid(user.getAddress()) && userValidator.isValid(user)) {
                connection.setAutoCommit(false);
                addressDAOImpl.addToDatabase(user.getAddress(), connection);
                userDAOImpl.addToDatabase(user, connection);
                answer = LOGIN_JSP;
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
                connection.commit();
            } else {
                request.setAttribute(OPERATION_STATUS, VALIDATION_ERROR);
            }

        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return answer;
    }
}
