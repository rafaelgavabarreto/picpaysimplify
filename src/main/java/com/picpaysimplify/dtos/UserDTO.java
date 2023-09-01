package com.picpaysimplify.dtos;

import com.picpaysimplify.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(UserType userType, String first_name, String last_name, BigDecimal balance, String email, String password) {
}
