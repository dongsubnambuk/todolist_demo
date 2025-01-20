package com.todo.todolist.Repository;

import com.todo.todolist.Entity.CategoryEntity;
import com.todo.todolist.Entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> category(CategoryEntity categoryEntity);
}
