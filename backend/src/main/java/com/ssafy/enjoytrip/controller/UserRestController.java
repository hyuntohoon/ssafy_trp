package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;
import com.ssafy.enjoytrip.model.service.TripPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.AuthService;
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
    public ResponseEntity<?> signOut(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        User result = userService.addUser(user);
        if (result == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/changepassword")
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

    @DeleteMapping
    public ResponseEntity<?> withdraw(HttpSession session) {
        User loginUser = getCurrentUser(session);
        if (loginUser == null) {
            return handleUserNotFound();
        }
        return processUserWithdraw(loginUser, session);
    }

    @PostMapping("/temporarypassword")
    public ResponseEntity<?> generateTemporaryPassword(@RequestParam String userId, @RequestParam String name) {
        String temporaryPassword = userService.generateTemporaryPassword(userId, name);
        if (temporaryPassword == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or mismatch");
        }
        return ResponseEntity.ok(temporaryPassword);
    }

    private ResponseEntity<?> updatePassword(User user, String newPassword) {
        boolean result = userService.changePW(user, newPassword);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().body("Password update failed");
        }
    }

    private ResponseEntity<?> processUserWithdraw(User user, HttpSession session) {
        boolean result = userService.delete(user);
        if (result) {
            session.invalidate();
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
