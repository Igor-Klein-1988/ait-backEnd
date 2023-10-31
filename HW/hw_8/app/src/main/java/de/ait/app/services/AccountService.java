package de.ait.app.services;

import de.ait.app.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    public void createAccount(String iban, BigDecimal balance);

    public List<Account> getAllAccounts();

    default void createAccount(String iban, String balance) {
        createAccount(iban, BigDecimal.valueOf(Double.parseDouble(balance)));
    }
}
