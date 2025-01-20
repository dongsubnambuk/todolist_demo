package com.todo.todolist.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sympathy")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SympathyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserEntity user;

    @Column
    private String type;

    @Column
    private LocalDateTime createdAt;
}