package com.springhw.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    //When the user visits http:/localhost/9092/hello
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello world from homework commit 1";
    }
}
