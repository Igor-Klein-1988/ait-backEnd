package de.ait.app.repositories;

import de.ait.app.model.Account;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account> {
    public Account findByIban(String iban);

    default String getNewIban() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
