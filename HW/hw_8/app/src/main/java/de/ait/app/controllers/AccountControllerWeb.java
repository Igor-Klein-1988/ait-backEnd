package de.ait.app.controllers;

import de.ait.app.model.Account;
import de.ait.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountControllerWeb {
    @Autowired
    private AccountService service;

    @PostMapping("/accounts")
    public String createAccount(@RequestParam String iban, @RequestParam String balance) {
        System.out.println("Start add account: " + iban + " " + balance);
        service.createAccount(iban, balance);

        return "redirect:/accounts";
    }

    @GetMapping("/accounts")
    public String listAccounts(Model model) {
        List<Account> allAccounts = service.getAllAccounts();
        model.addAttribute("accounts", allAccounts);
        return "all_accounts_view";
    }


    @GetMapping("/accounts/{id}")
    public String listAccounts(@PathVariable Long id, Model model) {
        List<Account> allAccounts = service.getAllAccounts().stream()
                .filter(account -> account.getId().equals(id))
                .toList();
        model.addAttribute("accounts", allAccounts);
        model.addAttribute("id", id);
        return "id_accounts_view";
    }

    @DeleteMapping("/accounts/{id}")
    public String listAccounts(@PathVariable Long id) {

        System.out.println("Delete " + service.getAllAccounts().get(id.intValue()));

        return "redirect:/accounts";
    }
}
