/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.ProductSize;
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
 * @author Thuong
 */
public class ProductSizeDAOImpl {
      private static final String GET_PRODUCT_SIZES_BY_PRODUCT_ID = 
        "SELECT ps.id, ps.product_id, ps.size_id, s.size_name, s.size_type, ps.quantity, ps.is_available " +
        "FROM product_size ps " +
        "JOIN [size] s ON ps.size_id = s.id " +
        "WHERE ps.product_id = ? AND ps.is_available = 1 " +
        "ORDER BY s.size_name";
    
    private static final String GET_PRODUCT_SIZE_BY_ID = 
        "SELECT ps.id, ps.product_id, ps.size_id, s.size_name, s.size_type, ps.quantity, ps.is_available " +
        "FROM product_size ps " +
        "JOIN [size] s ON ps.size_id = s.id " +
        "WHERE ps.id = ?";
    
    private static final String GET_PRODUCT_SIZE_BY_PRODUCT_AND_SIZE = 
        "SELECT ps.id, ps.product_id, ps.size_id, s.size_name, s.size_type, ps.quantity, ps.is_available " +
        "FROM product_size ps " +
        "JOIN [size] s ON ps.size_id = s.id " +
        "WHERE ps.product_id = ? AND ps.size_id = ?";
    
    private static final String INSERT_PRODUCT_SIZE = 
        "INSERT INTO product_size (product_id, size_id, quantity, is_available) VALUES (?, ?, ?, ?)";
    
    private static final String UPDATE_PRODUCT_SIZE = 
        "UPDATE product_size SET quantity = ?, is_available = ? WHERE id = ?";
    
    private static final String UPDATE_PRODUCT_SIZE_QUANTITY = 
        "UPDATE product_size SET quantity = ? WHERE id = ?";
    
    private static final String DELETE_PRODUCT_SIZE = 
        "UPDATE product_size SET is_available = 0 WHERE id = ?";
    
    private static final String CHECK_PRODUCT_SIZE_EXISTS = 
        "SELECT COUNT(*) FROM product_size WHERE product_id = ? AND size_id = ?";
    
    /**
     * Get all product sizes for a specific product
     * @param productId the product ID
     * @return list of ProductSize objects
     */
    public List<ProductSize> getProductSizesByProductId(int productId) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ProductSize> productSizes = new ArrayList<>();
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_SIZES_BY_PRODUCT_ID);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                
                while (rs.next()) {
                    ProductSize ps = mapResultSetToProductSize(rs);
                    productSizes.add(ps);
                }
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error getting product sizes by product ID: " + ex.getMessage());
        } finally {
            closeResources(rs, ptm, conn);
        }
        
        return productSizes;
    }
    
    /**
     * Get a specific product size by ID
     * @param productSizeId the product size ID
     * @return ProductSize object or null if not found
     */
    public ProductSize getProductSizeById(int productSizeId) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ProductSize productSize = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_SIZE_BY_ID);
                ptm.setInt(1, productSizeId);
                rs = ptm.executeQuery();
                
                if (rs.next()) {
                    productSize = mapResultSetToProductSize(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error getting product size by ID: " + ex.getMessage());
        } finally {
            closeResources(rs, ptm, conn);
        }
        
        return productSize;
    }
    
    /**
     * Get product size by product ID and size ID
     * @param productId the product ID
     * @param sizeId the size ID
     * @return ProductSize object or null if not found
     */
    public ProductSize getProductSizeByProductAndSize(int productId, int sizeId) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ProductSize productSize = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_SIZE_BY_PRODUCT_AND_SIZE);
                ptm.setInt(1, productId);
                ptm.setInt(2, sizeId);
                rs = ptm.executeQuery();
                
                if (rs.next()) {
                    productSize = mapResultSetToProductSize(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error getting product size by product and size: " + ex.getMessage());
        } finally {
            closeResources(rs, ptm, conn);
        }
        
        return productSize;
    }
    
    /**
     * Add a new product size
     * @param productSize the ProductSize object to add
     * @return true if successful, false otherwise
     */
    public boolean addProductSize(ProductSize productSize) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_PRODUCT_SIZE);
                ptm.setInt(1, productSize.getProductId());
                ptm.setInt(2, productSize.getSizeId());
                ptm.setInt(3, productSize.getQuantity());
                ptm.setBoolean(4, productSize.isAvailable());
                
                result = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error adding product size: " + ex.getMessage());
        } finally {
            closeResources(null, ptm, conn);
        }
        
        return result;
    }
    
    /**
     * Update a product size
     * @param productSize the ProductSize object to update
     * @return true if successful, false otherwise
     */
    public boolean updateProductSize(ProductSize productSize) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT_SIZE);
                ptm.setInt(1, productSize.getQuantity());
                ptm.setBoolean(2, productSize.isAvailable());
                ptm.setInt(3, productSize.getId());
                
                result = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error updating product size: " + ex.getMessage());
        } finally {
            closeResources(null, ptm, conn);
        }
        
        return result;
    }
    
    /**
     * Update quantity of a product size
     * @param productSizeId the product size ID
     * @param quantity the new quantity
     * @return true if successful, false otherwise
     */
    public boolean updateProductSizeQuantity(int productSizeId, int quantity) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT_SIZE_QUANTITY);
                ptm.setInt(1, quantity);
                ptm.setInt(2, productSizeId);
                
                result = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error updating product size quantity: " + ex.getMessage());
        } finally {
            closeResources(null, ptm, conn);
        }
        
        return result;
    }
    
    /**
     * Delete (deactivate) a product size
     * @param productSizeId the product size ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteProductSize(int productSizeId) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT_SIZE);
                ptm.setInt(1, productSizeId);
                
                result = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error deleting product size: " + ex.getMessage());
        } finally {
            closeResources(null, ptm, conn);
        }
        
        return result;
    }
    
    /**
     * Check if a product size combination already exists
     * @param productId the product ID
     * @param sizeId the size ID
     * @return true if exists, false otherwise
     */
    public boolean isProductSizeExists(int productId, int sizeId) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean exists = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_PRODUCT_SIZE_EXISTS);
                ptm.setInt(1, productId);
                ptm.setInt(2, sizeId);
                rs = ptm.executeQuery();
                
                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error checking product size exists: " + ex.getMessage());
        } finally {
            closeResources(rs, ptm, conn);
        }
        
        return exists;
    }
    
    /**
     * Map ResultSet to ProductSize object
     * @param rs the ResultSet
     * @return ProductSize object
     * @throws SQLException if SQL error occurs
     */
    private ProductSize mapResultSetToProductSize(ResultSet rs) throws SQLException {
        ProductSize ps = new ProductSize();
        ps.setId(rs.getInt("id"));
        ps.setProductId(rs.getInt("product_id"));
        ps.setSizeId(rs.getInt("size_id"));
        ps.setSizeName(rs.getString("size_name"));
        ps.setSizeType(rs.getString("size_type"));
        ps.setQuantity(rs.getInt("quantity"));
        ps.setAvailable(rs.getBoolean("is_available"));
        return ps;
    }
    
    /**
     * Close database resources
     * @param rs ResultSet to close
     * @param ptm PreparedStatement to close
     * @param conn Connection to close
     */
    private void closeResources(ResultSet rs, PreparedStatement ptm, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
        }
    }
}
