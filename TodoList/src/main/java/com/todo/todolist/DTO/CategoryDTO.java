package com.todo.todolist.DTO;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long userId;
    private String title;
    private String color;
}