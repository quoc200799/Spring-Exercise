package com.example.demojpa.controller;

import com.example.demojpa.entity.Todo;
import com.example.demojpa.request.CreateTodoRequest;
import com.example.demojpa.request.UpdateTodoRequest;
import com.example.demojpa.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class TodoController {
    @Autowired
    private final TodoService todoService;


    @GetMapping("todos")
    public List<Todo> getAllTodo() {
        return todoService.getAllTodo();
    }

    @GetMapping("todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Integer id) {

        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("todos")
    @ResponseStatus(HttpStatus.CREATED) //201
    public Todo createTodo(@Validated @RequestBody CreateTodoRequest request) {
        return todoService.createTodo(request);
    }

    @PutMapping("todos/{id}")
    public Todo updateTodo(@PathVariable Integer id, @PathVariable UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    @DeleteMapping("todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public Todo deleteTodo(@PathVariable Integer id) {
        return todoService.deleteTodo(id);
    }

}
