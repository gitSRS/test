package com.yet.spring.core.bean;

/**
 * Created by RStreltsov on 13.02.2017.
 */
public class Client {
    private Integer id;
    private String fullName;

    public Client(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setGreeting(String str) {
        System.out.println(str);
    }
}
