package me.denis.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstCon {

    @GetMapping
    public String helloWorld() {
        return "Hello web!";
    }

}
