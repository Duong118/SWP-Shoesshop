/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.Feedback;
import com.swp.shoeshop.utils.dbutil.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.text.NumberFormat;
import java.util.Formatter;

/**
 *
 * @author 
 */
public class FeedbackDAOImpl {

    private static final String FEEDBACK_MANAGER = "SELECT pro.name, fb.user_id, fb.product_id, fb.username, fb.feedback_content, fb.star, fb.date FROM product pro, feedback fb WHERE fb.product_id = pro.id  ";
    private static final String DELETE_FEEDBACK = "DELETE FROM feedback WHERE user_id = ? AND product_id = ?";
    private static final String CREATE_FEEDBACK = "INSERT INTO feedback(user_id, product_id, username, feedback_content, star) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_FEEDBACK_BY_PID = "SELECT user_id, product_id, username, feedback_content, star, date FROM feedback WHERE product_id = ?";
    private static final String GET_AVG_RATING = "SELECT AVG(star) FROM feedback WHERE product_id = ?";

    public List<Feedback> viewFeedback() throws SQLException {
        List<Feedback> fb = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(FEEDBACK_MANAGER);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    int pId = rs.getInt("product_id");
                    String username = rs.getString("username");
                    String feedbackContent = rs.getString("feedback_content");
                    double star = rs.getDouble("star");
                    Date date = rs.getDate("date");
                    String proname = rs.getString("name");
                    fb.add(new Feedback(userId, pId, username, feedbackContent, star, date, proname));
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
        return fb;
    }

    public boolean checkDelete(int user_id, int product_id) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE_FEEDBACK);
                ps.setInt(1, user_id);
                ps.setInt(2, product_id);
                checkDelete = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkDelete;
    }

    public boolean createFeedback(Feedback fb) throws SQLException, ClassNotFoundException {
        boolean createFeedback = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CREATE_FEEDBACK);
                ps.setInt(1, fb.getUserId());
                ps.setInt(2, fb.getProductId());
                ps.setString(3, fb.getUsername());
                ps.setString(4, fb.getFeedbackContent());
                ps.setDouble(5, fb.getStar());
                createFeedback = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return createFeedback;
    }

    public List<Feedback> getFeedbackbyProID(int pId) throws SQLException {
        List<Feedback> fblist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_FEEDBACK_BY_PID);
                ps.setInt(1, pId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String feedbackContent = rs.getString("feedback_content");
                    double star = rs.getDouble("star");
                    Date date = rs.getDate("date");
                    fblist.add(new Feedback(userId, username, feedbackContent, star, date));
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
        return fblist;
    }

    public double getAvgRating(int pId) throws SQLException {
        double avg = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_AVG_RATING);
                ps.setInt(1, pId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    avg =rs.getDouble(1);
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
        return avg;
    }
}
