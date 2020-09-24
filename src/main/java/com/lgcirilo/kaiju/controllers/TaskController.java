// TODO - create @PatchMapping
// TODO - create @PutMapping
package com.lgcirilo.kaiju.controllers;

import com.lgcirilo.kaiju.entities.Task;
import com.lgcirilo.kaiju.entities.User;
import com.lgcirilo.kaiju.repositories.TaskRepository;
import com.lgcirilo.kaiju.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/task", produces = "application/json")
@CrossOrigin(origins = "*")
public class TaskController {

    Logger logger = LoggerFactory.getLogger(TaskController.class);

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

    @GetMapping(path = "/all/{userId}")
    public List<Task> tasksByUser(@PathVariable long userId) {
        return taskRepo.findAllByUser_Id(userId);
    }

    @PostMapping(path = "/new/{userId}", consumes = "application/json")
    public ResponseEntity<Task> saveTask(@PathVariable long userId, @RequestBody Task task) {
        logger.warn(task.toString());
        Optional<User> user = userRepo.findById(userId);
        // TODO - change this condition when security is implemented. userId must exist and match the one in security credentials
        if(user.isPresent()) {
            User newUser = user.get();
            Task newTask = new Task(newUser, task.getText());
            taskRepo.save(newTask);
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping(path = "/delete/{taskId}")
    public void deleteTask(@PathVariable long taskId) {
            taskRepo.deleteById(taskId);
    }

    @PutMapping(path = "/update", consumes = "application/json")
    public Task putTask(@RequestBody Task task) {
        return taskRepo.save(task);
    }

    // TODO - check whether the user from actualTask is the same as the user from security credentials
    // TODO - change return type to ReponseEntity<Task>
    // TODO - check for a better status code for when no id or invalid is passed for patchTask (in which case task.isPresent returns false). Might be solve by TODO above
    @PatchMapping(path = "update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task patchTask(@RequestBody Task patchTask) {
        Optional<Task> task = taskRepo.findById(patchTask.getId());
        if (task.isPresent()) {
            // TODO - move whole if (task.isPresent())" body to a separate method to increase readability
            Task actualTask = task.get(); // test with an invalid task id on patchTask
            if (patchTask.getUser() != null) {
                actualTask.setUser(patchTask.getUser());
            }

            if (patchTask.getText() != null) {
                actualTask.setText(patchTask.getText());
            }

            // no case for createdAt since we don't want to modify that field. Are there any implications?
            actualTask.setComplete(patchTask.isComplete());

            return taskRepo.save(actualTask);
        } else {
            return null;
        }
    }

    /* delete later */
    @GetMapping(path = "/wannaupdate/{taskId}")
    public Task queroapagar(@PathVariable long taskId) {
        return taskRepo.findById(taskId).get();
    }
}