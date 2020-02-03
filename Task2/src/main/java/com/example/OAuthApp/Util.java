package com.example.OAuthApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    public static LocalDate convertToLocalDate(String date, String format) {

        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));

    }
}
