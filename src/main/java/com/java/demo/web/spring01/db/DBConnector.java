/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hoangnghiem
 */
public class DBConnector {
   public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String username = "Duynien";
        String password = "123123";
        String connStr = "jdbc:mysql://127.0.0.1:3306/ql_banhang";
        return DriverManager.getConnection(connStr, username, password);
    } 
}
