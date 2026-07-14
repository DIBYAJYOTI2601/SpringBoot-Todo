package com.enterprise.todo.modules.user.controller;

import com.enterprise.todo.modules.user.entity.User;
import com.enterprise.todo.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/usercreate")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/getuser/{userid}")
    public ResponseEntity<User> getUser(@PathVariable("userid") UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/userupdate/{userid}")
    public ResponseEntity<User> updateUser(
            @PathVariable("userid") UUID userId,
            @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    @DeleteMapping("/userdelete/{userid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userid") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
