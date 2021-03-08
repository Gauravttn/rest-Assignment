package com.bootcamp.demo.welcome;

import org.springframework.web.bind.annotation.*;

@RestController
public class Welcome {

    @GetMapping(path = "/welcome")
    public String welcome1(){
        return "Welcome to spring boot";
    }

    @GetMapping(path = "/welcome-bean")
    public WelcomeBean welcomeBean(){
        return new WelcomeBean("Welcome to spring boot");
    }

    @GetMapping(path = "/welcome-bean/{name}")
    public WelcomeBean welcomeBeanpath(@PathVariable  String name){
        return new WelcomeBean("Welcome to spring boot"+name);
    }





}
