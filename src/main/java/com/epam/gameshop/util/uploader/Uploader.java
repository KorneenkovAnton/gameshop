package com.epam.gameshop.util.uploader;

import com.epam.gameshop.DAO.DAO;
import com.epam.gameshop.DAO.FileDAOImpl;
import com.epam.gameshop.entity.Poster;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Uploader implements Constants {
    private final ConnectionPool connectionPool;
    private final ServletFileUpload servletFileUpload;
    private final DAO<Poster> fileDAO;

    {
        connectionPool = ConnectionPool.getInstance();
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        fileDAO = new FileDAOImpl();
    }

    public Poster uploadFile(HttpServletRequest request) throws FileUploadException {
        List<FileItem> items = servletFileUpload.parseRequest(request);
        Connection connection = connectionPool.getConnection();
        Poster poster = new Poster();

        for (FileItem item : items) {
            if (!item.isFormField()) {

                try {
                    poster.setFile(item.get());
                    poster.setMimeType(item.getContentType());
                    fileDAO.addToDatabase(poster, connection);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    connectionPool.closeConnection(connection);
                }
            }
        }
        return poster;
    }
}
