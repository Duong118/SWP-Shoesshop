/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.utils.dbutil;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author 
 */
public class DBConnect {
    private static DBConnect instance = null;
    private DBConnect() {}
    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        Context context = new InitialContext();
        Context end = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource)end.lookup("DataSource");
        conn = ds.getConnection();
        return conn;
    }
}