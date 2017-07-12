package com.damlakayali.notdefterim;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

/**
 * Created by damla on 29.06.2017.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private  int id;
    private String email;
    private String password;
    private String name;
    private  String surname;

    public User() {
        super();
    }

    public int getId(int id) {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }







}
