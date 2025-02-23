package com.example.demo.controller;

import com.example.demo.services.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private GreetingService greetingService;

    // injection of GreetingService
    GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Map<String, String> getGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        String message = greetingService.getGreetingMessage(firstName, lastName);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    @PostMapping
    public Map<String, String> postGreeting(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", greetingService.getGreetingMessage());
        response.put("received", request.getOrDefault("name", "No Name Provided"));
        return response;
    }

    @PutMapping
    public Map<String, String> putGreeting(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", greetingService.getGreetingMessage());
        response.put("updated", request.getOrDefault("name", "No Name Provided"));
        return response;
    }

    @DeleteMapping
    public Map<String, String> deleteGreeting() {
        Map<String, String> response = new HashMap<>();
        response.put("message", greetingService.getGreetingMessage());
        return response;
    }
}
