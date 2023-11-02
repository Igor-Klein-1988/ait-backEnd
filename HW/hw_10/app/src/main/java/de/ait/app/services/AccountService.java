package de.ait.app.services;

import de.ait.app.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    public void createAccount(String iban, BigDecimal balance);
    public Account createAccount(Account account);

    public List<Account> getAllAccounts();

    public Account findById(Long id);
    public Account findByIban(String iban);

    public void deleteById(Long id);


    default void createAccount(String iban, String balance) {
        createAccount(iban, BigDecimal.valueOf(Double.parseDouble(balance)));
    }
}
