package com.test.accounts.util;

import com.test.accounts.exception.AccountStatementException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
@Slf4j
public class Convert {

    public static LocalDate stringToDate(String fromDate)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT);
        return LocalDate.parse(fromDate, formatter);
    }

    public static String dateToString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(Constant.DATE_FORMAT));
    }

    public static String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            log.error("[Convert][getSHA256Hash] converting string to hashed error : {}" ,ex.getMessage());
        }
        return result;
    }

    public static boolean validationDate(String fromDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT);
            LocalDate.parse(fromDate, formatter);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}
