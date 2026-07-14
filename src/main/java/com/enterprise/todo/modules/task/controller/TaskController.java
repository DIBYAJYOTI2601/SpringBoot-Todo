package com.enterprise.todo.modules.task.controller;

import com.enterprise.todo.modules.task.entity.Tag;
import com.enterprise.todo.modules.task.entity.Task;
import com.enterprise.todo.modules.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/createtask/{userid}")
    public ResponseEntity<Task> createTask(
            @PathVariable("userid") UUID userId,
            @RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(userId, task));
    }

    @GetMapping("/gettask/{taskid}")
    public ResponseEntity<Task> getTask(@PathVariable("taskid") UUID taskId) {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @GetMapping("/gettasks/{userid}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable("userid") UUID userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @PutMapping("/updatetask/{taskid}")
    public ResponseEntity<Task> updateTask(
            @PathVariable("taskid") UUID taskId,
            @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(taskId, task));
    }

    @DeleteMapping("/deletetask/{taskid}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskid") UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletetasks/{userid}")
    public ResponseEntity<Void> deleteTasksByUser(@PathVariable("userid") UUID userId) {
        taskService.deleteTasksByUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/createtag")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTag(tag));
    }

    @GetMapping("/gettag/{tagid}")
    public ResponseEntity<Tag> getTag(@PathVariable("tagid") UUID tagId) {
        return ResponseEntity.ok(taskService.getTag(tagId));
    }

    @GetMapping("/gettags")
    public ResponseEntity<List<Tag>> getAllTags() {
        return ResponseEntity.ok(taskService.getAllTags());
    }

    @PutMapping("/updatetag/{tagid}")
    public ResponseEntity<Tag> updateTag(
            @PathVariable("tagid") UUID tagId,
            @RequestBody Tag tag) {
        return ResponseEntity.ok(taskService.updateTag(tagId, tag));
    }

    @DeleteMapping("/deletetag/{tagid}")
    public ResponseEntity<Void> deleteTag(@PathVariable("tagid") UUID tagId) {
        taskService.deleteTag(tagId);
        return ResponseEntity.noContent().build();
    }
}
