package com.example.functionalBookstore.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FunctionalBookstoreApplicationController {

    @GetMapping("/functionalBookstore")
    public String showWelcomePage(){
        return "welcomePage";
    }
}
