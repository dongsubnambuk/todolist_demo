package com.todo.todolist.Service.Auth;

import com.todo.todolist.DTO.UserDTO;

import java.util.LinkedHashMap;

public interface AuthService {
    UserDTO signUp(UserDTO userDTO);
    LinkedHashMap<String, Object> signIn(String email, String password);
    void withdraw(String email, String password);
}
