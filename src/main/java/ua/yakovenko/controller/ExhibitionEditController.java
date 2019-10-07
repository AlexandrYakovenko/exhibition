package ua.yakovenko.controller;

import static ua.yakovenko.controller.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yakovenko.domain.entity.Exhibition;
import ua.yakovenko.domain.entity.User;
import ua.yakovenko.service.ExhibitionService;

@Controller
public class ExhibitionEditController {

    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping(USER_EXHIBITIONS_MAPPING)
    public String userExhibitions(
        @AuthenticationPrincipal User currentUser,
        @PathVariable User user,
        @RequestParam(required = false) Exhibition exhibition,
        @PageableDefault(sort = {PARAM_ID}, direction = Sort.Direction.DESC) Pageable pageable,
        Model model
    ) {
        model.addAttribute(EXHIBITION, exhibition);
        model.addAttribute(IS_CURRENT_USER, currentUser.equals(user));
        model.addAttribute(USER_CHANNEL, user);
        model.addAttribute(SUBSCRIPTIONS_COUNT, user.getSubscriptions().size());
        model.addAttribute(SUBSCRIBERS_COUNT, user.getSubscribers().size());
        model.addAttribute(IS_SUBSCRIBER, user.getSubscribers().contains(currentUser));

        model.addAttribute(URL, URL_USER_EXHIBITIONS + user.getId());
        model.addAttribute(PAGE, exhibitionService.findByAuthor(user, pageable));

        return PAGE_USER_EXHIBITIONS;
    }

    @GetMapping(EDIT_EXHIBITION_MAPPING)
    public String editExhibition (
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @PathVariable Exhibition exhibition,
            @PageableDefault(sort = {PARAM_ID}, direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        model.addAttribute(EXHIBITION, exhibition);
        model.addAttribute(IS_CURRENT_USER, currentUser.equals(user));
        model.addAttribute(USER_CHANNEL, user);
        model.addAttribute(SUBSCRIPTIONS_COUNT, user.getSubscriptions().size());
        model.addAttribute(SUBSCRIBERS_COUNT, user.getSubscribers().size());
        model.addAttribute(IS_SUBSCRIBER, user.getSubscribers().contains(currentUser));

        model.addAttribute(URL, URL_USER_EXHIBITIONS + user.getId() + "/" + exhibition.getId());
        model.addAttribute(PAGE, exhibitionService.findByAuthor(user, pageable));
        model.addAttribute(DELETE_FACTOR, true);

        return PAGE_USER_EXHIBITIONS;
    }

    @PostMapping(UPDATE_EXHIBITION_MAPPING)
    public String updateExhibition(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam(PARAM_ID) Exhibition exhibition,
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

        return REDIRECT + URL_USER_EXHIBITIONS + user;
    }

    @PostMapping(DELETE_EXHIBITION_MAPPING)
    public String deleteExhibition(
            @RequestParam(PARAM_EXHB_ID) Long id
    ) {
        exhibitionService.deleteById(id);

        return REDIRECT + URL_MAIN;
    }
}
