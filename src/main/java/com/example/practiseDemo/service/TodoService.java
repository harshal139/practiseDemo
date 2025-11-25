package com.example.practiseDemo.service;

import com.example.practiseDemo.Mapper.TodoMapper;
import com.example.practiseDemo.dto.TodoRequest;
import com.example.practiseDemo.dto.TodoResponse;
import com.example.practiseDemo.entity.Todo;
import com.example.practiseDemo.entity.User;
import com.example.practiseDemo.repository.TodoRepository;
import com.example.practiseDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoMapper mapper;

    public TodoResponse createTodo(TodoRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todo todo = mapper.toEntity(request);
        todo.setUser(user);

        Todo saved = todoRepository.save(todo);
        return mapper.toResponse(saved);
    }

    public TodoResponse updateTodo(Long id, TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setTodo(request.getTodo());
        todo.setCheck(request.isCheck());

        Todo updated = todoRepository.save(todo);
        return mapper.toResponse(updated);
    }

    public void delete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
    }

    public List<TodoResponse> getAll() {
        return todoRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
