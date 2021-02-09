/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.dto;

/**
 *
 * @author hoangnghiem
 */
public class RegisterInfo {
    
    private String email;
    private String fullname;
    private String password;
    private String address;
    private String phone;

    public RegisterInfo() {
    }

    public RegisterInfo(String email, String fullname, String password, String address) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.address = address;
    }

    public RegisterInfo(String email, String fullname, String password, String address, String phone) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
