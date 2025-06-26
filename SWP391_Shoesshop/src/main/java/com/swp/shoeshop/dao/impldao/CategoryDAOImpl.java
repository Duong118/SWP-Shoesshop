/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.Category;
import com.swp.shoeshop.utils.dbutil.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author 
 */
public class CategoryDAOImpl {

    private static final String GET_BY_CATETYPE = "SELECT [id] ,[name] FROM category WHERE type =?";
    private static final String VIEW_CATEGORY = "SELECT id, name, type FROM category";
    private static final String CREATE_CATEGORY = "INSERT INTO category(name, type) VALUES (?, ?)";
    private static final String CHECK_DUPLICATE_CATE = "SELECT name FROM category WHERE name = ?";
    private static final String GET_NEW_ID = "SELECT MAX(id) AS id FROM category";
    private static final String GET_NAME_TYPE = "SELECT name, type FROM category WHERE id = ?";
    private static final String GET_FULL_CATEGORIES = "SELECT [id] ,[name] FROM category";

    public List<Category> getByCateType(String type) throws SQLException {
        List<Category> listCategorys = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_BY_CATETYPE);
                ps.setString(1, type);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listCategorys.add(new Category(rs.getInt("id"),
                            rs.getString("name")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listCategorys;
    }

    public List<Category> getFullCategories() throws SQLException {
        List<Category> listCategorys = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_FULL_CATEGORIES);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listCategorys.add(new Category(rs.getInt("id"),
                            rs.getString("name")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listCategorys;
    }

    public List<Category> viewCate() throws SQLException {
        List<Category> viewCate = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(VIEW_CATEGORY);
                rs = ps.executeQuery();
                while (rs.next()) {
                    viewCate.add(new Category(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("type")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return viewCate;
    }

    public boolean createCate(Category cate) throws SQLException, ClassNotFoundException, NamingException {
        boolean createCate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CREATE_CATEGORY);
                ps.setString(1, cate.getName());
                ps.setString(2, cate.getType());
                createCate = ps.executeUpdate() > 0;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return createCate;
    }

    public boolean checkCateDuplicate(String name) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_DUPLICATE_CATE);
                ps.setString(1, name);
                rs = ps.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int getNewCateId() throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_NEW_ID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return id;
    }

    public Category getNameType(int id) throws SQLException {
        Category cate = new Category();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_NAME_TYPE);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    cate.setName(rs.getString("name"));
                    cate.setType(rs.getString("type"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return cate;
    }
}
