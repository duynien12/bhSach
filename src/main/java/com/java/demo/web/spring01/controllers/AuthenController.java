/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.controllers;

import com.java.demo.web.spring01.dto.Customer;
import com.java.demo.web.spring01.dto.RegisterInfo;
import com.java.demo.web.spring01.model.CustomerModel;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author hoangnghiem
 */
@Controller
public class AuthenController {

    @RequestMapping("/register")
    public String register(Model model) {
        RegisterInfo registerInfo=new RegisterInfo("","","","","");
        model.addAttribute("registerInfo",registerInfo);
        return "register";
    }
    
    @RequestMapping("/handle_register")
    public String handleRegister(@ModelAttribute RegisterInfo registerInfo, Model model, HttpServletResponse response) {     
        try {
            CustomerModel customerModel = new CustomerModel();    
            Customer c = new Customer();
            c.setName(registerInfo.getFullname());
            c.setEmail(registerInfo.getEmail());
            c.setAddress(registerInfo.getAddress());
            c.setPassword(registerInfo.getPassword());
            c.setPhone(registerInfo.getPhone());
            c.setCreatedAt(new Date());
            int add = customerModel.add(c);
            if (add > 0) {
                Cookie cookie = new Cookie("email", registerInfo.getEmail());
                response.addCookie(cookie);
                return "redirect:/login";
            } else {
                return "redirect:/register";
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/";
    }
}
