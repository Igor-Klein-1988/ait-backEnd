package de.ait.app.controllers;

import de.ait.app.model.Account;
import de.ait.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService service;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @GetMapping
    public List<Account> getAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity listAccounts(@PathVariable Long id) {
        Account accountToDelete = service.findById(id);
        if (accountToDelete != null) {
            service.deleteById(id);
            return ResponseEntity.ok(accountToDelete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
