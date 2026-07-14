package com.enterprise.todo.modules.task.repository;

import com.enterprise.todo.modules.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByUser_Id(UUID userId);

    void deleteByUser_Id(UUID userId);

    boolean existsByUser_Id(UUID userId);
}
