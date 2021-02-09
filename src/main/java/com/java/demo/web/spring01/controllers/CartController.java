/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.controllers;

import com.java.demo.web.spring01.dto.Cart;
import com.java.demo.web.spring01.dto.Product;
import com.java.demo.web.spring01.model.ProductModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Duy Nien
 */
@Controller
@SessionAttributes("carts")
public class CartController {

    List<Cart> listcart = new ArrayList<>();

    @RequestMapping("/cart")
    public String cart() throws Exception{       
        return "cart";
    }

    @RequestMapping("/formCart/{id}")
    public String listcart(Model model, @ModelAttribute Cart cart,RedirectAttributes reAt, @PathVariable(name = "id") String id) {
        try {
            int quantity = cart.getQuantity();
            ProductModel productModel = new ProductModel();
            Product productCart = productModel.getCartById(Integer.parseInt(id));
            Cart c = new Cart(productCart.getName(), quantity, productCart.getPrice(), 5);
            listcart.add(c);
            model.addAttribute("carts", listcart);
            reAt.addFlashAttribute("success","Thêm thành công!");
            return "redirect:/product/"+id;
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }
}
