package com.epam.gameshop.action;

import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


public class LogoutAction implements Action,Constants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        session.invalidate();
        return MAIN_PAGE_ACTION;
    }
}
