package ua.yakovenko.controller;

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
@SessionAttributes("exhibitionS")
public class SalesController {

    private static final String BUY_ERROR = "You have already bought this ticket";

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

    @GetMapping("/sales/{user}")
    public String buyTicket(
            @PathVariable User user,
            @RequestParam(value = "ex",required = false) Long id,
            Model model
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("balance", user.getAccountMoney());

        if (id != null) {
            Exhibition exhibition = exhibitionService.findById(id);
            model.addAttribute("exhibition", exhibition);

            if (user.getBoughtTickets().contains(exhibition)) {
                model.addAttribute("buyError", BUY_ERROR);
            }

            return "sales";
        }

        return "redirect:/main";
    }

    @PostMapping("/sales/{user}")
    public String updateBalance(
            @PathVariable User user,
            @RequestParam(value = "money") Long money,
            @RequestParam(value = "ex") Long id,
            Model model
    ) {
        if (money != null) {
            userService.updateUserBalance(user, money);
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("balance", user.getAccountMoney());

        Exhibition exhibition = exhibitionService.findById(id);
        model.addAttribute("exhibition", exhibition);

        if (user.getBoughtTickets().contains(exhibition)) {
            model.addAttribute("buyError", BUY_ERROR);
        }

        return "sales";
    }

    @GetMapping("bought-tickets/{user}")
    public String salesUser(
            @PathVariable User user,
            Model model
    ){
        List<Exhibition> tickets = salesService.findUserTickets(user);
        model.addAttribute("tickets", tickets);

        return "salesUser";
    }


    @PostMapping("bought-tickets/{user}")
    public String salesUser(
            @PathVariable User user,
            @RequestParam Long ticketId,
            Model model
    ) {
        model.addAttribute("user", user);

        try {
            salesService.addTicket(user, ticketId);
        } catch (BuyException e) {

            return "redirect:/sales/" + user.getId();
        }

        List<Exhibition> tickets = salesService.findUserTickets(user);

        model.addAttribute("tickets", tickets);

        return "salesUser";
    }
}
