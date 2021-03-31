package com.cg.fds.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	
	private final String pattern="dd/MM/yy hh:mm:ss";

    public String toText(LocalDateTime datetime, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String text=formatter.format(datetime);
        return text;
    }

    public String toText(LocalDateTime datetime){
        String text= toText(datetime,pattern);
        return text;
    }

    public LocalDateTime toLocalDateTime(String datetimeText, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime datetime= LocalDateTime.parse(datetimeText,formatter);
        return datetime;
    }


    public LocalDateTime toLocalDateTime(String datetimeText){
        LocalDateTime datetime= toLocalDateTime(datetimeText,pattern);
        return datetime;
    }


}
