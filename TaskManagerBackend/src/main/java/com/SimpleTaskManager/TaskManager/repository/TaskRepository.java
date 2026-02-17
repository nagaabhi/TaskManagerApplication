package com.SimpleTaskManager.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SimpleTaskManager.TaskManager.model.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(String status);
}
