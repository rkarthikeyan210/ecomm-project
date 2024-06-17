package com.ecommproject.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignupEventDto {
    private String to;
    private String subject;
    private String body;
    private String event;
}
