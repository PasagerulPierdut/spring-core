package com.accenture.springcore.controller;

import com.accenture.springcore.model.User;
import com.accenture.springcore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    private final JmsTemplate jmsTemplate;

    public UserController(UserService userService, JmsTemplate jmsTemplate) {
        this.userService = userService;
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }
}
