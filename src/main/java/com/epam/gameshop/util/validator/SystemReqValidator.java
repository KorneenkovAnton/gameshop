package com.epam.gameshop.util.validator;

import com.epam.gameshop.entity.SystemRequirements;


public class SystemReqValidator implements Validator<SystemRequirements> {
    private static final String STRING_REGEX = "^[a-zA-Z 0-9]+$";

    @Override
    public boolean isValid(SystemRequirements systemRequirements) {
        boolean answer = false;
        if (systemRequirements != null && validateString(systemRequirements.getCpuName(), STRING_REGEX) &&
                validateString(systemRequirements.getOperationSystem(), STRING_REGEX) &&
                validateString(systemRequirements.getVideoAdapterName(), STRING_REGEX)) {
            answer = true;
        }
        return answer;
    }


}
