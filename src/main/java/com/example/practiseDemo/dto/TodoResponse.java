package com.example.practiseDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class TodoResponse {

    private Long id;
    private String todo;
    private boolean isCheck;
    private String email;

}
