/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.constant.ProductStatus;
import com.swp.shoeshop.model.Product;
import com.swp.shoeshop.utils.dbutil.DBConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class ProductDAOImpl {

    private static final String GET_ALL_PRODUCT = "SELECT [id] ,[name] ,[image] ,[original_price] ,[description] FROM product WHERE status ='INSTOCK' OR status='OUTOFSTOCK'";
    private static final String GET_LATEST_PRODUCT = "SELECT TOP 4 [id] ,[name] ,[image] FROM product ORDER BY [created_date] DESC";
    private static final String GET_PRODUCTS_BY_CATETYPE = "SELECT [product].[id] ,[product].[name] ,[image] ,[original_price] ,[discount_price], [point], [cate_id], [status] \n"
            + "FROM [product]\n"
            + "INNER JOIN [category] \n"
            + "ON [product].[cate_id] = [category].[id] AND [category].[type] = ?\n"
            + "WHERE status = 'INSTOCK' OR status = 'OUTOFSTOCK'";
    private static final String GET_PRODUCTS_BY_CATEID = "SELECT [product].[id] ,[product].[name] ,[image] ,[original_price] ,[discount_price] ,[cate_id] ,[status], [point] "
            + "FROM [product] WHERE [cate_id] = ?";    private static final String DETAIL_OF_PRODUCT = "SELECT [id] ,[name] ,[image] ,[original_price] ,[discount_price] ,[description], [short_description] ,[point] ,[cate_id] ,[status] \n"
            + "FROM [product] WHERE id = ?";
    private static final String GET_NAME_OF_PRODUCT = "SELECT name FROM product WHERE id=?";
    private static final String VIEW_ALL_PRODUCT = "SELECT [id], [name], [image], [original_price], [discount_price], [description], [short_description], [point], [cate_id], [created_date], [status], [isHot] FROM product";
    private static final String GET_LIST_PRODUCT = "SELECT [id], [name], [image], [original_price], [discount_price], [point], [created_date], [status] FROM product WHERE name like ?";
    private static final String CREATE_PRODUCT = "INSERT INTO product(name, image, original_price, discount_price, description, short_description, point, cate_id, status, isHot) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, image = ?, original_price = ?, discount_price = ?, description = ?, short_description = ?, point = ?, cate_id = ?, status = ? WHERE id = ?";
    private static final String DELETE_PRODUCT = "UPDATE product SET status= 'DISCONTINUED' WHERE id = ?";
    private static final String CHECK_DUPLICATE_PRO = "SELECT name FROM product WHERE name = ?";
    private static final String SORT_PRO = "SELECT [id], [name], [image], [original_price], [discount_price], [description], [short_description], [point], [cate_id], [created_date], [status], [isHot] FROM product ORDER BY %s %s";
    private static final String UPDATE_HOT_PRODUCT = "UPDATE product SET isHot = ? WHERE id = ?";
    private static final String TOP7_HOT_PRODUCTS = "SELECT TOP 7 [product].[id] ,[product].[name] ,[image] ,[original_price] ,[discount_price] ,[cate_id] ,[status], [point] \n"
            + "FROM [product]\n"
            + "INNER JOIN [category] \n"
            + "ON [product].[cate_id] = [category].[id] AND [category].[type] = ? WHERE [product].isHot = 1";
    private static final String TOTAL_PRODUCTS = "SELECT COUNT(*) FROM product";
    private static final String GET_FULL_PRODUCTS = "SELECT [product].[id] ,[product].[name] ,[image] ,[original_price] ,[discount_price] ,[cate_id] ,[status], [point], [short_description] FROM [product] WHERE status ='INSTOCK' OR status='OUTOFSTOCK'";

    // Methods for quantity management using product_size table
    private static final String GET_TOTAL_QUANTITY_BY_PRODUCT_ID
        = "SELECT SUM(quantity) as total_quantity FROM product_size WHERE product_id = ? AND is_available = 1";

    private static final String GET_QUANTITY_BY_PRODUCT_AND_SIZE
        = "SELECT quantity FROM product_size WHERE product_id = ? AND size_id = ? AND is_available = 1";

    private static final String UPDATE_QUANTITY_BY_PRODUCT_SIZE
        = "UPDATE product_size SET quantity = ? WHERE product_id = ? AND size_id = ?";

    private static final String REDUCE_QUANTITY_BY_PRODUCT_SIZE
        = "UPDATE product_size SET quantity = quantity - ? WHERE product_id = ? AND size_id = ? AND quantity >= ?";

    /**
     * Get total available quantity for a product across all sizes
     *
     * @param productId the product ID
     * @return total quantity available
     */
    public int getTotalQuantityByProductId(int productId) throws SQLException {
        int totalQuantity = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_TOTAL_QUANTITY_BY_PRODUCT_ID);
                ps.setInt(1, productId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    totalQuantity = rs.getInt("total_quantity");
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
        return totalQuantity;
    }

    /**
     * Get quantity for a specific product size combination
     *
     * @param productId the product ID
     * @param sizeId the size ID
     * @return quantity available for that size
     */
    public int getQuantityByProductAndSize(int productId, int sizeId) throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_QUANTITY_BY_PRODUCT_AND_SIZE);
                ps.setInt(1, productId);
                ps.setInt(2, sizeId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    /**
     * Update quantity for a specific product size
     *
     * @param productId the product ID
     * @param sizeId the size ID
     * @param newQuantity the new quantity
     * @return true if update successful
     */
    public boolean updateQuantityByProductSize(int productId, int sizeId, int newQuantity) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_QUANTITY_BY_PRODUCT_SIZE);
                ps.setInt(1, newQuantity);
                ps.setInt(2, productId);
                ps.setInt(3, sizeId);
                checkUpdate = ps.executeUpdate() > 0;
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
        return checkUpdate;
    }

    /**
     * Reduce quantity for a specific product size (used when processing orders)
     *
     * @param productId the product ID
     * @param sizeId the size ID
     * @param quantityToReduce the quantity to reduce
     * @return true if reduction successful
     */
    public boolean reduceQuantityByProductSize(int productId, int sizeId, int quantityToReduce) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(REDUCE_QUANTITY_BY_PRODUCT_SIZE);
                ps.setInt(1, quantityToReduce);
                ps.setInt(2, productId);
                ps.setInt(3, sizeId);
                ps.setInt(4, quantityToReduce); // Ensure we have enough quantity
                checkUpdate = ps.executeUpdate() > 0;
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
        return checkUpdate;
    }

    /**
     * Reverts (adds back) quantity to a specific product size when an order is canceled
     */
    public boolean revertQuantityByProductSize(int productId, int sizeId, int quantity) throws SQLException {
        String sql = "UPDATE product_size SET quantity = quantity + ? WHERE product_id = ? AND size_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setInt(2, productId);
                ps.setInt(3, sizeId);
                success = ps.executeUpdate() > 0;
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
        return success;
    }

    /**
     * Backward compatibility method for reverting quantity
     * Note: This doesn't work correctly with the new size-based system
     * Use revertQuantityByProductSize() instead
     */
    @Deprecated
    public boolean revertQuantity(int quantity, int productId) throws SQLException {
        // This is problematic because we don't know which size to revert to
        // For now, let's just log a warning and return true to avoid breaking existing code
        System.err.println("WARNING: revertQuantity() called without size information for product " + productId + 
                          ". This method is deprecated. Use revertQuantityByProductSize() instead.");
        return true;
    }

    public List<Product> getAllProduct() throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        try (Connection conn = DBConnect.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_ALL_PRODUCT);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                listProduct.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("original_price"),
                        rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getLatestProduct() {
        List<Product> listProduct = new ArrayList<>();
        try (Connection conn = DBConnect.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_LATEST_PRODUCT);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                listProduct.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        0,
                        0
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getProductsByCateType(String type) throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_PRODUCTS_BY_CATETYPE);
                ps.setString(1, type);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listProduct.add(new Product(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getInt("original_price"),
                            rs.getInt("discount_price"),
                            rs.getInt("point"),
                            rs.getInt("cate_id"),
                            rs.getString("status")
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
        return listProduct;
    }

    public List<Product> getProductsByCateID(int id) throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_PRODUCTS_BY_CATEID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listProduct.add(new Product(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getInt("original_price"),
                            rs.getInt("discount_price"),
                            rs.getInt("point"),
                            rs.getInt("cate_id"),
                            rs.getString("status")));
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
        return listProduct;
    }

    public Product detailOfOneProduct(int pid) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DETAIL_OF_PRODUCT);
                ps.setInt(1, pid);                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    int originalPrice = rs.getInt("original_price");
                    int discountPrice = rs.getInt("discount_price");
                    String description = rs.getString("description");
                    String shortdescript = rs.getString("short_description");
                    int point = rs.getInt("point");
                    int cateID = rs.getInt("cate_id");
                    String status = rs.getString("status");

                    product = new Product(id, name, image, originalPrice, discountPrice, description, shortdescript, point, cateID, status);
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

        return product;
    }

    public String getProductName(int productId) throws SQLException {
        String name = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_NAME_OF_PRODUCT);
                ps.setInt(1, productId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
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
        return name;
    }

    public List<Product> viewAllProduct() throws SQLException {
        List<Product> pro = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(VIEW_ALL_PRODUCT);
                rs = ps.executeQuery();                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    int originalPrice = rs.getInt("original_price");
                    int discountPrice = rs.getInt("discount_price");
                    String description = rs.getString("description");
                    String shortdescript = rs.getString("short_description");
                    int point = rs.getInt("point");
                    int cateId = rs.getInt("cate_id");
                    Date createdDate = rs.getDate("created_date");
                    ProductStatus status = ProductStatus.valueOf(rs.getString("status"));
                    boolean hot = rs.getBoolean("isHot");

                    pro.add(new Product(id, name, image, originalPrice, discountPrice, description, shortdescript, point, cateId, createdDate, status, hot));
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
        return pro;
    }

    public List<Product> searchProductByName(String name) throws SQLException {
        List<Product> searchList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_LIST_PRODUCT);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nameProduct = rs.getString("name");
                    String image = rs.getString("image");
                    int originalPrice = rs.getInt("original_price");
                    int discountPrice = rs.getInt("discount_price");
                    int point = rs.getInt("point");
                    Date createdDate = rs.getDate("created_date");
                    ProductStatus status = ProductStatus.valueOf(rs.getString("status"));

                    searchList.add(new Product(id, nameProduct, image, originalPrice, discountPrice, point, createdDate, status));
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
        return searchList;
    }

    public boolean addProduct(Product product) throws SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CREATE_PRODUCT);
                ps.setString(1, product.getName());
                ps.setString(2, product.getImage());
                ps.setInt(3, product.getOriginalPrice());
                ps.setInt(4, product.getDiscountPrice());
                ps.setString(5, product.getDescription());
                ps.setString(6, product.getShortDescription());
                ps.setInt(7, product.getPoint());
                ps.setInt(8, product.getCateId());
                ps.setString(9, product.getStatus().toString());
                ps.setBoolean(10, product.isHot());
                checkInsert = ps.executeUpdate() > 0 ? true : false;
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
        return checkInsert;
    }

    public boolean updateProduct(Product product) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DETAIL_OF_PRODUCT);
                ps.setInt(1, product.getId());
                rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");                    String image = rs.getString("image");
                    int originalPrice = rs.getInt("original_price");
                    int discountPrice = rs.getInt("discount_price");
                    String description = rs.getString("description");
                    String shortDescription = rs.getString("short_description");
                    int point = rs.getInt("point");
                    int cateId = rs.getInt("cate_id");
                    String status = rs.getString("status");

                    if (!name.equals(product.getName()) || !image.equals(product.getImage())
                            || originalPrice != product.getOriginalPrice() || discountPrice != product.getDiscountPrice()
                            || !description.equals(product.getDescription()) || !shortDescription.equals(product.getShortDescription())
                            || point != product.getPoint() || cateId != product.getCateId()
                            || !status.equals(product.getStatus().toString())) {

                        if (ps != null) {
                            ps.close();
                        }
                        ps = conn.prepareStatement(UPDATE_PRODUCT);
                        ps.setString(1, product.getName());
                        ps.setString(2, product.getImage());
                        ps.setInt(3, product.getOriginalPrice());
                        ps.setInt(4, product.getDiscountPrice());
                        ps.setString(5, product.getDescription());
                        ps.setString(6, product.getShortDescription());
                        ps.setInt(7, product.getPoint());
                        ps.setInt(8, product.getCateId());
                        ps.setString(9, product.getStatus().toString());
                        ps.setInt(10, product.getId());
                        checkUpdate = ps.executeUpdate() > 0;
                    }
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
        return checkUpdate;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE_PRODUCT);
                ps.setInt(1, id);
                checkUpdate = ps.executeUpdate() > 0 ? true : false;
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
        return checkUpdate;
    }

    public boolean checkDuplicateProductName(String name) throws SQLException {
        boolean checkExist = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_DUPLICATE_PRO);
                ps.setString(1, name);
                rs = ps.executeQuery();
                if (rs.next()) {
                    checkExist = true;
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
        return checkExist;
    }

    public List<Product> sort(String column, String sortOrder) throws SQLException {
        List<Product> sortList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                String query = String.format(SORT_PRO, column, sortOrder);
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    int originalPrice = rs.getInt("original_price");
                    int discountPrice = rs.getInt("discount_price");
                    String description = rs.getString("description");
                    String shortdescript = rs.getString("short_description");
                    int point = rs.getInt("point");
                    int cateId = rs.getInt("cate_id");
                    Date createdDate = rs.getDate("created_date");
                    ProductStatus status = ProductStatus.valueOf(rs.getString("status"));
                    boolean hot = rs.getBoolean("isHot");

                    sortList.add(new Product(id, name, image, originalPrice, discountPrice, description, shortdescript, point, cateId, createdDate, status, hot));
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
        return sortList;
    }

    public boolean updateHotProduct(int productId, boolean isHot) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_HOT_PRODUCT);
                ps.setBoolean(1, isHot);
                ps.setInt(2, productId);
                checkUpdate = ps.executeUpdate() > 0;
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
        return checkUpdate;
    }

    public List<Product> getTop7HotProductsByType(String type) throws SQLException {
        List<Product> listHotProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(TOP7_HOT_PRODUCTS);
                ps.setString(1, type);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listHotProduct.add(new Product(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getInt("original_price"),
                            rs.getInt("discount_price"),
                            rs.getInt("point"),
                            rs.getInt("cate_id"),
                            rs.getString("status")));
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
        return listHotProduct;
    }

    public int getTotalProducts() throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(TOTAL_PRODUCTS);
                rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
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
        return count;
    }

    public List<Product> getFullProducts() throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {                ps = conn.prepareStatement(GET_FULL_PRODUCTS);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = new Product(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getInt("original_price"),
                            rs.getInt("discount_price"),
                            rs.getInt("point"),
                            rs.getInt("cate_id"),
                            rs.getString("status"));
                    product.setShortDescription(rs.getString("short_description"));
                    listProduct.add(product);
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
        return listProduct;
    }

    /**
     * Gets the total quantity across all sizes for a product
     * Note: This method is for backward compatibility. 
     * New code should use getQuantityByProductAndSize() for specific size checking.
     */
    public int getQuantity(int productId) throws SQLException {
        return getTotalQuantityByProductId(productId);
    }

    /**
     * Get top 7 hot products by category type
     */
    public List<Product> getTop7HotProducts(String categoryType) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(TOP7_HOT_PRODUCTS);
                ps.setString(1, categoryType);
                rs = ps.executeQuery();                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    int originalPrice = rs.getInt("original_price");
                    int discountPrice = rs.getInt("discount_price");
                    int cateId = rs.getInt("cate_id");
                    String status = rs.getString("status");
                    int point = rs.getInt("point");
                    products.add(new Product(id, name, image, originalPrice, discountPrice, point, cateId, status));
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
        return products;
    }

    /**
     * Check if product name already exists (for duplicate checking)
     */
    public boolean checkDuplicate(String productName) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_DUPLICATE_PRO);
                ps.setString(1, productName);
                rs = ps.executeQuery();
                exists = rs.next();
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
        return exists;
    }

    /**
     * Create a new product
     */
    public boolean createProduct(Product product) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();            if (conn != null) {
                ps = conn.prepareStatement(CREATE_PRODUCT);
                ps.setString(1, product.getName());
                ps.setString(2, product.getImage());
                ps.setInt(3, product.getOriginalPrice());
                ps.setInt(4, product.getDiscountPrice());
                ps.setString(5, product.getDescription());
                ps.setString(6, product.getShortDescription());
                ps.setInt(7, product.getPoint());
                ps.setInt(8, product.getCateId());
                ps.setString(9, product.getStatus().toString());
                ps.setBoolean(10, product.isHot());
                success = ps.executeUpdate() > 0;
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
        return success;
    }

    /**
     * Update an existing product
     */
    public boolean checkUpdate(Product product) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_PRODUCT);
                ps.setString(1, product.getName());
                ps.setString(2, product.getImage());
                ps.setInt(3, product.getOriginalPrice());
                ps.setInt(4, product.getDiscountPrice());
                ps.setString(5, product.getDescription());
                ps.setString(6, product.getShortDescription());
                ps.setInt(7, product.getPoint());
                ps.setInt(8, product.getCateId());
                ps.setString(9, product.getStatus().toString());
                ps.setInt(10, product.getId());
                success = ps.executeUpdate() > 0;
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
        return success;
    }

    /**
     * Update hot product status
     */
    public boolean checkHot(boolean isHot, int productId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_HOT_PRODUCT);
                ps.setBoolean(1, isHot);
                ps.setInt(2, productId);
                success = ps.executeUpdate() > 0;
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
        return success;
    }

    /**
     * Get product name by ID
     */
    public String getName(int productId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_NAME_OF_PRODUCT);
                ps.setInt(1, productId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
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
        return name;
    }

    /**
     * Get list of products by name search
     */
    public List<Product> getListProductbyName(String productName) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_LIST_PRODUCT);
                ps.setString(1, "%" + productName + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    int originalPrice = rs.getInt("original_price");
                    int discountPrice = rs.getInt("discount_price");
                    int point = rs.getInt("point");
                    Date createdDate = rs.getDate("created_date");
                    String status = rs.getString("status");
                    products.add(new Product(id, name, image, originalPrice, discountPrice, point, createdDate, ProductStatus.valueOf(status)));
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
        return products;
    }

    /**
     * Delete (mark as discontinued) a product
     */
    public boolean delete(int productId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE_PRODUCT);
                ps.setInt(1, productId);
                success = ps.executeUpdate() > 0;
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
        return success;
    }

    /**
     * Get total number of products
     */
    public int getTotalProduct() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(TOTAL_PRODUCTS);
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
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
        return total;
    }

    /**
     * Get all products with total quantity from product_size table
     * @return list of products with calculated total quantity
     * @throws SQLException if database error occurs
     */
    public List<Product> viewAllProductWithQuantity() throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT p.id, p.name, p.image, p.original_price, p.discount_price, p.description, " +
                    "p.short_description, p.point, p.cate_id, p.created_date, p.status, p.isHot, " +
                    "COALESCE(SUM(ps.quantity), 0) as total_quantity " +
                    "FROM product p " +
                    "LEFT JOIN product_size ps ON p.id = ps.product_id AND ps.is_available = 1 " +
                    "GROUP BY p.id, p.name, p.image, p.original_price, p.discount_price, p.description, " +
                    "p.short_description, p.point, p.cate_id, p.created_date, p.status, p.isHot " +
                    "ORDER BY p.id";
        
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    // Create Product with all fields including the calculated quantity
                    Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("original_price"),
                        rs.getInt("discount_price"),
                        rs.getString("description"),
                        rs.getString("short_description"),
                        rs.getInt("point"),
                        rs.getInt("cate_id"),
                        rs.getDate("created_date"),
                        ProductStatus.valueOf(rs.getString("status")),
                        rs.getBoolean("isHot")
                    );
                    
                    // Set the total quantity as a transient field
                    // We'll add this field to the Product model
                    product.setQuantity(rs.getInt("total_quantity"));
                    
                    listProduct.add(product);
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
        return listProduct;
    }
}
