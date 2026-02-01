package com.example.springsession5.controllers;

import com.example.springsession5.models.Task;
import com.example.springsession5.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) String search) {
        List<Task> allTasks = taskService.findAllTasks();

        if (search != null && !search.isEmpty()) {
            List<Task> filteredTasks = allTasks.stream()
                    .filter(task -> task.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .toList();
            return ResponseEntity.ok(filteredTasks);
        }

        return ResponseEntity.ok(allTasks); // Cách viết ngắn gọn của status 200
    }
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task newTask) {
        if (newTask.getTitle() == null || newTask.getTitle().isEmpty()) {
            return ResponseEntity.status(400).body("Tiêu đề công việc không được để trống");
        }
        Task savedTask = taskService.saveTask(newTask);
        if (savedTask != null) {
            return ResponseEntity.status(201).body(savedTask);
        } else {
            return ResponseEntity.status(400).body("Lỗi: Người dùng (assignedTo) không tồn tại trên hệ thống!");
        }
    }

}