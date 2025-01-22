package com.todo.todolist.Controller;

import com.todo.todolist.DTO.StatusDTO;
import com.todo.todolist.DTO.TodoDTO;
import com.todo.todolist.Service.Todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@CrossOrigin("https://superlative-entremet-ac0250.netlify.app")
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

    @DeleteMapping("/category")
    public ResponseEntity<?> deleteCategory(@RequestParam Long userId,
                                            @RequestParam Long categoryId) {
        try{
            todoService.deleteCategory(userId, categoryId);
            return ResponseEntity.ok(StatusDTO.builder()
                            .code(200)
                            .message("삭제 성공")
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PatchMapping("/category")
    public ResponseEntity<?> updateCategory(@RequestParam Long categoryId,
                                            @RequestParam String title,
                                            @RequestParam String color){
        try{
            return ResponseEntity.ok(todoService.updateCategory(categoryId, title, color));
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

    @DeleteMapping("/todo")
    public ResponseEntity<?> deleteTodo(@RequestParam Long todoId){
        try{
            todoService.deleteTodo(todoId);
            return ResponseEntity.ok(StatusDTO.builder()
                            .code(200)
                            .message("삭제 성공")
                            .build());
        } catch (Exception e){
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/todo/user")
    public ResponseEntity<?> getTodoList(@RequestParam Long userId){
        try{
            return ResponseEntity.ok(todoService.getTodoList(userId));
        } catch (Exception e){
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/todo/sympathy")
    public ResponseEntity<?> addSympathy(
            @RequestParam Long todoId,
            @RequestParam Long userId,
            @RequestParam String type){
        try{
            return ResponseEntity.ok(todoService.addSympathy(todoId, userId, type));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }
}
