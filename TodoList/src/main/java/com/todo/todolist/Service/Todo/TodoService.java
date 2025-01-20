package com.todo.todolist.Service.Todo;

import com.todo.todolist.DTO.TodoDTO;

import java.util.LinkedHashMap;

public interface TodoService {
    LinkedHashMap<String, Object> createTodo(TodoDTO todoDTO);
    LinkedHashMap<String, Object> createCategory(Long userId, String title, String color);
    LinkedHashMap<String, Object> getTodoList(Long userId);
    void deleteTodo(Long todoId);
    void deleteCategory(Long userId, Long CategoryId);
}
