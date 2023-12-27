package com.example.library.Service;

public interface DateTimeService {
    String DateFormatNoTime(String date);
    String DateFormatTime(String datetime);

    String CurrentDateTime(boolean tomorrow);
}
