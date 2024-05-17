package com.ssafy.enjoytrip.model.dao;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.enjoytrip.model.dto.User;

@Mapper
public interface UserDao {

    int addUser(User user);

    User selectUserById(User user);

    boolean checkId(String id);

    boolean changePW(User user);

    boolean delete(User user);

    void deleteAllUsers();

    // 임시 비밀번호 생성을 위한 메서드 추가
    User findByUserIdAndName(String userId, String name);
}
