package de.ait.app.controllers.api;

import de.ait.app.dto.AccountDto;
import de.ait.app.model.Account;
import de.ait.app.services.AccountService;
import de.ait.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/api/accounts")
    public List<AccountDto> getAccounts() {
        return service.getAllAccounts();
    }
}
