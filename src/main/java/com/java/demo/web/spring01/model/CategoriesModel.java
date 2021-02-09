/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo.web.spring01.model;

import com.java.demo.web.spring01.db.DBConnector;
import com.java.demo.web.spring01.dto.Categories;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duy Nien
 */
public class CategoriesModel {
    
    Connection conn=null;
    public CategoriesModel() throws Exception{
        conn=DBConnector.getConnection();
    }
    public List<Categories> getCate()throws  Exception{
        List<Categories> listCate=new ArrayList<>();
        String query="select * from categories";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            Categories c=new Categories();
            c.setId(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setDesc(rs.getString(3));
            listCate.add(c);
        }
        return listCate;  
    }
    
}
