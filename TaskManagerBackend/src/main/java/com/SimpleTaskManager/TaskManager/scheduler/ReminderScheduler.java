package com.SimpleTaskManager.TaskManager.scheduler;

import com.SimpleTaskManager.TaskManager.model.Task;
import com.SimpleTaskManager.TaskManager.repository.TaskRepository;
import com.SimpleTaskManager.TaskManager.service.SmsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    private final TaskRepository repo;
    private final SmsService smsService;

    public ReminderScheduler(TaskRepository repo, SmsService smsService) {
        this.repo = repo;
        this.smsService = smsService;
    }

    @Scheduled(fixedRate = 60000)
    public void checkReminders() {

        System.out.println("Checking reminders at: " + LocalDateTime.now());

        List<Task> tasks = repo.findByStatus("PENDING");

        for (Task task : tasks) {

            System.out.println("Task time: " + task.getReminderTime());

            if (!task.getReminderTime().isAfter(LocalDateTime.now())) {

                try {
                    smsService.sendSms(task.getPhoneNumber(), task.getMessage());

                    task.setStatus("SENT");
                    repo.save(task);

                    System.out.println("SMS Sent for task: " + task.getTitle());

                } catch (Exception e) {
                    System.out.println("SMS Error: " + e.getMessage());
                }
            }
        }
    }

}
