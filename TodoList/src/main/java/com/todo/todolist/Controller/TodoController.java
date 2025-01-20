package com.todo.todolist.Controller;

import com.todo.todolist.DTO.StatusDTO;
import com.todo.todolist.DTO.TodoDTO;
import com.todo.todolist.Service.Todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/category")
    public ResponseEntity<?> postCategory(@RequestParam Long userId,
                                          @RequestParam String title,
                                          @RequestParam String color) {
        try{
            return ResponseEntity.ok(todoService.createCategory(userId, title, color));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/todo")
    public ResponseEntity<?> postTodo(@RequestBody TodoDTO todo){
        try{
            return ResponseEntity.ok(todoService.createTodo(todo));
        } catch (Exception e){
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }
}
