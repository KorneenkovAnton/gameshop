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
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateUserAction implements Action, Constants {

    private static final Logger logger = Logger.getLogger(UpdateUserAction.class);

    private final ConnectionPool pool;
    private final UserDAO userDAOImpl;
    private final DAO<Address> addressDAO;
    private final Creator<User> creator;
    private final Validator<User> userValidator;
    private final Validator<Address> addressValidator;

    {
        pool = ConnectionPool.getInstance();
        userDAOImpl = new UserDAOImpl();
        addressDAO = new AddressDAOImpl();
        creator = new UserCreator();
        userValidator = new UserValidator();
        addressValidator = new AddressValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(USER_ATTRIBUTE);
        User userUpdate = creator.create(request);

        userUpdate.setMoney(currentUser.getMoney());
        userUpdate.setId(currentUser.getId());

        if (userValidator.isValid(userUpdate) && addressValidator.isValid(userUpdate.getAddress())) {
            try {
                connection.setAutoCommit(false);
                userDAOImpl.update(userUpdate, connection);
                addressDAO.update(userUpdate.getAddress(), connection);
                connection.commit();
                session.setAttribute(USER_ATTRIBUTE, userUpdate);
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage());
                logger.error(e.getStackTrace());
                request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
                throw new SQLException(e.getMessage());
            } finally {
                pool.closeConnection(connection);
            }
        } else {
            request.setAttribute(OPERATION_STATUS, VALIDATION_ERROR);
        }
        return MAIN_PAGE_ACTION;
    }
}
