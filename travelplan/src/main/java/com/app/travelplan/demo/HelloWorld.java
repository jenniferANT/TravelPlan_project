package com.app.travelplan.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HelloWorld {

    @GetMapping("/demo")
    public String userSayHello() {
        return "User Hello World";
    }

    @GetMapping("/admin/demo")
    public String adminSayHello() {
        return "Admin Hello World";
    }
}
