package com.todo.todolist.Entity;

import com.todo.todolist.DTO.TodoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    private Map<Long, SympathyEntity> sympathyMap = new HashMap<>();

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
