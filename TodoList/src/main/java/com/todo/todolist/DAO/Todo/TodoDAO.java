package com.todo.todolist.DAO.Todo;

import com.todo.todolist.DTO.TodoDTO;

import java.util.LinkedHashMap;

public interface TodoDAO {
    LinkedHashMap<String, Object> newTodo(TodoDTO todoDTO);
    LinkedHashMap<String, Object> newCategory(Long userId, String category, String color);
}
