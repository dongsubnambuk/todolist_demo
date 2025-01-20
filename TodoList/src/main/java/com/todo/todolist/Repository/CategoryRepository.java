package com.todo.todolist.Repository;

import com.todo.todolist.Entity.CategoryEntity;
import com.todo.todolist.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> user(UserEntity user);
}
