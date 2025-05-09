package com.example.demo.controller;

import com.example.demo.model.Greeting;
import com.example.demo.services.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

        String message;
        if (firstName != null && lastName != null) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello, World!";
        }

        // Save the greeting message in the database
        greetingService.saveGreeting(message);

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }


    @GetMapping("/{id}")
    public String getGreetingMessageById(@PathVariable Long id) {
        return greetingService.getGreetingMessageById(id);
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



    // Get the list of all greetings
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.listAllGreetings();
    }

    // Update message from the repository
    @GetMapping("/update/{id}/{message}")
    public String getUpdateMessage(@PathVariable Long id, @PathVariable String message) {
        return greetingService.updateMessage(id,message);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteGreeting(@PathVariable Long id) {
        String message = greetingService.deleteGreetingById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

}
