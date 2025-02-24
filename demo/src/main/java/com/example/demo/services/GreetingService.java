package com.example.demo.services;

import com.example.demo.model.Greeting;
import com.example.demo.repository.GreetingRespository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRespository greetingRepository;

    public GreetingService(GreetingRespository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public String getGreetingMessage() {
        return "Hello";
    }
    public String getGreetingMessage(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            return "Hello, " + firstName + "!";
        } else if (lastName != null) {
            return "Hello, " + lastName + "!";
        } else {
            return "Hello, World!";
        }
    }

    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    // To search message by id
    public String getGreetingMessageById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        if(greeting.isPresent()) {
            return greeting.get().getMessage();
        }
        else {
            return "There is no such message with entered id";
        }
    }

    // Method to list all the greetings
    public List<Greeting> listAllGreetings() {
        return greetingRepository.findAll();
    }

    // Method to update the message in the repository
    public String updateMessage(Long id, String newMessage) {
        List<Greeting> greetings = greetingRepository.findAll();
        for(Greeting greet:greetings) {
            if(Objects.equals(greet.getId(), id)) {
                greet.setMessage(newMessage);
                return greet.getMessage();
            }
        }
        return "No such id present in repository";
    }

    public String deleteGreetingById(Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return "Greeting with ID " + id + " has been deleted.";
        } else {
            return "No greeting found with ID " + id;
        }
    }

}
