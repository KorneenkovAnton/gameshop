package com.epam.gameshop.action;

import com.epam.gameshop.entity.Game;
import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class DeleteFromCartAction implements Action, Constants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        long gameId = Long.parseLong(request.getParameter(DELETE_FROM_CART_GAME_ID));
        HttpSession session = request.getSession();
        List<Game> cart = (List<Game>) session.getAttribute(CART_ATTRIBUTE);

        cart.removeIf(n -> (n.getId() == gameId));

        session.setAttribute(CART_ATTRIBUTE, cart);
        return CART_JSP;
    }
}
