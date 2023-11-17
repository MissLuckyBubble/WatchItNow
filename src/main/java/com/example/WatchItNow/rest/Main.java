package com.example.WatchItNow.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    @GetMapping("/")
    public String getMain() {return "Hello world";}
}
