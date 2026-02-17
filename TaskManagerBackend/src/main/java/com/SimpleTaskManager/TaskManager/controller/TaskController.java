package com.SimpleTaskManager.TaskManager.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.SimpleTaskManager.TaskManager.model.Task;
import com.SimpleTaskManager.TaskManager.repository.TaskRepository;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Task> getTasks() {
        return repo.findAll();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return repo.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
