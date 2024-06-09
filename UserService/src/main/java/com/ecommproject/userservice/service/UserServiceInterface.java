package com.ecommproject.userservice.service;

import com.ecommproject.userservice.dto.SignUpRequestDto;
import com.ecommproject.userservice.model.User;

public interface UserServiceInterface {
    User signUp(SignUpRequestDto request, String password);
    String signIn(String email, String password);
    User getUser(Long id);
}
