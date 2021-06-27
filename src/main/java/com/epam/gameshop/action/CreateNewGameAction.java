package com.epam.gameshop.action;

import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.SystemRequirements;
import com.epam.gameshop.util.constants.Constants;
import com.epam.gameshop.util.creator.Creator;
import com.epam.gameshop.util.creator.GameCreator;
import com.epam.gameshop.util.validator.GameValidator;
import com.epam.gameshop.util.validator.SystemReqValidator;
import com.epam.gameshop.util.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


public class CreateNewGameAction implements Action, Constants {

    private final Creator<Game> creator;
    private final Validator<Game> validatorGame;
    private final Validator<SystemRequirements> validatorSys;

    {
        creator = new GameCreator();
        validatorGame = new GameValidator();
        validatorSys = new SystemReqValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Game game = creator.create(request);
        String answer;

        if (validatorGame.isValid(game) && validatorSys.isValid(game.getMinimalSystemRequirements()) &&
                validatorSys.isValid(game.getRecommendedSystemRequirements())) {
            session.setAttribute(ADDED_GAME, game);
            answer = ADD_POSTER_JSP;
        } else {
            request.setAttribute(OPERATION_STATUS, VALIDATION_ERROR);
            answer = ADD_GAME_PAGE_JSP;
        }

        return answer;
    }
}
