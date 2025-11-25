package com.example.practiseDemo.Mapper;

import com.example.practiseDemo.dto.TodoRequest;
import com.example.practiseDemo.dto.TodoResponse;
import com.example.practiseDemo.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo toEntity(TodoRequest request){
        Todo todo = new Todo();
        todo.setTodo(request.getTodo());
        todo.setTodo(String.valueOf(request.isCheck()));
return todo;
    }

    public TodoResponse toResponse(Todo todo){
        return  new TodoResponse(
                todo.getId(),
                todo.getTodo(),
                todo.isCheck(),
                todo.getUser().getEmail()
        );
    }
}
