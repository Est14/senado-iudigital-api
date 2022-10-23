package com.est14.senadoiudigital.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateUtil {

    @Value("${est14.jwt.timezone}")
    private String TIMEZONE;

    private SimpleDateFormat simpleDateFormat(){
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        format2.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
        return format2;
    }

    public String getDatestring(){
        Date now = new Date();
        return simpleDateFormat().format(now);
    }

    public long getDateMillis(){
        Date now = new Date();
        String strDate = simpleDateFormat().format(now);
        Date strNow = new Date();

        try {
            strNow = simpleDateFormat().parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return strNow.getTime();
    }

}
