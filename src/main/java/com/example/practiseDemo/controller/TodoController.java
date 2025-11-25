package com.example.practiseDemo.controller;

import com.example.practiseDemo.dto.TodoRequest;
import com.example.practiseDemo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  com.example.practiseDemo.dto.TodoResponse;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService service;

    @PostMapping
    public TodoResponse create(@RequestBody TodoRequest todo) {
        return service.createTodo(todo);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @RequestBody TodoRequest request) {
        return service.updateTodo(id, request);
    }

    @GetMapping()
    public List<TodoResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }
}
