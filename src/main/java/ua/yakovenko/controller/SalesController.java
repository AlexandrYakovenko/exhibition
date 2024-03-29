package ua.yakovenko.controller;

import static ua.yakovenko.controller.Constants.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.yakovenko.domain.entity.Exhibition;
import ua.yakovenko.domain.entity.User;
import ua.yakovenko.exception.BuyException;
import ua.yakovenko.service.ExhibitionService;
import ua.yakovenko.service.SalesService;
import ua.yakovenko.service.UserService;

import java.util.List;

@Controller
public class SalesController {

    private SalesService salesService;

    private UserService userService;

    private ExhibitionService exhibitionService;

    public SalesController(SalesService salesService,
                           UserService userService,
                           ExhibitionService exhibitionService
    ) {
        this.salesService = salesService;
        this.userService = userService;
        this.exhibitionService = exhibitionService;
    }

    @GetMapping(BUY_TICKET_MAPPING)
    public String buyTicket(
            @PathVariable User user

    ) {
        return REDIRECT + URL_MAIN;
    }

    @PostMapping(BUY_TICKET_MAPPING)
    public String buyAndUpdate(
            @PathVariable User user,
            @RequestParam(value = PARAM_MONEY, required = false) Long money,
            @RequestParam(value = PARAM_EXHB_ID, required = false) Long id,
            Model model
    ) {
        if (money != null) {
            userService.updateUserBalance(user, money);
        }

        model.addAttribute(USERNAME, user.getUsername());
        model.addAttribute(BALANCE, user.getAccountMoney());

        if (id != null) {
            Exhibition exhibition = exhibitionService.findById(id);
            model.addAttribute(EXHIBITION, exhibition);

            if (user.getBoughtTickets().contains(exhibition)) {
                model.addAttribute(BUY_ERROR, BUY_ERROR_MESSAGE);
            }

            return PAGE_SALES;
        }

        return PAGE_SALES;
    }

    @GetMapping(BOUGHT_TICKETS_MAPPING)
    public String salesUser(
            @PathVariable User user,
            Model model
    ){
        List<Exhibition> tickets = salesService.findUserTickets(user);
        model.addAttribute(TICKETS, tickets);

        return PAGE_SALES_USER;
    }


    @PostMapping(BOUGHT_TICKETS_MAPPING)
    public String salesUser(
            @PathVariable User user,
            @RequestParam Long ticketId,
            Model model
    ) {
        model.addAttribute(USER, user);

        try {

            salesService.addTicket(user, ticketId);

        } catch (BuyException e) {
            model.addAttribute(BUY_ERROR, BUY_ERROR_MONEY);
            model.addAttribute(USERNAME, user.getUsername());
            model.addAttribute(BALANCE, user.getAccountMoney());

            Exhibition exhibition = exhibitionService.findById(ticketId);
            model.addAttribute(EXHIBITION, exhibition);

            return PAGE_SALES;
        }

        List<Exhibition> tickets = salesService.findUserTickets(user);

        model.addAttribute(TICKETS, tickets);

        return PAGE_SALES_USER;
    }
}
