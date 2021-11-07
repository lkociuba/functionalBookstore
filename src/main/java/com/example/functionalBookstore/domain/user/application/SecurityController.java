package com.example.functionalBookstore.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
