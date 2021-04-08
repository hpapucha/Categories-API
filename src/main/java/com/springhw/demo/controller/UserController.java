package com.springhw.demo.controller;


import com.springhw.demo.model.Request.LoginRequest;
import com.springhw.demo.model.User;
import com.springhw.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    //When the user visits http:/localhost/9092/hello. Testing Get with HelloWorld
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


    //ResponseEntity with custom message for creating new user
    @PostMapping("/register")
    public ResponseEntity<HashMap> createUser(@RequestBody User userObject){
        System.out.println("calling createUser ==>");
        userService.createUser(userObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "user with username: " + userObject.getUsername() + " was successfully created");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Login method for the user which will authenticate
    //http://localhost/9092/auth/users/login
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("calling loginUser ==>");
        return userService.loginUser(loginRequest);
    }
}
