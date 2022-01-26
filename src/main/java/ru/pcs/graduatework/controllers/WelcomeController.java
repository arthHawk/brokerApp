package ru.pcs.graduatework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String startPage() {
        return "redirect:/welcome.html";
    }
}
