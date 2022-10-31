package com.test.accounts.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    public static final String  START_DATE = "startDate";
    public static final String  END_DATE = "endDate";
    public static final String  START_AMOUNT = "startAmount";
    public static final  String  END_AMOUNT = "endAmount";
    public static final String  ACCOUNT_NUMBER = "accountNumber";

    //SQL QURIES
    public static final String FIND_ACCOUNT_BY_ACCOUNT_NUMBER = "SELECT id, account_type, account_number FROM account where account_number = %s";
    public static final String FIND_STATEMENTS_BY_ACCOUNT_NUMBER = "SELECT a.id, " +
            "a.account_type as accountType, " +
            "account_number as accountNumber, " +
            "st.id as statementId, " +
            "st.amount, " +
            "st.datefield as statementDate\n" +
            "FROM account as a \n" +
            "\n INNER JOIN statement as st\n" +
            "ON a.id = st.account_id where account_number = %s ";
}
