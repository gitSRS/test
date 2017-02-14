package com.yet.spring.core;

import java.text.DateFormat;
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
}
