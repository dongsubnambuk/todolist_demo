package com.todo.todolist.Repository;

import com.todo.todolist.Entity.SympathyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface SympathyRepository extends JpaRepository<SympathyEntity, Long> {
}
