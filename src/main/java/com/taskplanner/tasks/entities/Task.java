package com.taskplanner.tasks.entities;

import com.taskplanner.tasks.dto.TaskDto;
import com.taskplanner.tasks.utils.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Laura Garcia
 */

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String description;
    private Status status;
    private String assignedTo;
    private String dueDate;
    private String createAt;

    public Task(){
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.createAt = LocalDate.now().toString();
    }

    public Task(String name, String description, Status status, String assignedTo, String dueDate) {
        this();
        this.name = name;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
    }

    public void update(TaskDto taskDto){
        name = taskDto.getName();
        description = taskDto.getDescription();
        status = taskDto.getStatus();
        assignedTo = taskDto.getAssignedTo();
        dueDate = taskDto.getDueDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
