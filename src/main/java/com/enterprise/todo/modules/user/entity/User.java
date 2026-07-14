package com.enterprise.todo.modules.user.entity;

import com.enterprise.todo.modules.common.entity.BaseAuditEntity;
import com.enterprise.todo.modules.task.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hashed", nullable = false)
    private String passwordHashed;

    // CascadeType.ALL and orphanRemoval = true handles the "delete cascade" requirement.
    // If a user is deleted, all their tasks are deleted.
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
}