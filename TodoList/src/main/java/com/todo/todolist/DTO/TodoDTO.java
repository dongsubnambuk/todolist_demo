package com.todo.todolist.DTO;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDTO {
    private Long categoryId;
    private String title;
    private String memo;
    private LocalDate date;
    private Boolean completed;
}
