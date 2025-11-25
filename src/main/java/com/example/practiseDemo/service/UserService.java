package com.example.practiseDemo.service;

import com.example.practiseDemo.Mapper.UserMapper;
import com.example.practiseDemo.dto.UserRequest;
import com.example.practiseDemo.dto.UserResponse;
import com.example.practiseDemo.entity.User;
import com.example.practiseDemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest request) {
        User user = mapper.toEntity(request);
        user.setPassword(passwordEncoder().encode(request.getPassword()));
        User save = userRepository.save(user);
        return mapper.toResponse(save);
    }

    public List<UserResponse> getAll() {
        List<User> user = userRepository.findAll();
        return user.stream().map(mapper::toResponse).toList();
    }

    public UserResponse findId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return mapper.toResponse(userOptional.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Run Time"));

        // only update password if provided (and encode!)
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user.setEmail(request.getEmail());

        User updated = userRepository.save(user);
        return mapper.toResponse(updated);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        userRepository.delete(user);
    }

    public UserResponse findEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not found"));
        return mapper.toResponse(user);
    }
}
