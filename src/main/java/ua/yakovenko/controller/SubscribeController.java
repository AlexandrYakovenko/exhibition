package ua.yakovenko.controller;

import static ua.yakovenko.controller.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yakovenko.domain.entity.User;
import ua.yakovenko.service.SubscribeService;

@Controller
@RequestMapping(USER_MAPPING)
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @GetMapping(SUBSCRIBE_MAPPING)
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ) {
        subscribeService.subscribe(currentUser, user);

        return REDIRECT + URL_USER_EXHIBITIONS + user.getId();
    }

    @GetMapping(UNSUBSCRIBE_MAPPING)
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ) {
        subscribeService.unsubscribe(currentUser, user);

        return REDIRECT + URL_USER_EXHIBITIONS + user.getId();
    }

    @GetMapping(USER_LIST_MAPPING)
    public String userList(
            Model model,
            @PathVariable User user,
            @PathVariable String type
    ) {
        model.addAttribute(USER_CHANNEL, user);
        model.addAttribute(TYPE, type);

        if (SUBSCRIPTIONS.equals(type)) {
            model.addAttribute(USERS, user.getSubscriptions());
        } else {
            model.addAttribute(USERS, user.getSubscribers());
        }

        return PAGE_SUBSCRIPTIONS;
    }
}
