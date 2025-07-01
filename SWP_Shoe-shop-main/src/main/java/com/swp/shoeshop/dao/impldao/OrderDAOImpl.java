/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.Order;
import com.swp.shoeshop.utils.dbutil.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.swp.shoeshop.constant.OrderStatus;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Types;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 
 */
public class OrderDAOImpl {

    private static final String INSERT_ORDER = "INSERT INTO [order] (user_id, name, [total], [phone_number], [address], [email], [payment_id], [status] ,all_points ,apply_points) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO [order_detail] (order_id, product_id, price, quantity, size_id) "
            + "VALUES ((SELECT TOP 1 id FROM [order] ORDER BY id DESC),?,?,?,?)";
    private static final String VIEW_ORDER = "SELECT * FROM [order] WHERE status like ?";
    private static final String VIEW_ORDER_DETAIL = "SELECT pro.name, detail.product_id, detail.quantity, detail.price, detail.size_id, ss.size_name " +
            "FROM [order_detail] detail " +
            "INNER JOIN [product] pro ON detail.product_id = pro.id " +
            "LEFT JOIN [shoe_size] ss ON detail.size_id = ss.id " +
            "WHERE detail.order_id = ?";
    private static final String UPDATE_ORDERSTATUS_FOR_ADMIN = "UPDATE [order] SET [status] = ? WHERE id = ?";
    private static final String GET_STATUS_OF_ORDER = "SELECT [status] FROM [order] WHERE id = ?";
    private static final String GET_POINTS_AND_APPLYPOINT_OF_ORDER = "SELECT [all_points] ,[apply_points] FROM [order] WHERE id = ?";
    private static final String GET_USERID_OF_ORDER_ID = "SELECT [user_id] FROM [order] WHERE id = ?";
    private static final String VIEW_ALL_ORDER = "SELECT * FROM [order]";
    private static final String VIEW_ORDER_FOR_USER = "SELECT * FROM [order] WHERE user_id =?";
    private static final String VIEW_ORDERDETAIL_FOR_USER = "SELECT product.[name], order_detail.[product_id], order_detail.quantity, order_detail.price, order_detail.size_id, ss.size_name, [order].[status]\n"
            + "FROM order_detail " +
            "INNER JOIN product ON order_detail.product_id = product.id " +
            "INNER JOIN [order] ON order_detail.order_id = [order].id " +
            "LEFT JOIN [shoe_size] ss ON order_detail.size_id = ss.id " +
            "WHERE [order].id = ?";

    private static final String GET_ORDER = "SELECT [user_id], [name], [total], [date], [phone_number], [address], [email], [payment_id], [status] ,[all_points], [apply_points] FROM [swp_birdshop].[dbo].[order] WHERE id = ?";
    private static final String SEARCH_ORDER = "SELECT id, user_id, name, total, date, phone_number, address, email, payment_id, status, all_points, apply_points FROM [order] WHERE id=? OR name like N'%' + ? + N'%' ";
    private static final String SORT_ORDER = "SELECT id, user_id, name, total, date, phone_number, address, email, payment_id, status, all_points, apply_points FROM [order] ORDER BY %s %s";
    private static final String TOTAL_SUCCESS_ORDERS = "SELECT COUNT(*) FROM [order] WHERE status = 'SUCCESS'";
    private static final String TOTAL_SALES = "SELECT SUM(total) FROM [order] WHERE status = 'SUCCESS'";
    private static final String CHART_SALES = "SELECT DAY(date) AS date, SUM(total) AS total\n"
            + "FROM [order]\n"
            + "WHERE MONTH(date) = ? AND status = 'SUCCESS'\n"
            + "GROUP BY DAY(date)";
    private static final String DAILY_SALE = "SELECT CONVERT(date, date) AS day, SUM(total) AS total \n"
            + "FROM [order] \n"
            + "WHERE status = 'SUCCESS' \n"
            + "GROUP BY CONVERT(date, date)";
    private static final String WEEKLY_SALE = "SELECT DATEADD(WEEK, DATEDIFF(WEEK, 0, [date]), 0) AS week_start_date, SUM(total) AS total \n"
            + "FROM [order]\n"
            + "WHERE status = 'SUCCESS'\n"
            + "GROUP BY DATEADD(WEEK, DATEDIFF(WEEK, 0, [date]), 0)";
    private static final String MONTHLY_SALE = "SELECT DATEFROMPARTS(YEAR(date), MONTH(date), 1) AS monthly, \n"
            + "SUM(total) AS total \n"
            + "FROM [order] \n"
            + "WHERE status = 'SUCCESS'\n"
            + "GROUP BY YEAR(date), MONTH(date), DATEFROMPARTS(YEAR(date), MONTH(date), 1)\n"
            + "ORDER BY DATEFROMPARTS(YEAR(date), MONTH(date), 1) ASC";

