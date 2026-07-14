package com.enterprise.todo.modules.task.service;

import com.enterprise.todo.modules.task.entity.Tag;
import com.enterprise.todo.modules.task.entity.Task;
import com.enterprise.todo.modules.task.repository.TagRepository;
import com.enterprise.todo.modules.task.repository.TaskRepository;
import com.enterprise.todo.modules.user.entity.User;
import com.enterprise.todo.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public Task createTask(UUID userId, Task task) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        task.setUser(user);
        task.setTags(resolveTags(task.getTags()));
        return taskRepository.save(task);
    }

    private Set<Tag> resolveTags(Set<Tag> tagRefs) {
        if (tagRefs == null || tagRefs.isEmpty()) {
            return new HashSet<>();
        }

        Set<Tag> resolvedTags = new HashSet<>();
        for (Tag tagRef : tagRefs) {
            if (tagRef.getId() == null) {
                throw new RuntimeException("Tag id is required when assigning tags to a task");
            }
            Tag tag = tagRepository.findById(tagRef.getId())
                    .orElseThrow(() -> new RuntimeException("Tag not found: " + tagRef.getId()));
            resolvedTags.add(tag);
        }
        return resolvedTags;
    }

    public Task getTask(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));
    }

    public List<Task> getTasksByUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found: " + userId);
        }
        return taskRepository.findByUser_Id(userId);
    }

    public Task updateTask(UUID taskId, Task taskDetails) {
        Task task = getTask(taskId);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());

        if (taskDetails.getTags() != null) {
            task.setTags(resolveTags(taskDetails.getTags()));
        }

        return taskRepository.save(task);
    }

    public void deleteTask(UUID taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task not found: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public void deleteTasksByUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found: " + userId);
        }
        taskRepository.deleteByUser_Id(userId);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag getTag(UUID tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found: " + tagId));
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag updateTag(UUID tagId, Tag tagDetails) {
        Tag tag = getTag(tagId);
        tag.setTagName(tagDetails.getTagName());
        return tagRepository.save(tag);
    }

    public void deleteTag(UUID tagId) {
        if (!tagRepository.existsById(tagId)) {
            throw new RuntimeException("Tag not found: " + tagId);
        }
        tagRepository.deleteById(tagId);
    }
}
