package com.kodilla.task.security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TaskController {

    @GetMapping("/m1")
    public String m1(@AuthenticationPrincipal UserDetails user) {
        return String.format("M1 endpoint, user: %s", user.getUsername());
    }

    @GetMapping("/m2")
    public String m2(@AuthenticationPrincipal UserDetails user) {
        return String.format("M2 endpoint, user: %s", user.getUsername());
    }

    @GetMapping("/m3")
    public String m3(@AuthenticationPrincipal UserDetails user) {
        return String.format("M3 endpoint, user: %s", user.getUsername());
    }
}
