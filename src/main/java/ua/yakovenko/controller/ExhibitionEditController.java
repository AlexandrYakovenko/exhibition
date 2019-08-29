package ua.yakovenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yakovenko.domain.Exhibition;
import ua.yakovenko.domain.User;
import ua.yakovenko.service.ExhibitionService;

import java.util.Set;

@Controller
public class ExhibitionEditController {
    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping("/user-exhibitions/{user}")
    public String userExhibitions(
        @AuthenticationPrincipal User currentUser,
        @PathVariable User user,
        Model model,
        @RequestParam(required = false) Exhibition exhibition
    ) {
        Set<Exhibition> exhibitions = user.getExhibitions();

        model.addAttribute("exhibition", exhibition);
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        return "userExhibitions";
    }

    @PostMapping("/user-exhibitions/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Exhibition exhibition,
            @RequestParam String name,
            @RequestParam String showroom,
            @RequestParam String description
    ) {

        if (exhibition.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(name)) {
                exhibition.setName(name);
            }

            if (!StringUtils.isEmpty(showroom)) {
                exhibition.setShowroom(showroom);
            }

            if (!StringUtils.isEmpty(description)) {
                exhibition.setShowroom(description);
            }

            exhibitionService.save(exhibition);
        }

        return "redirect:/user-exhibitions/" + user;
    }
}
