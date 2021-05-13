package com.epam.gameshop.util.validator;

import com.epam.gameshop.entity.Game;


public class GameValidator implements Validator<Game> {
    private static final String STRING_REGEX = "^[а-яА-ЯёЁa-zA-Z\\p{P}:?0-9]+$";

    @Override
    public boolean isValid(Game game) {
        System.out.println(game);
        boolean answer = false;
        System.out.println(validateString(game.getName(), STRING_REGEX));
        System.out.println(validateString(game.getDeveloper(), STRING_REGEX));
        if (game != null && validateString(game.getName(), STRING_REGEX) && validateString(game.getDeveloper(), STRING_REGEX)) {
            answer = true;
        }
        return answer;
    }

}
