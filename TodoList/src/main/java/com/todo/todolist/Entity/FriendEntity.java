package com.todo.todolist.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "friend")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserEntity user;

    @OneToMany
    @MapKey(name = "userId")
    private Map<Long, UserEntity> friends = new HashMap<>();
}
