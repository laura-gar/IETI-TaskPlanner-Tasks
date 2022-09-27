package com.taskplanner.tasks.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import com.taskplanner.tasks.dto.TaskDto;
import org.springframework.http.HttpStatus;
import com.taskplanner.tasks.entities.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.taskplanner.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Laura Garcia
 */

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private ModelMapper modelMapper = new ModelMapper();

    public TaskController(@Autowired TaskService taskService){this.taskService =taskService; }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll(){
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable String id){
        try{
            Task task = taskService.findById(id);
            TaskDto taskDto = modelMapper.map(task, TaskDto.class);
            return new ResponseEntity<>(taskDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/assignedTo/{userId}")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable String userId){
        return new ResponseEntity<>(taskService.getTasksByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto){
        Task task = modelMapper.map(taskDto, Task.class);
        System.out.println(task.getId());
        taskService.create(task);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@RequestBody TaskDto task, @PathVariable String id){
        try{
            TaskDto taskDto =  modelMapper.map(taskService.update(task, id), TaskDto.class);
            return new ResponseEntity<>(taskDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        return new ResponseEntity<>(taskService.deleteById(id), HttpStatus.OK);
    }

}
