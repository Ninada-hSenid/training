package com.trainingprogram.springsecurity_api.dto.mapper;

import com.trainingprogram.springsecurity_api.dto.request.SignupRequest;
import com.trainingprogram.springsecurity_api.model.User;

import java.util.Set;

public class UserMapper {
    public static User toUser(SignupRequest request, String encodedPassword, String role) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(role));
        return user;
    }
}