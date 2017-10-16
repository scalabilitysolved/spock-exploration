package com.scalabilitysolved;

import java.util.Arrays;

public enum Role {

    UNKNOWN_USER,
    GUEST,
    USER,
    ADMIN;


    public static Role findRole(String name) {
        return Arrays.stream(values())
                .filter(role -> role.name().toLowerCase().equals(name))
                .findFirst()
                .orElse(UNKNOWN_USER);
    }

}
