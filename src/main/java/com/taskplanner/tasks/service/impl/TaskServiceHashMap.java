package com.taskplanner.tasks.service.impl;

import com.taskplanner.tasks.entities.Task;
import com.taskplanner.tasks.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Laura Garcia
 */
@Service
public class TaskServiceHashMap implements TaskService {
    private final ConcurrentHashMap<String, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public Task create(Task task) {
        tasks.putIfAbsent(task.getId(), task);
        return task;
    }

    @Override
    public Task findById(String id) { return tasks.get(id); }

    @Override
    public List<Task> getAll() {
        List<Task> allTasks = new ArrayList<>();
        for(String key : tasks.keySet()){
            allTasks.add(tasks.get(key));
        }
        return allTasks;
    }

    @Override
    public boolean deleteById(String id) {
        try{
            tasks.remove(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Task update(Task task, String id) {
        tasks.replace(id, task);
        return tasks.get(id);
    }
}
