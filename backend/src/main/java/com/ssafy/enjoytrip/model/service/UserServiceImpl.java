package com.ssafy.enjoytrip.model.service;

import java.util.Random;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.UserDao;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.utils.HashUtil;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final HashUtil hashUtil;

    public UserServiceImpl(UserDao userDao, HashUtil hashUtil) {
        this.userDao = userDao;
        this.hashUtil = hashUtil;
    }

    @Override
    public User addUser(User user) {
        String salt = makeRandomSalt();

        String hashedPw = hashUtil.hash(user.getPw(), salt);

        user.setPw(hashedPw);
        user.setSalt(salt);
        return userDao.addUser(user);
    }



    @Override
    public User selectUserById(User user) {
        User result = userDao.selectUserById(user);
        if (result == null || !isValidPassword(user, result)) {
            return null;
        }

        clearSensitiveData(result);
        // 인증이 성공적이면 사용자 객체 반환
        return result;
    }



    @Override
    public boolean checkId(String id) {
        return userDao.checkId(id);
    }

    @Override
    public boolean changePW(User user, String pw) {
        user.setPw(pw);
		return userDao.changePW(user);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Profile("test")
    @Override
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    private boolean isValidPassword(User inputUser, User storedUser) {
        String hashedPw = hashUtil.hash(inputUser.getPw(), storedUser.getSalt());
        return storedUser.getPw().equals(hashedPw);
    }

    private void clearSensitiveData(User user) {
        user.setPw(null);
        user.setSalt(null);
    }

    private String makeRandomSalt() {
        StringBuilder saltBuilder = new StringBuilder();
        Random random = new Random();
        final int length = 20; // 솔트의 길이
        final int alphabetRange = 26; // 알파벳 문자 범위 (A-Z, a-z)
        final int asciiLowerA = 97; // 'a'의 ASCII 값
        final int asciiUpperA = 65; // 'A'의 ASCII 값
        final int numberRange = 10; // 숫자 범위 (0-9)

        for (int i = 0; i < length; i++) {
            int rIndex = random.nextInt(3);
            switch (rIndex) {
                case 0:
                    saltBuilder.append((char) (random.nextInt(alphabetRange) + asciiLowerA));
                    break;
                case 1:
                    saltBuilder.append((char) (random.nextInt(alphabetRange) + asciiUpperA));
                    break;
                case 2:
                    saltBuilder.append(random.nextInt(numberRange));
                    break;
            }
        }
        return saltBuilder.toString();
    }
}
