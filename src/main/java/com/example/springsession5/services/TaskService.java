package com.example.springsession5.services;

import com.example.springsession5.models.Task;
import com.example.springsession5.models.User;
import com.example.springsession5.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
    public Task saveTask(Task newTask) {
        User user = userService.findUserById(newTask.getAssignedTo());

        if (user != null) {
            taskRepository.findAll().add(newTask);
            return newTask;
        }
        return null;
    }
}