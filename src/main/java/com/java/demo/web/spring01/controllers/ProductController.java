/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.controllers;

import com.java.demo.web.spring01.dto.LoginInfo;
import com.java.demo.web.spring01.dto.Product;
import com.java.demo.web.spring01.model.ProductModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 *
 * @author hoangnghiem
 */
@Controller
public class ProductController {

    public String getCate(Model model) {
        return "";
    }

    @RequestMapping("/product/{id}")
    public String showProductDetail(@PathVariable(name = "id") String id, RedirectAttributes remodel, Model model, HttpServletRequest req) {
        try {
            HttpSession session = req.getSession();
            String user = (String) session.getAttribute("user");
            if (user != null && !user.equals("")) {
                ProductModel prodModel = new ProductModel();
                Product prod = prodModel.findById(Integer.parseInt(id));
                model.addAttribute("prod", prod);
                return "product";
            } else {
                remodel.addFlashAttribute("error", "Bạn phải đăng nhập");
                return "redirect:/login";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/";
    }

//    @RequestMapping("/login/product/{id}")
//    public String showProductLogin(@PathVariable(name = "id") String id, RedirectAttributes remodel, HttpServletResponse response,
//            Model model, HttpServletRequest req) {
//        try {
//            List<Cookie> list = new HomeController().getCookie(response, req);
//            if (list.isEmpty()) {
//                remodel.addFlashAttribute("isempty", "Bạn chưa có tài khoản");
//                return "redirect:/register";
//            }
//            for (int i = 0; i < list.size(); i++) {
//                Cookie cookie = list.get(i);
//                if (cookie.getName().contains("email")){
//                    String email = cookie.getValue();
//                    model.addAttribute("loginInfo", new LoginInfo(email,""));
//                    return "redirect:/product/{id}";
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "redirect:/";
//    }

}
