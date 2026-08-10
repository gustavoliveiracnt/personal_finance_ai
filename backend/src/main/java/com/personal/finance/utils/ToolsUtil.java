package com.personal.finance.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public final class ToolsUtil {

    public static String normalize(String value){
        if(Objects.isNull(value) || value.isBlank()){
            throw new IllegalArgumentException("Value cannot be null or blank");
        }

        return value.trim();
    }
}
