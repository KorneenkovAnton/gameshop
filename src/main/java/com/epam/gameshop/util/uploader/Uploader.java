package com.epam.gameshop.util.uploader;

import com.epam.gameshop.DAO.DAO;
import com.epam.gameshop.DAO.FileDAOImpl;
import com.epam.gameshop.entity.Poster;
import com.epam.gameshop.pool.ConnectionPool;
import com.epam.gameshop.util.constants.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Uploader implements Constants {

    private static final Logger logger = Logger.getLogger(Uploader.class);

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
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    logger.error(e.getStackTrace());
                }finally {
                    connectionPool.closeConnection(connection);
                }
            }
        }
        return poster;
    }
}
