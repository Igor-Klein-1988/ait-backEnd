package de.ait.app.dto;

import de.ait.app.model.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Account", description = "Info about Account")
public class AccountDto {
    @Schema(description = "Account id", example = "1")
    private Long id;

    @Schema(description = "iban of Account", example = "DE 3456 3432")
    private String iban;

    @Schema(description = "balance of Account", example = "20000")
    private BigDecimal balance;

    public static AccountDto from(Account account) {
        return new AccountDto(account.getId(), account.getIban(), account.getBalance());
    }

    public static List<AccountDto> from(List<Account> users) {
        return users.stream()
                .map(AccountDto::from)
                .collect(Collectors.toList());
    }
}
