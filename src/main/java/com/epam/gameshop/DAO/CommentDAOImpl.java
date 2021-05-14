package com.epam.gameshop.DAO;

import com.epam.gameshop.entity.Comment;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.util.constants.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO, Constants{

    @Override
    public void addToDatabase(Comment comment, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_COMMENT);
        preparedStatement.setLong(1,comment.getUser().getId());
        preparedStatement.setLong(2, comment.getGameId());
        preparedStatement.setString(3, comment.getMessage());
        preparedStatement.execute();
    }

    @Override
    public void update(Comment comment, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Comment V, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Comment getById(long id, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Comment> getByGame(long gameId, Connection connection) throws SQLException {
        List<Comment> result = new ArrayList<>();
        PreparedStatement preparedStatement =
                connection.prepareStatement(SELECT_COMMENT_BY_GAME);
        preparedStatement.setLong(1, gameId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            result = getCommentList(resultSet);
        }

        return result;
    }

    private List<Comment> getCommentList(ResultSet resultSet) throws SQLException {
        List<Comment> result = new ArrayList<>();
        do {
            Comment comment = new Comment();
            User user = new User();
            comment.setUser(user);
            comment.setId(resultSet.getLong(ID_COLUMN));
            comment.setCreatedAt(resultSet.getDate(CREATE_DATE_COLUMN));
            comment.setMessage(resultSet.getString(MESSAGE_COLUMN));
            user.setName(resultSet.getString(NAME_COLUMN));
            user.setsName(resultSet.getString(SNAME_COLUMN));
            result.add(comment);
        }while (resultSet.next());

        return result;
    }
}
