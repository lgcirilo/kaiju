// TODO - create @PatchMapping
package com.lgcirilo.kaiju.controllers;

import com.lgcirilo.kaiju.entities.User;
import com.lgcirilo.kaiju.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user", produces = "application/json")
@CrossOrigin(origins = "*")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(path = "/{userId}")
    public User getUser(@PathVariable long userId) {
        return userRepo.findById(userId).get();
    }

    @GetMapping(path = "/getall")
    public List<User> getUser() {
        return userRepo.findAll();
    }

    @PostMapping(path = "/new", consumes = "application/json")
    public ResponseEntity<User> register(@RequestBody User user) {
        logger.error(user.toString());
        if (user.getName() != null && user.getEmailAddress() != null) {
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/delete/{userId}")
    // changed to use responseentity. not yes tested. orignal code below
//    public void deleteUser(@PathVariable long userId) {
//        userRepo.deleteById(userId);
//    }
    public ResponseEntity<User> deleteUser(@PathVariable long userId) {
        if (userRepo.findById(userId) != null) {
            userRepo.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // TODO - @PatchMapping. Allow only name correction



}
