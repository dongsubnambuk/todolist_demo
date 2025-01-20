package com.todo.todolist.Service.Auth;

import com.todo.todolist.DAO.Auth.AuthDAO;
import com.todo.todolist.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthDAO authDAO;

    @Override
    public UserDTO signUp(UserDTO userDTO) {
        try {
            if(!authDAO.existEmail(userDTO.getEmail())) {
                return authDAO.saveUser(userDTO);
            } else throw new IllegalStateException("중복 이메일");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public LinkedHashMap<String, Object> signIn(String email, String password) {
        try{
            if(authDAO.existEmail(email)){
                return  authDAO.signIn(email, password);
            } else throw new IllegalStateException("잘못된 이메일");
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void withdraw(String email, String password) {
        try{
            if(authDAO.existEmail(email)){
                authDAO.deleteUser(email, password);
            } else throw new IllegalStateException("잘못된 이메일");
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
}
