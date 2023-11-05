package de.ait.app.repositories;

import de.ait.app.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Repository
public class AccountRepositoryFileImpl implements AccountRepository {
    private final String fileName;
    private Long currentID = 0L;


    public AccountRepositoryFileImpl(@Value("accounts.txt") String fileName) {
        this.fileName = fileName;
        this.currentID = getLastID();

    }

    private long getLastID() {
        return findAll()
                .stream()
                .mapToLong(Account::getId)
                .max()
                .orElse(0);

    }

    @Override
    public Account findByIban(String iban) {
        return findAll().stream()
                .filter(account -> account.getIban().equals(iban))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Account account) {
        System.out.println("save to file repo");
        account.setId(++currentID);
        String line = String.format("%d;%s;%s%n", account.getId(), account.getIban(), account.getBalance());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
        } catch (IOException e) {
            System.out.println("file save error");
        }

    }

    @Override
    public Account findByID(Long id) {
        return findAll().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Account> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(this::parseLine)
                    .filter(Objects::nonNull)
                    .toList();

        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }

    private Account parseLine(String line) {
        String[] tokenArray = line.split(";");
        try {
            return new Account(
                    Long.parseLong(tokenArray[0]),
                    tokenArray[1],
                    BigDecimal.valueOf(Double.parseDouble(tokenArray[2]))
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        deleteAccountById(id);
    }

    @Override
    public Account deleteAccountById(Long id) {
        List<Account> accounts = findAll();

        Optional<Account> accountToDelete = accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();

        accountToDelete.ifPresent(account -> {
            List<Account> updatedAccounts = new ArrayList<>(accounts);
            updatedAccounts.remove(accountToDelete.get());
            saveAll(updatedAccounts);
        });

        if (accountToDelete.isEmpty()) {
            return null;
        }

        List<Account> updatedAccounts = new ArrayList<>(accounts);
        updatedAccounts.remove(accountToDelete.get());
        saveAll(updatedAccounts);

        return accountToDelete.get();
    }

    private void saveAll(List<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (Account account : accounts) {
                String line = String.format("%d;%s;%s%n", account.getId(), account.getIban(), account.getBalance());
                writer.write(line);
            }
        } catch (IOException e) {
            System.out.println("Error while saving accounts to file");
        }
    }


    @Override
    public void update(Account item) {

    }

}
