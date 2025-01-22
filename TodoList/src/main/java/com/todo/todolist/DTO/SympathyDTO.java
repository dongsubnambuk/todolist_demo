package com.todo.todolist.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SympathyDTO {
    private Long id;
    private Long userId;
    private String type;
    private LocalDateTime createAt;
}
