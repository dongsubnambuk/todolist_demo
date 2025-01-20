package com.todo.todolist.DAO.Todo;

import com.todo.todolist.DTO.TodoDTO;
import com.todo.todolist.Entity.CategoryEntity;
import com.todo.todolist.Entity.TodoEntity;
import com.todo.todolist.Repository.CategoryRepository;
import com.todo.todolist.Repository.SympathyRepository;
import com.todo.todolist.Repository.TodoRepository;
import com.todo.todolist.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class TodoDAOImpl implements TodoDAO{
    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final SympathyRepository sympathyRepository;

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
        return getStringObjectLinkedHashMap(title, color, categoryEntity);
    }

    // 3중맵으로 해야 할 듯?
    @Override
    public LinkedHashMap<String, Object> getTodoList(Long userId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        List<CategoryEntity> categoryEntities = categoryRepository.user(userRepository.getReferenceById(userId));

        // 전체 정보 담기 프로세스
        for(CategoryEntity categoryEntity : categoryEntities) {
            // 상세 정보 해시맵 선언
            LinkedHashMap<String, Object> details = new LinkedHashMap<>();
            // 카테고리 정보
            details.put("details", CategoryEntity.entityToDTO(categoryEntity));
            // 카테고리에 연결된 투두리스트 리스트에 저장
            LinkedHashMap<Long, TodoDTO> todoDTOList = new LinkedHashMap<>();
            for(TodoEntity todo : todoRepository.category(categoryEntity)){
                todoDTOList.put(todo.getId(), TodoEntity.entityToDTO(todo));
            }
            // 해당 리스트 상세정보 해시맵에 저장
            details.put("todo", todoDTOList);
            //최종 결과 해시맵에 저장
            result.put(categoryEntity.getId().toString(), details);
        }

        return result;

        // 연결 해시 맵을 쓰는 이유
        /*
        * 일단 상당히 편함.
        * 제네릭에 두 가지의 자료형을 설정하는데, 앞에는 키 뒤에는 벨류값이다.
        * 벨류값은 자료형을 딱 정해주면 좋지만, 다양한 종류가 들어갈 때는 object 를 넣어 유동적으로 설정할 수 있다.
        * 사용법은 그냥 맵과 동일하게 put, get, clear, containsValue 등등.. 있다.
        * 얘는 넣는 순서대로 입력 순서가 저장 됨.
        * 대신 메모리를 좀 더 잡아먹음.
        * 그러합니다.
        * 스프링에서는 Map 선언하면 뒤에 구현체로 LinkedHashMap 이 자동으로 따라오지만.. 헷갈리니까 난 첨부터 LinkedHashMap 으로 선언하는편임.
        * */
    }

    @Override
    public void removeTodo(Long todoId) throws IllegalStateException{
        TodoEntity todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalStateException("잘못된 투두 아이디"));
        sympathyRepository.deleteAll(todo.getSympathyList());
        todoRepository.delete(todo);
    }

    @Override
    public void removeCategory(Long categoryId) throws IllegalStateException{
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("잘못된 카테고리 아이디"));
        List<TodoEntity> todoEntities = todoRepository.category(categoryEntity);
        for(TodoEntity todo : todoEntities)
            removeTodo(todo.getId());
        categoryRepository.delete(categoryEntity);
    }

    @Override
    public LinkedHashMap<String, Object> editCategory(Long categoryId, String title, String color) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("잘못된 카테고리 아이디"));
        return getStringObjectLinkedHashMap(title, color, categoryEntity);
    }

    private LinkedHashMap<String, Object> getStringObjectLinkedHashMap(String title, String color, CategoryEntity categoryEntity) {
        categoryEntity.setTitle(title);
        categoryEntity.setColor(color);
        categoryEntity = categoryRepository.save(categoryEntity);

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("categoryId", categoryEntity.getId());
        result.put("details", CategoryEntity.entityToDTO(categoryEntity));
        return result;
    }
}