    private static final String ANNUALLY_SALE = "SELECT DATEFROMPARTS(YEAR(date),1,1) AS year, SUM(total) AS total\n"
            + "FROM [order]\n"
            + "WHERE status='SUCCESS'\n"
            + "GROUP BY DATEFROMPARTS(YEAR(date),1,1)";
    private static final String GET_BESTSELLERS = "SELECT TOP 5\n"
            + "detail.product_id, SUM(detail.price*detail.quantity) AS total, SUM(detail.quantity) AS sold\n"
            + "FROM order_detail detail, [order] o\n"
            + "WHERE o.status = 'SUCCESS' AND o.id = detail.order_id\n"
            + "GROUP BY product_id\n"
            + "ORDER BY total DESC";
    private static final String UPDATE_DATE = "UPDATE [order] SET date=? WHERE id = ?";

    public boolean addOrder(Order order) throws SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_ORDER);
                Integer userId = order.getUserId();
                if (userId != null) { //trường hợp login user thì userId khác null
                    ps.setInt(1, userId);
                } else { //trường hợp user ko login thì userId = null
                    ps.setNull(1, Types.INTEGER);
                }
                ps.setString(2, order.getUsername());
                ps.setInt(3, order.getTotal());
                ps.setString(4, order.getPhone());
                ps.setString(5, order.getAddress());
                ps.setString(6, order.getEmail());
                ps.setInt(7, order.getPaymentId());
                ps.setString(8, order.getStatus());
                ps.setInt(9, order.getTotalPoints());
                ps.setString(10, order.getApplyPoints());

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

    public boolean addOrderDetail(Order order) throws SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_ORDER_DETAIL);
                ps.setInt(1, order.getProductId());
                ps.setInt(2, order.getPrice());
                ps.setInt(3, order.getQuantity());
                ps.setInt(4, order.getSizeId());
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

    public List<Order> viewOrder(OrderStatus status) throws SQLException {
        List<Order> order = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(VIEW_ORDER);
                ps.setString(1, status.toString());
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userid = rs.getInt("user_id");
                    String name = rs.getString("name");
                    int total = rs.getInt("total");
                    Date date = rs.getDate("date");
                    String phone = rs.getString("phone_number");
                    int pId = rs.getInt("payment_id");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    order.add(new Order(id, userid, total, date, name, phone, address, email, pId, status));
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
        return order;
    }

    public List<Order> viewOrderDetail(int id) throws SQLException {
        List<Order> detail = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(VIEW_ORDER_DETAIL);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int proid = rs.getInt("product_id");
                    String proname = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    int sizeId = rs.getInt("size_id");
                    String sizeName = rs.getString("size_name");
                    detail.add(new Order(id, proid, proname, quantity, price, sizeId, sizeName));
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
        return detail;
    }

    public List<Order> viewOrderDetailForUser(int OrderId) throws SQLException {
        List<Order> detail = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(VIEW_ORDERDETAIL_FOR_USER);
                ps.setInt(1, OrderId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int proid = rs.getInt("product_id");
                    String proname = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    int sizeId = rs.getInt("size_id");
                    String sizeName = rs.getString("size_name");
                    String status = rs.getString("status");
                    detail.add(new Order(OrderId, proid, proname, quantity, price, sizeId, sizeName, status));
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
        return detail;
    }

    public boolean updateStatusOfOrder(OrderStatus status, int orderId) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_ORDERSTATUS_FOR_ADMIN);
                ps.setString(1, status.toString());
                ps.setInt(2, orderId);
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

    public String getStatusOfOrder(int orderID) throws SQLException {
        String status = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_STATUS_OF_ORDER);
                ps.setInt(1, orderID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    status = rs.getString("status");
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
        return status;
    }

    public Order getPointsAndApplyPointOfOrder(int orderID) throws SQLException {
        Order order = new Order();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_POINTS_AND_APPLYPOINT_OF_ORDER);
                ps.setInt(1, orderID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int totalPoints = rs.getInt("all_points");
                    String applyPoint = rs.getString("apply_points");
                    order = new Order(totalPoints, applyPoint);
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
        return order;
    }

    public int getAccountIDOfOrder(int orderID) throws SQLException {
        Integer userID = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_USERID_OF_ORDER_ID);
                ps.setInt(1, orderID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    userID = rs.getInt("user_id"); //nếu user ko login thì userID = 0 dù trong DB là null
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
        return userID;
    }

    public List<Order> viewAllOrder() throws SQLException {
        List<Order> order = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(VIEW_ALL_ORDER);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userid = rs.getInt("user_id");
                    String name = rs.getString("name");
                    int total = rs.getInt("total");
                    Date date = rs.getDate("date");
                    String phone = rs.getString("phone_number");
                    int pId = rs.getInt("payment_id");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    OrderStatus status = OrderStatus.valueOf(rs.getString("status"));
                    Integer totalPoints = rs.getInt("all_points");
                    String applyPoints = rs.getString("apply_points");
                    order.add(new Order(id, userid, total, date, name, phone, address, email, pId, status, totalPoints, applyPoints));
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
        return order;
    }

    public List<Order> viewOrderForUser(int accountId) throws SQLException {
        List<Order> order = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(VIEW_ORDER_FOR_USER);
                ps.setInt(1, accountId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userid = rs.getInt("user_id");
                    String name = rs.getString("name");
                    int total = rs.getInt("total");
                    Date date = rs.getDate("date");
                    String phone = rs.getString("phone_number");
                    int pId = rs.getInt("payment_id");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    OrderStatus status = OrderStatus.valueOf(rs.getString("status"));
                    Integer totalPoints = rs.getInt("all_points");
                    String applyPoints = rs.getString("apply_points");
                    order.add(new Order(id, userid, total, date, name, phone, address, email, pId, status, totalPoints, applyPoints));
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
        return order;
    }

    public Order getOrderByID(int id) throws SQLException {
        Order order = new Order();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ORDER);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    order.setId(id);
                    order.setUserId(rs.getInt("user_id"));
                    order.setUsername(rs.getString("name"));
                    order.setTotal(rs.getInt("total"));
                    order.setDate(rs.getDate("date"));
                    order.setPhone(rs.getString("phone_number"));
                    order.setPaymentId(rs.getInt("payment_id"));
                    order.setEmail(rs.getString("email"));
                    order.setAddress(rs.getString("address"));
                    order.setStatus(rs.getString("status"));
                    order.setApplyPoints(rs.getString("apply_points"));
                    order.setTotalPoints(rs.getInt("all_points"));
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
        return order;
    }

    public List<Order> sort(String column, String sortOrder) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                String sql = String.format(SORT_ORDER, column, sortOrder);
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userid = rs.getInt("user_id");
                    String name = rs.getString("name");
                    int total = rs.getInt("total");
                    Date date = rs.getDate("date");
                    String phone = rs.getString("phone_number");
                    int pId = rs.getInt("payment_id");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    OrderStatus status = OrderStatus.valueOf(rs.getString("status"));
                    Integer totalPoints = rs.getInt("all_points");
                    String applyPoints = rs.getString("apply_points");
                    orderList.add(new Order(id, userid, total, date, name, phone, address, email, pId, status, totalPoints, applyPoints));
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
        return orderList;
    }

    public List<Order> getListOrderbyNameorID(String search) throws SQLException {
        List<Order> searchList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SEARCH_ORDER);
                try {
                    int orderId = Integer.parseInt(search);
                    ps.setInt(1, orderId);
                    ps.setString(2, search);
                } catch (NumberFormatException e) {
                    ps.setInt(1, 0);
                    ps.setString(2, "%" + search + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userid = rs.getInt("user_id");
                    String name = rs.getString("name");
                    int total = rs.getInt("total");
                    Date date = rs.getDate("date");
                    String phone = rs.getString("phone_number");
                    int pId = rs.getInt("payment_id");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    OrderStatus status = OrderStatus.valueOf(rs.getString("status"));
                    Integer totalPoints = rs.getInt("all_points");
                    String applyPoints = rs.getString("apply_points");
                    searchList.add(new Order(id, userid, total, date, name, phone, address, email, pId, status, totalPoints, applyPoints));
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

    public int getTotalOrder() throws SQLException {
        int total = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(TOTAL_SUCCESS_ORDERS);
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

    public int getTotalSales() throws SQLException {
        int total = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(TOTAL_SALES);
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

    public Map<String, List<Integer>> getTotalbyDay(int month) throws SQLException {
        YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), Month.of(month));
        int maxDays = yearMonth.lengthOfMonth();
        List<Integer> labels = new ArrayList<>();
        int index = 0;
        for (int i = 1; i <= maxDays; i++) {
            labels.add(i);
        }
        List<Integer> values = new ArrayList<>(Collections.nCopies(maxDays, 0));
        Map<String, List<Integer>> resultMap = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHART_SALES);
                ps.setInt(1, month);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int date = rs.getInt("date");
                    index = date - 1;
                    values.set(index, rs.getInt("total"));
                }
                resultMap.put("labels", labels);
                resultMap.put("values", values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return resultMap;
    }

    public List<Order> getDailySale() throws SQLException {
        List<Order> values = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DAILY_SALE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Date date = rs.getTimestamp("day");
                    int total = rs.getInt("total");
                    values.add(new Order(total, date));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return values;
    }

    public List<Order> getWeeklySale() throws SQLException {
        List<Order> values = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(WEEKLY_SALE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Date date = rs.getTimestamp("week_start_date");
                    int total = rs.getInt("total");
                    values.add(new Order(total, date));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return values;
    }

    public List<Order> getMonthlySale() throws SQLException {
        List<Order> values = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(MONTHLY_SALE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Date date = rs.getTimestamp("monthly");
                    int total = rs.getInt("total");
                    values.add(new Order(total, date));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return values;
    }

    public List<Order> getAnnuallySale() throws SQLException {
        List<Order> values = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ANNUALLY_SALE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Date date = rs.getDate("year");
                    int total = rs.getInt("total");
                    values.add(new Order(total, date));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return values;
    }

    public List<Order> getBestsellers() throws SQLException {
        List<Order> bestsellers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_BESTSELLERS);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int pId = rs.getInt("product_id");
                    int total = rs.getInt("total");
                    int sold = rs.getInt("sold");
                    bestsellers.add(new Order(pId, sold, total));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return bestsellers;
    }

    public boolean updateDateOfOrder(int orderId) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_DATE);
                Timestamp date = new Timestamp(System.currentTimeMillis());
                ps.setTimestamp(1, date);
                ps.setInt(2, orderId);
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

}
