package com.todo.todolist.Entity;

import com.todo.todolist.DTO.TodoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "todo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

    @OneToMany
    private List<SympathyEntity> sympathyList;

    @Column
    private String title;

    @Column
    private String memo;

    @Column
    private LocalDate date;

    @Column
    private Boolean completed;

    public static TodoDTO entityToDTO(TodoEntity todo){
        return TodoDTO.builder()
                .categoryId(todo.getCategory().getId())
                .title(todo.getTitle())
                .memo(todo.getMemo())
                .date(todo.getDate())
                .completed(todo.getCompleted())
                .build();
    }
}
