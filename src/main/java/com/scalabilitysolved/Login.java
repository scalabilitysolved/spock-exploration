package com.scalabilitysolved;

import lombok.Builder;

import java.util.function.Predicate;

import static io.vavr.API.*;

@Builder
public class Login {

    private String role;
    private Integer failedAuthenticationAttempts;

    public boolean isValid() {
        return Match(Role.findRole(this.role)).of(
                Case($(isValidUser()), true),
                Case($(isValidAdmin()), true),
                Case($(isValidGuestUser()), true),
                Case($(), false)
        );
    }

    private Predicate<Role> isValidUser() {
        return role -> role == Role.USER && failedAuthenticationAttempts < 1;
    }

    private Predicate<Role> isValidAdmin() {
        return role -> role == Role.ADMIN && failedAuthenticationAttempts <= 5;
    }

    private Predicate<Role> isValidGuestUser() {
        return role -> role == Role.GUEST;
    }
}
