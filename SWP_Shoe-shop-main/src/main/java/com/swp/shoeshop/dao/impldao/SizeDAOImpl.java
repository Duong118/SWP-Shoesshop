package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.ShoeSize;
import com.swp.shoeshop.utils.dbutil.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Size DAO Implementation
 * @author 
 */
public class SizeDAOImpl {
    
    private static final String GET_ALL_SIZES = "SELECT id, size_name, size_type FROM size ORDER BY id";
    private static final String GET_SIZE_BY_ID = "SELECT id, size_name, size_type FROM size WHERE id = ?";
    private static final String CREATE_SIZE = "INSERT INTO size (size_name, size_type) VALUES (?, ?)";
    private static final String UPDATE_SIZE = "UPDATE size SET size_name = ?, size_type = ? WHERE id = ?";
    private static final String DELETE_SIZE = "DELETE FROM size WHERE id = ?";
    private static final String CHECK_SIZE_IN_USE = "SELECT COUNT(*) FROM product_size WHERE size_id = ?";
    private static final String CHECK_DUPLICATE_SIZE = "SELECT COUNT(*) FROM size WHERE size_name = ? AND size_type = ?";
    
    /**
     * Get all sizes
     * @return list of all sizes
     * @throws SQLException if database error occurs
     */
    public List<ShoeSize> getAllSizes() throws SQLException {
        List<ShoeSize> sizes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_SIZES);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    ShoeSize size = new ShoeSize(
                        rs.getInt("id"),
                        rs.getString("size_name"),
                        rs.getString("size_type"),
                        0, // sortOrder - database không có cột này
                        true // isActive - database không có cột này
                    );
                    sizes.add(size);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error getting all sizes: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return sizes;
    }
    
    /**
     * Get size by ID
     * @param id size ID
     * @return ShoeSize object or null if not found
     * @throws SQLException if database error occurs
     */
    public ShoeSize getSizeById(int id) throws SQLException {
        ShoeSize size = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_SIZE_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    size = new ShoeSize(
                        rs.getInt("id"),
                        rs.getString("size_name"),
                        rs.getString("size_type"),
                        0, // sortOrder - database không có cột này
                        true // isActive - database không có cột này
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error getting size by ID: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return size;
    }
    
    /**
     * Create new size
     * @param size ShoeSize object to create
     * @return true if creation successful
     * @throws SQLException if database error occurs
     */
    public boolean createSize(ShoeSize size) throws SQLException {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CREATE_SIZE);
                ps.setString(1, size.getSizeName());
                ps.setString(2, size.getSizeType());
                
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error creating size: " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return success;
    }
    
    /**
     * Update existing size
     * @param size ShoeSize object with updated information
     * @return true if update successful
     * @throws SQLException if database error occurs
     */
    public boolean updateSize(ShoeSize size) throws SQLException {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_SIZE);
                ps.setString(1, size.getSizeName());
                ps.setString(2, size.getSizeType());
                ps.setInt(3, size.getId());
                
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error updating size: " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return success;
    }
    
    /**
     * Delete size by ID
     * @param id size ID to delete
     * @return true if deletion successful
     * @throws SQLException if database error occurs
     */
    public boolean deleteSize(int id) throws SQLException {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE_SIZE);
                ps.setInt(1, id);
                
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error deleting size: " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return success;
    }
    
    /**
     * Check if size is being used in product_size table
     * @param id size ID to check
     * @return true if size is in use
     * @throws SQLException if database error occurs
     */
    public boolean isSizeInUse(int id) throws SQLException {
        boolean inUse = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_SIZE_IN_USE);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    inUse = rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error checking if size is in use: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return inUse;
    }
    
    /**
     * Check if size name and type combination already exists
     * @param sizeName size name
     * @param sizeType size type
     * @return true if duplicate exists
     * @throws SQLException if database error occurs
     */
    public boolean isDuplicateSize(String sizeName, String sizeType) throws SQLException {
        boolean duplicate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_DUPLICATE_SIZE);
                ps.setString(1, sizeName);
                ps.setString(2, sizeType);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    duplicate = rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error checking duplicate size: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return duplicate;
    }
}
