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

    public String signIn(User user) {
        User existingUser = userService.selectUserById(user);
        if (existingUser != null) {
            return jwtTokenUtil.generateToken(existingUser.getName());
        }
        return null;
    }
}
