/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.controllers;

import com.java.demo.web.spring01.dto.Categories;
import com.java.demo.web.spring01.dto.Customer;
import com.java.demo.web.spring01.dto.LoginInfo;
import com.java.demo.web.spring01.dto.Product;
import com.java.demo.web.spring01.dto.Slider;
import com.java.demo.web.spring01.model.CategoriesModel;
import com.java.demo.web.spring01.model.CustomerModel;
import com.java.demo.web.spring01.model.ProductModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author hoangnghiem
 */
@Controller
@SessionAttributes("user")
public class HomeController {

    public List<Cookie> getCookie(HttpServletResponse response,
            HttpServletRequest req) {
        List<Cookie> list = new ArrayList<>();
        try {
            Cookie cookie = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    list.add(cookie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Slider> getSliders() {
        List<Slider> sliders = new ArrayList<>();
        sliders.add(new Slider(1,
                "SLIDER 01",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "../image/slide.png")
        );
        sliders.add(new Slider(2,
                "SLIDER 02",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "../image/slide.png")
        );
        sliders.add(new Slider(3,
                "SLIDER 03",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "../image/slide.png")
        );
        sliders.add(new Slider(4,
                "SLIDER 04",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "../image/slide.png")
        );

        return sliders;
    }

    @RequestMapping("/loginHandle")
    public String login(@ModelAttribute LoginInfo loginInfo, Model model, RedirectAttributes reAtt) {
        try {
            CustomerModel customerModel = new CustomerModel();
            Customer c = customerModel.login(loginInfo.getUsername(), loginInfo.getPassword());
            if (c != null) {
                model.addAttribute("user", loginInfo.getUsername());
                return "redirect:/";
            } else {
                reAtt.addFlashAttribute("errorlogin", "Đăng nhập sai!,hãy chắc chắn bạn có tài khoản");
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping("/")
    public String home(Model model) {
        try {
            model.addAttribute("sliders", this.getSliders());
            ProductModel prodModel = new ProductModel();
            CategoriesModel categoriesModel = new CategoriesModel();
            List<Categories> cate = categoriesModel.getCate();
            model.addAttribute("cate", cate);
            List<Product> hotProducts = prodModel.getHotProduct(4);
            model.addAttribute("hotProducts", hotProducts);
            List<Product> newProducts = prodModel.getNewProduct(4);
            model.addAttribute("newProducts", newProducts);
            List<Product> randProducts = prodModel.getRandProduct(4);
            model.addAttribute("randProducts", randProducts);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "home";
    }

    @RequestMapping("/notice")
    public String notice() {
        return "notice";
    }

    @RequestMapping("/login")
    public String login(Model model, HttpServletResponse response,
            HttpServletRequest req, RedirectAttributes reAtt) {
        int index = -1;
        List<Cookie> list = this.getCookie(response, req);
        System.out.println("abc:" + list.isEmpty());
        if (list.isEmpty()) {
            reAtt.addFlashAttribute("isempty", "Bạn chưa có tài khoản");
            return "redirect:/register";
        }
        for (int i = 0; i < list.size(); i++) {
            Cookie cookie = list.get(i);
            if (cookie.getName().equals("email")) {
                index = i;
            }
        }
        if (index != -1) {
            String email = list.get(index).getValue();
            model.addAttribute("loginInfo", new LoginInfo(email, ""));
            return "login";
        }
        else {
            reAtt.addFlashAttribute("isempty", "Bạn chưa có tài khoản");
            return "redirect:/register";
        }
        //return null;
    }
}
