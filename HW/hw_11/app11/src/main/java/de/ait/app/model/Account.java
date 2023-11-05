package de.ait.app.model;

import lombok.*;

import java.math.BigDecimal;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    private Long id;
    private String iban;
    private BigDecimal balance;

    public Account(String iban, BigDecimal balance) {
        this.iban = iban;
        this.balance = balance;
    }
}
