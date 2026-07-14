package com.enterprise.todo.modules.user.service;

import com.enterprise.todo.modules.user.entity.User;
import com.enterprise.todo.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(UUID userId, User userDetails) {
        User user = getUser(userId);
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        user.setPasswordHashed(userDetails.getPasswordHashed());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
