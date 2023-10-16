package com.app.travelplan.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class HelloWorld {

    @GetMapping("/user")
    public String userSayHello() {
        return "User Hello World";
    }

    @GetMapping("/admin")
    public String adminSayHello() {
        return "Admin Hello World";
    }
}
