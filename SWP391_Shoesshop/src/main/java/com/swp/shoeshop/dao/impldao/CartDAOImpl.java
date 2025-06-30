/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.Cart;
import com.swp.shoeshop.model.CartItem;
import com.swp.shoeshop.model.Product;
import com.swp.shoeshop.utils.dbutil.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author TrucBQCE181355
 */
public class CartDAOImpl {

    private static final String SAVE_CART = "INSERT INTO cart (account_id) VALUES(?)";
    private static final String GET_CARTID_BY_USERID = "SELECT id FROM cart WHERE account_id = ?";
    private static final String SAVE_CARTITEM = "INSERT INTO cart_items (cart_id, product_id, amount) VALUES (?, ?, ?)";
    private static final String CHECK_CARTITEM = "SELECT product_id FROM cart_items  WHERE product_id = ? AND cart_id = ?";
    private static final String INCREASE_AND_UPDATE_AMOUNT_CARTITEM = "UPDATE cart_items SET amount = ? WHERE product_id = ? AND cart_id = ?";
    private static final String GET_CURENT_CARTITEM_AMOUNT = "SELECT amount FROM cart_items WHERE product_id = ? AND cart_id = ?";
    private static final String GET_CARTITEM_LIST = "SELECT p.id, p.[name], p.[image], p.[original_price], p.[discount_price], p.point, c.amount, c.size_id, s.size_name "
            + "FROM product p LEFT JOIN cart_items c ON p.id = c.product_id "
            + "LEFT JOIN [size] s ON c.size_id = s.id "
            + "WHERE c.cart_id = ?";
    private static final String CLEAR_CART_ITEMS = "DELETE FROM cart_items WHERE cart_id = ?";
    private static final String CLEAR_CART = "DELETE FROM cart WHERE account_id = ?";
    private static final String REMOVE_ONE_CARTITEM = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ?";

    // Constants for size-based cart operations
    private static final String SAVE_CARTITEM_WITH_SIZE = "INSERT INTO cart_items (cart_id, product_id, amount, size_id) VALUES (?, ?, ?, ?)";
    private static final String CHECK_CARTITEM_WITH_SIZE = "SELECT product_id FROM cart_items WHERE product_id = ? AND cart_id = ? AND size_id = ?";
    private static final String INCREASE_AND_UPDATE_AMOUNT_CARTITEM_WITH_SIZE = "UPDATE cart_items SET amount = ? WHERE product_id = ? AND cart_id = ? AND size_id = ?";
    private static final String GET_CURENT_CARTITEM_AMOUNT_WITH_SIZE = "SELECT amount FROM cart_items WHERE product_id = ? AND size_id = ? AND cart_id = ?";
    private static final String REMOVE_ONE_CARTITEM_WITH_SIZE = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ? AND size_id = ?";

    public ArrayList<CartItem> getCartItemList(int cartID) throws SQLException {
        ArrayList<CartItem> cartItemList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_CARTITEM_LIST);
                ps.setInt(1, cartID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    int originalPrice = rs.getInt("original_price");
                    int discountPrice = rs.getInt("discount_price");
                    int point = rs.getInt("point");
                    int amount = rs.getInt("amount");
                    int sizeId = rs.getInt("size_id");
                    String sizeName = rs.getString("size_name");
                    cartItemList.add(new CartItem(new Product(id, name, image, originalPrice, discountPrice, point), amount, sizeId, sizeName));
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
        return cartItemList;
    }

