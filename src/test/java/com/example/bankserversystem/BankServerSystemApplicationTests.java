package com.example.bankserversystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.DateFormatter;
import javax.swing.text.Style;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
class BankServerSystemApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        System.out.println(localDateTime.format(formatter));
        double dValue = Math.random();
        System.out.println(dValue);
        Long lValue = (long) (dValue * 100000000000l);
        System.out.println(lValue);
        Long d = lValue;
        Long date = Long.parseLong(String.valueOf(localDateTime.format(formatter)));
        Long lAccountNumber = date+d;
        System.out.println(lAccountNumber);
    }

}
