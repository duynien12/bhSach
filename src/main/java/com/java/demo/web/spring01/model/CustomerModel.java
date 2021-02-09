/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.model;

import com.java.demo.web.spring01.db.DBConnector;
import com.java.demo.web.spring01.dto.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author hoangnghiem
 */
public class CustomerModel {

    private Connection conn;

    public CustomerModel() throws Exception {
        this.conn = DBConnector.getConnection();
    }
    
    //findByEmailAndPassword
    public Customer login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS WHERE email = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        
        ResultSet rs = pstmt.executeQuery();

        Customer c = new Customer();
        if (rs.next()) { // nếu có bản ghi tồn tại 
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setAddress(rs.getString("address"));
            c.setEmail(rs.getString("email"));
            c.setCreatedAt(rs.getDate("createdAt"));
            return c;
        } else { // ko có bản ghi nào phù hợp
            return null;
        }
    }
    
    public int add(Customer customer) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        String sql = "INSERT INTO `CUSTOMERS` (`name`, `phone`, `address`, "
                + "`createdAt`, `email`, `password`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, customer.getName());
        pstmt.setString(2, customer.getPhone());
        pstmt.setString(3, customer.getAddress());
        pstmt.setString(4, sdf.format(customer.getCreatedAt()));
        pstmt.setString(5, customer.getEmail());
        pstmt.setString(6, customer.getPassword());
        return pstmt.executeUpdate();
    }
}
