package com.todo.todolist.DAO.Auth;

import com.todo.todolist.DTO.UserDTO;

import java.util.LinkedHashMap;

public interface AuthDAO {
    boolean existEmail(String email);
    boolean existUserId(Long userId);
    UserDTO saveUser(UserDTO user);
    LinkedHashMap<String, Object> signIn(String email, String password);
    void deleteUser(String email, String password);
}
