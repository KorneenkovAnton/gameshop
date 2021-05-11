package com.epam.gameshop.action;

import com.epam.gameshop.DAO.UserFriendDAO;
import com.epam.gameshop.DAO.UserFriendDAOImpl;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddFriendAction implements Action,Constants {
    private final ConnectionPool pool;
    private final UserFriendDAO userFriendDAO;

    {
        pool = ConnectionPool.getInstance();
        userFriendDAO = new UserFriendDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        Long friendID = Long.valueOf(request.getParameter(ADD_FRIEND_ID));

        try {
            if(friendID != user.getId()) {
                connection.setAutoCommit(false);
                userFriendDAO.addFriendToDataBase(user, friendID, connection);
                connection.commit();
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
            }else {
                request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
            }
        }catch (SQLIntegrityConstraintViolationException e){
            connection.rollback();
            request.setAttribute(OPERATION_STATUS,OPERATION_WRONG_ID);
        } catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
            throw  new SQLException(e.getMessage());
        }
        finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_ACTION;
    }
}
