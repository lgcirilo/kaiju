// TODO - create @PatchMapping
// TODO - create @PutMapping
package com.lgcirilo.kaiju.controllers;

import com.lgcirilo.kaiju.entities.Task;
import com.lgcirilo.kaiju.entities.User;
import com.lgcirilo.kaiju.repositories.TaskRepository;
import com.lgcirilo.kaiju.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/task", produces = "application/json")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskRepository taskRepo;

    private final UserRepository userRepo;

    public TaskController(TaskRepository taskRepo, UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/last/{userId}")
    public List<Task> getToptasksByUser(@PathVariable long userId) {
        return taskRepo.findTop10ByUser_Id(userId);
    }

    // TODO - create a version of this method that uses a request param instead of a path variable
    // TODO - create a version of this method that uses the user in the request body instead of a path variable
    @GetMapping(path = "/all/{userId}")
    public List<Task> tasksByUser(@PathVariable long userId) {
        return taskRepo.findAllByUser_Id(userId);
    }

    // TODO - replace userId request parameter by a User in the request Body
    @PostMapping(path = "/newtask", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTask(@RequestParam long userId, @RequestParam String text) {
        Optional<User> user = userRepo.findById(userId);
        taskRepo.save(new Task(user.get(), text));
    }

    @DeleteMapping(path = "/delete/{taskId}")
    public void deleteTask(@PathVariable long taskId) {
        Optional<Task> task = taskRepo.findById(taskId);
        taskRepo.delete(task.get());
    }
}