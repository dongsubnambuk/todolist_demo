package com.todo.todolist.DAO.Todo;

import com.todo.todolist.DTO.TodoDTO;
import com.todo.todolist.Entity.CategoryEntity;
import com.todo.todolist.Entity.TodoEntity;
import com.todo.todolist.Repository.CategoryRepository;
import com.todo.todolist.Repository.TodoRepository;
import com.todo.todolist.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
@Transactional
public class TodoDAOImpl implements TodoDAO{
    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public LinkedHashMap<String, Object> newTodo(TodoDTO todoDTO) {
        TodoEntity todo = new TodoEntity();
        try {
            todo.setCategory(categoryRepository.findById(todoDTO.getCategoryId())
                    .orElseThrow(() -> new IllegalStateException("잘못된 카테고리 아이디")));
            todo.setTitle(todoDTO.getTitle());
            todo.setMemo(todoDTO.getMemo());
            todo.setDate(todoDTO.getDate());
            todo.setCompleted(Boolean.FALSE);
            todo = todoRepository.save(todo);
        } catch (Exception e) {
            throw new IllegalStateException("저장 실패");
        }

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("id", todo.getId());
        result.put("category", CategoryEntity.entityToDTO(todo.getCategory()));
        result.put("memo", todo.getMemo());
        result.put("date", todo.getDate());
        result.put("completed", todo.getCompleted());
        return result;
    }

    @Override
    public LinkedHashMap<String, Object> newCategory(Long userId, String title, String color) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setUser(userRepository.getReferenceById(userId));
        categoryEntity.setTitle(title);
        categoryEntity.setColor(color);
        categoryEntity = categoryRepository.save(categoryEntity);

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("categoryId", categoryEntity.getId());
        result.put("details", CategoryEntity.entityToDTO(categoryEntity));
        return result;
    }
}
