package com.ecommproject.userservice.service;

import com.ecommproject.userservice.dto.SignUpRequestDto;
import com.ecommproject.userservice.exception.InvalidUsernameOrPasswordException;
import com.ecommproject.userservice.exception.UserNotFoundException;
import com.ecommproject.userservice.model.User;
import com.ecommproject.userservice.repo.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SecretKey secretKey;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SecretKey secretKey) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.secretKey = secretKey;
    }

    @Override
    public User signUp(SignUpRequestDto request, String password) {
        User user = User.from(request);
        user.setHashPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public String signIn(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new InvalidUsernameOrPasswordException("Invalid username");
        }

        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getHashPassword())) {
            throw new InvalidUsernameOrPasswordException("Invalid password");
        }

        return this.jwtToken(user);
    }

    private String jwtToken(User user) {
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));

        return Jwts.builder()
                .subject("Joe")
                .claim("email", user.getEmail())
                .claim("user_id", user.getId())
                .claim("roles", roles)
                .signWith(secretKey)
                .compact();
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Invalid username");
        }

        return optionalUser.get();
    }
}
