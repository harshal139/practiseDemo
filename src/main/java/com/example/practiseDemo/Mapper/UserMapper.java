package com.example.practiseDemo.Mapper;

import com.example.practiseDemo.dto.UserRequest;
import com.example.practiseDemo.dto.UserResponse;
import com.example.practiseDemo.entity.User;
import org.springframework.boot.autoconfigure.batch.BatchTaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

        public UserResponse toResponse(User user) {
            return new UserResponse(
                    user.getId(),
                    user.getEmail()
            );
        }
    }

