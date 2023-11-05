package de.ait.app.controllers;

import de.ait.app.dto.AccountDto;
import de.ait.app.dto.NewAccountDto;
import de.ait.app.model.Account;
import de.ait.app.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@Tags(value = @Tag(name = "Accounts"))
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    @Operation(summary = "create account", description = "Доступно администратору системы")
    @PostMapping
    @ResponseBody
    public AccountDto createAccount(@RequestBody NewAccountDto newAccount) {
        return service.createAccount(newAccount);
    }

    @Operation(summary = "Get all Accounts", description = "some description")
    @GetMapping
    @ResponseBody
    public List<AccountDto> getAccounts() {
        return service.getAllAccounts();
    }

    @Operation(summary = "Get by id", description = "get all Account info")
    @GetMapping("/{id}")
    @ResponseBody
    public AccountDto getAccount(@PathVariable Long id) {
        return service.findDtoById(id);
    }

    @Operation(summary = "Delete", description = "Delete by id")
    @DeleteMapping("/{id}")
    @ResponseBody
    public AccountDto delete(@PathVariable Long id) {
        return service.deleteAccountById(id);
    }
}
