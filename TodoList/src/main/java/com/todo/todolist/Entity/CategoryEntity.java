package com.todo.todolist.Entity;

import com.todo.todolist.DTO.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserEntity user;

    @Column
    private String title;

    @Column
    private String color;

    public static CategoryDTO entityToDTO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .userId(categoryEntity.getUser().getUserId())
                .title(categoryEntity.getTitle())
                .color(categoryEntity.getColor())
                .build();
    }
}
