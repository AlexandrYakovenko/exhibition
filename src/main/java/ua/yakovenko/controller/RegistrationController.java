package ua.yakovenko.controller;

import static ua.yakovenko.controller.Constants.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ua.yakovenko.domain.entity.User;
import ua.yakovenko.domain.dto.CaptchaResponseDto;
import ua.yakovenko.service.RegistrationService;
import ua.yakovenko.util.ControllerUtils;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller("/")
public class RegistrationController {

    private final RegistrationService registrationService;

    private final RestTemplate restTemplate;

    public RegistrationController(RegistrationService registrationService, RestTemplate restTemplate) {
        this.registrationService = registrationService;
        this.restTemplate = restTemplate;
    }

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("registration")
    public String registration() {
        return PAGE_REGISTRATION;
    }

    @PostMapping("registration")
    public String addUser(
            @RequestParam String passwordConfirm,
            @RequestParam(G_RECAPTCHA_RESPONSE) String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute(PASSWORD_CONFIRM_ERROR, PASSWORD_CONFIRM_ERROR_MESSAGE);
        }

        boolean isConfirmInvalid = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);
        if (isConfirmInvalid) {
            model.addAttribute(PASSWORD_ERROR, PASSWORD_ERROR_MESSAGE);
        }

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (!response.isSuccess()) {
            model.addAttribute(CAPTCHA_ERROR, CAPTCHA_ERROR_MESSAGE);
        }

        if (isConfirmEmpty
                || isConfirmInvalid
                || bindingResult.hasErrors()
                || !response.isSuccess()
        ) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return PAGE_REGISTRATION;
        }

        if (!registrationService.addUser(user)) {
            model.addAttribute(USERNAME_ERROR, USERNAME_ERROR_MESSAGE);

            return PAGE_REGISTRATION;
        }

        return REDIRECT + URL_LOGIN;
    }
}
