package ua.yakovenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.yakovenko.domain.User;
import ua.yakovenko.service.RegistrationService;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (!registrationService.addUser(user)) {
            model.addAttribute("message", "This user already exists!");
            return "/registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(
            @PathVariable String code,
            Model model
    ) {
        boolean isActivated = registrationService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found");
        }

        return "login";
    }
}
