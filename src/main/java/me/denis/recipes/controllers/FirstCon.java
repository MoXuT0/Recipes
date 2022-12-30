package me.denis.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstCon {

    @GetMapping
    public String helloWorld() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info() {
        return "Ученик - Денис. " +
                "Веб-приложение рецептов. " +
                "Дата создания 29.12.2022. " +
                "Просто приложение для сайта рецептов.";
    }

}
