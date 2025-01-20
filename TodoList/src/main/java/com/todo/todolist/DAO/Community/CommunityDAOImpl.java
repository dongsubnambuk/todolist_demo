package com.todo.todolist.DAO.Community;

import com.todo.todolist.Repository.FriendRepository;
import com.todo.todolist.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityDAOImpl implements CommunityDAO {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
}
