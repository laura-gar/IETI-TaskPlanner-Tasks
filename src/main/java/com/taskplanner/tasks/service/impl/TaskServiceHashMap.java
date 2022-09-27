package com.taskplanner.tasks.service.impl;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import com.taskplanner.tasks.dto.TaskDto;
import com.taskplanner.tasks.entities.Task;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.taskplanner.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 * @author Laura Garcia
 */

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
    public List<TaskDto> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Task> allTasks = new ArrayList<>();
        for(String key : tasks.keySet()){
            allTasks.add(tasks.get(key));
        }
        return allTasks.stream()
                .map(task ->{
                    return modelMapper.map(task, TaskDto.class);
                }).collect(Collectors.toList());
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
    public Task update(TaskDto task, String id) {
        Task task1 = findById(id);
        task1.update(task);
        tasks.replace(id, task1);
        return tasks.get(id);
    }

    @Override
    public List<TaskDto> getTasksByUserId(String userId) {
        return null;
    }
}
