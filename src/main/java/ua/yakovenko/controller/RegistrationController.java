package ua.yakovenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.yakovenko.domain.Role.*;
import ua.yakovenko.domain.User;
import ua.yakovenko.service.RegistrationService;
import ua.yakovenko.service.UserService;

import java.util.Collections;
import java.util.Optional;

import static ua.yakovenko.domain.Role.USER;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            User user,
            Model model
    ) {
        if (!registrationService.addUser(user)) {
            model.addAttribute("message", "User is exists!");
            
            return "/registration";
        }

        return "redirect:/login";
    }
}
