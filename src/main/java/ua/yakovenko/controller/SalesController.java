package ua.yakovenko.controller;

import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yakovenko.domain.Exhibition;
import ua.yakovenko.domain.User;
import ua.yakovenko.service.SalesService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class SalesController {
    @Autowired
    private SalesService salesService;
    private Long exhibitionId;

    @GetMapping("/sales/{user}/{id}")
    public String buyTicket(
            @PathVariable User user,
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("money", user.getAccountMoney());
        exhibitionId =  id;
        model.addAttribute("exhibition", salesService.findById(id).get());

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
        } catch (Exception e) {
            model.addAttribute("buyError", "You have bought this ticket");
            return "redirect:/sales/" + user.getId() + "/" + exhibitionId;
        }

        List<Exhibition> tickets = salesService.findUserTickets(user);

        model.addAttribute("tickets", tickets);

        return "salesUser";
    }
}
