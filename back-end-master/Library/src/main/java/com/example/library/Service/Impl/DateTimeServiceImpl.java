package com.example.library.Service.Impl;

import com.example.library.Service.DateTimeService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
@Service
public class DateTimeServiceImpl implements DateTimeService {
    @Override
    public String DateFormatNoTime(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            Date orderDate = inputFormat.parse(date);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            return outputFormat.format(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String DateFormatTime(String datetime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            Date orderDate = inputFormat.parse(datetime);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return outputFormat.format(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String CurrentDateTime(boolean tomorrow) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime nextDay = currentTime.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentFormatted = currentTime.format(formatter);
        String nextDayFormatted = nextDay.format(formatter);
       if (tomorrow)
           return nextDayFormatted;
       else
           return currentFormatted;
    }
}
