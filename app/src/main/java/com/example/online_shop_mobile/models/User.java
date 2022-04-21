package com.example.online_shop_mobile.models;

public class User {

    private int id;
    private String email, fName, lName;

    public User(int id, String email, String fName, String lName) {
        this.id = id;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }
}
