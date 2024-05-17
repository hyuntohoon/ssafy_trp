package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.User;

public interface UserService {
    User addUser(User user);

    User selectUserById(User user);

    boolean checkId(String id);

    boolean changePW(User user, String pw);

    boolean delete(User user);

    void deleteAllUsers();  // 테스트를 위한 메서드

    // 임시 비밀번호 생성을 위한 메서드 추가
    String generateTemporaryPassword(String userId, String name);
}