    public Cart getCartByAccID(int userID) throws SQLException {
        Cart cart = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_CARTID_BY_USERID);
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int cartID = rs.getInt("id");
                    cart = new Cart(cartID, userID, getCartItemList(cartID));
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
        return cart;
    }

    public int saveCart(int accountId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SAVE_CART);
                ps.setInt(1, accountId);
                ps.executeUpdate();
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
        return getCartIDByUserID(accountId);
    }

    private int getCartIDByUserID(int userID) throws SQLException {
        int cartID = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_CARTID_BY_USERID);
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    cartID = rs.getInt("id");
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
        return cartID;
    }

    public void saveCartItem(CartItem cartItem, int cartID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                boolean check = checkCartItem(cartItem.getProduct().getId(), cartID);
                if (check) {
                    increaseAmountCartItem(cartItem, cartID);
                } else {
                    ps = conn.prepareStatement(SAVE_CARTITEM);
                    ps.setInt(1, cartID);
                    ps.setInt(2, cartItem.getProduct().getId());
                    ps.setInt(3, cartItem.getAmount());
                    ps.executeUpdate();
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
    }

    /**
     * Save cart item with size support
     */
    public void saveCartItemWithSize(CartItem cartItem, int cartID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                boolean check = checkCartItemWithSize(cartItem.getProduct().getId(), cartItem.getSizeId(), cartID);
                if (check) {
                    increaseAmountCartItemWithSize(cartItem, cartID);
                } else {
                    ps = conn.prepareStatement(SAVE_CARTITEM_WITH_SIZE);
                    ps.setInt(1, cartID);
                    ps.setInt(2, cartItem.getProduct().getId());
                    ps.setInt(3, cartItem.getAmount());
                    ps.setInt(4, cartItem.getSizeId());
                    ps.executeUpdate();
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
    }

    private boolean checkCartItem(int prodID, int cartID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_CARTITEM);
                ps.setInt(1, prodID);
                ps.setInt(2, cartID);
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

    /**
     * Check if cart item with specific size exists
     */
    private boolean checkCartItemWithSize(int prodID, int sizeId, int cartID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_CARTITEM_WITH_SIZE);
                ps.setInt(1, prodID);
                ps.setInt(2, cartID);
                ps.setInt(3, sizeId);
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

    public void increaseAmountCartItem(CartItem cartItem, int cartID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int plusAmount = cartItem.getAmount();
        int totalAmount = getCurrentCartItemAmount(cartItem.getProduct().getId(), cartID) + plusAmount;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INCREASE_AND_UPDATE_AMOUNT_CARTITEM);
                ps.setInt(1, totalAmount);
                ps.setInt(2, cartItem.getProduct().getId());
                ps.setInt(3, cartID);
                ps.executeUpdate();

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
    }

    /**
     * Increase amount for cart item with size
     */
    public void increaseAmountCartItemWithSize(CartItem cartItem, int cartID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int plusAmount = cartItem.getAmount();
        int totalAmount = getCurrentCartItemAmountBySize(cartItem.getProduct().getId(), cartItem.getSizeId(), cartID) + plusAmount;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INCREASE_AND_UPDATE_AMOUNT_CARTITEM_WITH_SIZE);
                ps.setInt(1, totalAmount);
                ps.setInt(2, cartItem.getProduct().getId());
                ps.setInt(3, cartID);
                ps.setInt(4, cartItem.getSizeId());
                ps.executeUpdate();
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
    }

    public int getCurrentCartItemAmount(int prodID, int cartID) throws SQLException {
        int amount = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_CURENT_CARTITEM_AMOUNT);
                ps.setInt(1, prodID);
                ps.setInt(2, cartID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    amount = rs.getInt("amount");
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
        return amount;
    }

    /**
     * Get current cart item amount by product ID, size ID, and cart ID
     */
    public int getCurrentCartItemAmountBySize(int prodID, int sizeId, int cartID) throws SQLException {
        int amount = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_CURENT_CARTITEM_AMOUNT_WITH_SIZE);
                ps.setInt(1, prodID);
                ps.setInt(2, sizeId);
                ps.setInt(3, cartID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    amount = rs.getInt("amount");
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
        return amount;
    }

    public boolean clearCartItems(int cartID) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CLEAR_CART_ITEMS);
                ps.setInt(1, cartID);
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

    public boolean clearCart(int accountID) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CLEAR_CART);
                ps.setInt(1, accountID);
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

    public boolean removeOneCartItems(int cartID, int productID) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(REMOVE_ONE_CARTITEM);
                ps.setInt(1, cartID);
                ps.setInt(2, productID);
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

    public boolean removeOneCartItemsWithSize(int cartID, int productID, int sizeId) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(REMOVE_ONE_CARTITEM_WITH_SIZE);
                ps.setInt(1, cartID);
                ps.setInt(2, productID);
                ps.setInt(3, sizeId);
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

    public void updateAmount(int cartId, int productId, int amount) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INCREASE_AND_UPDATE_AMOUNT_CARTITEM);
                ps.setInt(1, amount);
                ps.setInt(2, productId);
                ps.setInt(3, cartId);
                ps.executeUpdate();

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
    }

    public void updateAmountWithSize(int cartId, int productId, int sizeId, int amount) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INCREASE_AND_UPDATE_AMOUNT_CARTITEM_WITH_SIZE);
                ps.setInt(1, amount);
                ps.setInt(2, productId);
                ps.setInt(3, cartId);
                ps.setInt(4, sizeId);
                ps.executeUpdate();
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
    }
}
