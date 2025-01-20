package com.todo.todolist.DTO;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusDTO {
    private Integer code;
    private String message;
}
