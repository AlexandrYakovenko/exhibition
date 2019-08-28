package ua.yakovenko.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yakovenko.domain.Exhibition;
import ua.yakovenko.domain.User;
import ua.yakovenko.service.ExhibitionService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class MainController {
    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping("/")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/main")
    public String mainForm(
            @RequestParam(required = false, defaultValue = "") String showroom,
            Model model
    ){
        List<Exhibition> exhibitions;

        if(!showroom.isEmpty()) {
            exhibitions =
                    exhibitionService.findByShowroom(showroom);
        } else {
            exhibitions =
                    exhibitionService.findAll();
        }

        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("showroom", showroom);

        return "mainPage";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String showroom,
            @RequestParam String description,
            Model model
    ) {
        Exhibition exhibition = new Exhibition(name, showroom, description, user);

        exhibitionService.save(exhibition);

        model.addAttribute("exhibitions", exhibitionService.findAll());

        return "mainPage";
    }
}
