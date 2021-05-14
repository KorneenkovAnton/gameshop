package com.epam.gameshop.DAO;

import com.epam.gameshop.entity.Poster;
import com.epam.gameshop.util.constants.Constants;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Base64;

public class FileDAOImpl implements DAO<Poster>, Constants {

    @Override
    public void addToDatabase(Poster poster, Connection connection) throws SQLException {
        try (InputStream is = new ByteArrayInputStream(poster.getFile())) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBlob(1, is);
            preparedStatement.setString(2, poster.getMimeType());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    poster.setId(generatedKey.getLong(1));
                    closeResultSet(generatedKey);
                } else {
                    closeResultSet(generatedKey);
                    closePrepareStatement(preparedStatement);
                    throw new SQLException("File save fail");
                }
            }
            closePrepareStatement(preparedStatement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Poster poster, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Poster V, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Poster getById(long id, Connection connection) throws SQLException {
        Poster poster = new Poster();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_FILE_BY_ID);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Blob blob = resultSet.getBlob(DATA_COLUMN);
            poster.setId(resultSet.getLong(ID_COLUMN));
            poster.setMimeType(resultSet.getString(MIME_TYPE_COLUMN));
            poster.setFile(blob.getBytes(1, (int) blob.length()));
            poster.setBase64file(Base64.getEncoder().encodeToString(poster.getFile()));
            blob.free();
        }
        closeResultSet(resultSet);
        closePrepareStatement(preparedStatement);
        return poster;
    }
}
