package com.todo.todolist.DAO.Auth;

import com.todo.todolist.DTO.UserDTO;
import com.todo.todolist.Entity.UserEntity;
import com.todo.todolist.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@AllArgsConstructor
@Transactional
public class AuthDAOImpl implements AuthDAO {
    private final UserRepository userRepository;

    @Override
    public boolean existEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existUserId(Long userId){
        return userRepository.existsById(userId);
    }

    @Override
    public UserDTO saveUser(UserDTO user) throws RuntimeException {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        return UserDTO.entityToDTO(userRepository.save(userEntity));
    }

    @Override
    public LinkedHashMap<String, Object> signIn(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);
        if (user.getPassword().equals(password)) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("userId", user.getUserId());
            map.put("name", user.getName());
            map.put("email", user.getEmail());
            return map;
        } else throw new RuntimeException("패스워드 애러");
    }

    @Override
    public void deleteUser(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity.getPassword().equals(password)){
            userRepository.delete(userEntity);
        } else throw new RuntimeException("패스워드 애러");
    }
}
