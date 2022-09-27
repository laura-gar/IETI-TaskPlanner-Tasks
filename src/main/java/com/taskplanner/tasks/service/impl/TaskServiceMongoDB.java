package com.taskplanner.tasks.service.impl;

import com.taskplanner.tasks.dto.TaskDto;
import com.taskplanner.tasks.entities.Task;
import com.taskplanner.tasks.repository.TaskRepository;
import com.taskplanner.tasks.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Laura Garcia
 */
@Service
public class TaskServiceMongoDB implements TaskService {
    private final TaskRepository taskRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public TaskServiceMongoDB(@Autowired TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task findById(String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.get();
    }

    @Override
    public List<TaskDto> getAll() {
        List<Task> tasks;
        tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task ->{
                    return modelMapper.map(task, TaskDto.class);
                        }).collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(String id) {
        if(findById(id) == null){
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public Task update(TaskDto taskDto, String id) {
        Task updateTask = findById(id);
        updateTask.update(taskDto);
        return taskRepository.save(updateTask);
    }

    @Override
    public List<TaskDto> getTasksByUserId(String userId) {
        List<Task> tasks;
        tasks = taskRepository.findByAssignedTo(userId);
        return tasks.stream()
                .map(task ->{
                    return modelMapper.map(task, TaskDto.class);
                }).collect(Collectors.toList());
    }
}
