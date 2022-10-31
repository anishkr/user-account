package com.test.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatementDTO {
    private String hashedAccountNumber;
    private String accountType;
    private List<StatementDTO> statements;
}
