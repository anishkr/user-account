package com.test.accounts.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@SpringBootTest
class ConvertTest {

    @Test
    void testStringToDate() {
        LocalDate actualResult = Convert.stringToDate("01.01.2000");
        LocalDate expected = LocalDate.of(2000,01,01);
        assertEquals(expected, actualResult);
    }
    
    @Test
    void testStringToDateParseException() {
        assertThrows(DateTimeParseException.class,
                ()->{
                    Convert.stringToDate("01-01-2000");
                });
    }

    @Test
    void testGetSHA256Hash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String actualResult = Convert.getSHA256Hash("123");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest("123".getBytes("UTF-8"));
        assertEquals(DatatypeConverter.printHexBinary(hash), actualResult);
    }
}
