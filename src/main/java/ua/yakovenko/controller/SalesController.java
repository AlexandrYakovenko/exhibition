package ua.yakovenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yakovenko.domain.User;
import ua.yakovenko.service.SalesService;

@Controller
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping("/sales/{user}")
    public String buyTicket(
            @PathVariable User user,
            Model model
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("money", user.getAccountMoney());

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

        return"redirect:/sales/" + user.getId();
    }
}
