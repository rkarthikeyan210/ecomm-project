package com.ecommproject.userservice.controller;

import com.ecommproject.userservice.dto.LoginRequestDto;
import com.ecommproject.userservice.dto.SignUpRequestDto;
import com.ecommproject.userservice.dto.UserDto;
import com.ecommproject.userservice.exception.InvalidRoleException;
import com.ecommproject.userservice.model.User;
import com.ecommproject.userservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final SecretKey secretKey;

    public UserController(UserService userService, SecretKey secretKey) {
        this.userService = userService;
        this.secretKey = secretKey;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto request) {
        User savedUser = this.userService.signUp(request, request.getPassword());
        return new ResponseEntity<>(UserDto.from(savedUser), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> signIn(@RequestBody LoginRequestDto request) {
        String token = this.userService.signIn(request.getUsername(), request.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            this.checkToken(token, "ADMIN");
        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (InvalidRoleException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = this.userService.getUser(id);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    private void checkToken(String token, String role) {
        Jws<Claims> claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);

        //check for role in the token payload
        if (!role.isEmpty()) {
            List<String> roles = claims.getPayload().get("roles", List.class);

            for (String roleName : roles) {
                if (roleName.equals(role)) {
                    return;
                }
            }
            throw new InvalidRoleException("Invalid role");
        }
    }
}
