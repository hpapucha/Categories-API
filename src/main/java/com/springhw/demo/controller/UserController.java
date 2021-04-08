package com.springhw.demo.controller;


import com.springhw.demo.model.User;
import com.springhw.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    //When the user visits http:/localhost/9092/hello
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    //http://localhost/9092/auth/users
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("calling createUser ==>");
        return userService.createUser(userObject);
    }
}
