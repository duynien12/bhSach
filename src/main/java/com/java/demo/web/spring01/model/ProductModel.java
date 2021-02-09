/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.model;

import com.java.demo.web.spring01.db.DBConnector;
import com.java.demo.web.spring01.dto.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoangnghiem
 */
public class ProductModel {

    private Connection conn;

    public ProductModel() throws Exception {
        this.conn = DBConnector.getConnection();
    }

    public int delete(int id) throws Exception {
        String sql = "DELETE FROM PRODUCTS WHERE id = ?";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        return pstmt.executeUpdate();
    }

    public int update(Product product) throws Exception {
        String sql = "UPDATE PRODUCTS SET `name` = ?, `price` = ?, `cateId` = ?, `isHot` = ? WHERE id = ?";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, product.getName());
        pstmt.setDouble(2, product.getPrice());
        pstmt.setInt(3, product.getCategory());
        pstmt.setBoolean(4, product.isHot());
        pstmt.setInt(5, product.getId());
        return pstmt.executeUpdate();
    }

    public int add(Product product) throws Exception {
        String sql = "INSERT INTO `PRODUCTS` (`name`, `price`, `cateId`, `isHot`) VALUES (?,?,?,?)";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, product.getName());
        pstmt.setDouble(2, product.getPrice());
        pstmt.setInt(3, product.getCategory());
        pstmt.setBoolean(4, product.isHot());
        return pstmt.executeUpdate();
    }

    public Product findById(int id) throws Exception {
        String sql = "SELECT * FROM PRODUCTS WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        Product product = new Product();
        if (rs.next()) {
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setCategory(rs.getInt("cateId"));
            product.setHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));
            product.setDesc(rs.getString("desc"));
            product.setAuthor(rs.getString("author"));
            product.setPublisher(rs.getString("publisher"));
            product.setPublishedDate(rs.getDate("publishedDate"));
            product.setContent(rs.getString("content"));
        }
        return product;
    }
    public Product getCartById(int id) throws Exception {
        String sql = "SELECT name,price FROM PRODUCTS WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        Product product = new Product();
        if (rs.next()) {
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
        }
        return product;
    }

    public int count() throws Exception {
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT COUNT(*) as TOTAL FROM PRODUCTS";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("TOTAL");
        }
        return 0;
    }

    public List<Product> getNewProduct(int limit) throws Exception {
        List<Product> products = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS ORDER BY id DESC LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            p.setCategory(rs.getInt("cateId"));
            p.setHot(rs.getBoolean("isHot"));
            p.setDesc(rs.getString("desc"));
            p.setImage(rs.getString("image"));

            products.add(p);
        }
        return products;
    }

    public List<Product> getHotProduct(int limit) throws Exception {
        List<Product> products = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE isHot = 1 LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            p.setCategory(rs.getInt("cateId"));
            p.setHot(rs.getBoolean("isHot"));
            p.setDesc(rs.getString("desc"));
            p.setImage(rs.getString("image"));

            products.add(p);
        }
        return products;
    }

    public List<Product> getRandProduct(int limit) throws Exception {
        List<Product> products = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            p.setCategory(rs.getInt("cateId"));
            p.setHot(rs.getBoolean("isHot"));
            p.setDesc(rs.getString("desc"));
            p.setImage(rs.getString("image"));

            products.add(p);
        }
        return products;
    }

    public List<Product> list(int limit, int page) throws Exception {
        List<Product> products = new ArrayList<>();
        Connection conn = DBConnector.getConnection();

        /**
         * page = 1 --> offset = 0 page = 2 --> offset = 10 page = 3 --> offset
         * = 20 ... page = n --> offset = (n - 1)*limit
         */
        int offset = (page - 1) * limit;

        String sql = "SELECT * FROM PRODUCTS LIMIT ? OFFSET ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        pstmt.setInt(2, offset);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            p.setCategory(rs.getInt("cateId"));
            p.setHot(rs.getBoolean("isHot"));

            products.add(p);
        }
        return products;
    }
}
