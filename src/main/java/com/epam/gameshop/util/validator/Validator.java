package com.epam.gameshop.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface Validator<T> {

    boolean isValid(T t);

    default boolean validateString(String string, String regex){
        boolean answer = false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if(matcher.matches()){
            answer = true;
        }
        return answer;
    }
}
