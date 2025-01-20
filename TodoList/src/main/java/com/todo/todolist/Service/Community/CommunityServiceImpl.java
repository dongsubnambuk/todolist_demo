package com.todo.todolist.Service.Community;

import com.todo.todolist.DAO.Auth.AuthDAO;
import com.todo.todolist.DAO.Community.CommunityDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityDAO communityDAO;
    private final AuthDAO authDAO;

    /*
        친구 신청을 넣고 받아주는거로 해야하나? 아니면 걍 이메일 딸깍 하면 추가 되는거로 해야하나?
        만들다보니까 갑자기 생각남.
        일단 보류
    */
    @Override
    public void addFriend(Long userId, String friendEmail) {
        try{
            if(authDAO.existUserId(userId) && authDAO.existEmail(friendEmail)){
//                communityDAO.sendFriend
            } else throw new Exception("잘못된 정보");
        } catch (Exception e){
            throw new IllegalStateException("뭔가 잘못됨");
        }
    }
}