package ua.yakovenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.yakovenko.domain.Role.*;
import ua.yakovenko.domain.User;
import ua.yakovenko.service.UserService;

import java.util.Collections;
import java.util.Optional;

import static ua.yakovenko.domain.Role.USER;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            User user,
            Model model
    ) {
        User userFromDb = userService.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User is exists!");
            return "/registration";
        }

        user.setActive(true);
        user.setAccountMoney(0L);
        user.setRoles(Collections.singleton(USER));

        userService.save(user);

        return "redirect:/login";
    }
}
