/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.News;
import com.swp.shoeshop.utils.dbutil.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class NewsDAOImpl {

    //Queries 
    private static final String GET_NEWS_BY_ID = "SELECT [id], [title], [news_content], [image], [date] "
            + "FROM dbo.news "
            + "WHERE [id] LIKE ?";
    private static final String GET_ALL_NEWS = "SELECT [id], [title], [news_content], [image], [date] "
            + "FROM dbo.news ";
    private static final String INSERT_NEWS = "INSERT INTO [news]"
            + "([title], [news_content], [image])"
            + "VALUES(?, ?, ?) ";

    private static final String DELETE_NEWS = "DELETE FROM dbo.News "
            + "WHERE [id] = ? ";
    private static final String UPDATE_NEWS = "UPDATE NEWS "
            + "SET [title] = ?, [news_content] = ?, [image] = ? "
            + "WHERE [id] = ?";
    
    private static final String TOTAL_BLOGS = "SELECT COUNT(*) FROM news";
    

    public List<News> getAllNews() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        News getNews = null;
        List<News> listNews = new ArrayList<>();

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_NEWS);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listNews.add(new News(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("news_content"),
                            rs.getString("image"),
                            rs.getDate("date")));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        //System.out.println("List news:" +listNews);
        return listNews;
    }

    public News getNewsByID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        News getNews = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_NEWS_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    getNews = new News(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("news_content"),
                            rs.getString("image"),
                            rs.getDate("date"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        //System.out.println("news with ID " +id +" :" +getNews);
        return getNews;
    }

    public boolean addNews(News news) throws SQLException {
        boolean isAdded = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_NEWS);
                ps.setString(1, news.getTitle());
                ps.setString(2, news.getNewsContent());
                ps.setString(3, news.getImage());
                isAdded = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //System.out.println("Added news "+news);
        return isAdded;
    }

    public boolean updateNews(String title, String newsContent, String image, int postId) throws SQLException {
        boolean isUpdated = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_NEWS);
                ps.setString(1, title);
                ps.setString(2, newsContent);
                ps.setString(3, image);
                ps.setInt(4, postId);
                isUpdated = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return isUpdated;
    }

    public boolean deleteNews(int id) throws SQLException {
        boolean isDeleted = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE_NEWS);
                ps.setInt(1, id);
                isDeleted = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //System.out.println("Deleted news with id" +id);
        return isDeleted;
    }
    
        public int getTotalBlog() throws SQLException {
        int total = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(TOTAL_BLOGS);
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                }
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
        return total;
    }
}
