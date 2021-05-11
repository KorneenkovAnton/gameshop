package com.epam.gameshop.action;

import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


public class ShowLoginPageAction implements Action,Constants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return LOGIN_JSP;
    }
}
