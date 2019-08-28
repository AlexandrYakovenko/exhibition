package ua.yakovenko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {
    @GetMapping("buyTicket")
    public String buyTicket() {
        return "buyTicket";
    }
}
