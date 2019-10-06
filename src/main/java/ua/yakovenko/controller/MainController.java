package ua.yakovenko.controller;

import static ua.yakovenko.controller.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yakovenko.domain.entity.Exhibition;
import ua.yakovenko.domain.entity.User;
import ua.yakovenko.service.ExhibitionService;
import ua.yakovenko.util.ControllerUtils;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping("/")
    public String mainPage() {
        return PAGE_HOME;
    }

    @GetMapping("/main")
    public String mainForm(
            @RequestParam(required = false, defaultValue = "") String showroom,
            Model model,
            @PageableDefault(sort = {PARAM_ID}, direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<Exhibition> page;

        if(!showroom.isEmpty()) {
            page = exhibitionService.findByShowroom(showroom, pageable);
        } else {
            page = exhibitionService.findAll(pageable);
        }

        model.addAttribute(PAGE, page);
        model.addAttribute(URL, URL_MAIN);
        model.addAttribute(SHOWROOM, showroom);

        return PAGE_MAIN_PAGE;
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Exhibition exhibition,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {PARAM_ID}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        exhibition.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute(EXHIBITION, exhibition);
        } else {
            model.addAttribute(EXHIBITION, null);
            exhibitionService.save(exhibition);
        }

        model.addAttribute(URL, URL_MAIN);
        model.addAttribute(PAGE, exhibitionService.findAll(pageable));

        return PAGE_MAIN_PAGE;
    }
}
