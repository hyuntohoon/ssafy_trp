package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.UpdatePasswordRequest;
import com.ssafy.enjoytrip.model.dto.UserCreateDTO;
import com.ssafy.enjoytrip.model.entity.User;
import com.ssafy.enjoytrip.model.service.AuthService;
import com.ssafy.enjoytrip.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final AuthService authService;
    private final UserService userService;

    public UserRestController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        String token = authService.signIn(user);
        if (token == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.ok(token);
    }

    @GetMapping("/signout")
    public ResponseEntity<?> doSignOut(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build(); // 200 OK
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserCreateDTO userCreate) {
        User user = new User();
        user.setId(userCreate.getId());
        user.setPw(userCreate.getPw());
        user.setName(userCreate.getName());
        User result = userService.addUser(user);
        if (result == null) {
            return ResponseEntity.status(401).body("Unauthorized"); // 401 Unauthorized
        }
        return ResponseEntity.ok().build(); // 200 OK
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request) {
        User user = new User();
        user.setId(request.getUserId());
        System.out.println(user);
        user = userService.selectUserById(user);
        System.out.println(user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        boolean result = userService.changePW(user, request.getNewPassword());
        if (result) {
            return ResponseEntity.ok().build(); // 200 OK
        } else {
            return ResponseEntity.internalServerError().body("Password update failed"); // 500 Internal Server Error
        }
    }

    @PostMapping("/temporarypassword")
    public ResponseEntity<?> generateTemporaryPassword(@RequestParam String userId, @RequestParam String name) {
        String temporaryPassword = userService.generateTemporaryPassword(userId, name);
        if (temporaryPassword == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or mismatch");
        }
        return ResponseEntity.ok(temporaryPassword);
    }

    private ResponseEntity<?> handleUserNotFound() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized - No user session found");
    }

    private ResponseEntity<?> handlePwNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request - No password found");
    }

    private boolean isValidPassword(String pw) {
        return pw != null && !pw.isEmpty();
    }

    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
