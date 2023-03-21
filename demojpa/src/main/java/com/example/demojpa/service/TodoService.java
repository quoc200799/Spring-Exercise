package com.example.demojpa.service;

import com.example.demojpa.entity.Todo;
import com.example.demojpa.entity.User;
import com.example.demojpa.repository.TodoRepository;
import com.example.demojpa.request.CreateTodoRequest;
import com.example.demojpa.request.UpdateTodoRequest;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TodoService {

    private TodoRepository todoRepository;
@Autowired
    public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Todo user = Todo.builder()
                    .title(faker.name().fullName())
                    .status(faker.bool().bool())
                    .build();

            todoRepository.save(user);
        }


    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Integer id) {

        Optional<Todo> todo = todoRepository.findById(31);
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new RuntimeException("Not found id");
        }
    }

    public Todo createTodo(CreateTodoRequest request) {

        Optional<Todo> todoOptional = todoRepository.findByTitle(request.getTitle());
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setTitle("Luong Quoc");
            todoRepository.save(todo);
            return todo;
        } else {
            throw new RuntimeException("title không được để trống");
        }
    }

    public Todo updateTodo(Integer id, UpdateTodoRequest request) {
        for (Todo t : todoRepository.findAll()) {
            if (Objects.equals(t.getId(), id)) {
                t.setTitle(request.getTitle());
                t.setStatus(request.getStatus());
                return t;
            }
        }
        throw new RuntimeException("Not found id");
    }

    public Todo deleteTodo(Integer id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todoRepository.delete(todo);

        } else {
            throw new RuntimeException("Not found id");
        }
        return null;
    }
}
