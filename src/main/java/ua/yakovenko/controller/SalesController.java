package ua.yakovenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.yakovenko.domain.entity.Exhibition;
import ua.yakovenko.domain.entity.User;
import ua.yakovenko.exception.BuyException;
import ua.yakovenko.service.ExhibitionService;
import ua.yakovenko.service.SalesService;

import java.util.List;

@Controller
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private ExhibitionService exhibitionService;

    //TODO переделать без єтой переменной
    private Long exhibitionId;

    @GetMapping("/sales/{user}")
    public String buyTicket(
            @PathVariable User user,
            @RequestParam(value = "ex",required = false) Long id,
            Model model
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("money", user.getAccountMoney());

        Exhibition exhibition = exhibitionService.findById(id);
        model.addAttribute("exhibition", exhibition);

        if (user.getBoughtTickets().contains(exhibition)) {
            model.addAttribute("buyError", "You have already bought this ticket");
        }
        
        return "sales";
    }

    @GetMapping("/sales/addMoney/{user}")
    public String addMoney(
            @PathVariable User user,
            Model model
    ) {
        model.addAttribute("user", user);

        return "addMoney";
    }

    @PostMapping("/sales/addMoney/{user}")
    public String changeMoney(
            @PathVariable User user,
            @RequestParam Long accountMoney,
            Model model
    ) {
        if (accountMoney == null || accountMoney < 1 || accountMoney > 1000) {
            model.addAttribute("moneyError", "Please, put the amount from 1 to 1000$");

            return "addMoney";
        }

        salesService.addMoney(user, accountMoney);

        return"redirect:/sales/" + user.getId() + "/" + exhibitionId;
    }

    @GetMapping("salesUser/{user}")
    public String salesUser(
            @PathVariable User user,
            Model model
    ){
        List<Exhibition> tickets = salesService.findUserTickets(user);
        model.addAttribute("tickets", tickets);

        return "salesUser";
    }


    @PostMapping("salesUser/{user}")
    public String salesUser(
            @PathVariable User user,
            @RequestParam Long salesId,
            Model model
    ) {
        model.addAttribute("user", user);

        try {
            salesService.addTicket(user, salesId);
        } catch (BuyException e) {
            //TODO
           model.addAttribute("buyError", "You have bought this ticket");

            return "redirect:/sales/" + user.getId() + "/" + exhibitionId;
        }

        List<Exhibition> tickets = salesService.findUserTickets(user);

        model.addAttribute("tickets", tickets);

        return "salesUser";
    }
}
