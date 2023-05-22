package com.example.auto_warehouse.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    public String username;
    private String password;
    private String email;

    private int id;

    public String phone;

    private String token;

    public User(String username, String password,String email,int id,String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
        this.phone = phone;
    }

    public User() {

    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getemail(){return email;}

    public void setemail(String email) {
        this.email = email;
    }

    public int getId(){return id;}

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token=token;
    }
}

