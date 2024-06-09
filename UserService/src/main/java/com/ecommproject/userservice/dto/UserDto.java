package com.ecommproject.userservice.dto;

import com.ecommproject.userservice.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<String> roles;

    public static UserDto from(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));
        userDto.setRoles(roles);

        return userDto;
    }
}
