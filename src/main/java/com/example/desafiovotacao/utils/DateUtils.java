package com.example.desafiovotacao.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {
    public static String formatDate(Date date) {
        return date != null ? new SimpleDateFormat("dd/MM/yyyy").format(date) : null;
    }
}