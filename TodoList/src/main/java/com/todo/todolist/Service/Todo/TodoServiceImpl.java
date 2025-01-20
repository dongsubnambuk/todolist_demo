package com.todo.todolist.Service.Todo;

import com.todo.todolist.DAO.Auth.AuthDAO;
import com.todo.todolist.DAO.Todo.TodoDAO;
import com.todo.todolist.DTO.TodoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoDAO todoDAO;
    private final AuthDAO authDAO;

    @Override
    public LinkedHashMap<String, Object> createTodo(TodoDTO todoDTO) {
        try {
            return todoDAO.newTodo(todoDTO);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public LinkedHashMap<String, Object> createCategory(Long userId, String title, String color) {
        try {
            if (authDAO.existUserId(userId)) {
                return todoDAO.newCategory(userId, title, color);
            } else throw new IllegalStateException("잘못된 정보");
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public LinkedHashMap<String, Object> getTodoList(Long userId){
        if(authDAO.existUserId(userId)){
            try{
                return todoDAO.getTodoList(userId);
            } catch (Exception e){
                throw new IllegalStateException(e.getMessage());
            }
        } else throw new IllegalStateException("잘못된 유저 아이디");
    }

    @Override
    public void deleteTodo(Long todoId){
        try{
            todoDAO.removeTodo(todoId);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void deleteCategory(Long userId, Long categoryId){
        if(authDAO.existUserId(userId)) {
            try {
                todoDAO.removeCategory(categoryId);
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage());
            }
        } else throw new IllegalStateException("잘못된 아이디");
    }
}
