package com.example.springsession5.repositories;

import com.example.springsession5.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>(Arrays.asList(
            new Task(1L, "Fix Bug A", "Urgent bug", "high", 1L),
            new Task(2L, "Dev Feature B", "New UI", "medium", 2L),
            new Task(3L, "Testing", "UAT", "low", 3L),
            new Task(4L, "Cleanup", "Refactoring", "low", 1L),
            new Task(5L, "Audit", "Check security", "high", 1L),
            new Task(6L, "Docs", "Update README", "medium", 2L),
            new Task(7L, "Meeting", "Client sync", "medium", 3L),
            new Task(8L, "DB Update", "Postgres", "high", 1L),
            new Task(9L, "Design", "Figma", "low", 2L),
            new Task(10L, "Deploy", "Prod release", "high", 1L)
    ));

    public List<Task> findAll() { return tasks; }
}