package com.example.practiseDemo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String todo;
    private boolean isCheck;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
