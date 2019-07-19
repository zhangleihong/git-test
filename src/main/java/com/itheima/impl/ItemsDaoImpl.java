package com.itheima.impl;

import com.itheima.dao.ItemsDao;
import com.itheima.domain.Items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemsDaoImpl implements ItemsDao {
    public List<Items> findAll() throws Exception {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Items> list = null;
        try{

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:///gjp","root","123");
        pst = connection.prepareCall("select * from gjp_sort");
        rs = pst.executeQuery();
        list = new ArrayList<Items>();
        while(rs.next()){
            Items items = new Items();
            items.setId(rs.getInt("sid"));
            items.setName(rs.getString("sname"));
            list.add(items);
        }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
            pst.close();
            rs.close();
        }
        return list;

    }
}
