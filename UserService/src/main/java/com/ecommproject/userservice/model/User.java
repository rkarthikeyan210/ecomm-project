package com.ecommproject.userservice.model;

import com.ecommproject.userservice.dto.LoginRequestDto;
import com.ecommproject.userservice.dto.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel {
    private String name;
    private String email;
    private String hashPassword;
    private Boolean isVerified = false;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Role> roles;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public static User from(SignUpRequestDto request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());

        List<Role> roles = new ArrayList<>();
        request.getRoles().forEach(roleName -> {
            Role role = new Role();
            role.setName(roleName);
            roles.add(role);
        });
        user.setRoles(roles);

        return user;
    }

    public static User from(LoginRequestDto request) {
        return new User();
    }
}
