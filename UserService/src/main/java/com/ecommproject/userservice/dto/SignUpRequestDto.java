package com.ecommproject.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private List<String> roles;
}
