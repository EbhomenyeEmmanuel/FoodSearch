package com.esq.foodsearch;

public class LoginModel {
    private String user;
    private String password;

    public LoginModel(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
