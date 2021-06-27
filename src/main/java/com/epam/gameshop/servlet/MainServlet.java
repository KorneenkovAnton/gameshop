package com.epam.gameshop.servlet;

import com.epam.gameshop.action.AbstractActionFactory;
import com.epam.gameshop.action.Action;
import com.epam.gameshop.util.constants.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet implements Constants {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = AbstractActionFactory.getInstance().getAction(req, resp);
        if (action != null) {
            String view;
            try {
                view = action.execute(req, resp);
                getServletContext().getRequestDispatcher(view).forward(req, resp);
            } catch (Exception e) {
                req.setAttribute(HTTPError, ERROR_500);
                req.setAttribute(ERROR_MESSAGE, e.getMessage());
                getServletContext().getRequestDispatcher(ERROR_JSP).forward(req, resp);
            }
        } else {
            req.setAttribute(HTTPError, ERROR_404);
            getServletContext().getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
