// TODO - create @PostMapping
// TODO - create @PatchMapping
// TODO - create @PutMapping
// TODO - create @DeleteMapping
package com.lgcirilo.kaiju.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user", produces = "application/json")
@CrossOrigin(origins = "*")
public class UserController {
}
