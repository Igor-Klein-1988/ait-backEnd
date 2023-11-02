package de.ait.app.services;

import de.ait.app.model.Account;
import de.ait.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static final String MSG_BLANK_ARGUMENT_ERR = "The iban and iban should not be blank";
    private static final String MSG_IBAN_DUPLICATE_ERR = "The account with iban is exist";


    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createAccount(String iban, BigDecimal balance) {
        try {
            checkUserArguments(iban, balance);
            accountRepository.save(new Account(iban, balance));
        } catch (Exception e) {
            String msg = String.format("Error create account: iban:%s \n error message:%s", iban, e.getMessage());
            throw new RuntimeException(msg);
        }

    }

    @Override
    public Account createAccount(Account account) {
        createAccount(account.getIban(), account.getBalance());
        return accountRepository.findByIban(account.getIban());
    }

    private void checkUserArguments(String iban, BigDecimal balance) {
        if (iban == null || balance == null || iban.isBlank()) {
            throw new IllegalArgumentException(MSG_BLANK_ARGUMENT_ERR);
        }

        if (accountRepository.findByIban(iban) != null) {
            throw new IllegalArgumentException(MSG_IBAN_DUPLICATE_ERR);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findByID(id);
    }

    @Override
    public Account findByIban(String iban) {
        return accountRepository.findByIban(iban);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
