package com.SimpleTaskManager.TaskManager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDateTime reminderTime;

    private String phoneNumber;

    private String message;

    private String status = "PENDING";

    public Task() {}

    // Getters & Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getReminderTime() { return reminderTime; }

    public void setReminderTime(LocalDateTime reminderTime) { this.reminderTime = reminderTime; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
