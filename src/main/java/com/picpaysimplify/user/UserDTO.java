package com.picpaysimplify.user;

import java.math.BigDecimal;

public record UserDTO(UserType userType, String firstName, String lastName, BigDecimal balance, String email, String password) {
}
