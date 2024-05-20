package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.User;
import com.ssafy.enjoytrip.model.repository.UserRepository;
import com.ssafy.enjoytrip.utils.HashUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HashUtil hashUtil;

    public UserServiceImpl(UserRepository userRepository, HashUtil hashUtil) {
        this.userRepository = userRepository;
        this.hashUtil = hashUtil;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        String salt = makeRandomSalt();
        String hashedPw = hashUtil.hash(user.getPw(), salt);
        user.setPw(hashedPw);
        user.setSalt(salt);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User selectUserById(User user) {
        return userRepository.findById(user.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkId(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean changePW(User user, String newPassword) {
        String salt = user.getSalt();
        String hashedPw = hashUtil.hash(newPassword, salt);
        user.setPw(hashedPw);

        userRepository.save(user);
        return true;
    }


    @Override
    @Transactional
    public boolean delete(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    @Transactional
    public String generateTemporaryPassword(String userId, String name) {
        Optional<User> optionalUser = userRepository.findByIdAndName(userId, name);
        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();
        String temporaryPassword = makeRandomPassword();
        changePW(user, temporaryPassword);

        return temporaryPassword;
    }

    @Override
    public boolean isValidPassword(User inputUser, User storedUser) {
        // 해시된 비밀번호 생성
        String hashedPw = hashUtil.hash(inputUser.getPw(), storedUser.getSalt());

        // 해시된 비밀번호와 저장된 비밀번호 비교
        return storedUser.getPw().equals(hashedPw);
    }

    private String makeRandomSalt() {
        StringBuilder saltBuilder = new StringBuilder();
        Random random = new Random();
        final int length = 20;
        final int alphabetRange = 26;
        final int asciiLowerA = 97;
        final int asciiUpperA = 65;
        final int numberRange = 10;

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

    private String makeRandomPassword() {
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();
        final int length = 10;
        final int alphabetRange = 26;
        final int asciiLowerA = 97;
        final int asciiUpperA = 65;
        final int numberRange = 10;

        for (int i = 0; i < length; i++) {
            int rIndex = random.nextInt(3);
            switch (rIndex) {
                case 0:
                    passwordBuilder.append((char) (random.nextInt(alphabetRange) + asciiLowerA));
                    break;
                case 1:
                    passwordBuilder.append((char) (random.nextInt(alphabetRange) + asciiUpperA));
                    break;
                case 2:
                    passwordBuilder.append(random.nextInt(numberRange));
                    break;
            }
        }
        return passwordBuilder.toString();
    }

}
