package com.yet.spring.core.bean;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

/**
 * Created by RStreltsov on 13.02.2017.
 */
public class Event {
    private Integer id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;
    private static LocalDateTime creationDate;

    static {
        creationDate = LocalDateTime.now();
    }

    public Event(Date date, DateFormat dateFormat) {
        this.id = new Random().nextInt(10);
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {return this.msg;}

    @Override
    public String toString() {
        return "id = " + id + ", message is "+msg+", date = "+dateFormat.format(date);
    }

    public static boolean isDay(int from, int till){
        //LocalTime from = LocalTime.of(8,0);
        //LocalTime till = LocalTime.of(17,0);
        //LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return creationDate.getHour() > from && creationDate.getHour() < till;
    }
}
