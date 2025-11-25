package com.example.practiseDemo.controller;

import com.example.practiseDemo.dto.UserRequest;
import com.example.practiseDemo.dto.UserResponse;
import com.example.practiseDemo.entity.User;
import com.example.practiseDemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest request) {
        return service.createUser(request);
    }

    @GetMapping("")
    public List<UserResponse> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) {
        return service.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "Deleted";
    }

    @GetMapping("/{id}")
    public UserResponse byId(@PathVariable Long id) {
        return service.findId(id);
    }
    @GetMapping("/email/{email}")
    public  UserResponse Email(@PathVariable String email){
        return service.findEmail(email);
    }
}
