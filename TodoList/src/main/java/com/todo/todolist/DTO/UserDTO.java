package com.todo.todolist.DTO;

import com.todo.todolist.Entity.UserEntity;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private String password;

    public static UserDTO entityToDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
