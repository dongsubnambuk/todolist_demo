package com.todo.todolist.Controller;

import com.todo.todolist.DTO.StatusDTO;
import com.todo.todolist.DTO.UserDTO;
import com.todo.todolist.Service.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserDTO user) {
        try{
            return ResponseEntity.ok(authService.signUp(user));
        } catch (Exception e){
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password) {
        try{
            return ResponseEntity.ok(authService.signIn(email, password));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam String email, @RequestParam String password) {
        try{
            authService.withdraw(email, password);
            return ResponseEntity.ok(StatusDTO.builder()
                            .code(200)
                            .message("탈퇴 성공")
                            .build());
        } catch (Exception e){
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }
}
