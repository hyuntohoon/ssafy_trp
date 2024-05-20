package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.User;
import com.ssafy.enjoytrip.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String signIn(User inputUser) {
        User existingUser = userService.selectUserById(inputUser);

        // 유저가 존재하고 비밀번호가 유효한지 확인
        if (existingUser != null && userService.isValidPassword(inputUser, existingUser)) {
            return jwtTokenUtil.generateToken(existingUser.getName());
        }
        return null;
    }
}
