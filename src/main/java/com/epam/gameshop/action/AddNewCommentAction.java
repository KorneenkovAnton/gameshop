package com.epam.gameshop.action;

import com.epam.gameshop.DAO.CommentDAO;
import com.epam.gameshop.DAO.CommentDAOImpl;
import com.epam.gameshop.entity.Comment;
import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class AddNewCommentAction implements Action, Constants {
    private final ConnectionPool pool;
    private final CommentDAO commentDAO;

    {
        pool = ConnectionPool.getInstance();
        commentDAO = new CommentDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        Game currentGame = (Game) session.getAttribute("gameInfo");

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setMessage(request.getParameter("message"));
        comment.setGameId(currentGame.getId());

        try {
            commentDAO.addToDatabase(comment, connection);
            currentGame.getComments().add(comment);

            session.setAttribute("gameInfo", currentGame);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }finally {
            pool.closeConnection(connection);
        }

        return SHOW_GAME_INFO_JSP;
    }
}
