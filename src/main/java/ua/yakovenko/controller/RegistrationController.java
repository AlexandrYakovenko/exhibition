package ua.yakovenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yakovenko.domain.Role.*;
import ua.yakovenko.domain.User;
import ua.yakovenko.service.RegistrationService;
import ua.yakovenko.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static ua.yakovenko.domain.Role.USER;

@Controller("/")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("registration")
    public String addUser(
            @RequestParam String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean isConfirmInvalid = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        if (isConfirmEmpty) {
            model.addAttribute("passwordConfirmError", "Password confirmation cannot be empty");
        }

        if (isConfirmInvalid) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (isConfirmEmpty || isConfirmInvalid || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!registrationService.addUser(user)) {
            model.addAttribute("usernameError", "User is exists!");

            return "registration";
        }

        return "redirect:/login";
    }
}
