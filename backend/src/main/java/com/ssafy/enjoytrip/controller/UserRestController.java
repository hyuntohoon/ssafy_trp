package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoytrip.model.entity.User;
import com.ssafy.enjoytrip.model.service.UserService;

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

    @PostMapping(("/signup"))
    public ResponseEntity<?> signUP(@RequestBody User user) {
        User result = userService.addUser(user);
        System.out.println(user);
        if (result == null) {
            return ResponseEntity.status(401).body("Unauthorized"); // 401 Unauthorized
        }

        return ResponseEntity.ok().build(); // 200 OK
    }

    @PutMapping
    public ResponseEntity<?> changePassword(@RequestBody String password, HttpSession session) {
		User loginUser = getCurrentUser(session);
        if (loginUser == null) {
            return handleUserNotFound();
        }

        if (!isValidPassword(password)) {
            return handlePwNotFound();
        }

        return updatePassword(loginUser, password);
    }
    private ResponseEntity<?> updatePassword(User user, String newPassword) {
        boolean result = userService.changePW(user, newPassword);
        if (result) {
            return ResponseEntity.ok().build(); // 200 OK
        } else {
            return ResponseEntity.internalServerError().body("Password update failed"); // 500 Internal Server Error
        }
    }

	@DeleteMapping
    public ResponseEntity<?> doWithDraw(HttpSession session) {
		User loginUser = getCurrentUser(session);
        if (loginUser == null) {
            return handleUserNotFound();
        }
        return processUserWithdraw(loginUser, session);
    }

    private ResponseEntity<?> processUserWithdraw(User user, HttpSession session) {
        boolean result = userService.delete(user);
        if (result) {
            session.invalidate();  // 사용자 세션 무효화
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    private ResponseEntity<?> handleUserNotFound() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized - No user session found");
    }

    private ResponseEntity<?> handlePwNotFound() {
        return ResponseEntity.status(400).body("Bad Request - No password found");
    }
    private boolean isValidPassword(String pw) {
        return pw != null && !pw.isEmpty();
    }

    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
