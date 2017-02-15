package com.yet.spring.core.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by RStreltsov on 13.02.2017.
 */
@Component
public class Event {
    private Integer id;
    private String message;

    @Value("#{new java.util.Date()}")
    private Date date;
    @Value("#{T(java.text.DateFormat).getDateTimeInstance()}")
    private DateFormat dateFormat;

    public Event() {
        this.id = new Random().nextInt(10);
    }

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "id = " + id + ", message is "+message+", date = "+dateFormat.format(date);
    }
}
