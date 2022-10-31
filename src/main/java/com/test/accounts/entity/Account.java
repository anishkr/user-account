package com.test.accounts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Integer id;

    private String accountType;

    private String accountNumber;

    private String amount;

    private String statementDate;

    private Integer statementId;
}
