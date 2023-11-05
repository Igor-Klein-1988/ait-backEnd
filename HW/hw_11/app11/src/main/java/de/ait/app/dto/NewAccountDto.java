package de.ait.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "NewAccount", description = "Info for create Account")
public class NewAccountDto {

    @Schema(description = "iban of Account", example = "DE 3456 3432")
    private String iban;

    @Schema(description = "balance of Account", example = "20000")
    private BigDecimal balance;
}
