package com.yuriclaro.todolist.controller;

import com.yuriclaro.todolist.model.Task;
import com.yuriclaro.todolist.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping(value = "/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        log.info("Creating a new Task");
        return service.createTask(task);
    }

    @GetMapping(value = "/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTask() {
        log.info("Geting all tasks");
        return service.listAllTask();
    }

    @GetMapping(value = "/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id) {
        log.info("Getting tasks by id [{}]", id);
        return service.findTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getByTask(@PathVariable (value = "id") Long id, @RequestBody Task task) {
        log.info("Updating task id [{}]", id);
        return service.updateTaskById(task, id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id) {
        log.info("Deleting task id [{}]", id);
        return service.deleteById(id);
    }
}
