package com.taskplanner.tasks.service;

import com.taskplanner.tasks.dto.TaskDto;
import com.taskplanner.tasks.entities.Task;

import java.util.List;

/**
 * @author Laura Garcia
 */
public interface TaskService
{
    Task create( Task task );

    Task findById( String id );

    List<TaskDto> getAll();

    boolean deleteById( String id );

    Task update(TaskDto taskDto, String id );

    List<TaskDto> getTasksByUserId(String userId);
}
