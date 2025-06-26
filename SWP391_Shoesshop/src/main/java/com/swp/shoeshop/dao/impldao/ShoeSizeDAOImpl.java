/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.ShoeSize;
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
public class ShoeSizeDAOImpl {    private static final String GET_ALL_SIZES = "SELECT [id], [size_name], [size_type], [sort_order], [is_active] FROM shoe_size WHERE is_active = 1 ORDER BY sort_order";
    private static final String GET_SIZE_BY_ID = "SELECT [id], [size_name], [size_type], [sort_order], [is_active] FROM shoe_size WHERE id = ?";
    private static final String INSERT_SIZE = "INSERT INTO shoe_size (size_name, size_type, sort_order, is_active) VALUES (?, ?, ?, 1)";
    private static final String UPDATE_SIZE = "UPDATE shoe_size SET size_name = ?, size_type = ?, sort_order = ? WHERE id = ?";
    private static final String DELETE_SIZE = "UPDATE shoe_size SET is_active = 0 WHERE id = ?";
    private static final String CHECK_DUPLICATE_SIZE = "SELECT size_name FROM shoe_size WHERE size_name = ? AND size_type = ? AND is_active = 1";
    private static final String INSERT_SHOE_SIZE = "INSERT INTO shoe_size (size_name, size_type, sort_order, is_active) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_ACTIVE_SHOE_SIZES = "SELECT * FROM shoe_size WHERE is_active = 1 ORDER BY sort_order";
    private static final String CHECK_DUPLICATE_SIZE_NAME = "SELECT COUNT(*) FROM shoe_size WHERE size_name = ? AND is_active = 1";
    private static final String DELETE_SHOE_SIZE = "UPDATE shoe_size SET is_active = 0 WHERE id = ?";

    public List<ShoeSize> getAllSizes() throws SQLException, ClassNotFoundException, NamingException {
        List<ShoeSize> sizeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_SIZES);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String sizeName = rs.getString("size_name");
                    String sizeType = rs.getString("size_type");
                    int sortOrder = rs.getInt("sort_order");
                    boolean isActive = rs.getBoolean("is_active");

                    ShoeSize size = new ShoeSize(id, sizeName, sizeType, sortOrder, isActive);
                    sizeList.add(size);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return sizeList;
    }

    public ShoeSize getSizeById(int sizeId) throws SQLException, ClassNotFoundException, NamingException {
        ShoeSize size = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SIZE_BY_ID);
                ptm.setInt(1, sizeId);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String sizeName = rs.getString("size_name");
                    String sizeType = rs.getString("size_type");
                    int sortOrder = rs.getInt("sort_order");
                    boolean isActive = rs.getBoolean("is_active");

                    size = new ShoeSize(id, sizeName, sizeType, sortOrder, isActive);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return size;
    }

    public boolean insertSize(ShoeSize size) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_SIZE);
                ptm.setString(1, size.getSizeName());
                ptm.setString(2, size.getSizeType());
                ptm.setInt(3, size.getSortOrder());

                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return check;
    }

    public boolean updateSize(ShoeSize size) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_SIZE);
                ptm.setString(1, size.getSizeName());
                ptm.setString(2, size.getSizeType());
                ptm.setInt(3, size.getSortOrder());
                ptm.setInt(4, size.getId());

                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return check;
    }

    public boolean deleteSize(int sizeId) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_SIZE);
                ptm.setInt(1, sizeId);

                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return check;
    }

    public boolean checkDuplicateSize(String sizeName, String sizeType) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_SIZE);
                ptm.setString(1, sizeName);
                ptm.setString(2, sizeType);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return check;
    }

    /**
     * Add a new shoe size
     *
     * @param shoeSize the ShoeSize object to add
     * @return true if successful, false otherwise
     */
    public boolean addShoeSize(ShoeSize shoeSize) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_SHOE_SIZE);
                ptm.setString(1, shoeSize.getSizeName());
                ptm.setString(2, shoeSize.getSizeType());
                ptm.setInt(3, shoeSize.getSortOrder());
                ptm.setBoolean(4, shoeSize.isActive());

                result = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error adding shoe size: " + ex.getMessage());
        } finally {
            try {
                if (ptm != null) ptm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Get all active shoe sizes ordered by sort_order
     *
     * @return list of active ShoeSize objects
     */
    public List<ShoeSize> getAllActiveShoeSizes() {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ShoeSize> shoeSizes = new ArrayList<>();

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_ACTIVE_SHOE_SIZES);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    ShoeSize shoeSize = mapResultSetToShoeSize(rs);
                    shoeSizes.add(shoeSize);
                }
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error getting all active shoe sizes: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ptm != null) ptm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return shoeSizes;
    }

    /**
     * Check if a size name already exists
     *
     * @param sizeName the size name to check
     * @return true if duplicate exists, false otherwise
     */
    public boolean isDuplicateSizeName(String sizeName) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_SIZE_NAME);
                ptm.setString(1, sizeName);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error checking duplicate size name: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ptm != null) ptm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    /**
     * Delete (deactivate) a shoe size
     *
     * @param sizeId the size ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteShoeSize(int sizeId) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;

        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_SHOE_SIZE);
                ptm.setInt(1, sizeId);

                result = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            System.err.println("Error deleting shoe size: " + ex.getMessage());
        } finally {
            try {
                if (ptm != null) ptm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private void closeResources(ResultSet rs, PreparedStatement ptm, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptm != null) {
                    ptm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ShoeSize mapResultSetToShoeSize(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String sizeName = rs.getString("size_name");
        String sizeType = rs.getString("size_type");
        int sortOrder = rs.getInt("sort_order");
        boolean isActive = rs.getBoolean("is_active");

        return new ShoeSize(id, sizeName, sizeType, sortOrder, isActive);
    }
}
