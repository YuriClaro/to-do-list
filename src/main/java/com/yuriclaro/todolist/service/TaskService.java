package com.yuriclaro.todolist.service;

import com.yuriclaro.todolist.model.Task;
import com.yuriclaro.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task createTask (Task task) {
        return repository.save(task);
    }

    public List<Task> listAllTask() {
        return repository.findAll();
    }

    public ResponseEntity<Task> findTaskById(Long id) {
        return repository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> updateTaskById(Task task, Long id) {
        return repository.findById(id)
                .map(taskToUpdate -> {
                    taskToUpdate.setTitle(task.getTitle());
                    taskToUpdate.setDescription(task.getDescription());
                    taskToUpdate.setDeadLine(task.getDeadLine());
                    Task update = repository.save(taskToUpdate);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById(Long id) {
        return repository.findById(id)
                .map(taskToDelete -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
