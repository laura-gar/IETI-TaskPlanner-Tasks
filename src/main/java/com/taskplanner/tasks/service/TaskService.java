package com.taskplanner.tasks.service;

import com.taskplanner.tasks.entities.Task;

import java.util.List;

/**
 * @author Laura Garcia
 */
public interface TaskService
{
    Task create( Task task );

    Task findById( String id );

    List<Task> getAll();

    boolean deleteById( String id );

    Task update(Task task, String id );
}
