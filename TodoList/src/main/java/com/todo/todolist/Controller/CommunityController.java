package com.todo.todolist.Controller;

import com.todo.todolist.DTO.StatusDTO;
import com.todo.todolist.Service.Community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/friend")
    public ResponseEntity<?> addFriend(@RequestParam Long userId, @RequestParam String friendEmail) {
        try {
            communityService.addFriend(userId, friendEmail);
            return ResponseEntity.ok(StatusDTO.builder()
                            .code(200)
                            .message("친구 추가 성공")
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(StatusDTO.builder()
                            .code(404)
                            .message(e.getMessage())
                            .build());
        }
    }
}
