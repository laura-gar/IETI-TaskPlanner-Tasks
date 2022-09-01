package com.taskplanner.tasks.controller;

import com.taskplanner.tasks.dto.TaskDto;
import com.taskplanner.tasks.entities.Task;
import com.taskplanner.tasks.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;

import java.util.ArrayList;
import java.util.List;

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
        List<Task> tasks = taskService.getAll();
        List<TaskDto> tasksDto = new ArrayList<>();
        for(Task task : tasks){
            TaskDto taskDto = modelMapper.map(task, TaskDto.class);
            tasksDto.add(taskDto);
        }
        return new ResponseEntity<>(tasksDto, HttpStatus.OK);
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
            Task taskMp = modelMapper.map(task, Task.class);
            TaskDto taskDto =  modelMapper.map(taskService.update(taskMp, id), TaskDto.class);
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
