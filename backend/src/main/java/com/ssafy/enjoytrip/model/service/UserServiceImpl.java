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
        Optional<User> result = userRepository.findById(user.getId());
        return result.filter(u -> isValidPassword(user, u)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkId(String id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public boolean changePW(User user, String pw) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            String hashedPw = hashUtil.hash(pw, existingUser.getSalt());
            existingUser.setPw(hashedPw);
            userRepository.save(existingUser);
            return true;
        }
        return false;
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

    private boolean isValidPassword(User inputUser, User storedUser) {
        String hashedPw = hashUtil.hash(inputUser.getPw(), storedUser.getSalt());
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
}
