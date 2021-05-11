package com.epam.gameshop.DAO;

import com.epam.gameshop.entity.Comment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CommentDAO extends DAO<Comment>{
    List<Comment> getByGame(long gameId, Connection connection) throws SQLException;
}
