package com.test.accounts.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    private String accountNumber;
    private String startDate;
    private String endDate;
    private String startAmount;
    private String endAmount;
    private boolean isUser;
}
