/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.dao.impldao;

import com.swp.shoeshop.model.Account;
import com.swp.shoeshop.utils.dbutil.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LamVHCE171024
 */
public class AccountDAOImpl {

    private static final String LOGIN = "SELECT id, full_name, [phone], [address], email, points, isAdmin FROM dbo.account WHERE user_name = ? AND password = ?";
    private static final String REMEMBER_ME = "SELECT id, user_name, email, points, isAdmin, full_name, phone, address FROM dbo.account WHERE user_name = ?";
    private static final String SHOW_USER = "SELECT id, email, points, isAdmin FROM dbo.account WHERE user_name like ?";
    private static final String CHECK_ACCOUNT = "SELECT user_name, email FROM account WHERE user_name = ? OR email = ?";
    private static final String SIGNUP = "INSERT INTO account(user_name, password, email, points, isAdmin) VALUES(?,?,?,0,0)";
    private static final String ADD_POINT_WHEN_ORDER_SUCCESS = "UPDATE account SET points = points + ? WHERE id = ?";
    private static final String CLEAR_POINT_IF_USER_APPLY_POINT = "UPDATE account SET points = 0 WHERE id = ?";
    private static final String UPDATE_PROFILE_OF_USER = "UPDATE account SET full_name = ?, phone = ?, [address] = ?, [verification_code] = ? WHERE id = ?";
    private static final String UPDATE_EMAIL_BY_VERIFYCODE = "UPDATE account SET email = ? WHERE verification_code = ?";
    private static final String LOGIN_GOOGLE = "SELECT * FROM dbo.account WHERE user_name = ? AND email = ?";
    private static final String SIGNUP_GOOGLE = "INSERT INTO account(user_name, email, points, isAdmin) VALUES(?,?,0,0)";
    private static final String TOTAL_USERS = "SELECT COUNT(id) FROM account";
    private static final String RESET_PASSWORD = "SELECT * FROM account WHERE [user_name] =? AND email =?";
    private static final String SET_RANDOM_PASS_TO_RESET_PASS = "UPDATE account SET password = ? WHERE [user_name] = ? AND email = ?";
    private static final String CHANGE_PASSWORD = "UPDATE account SET password = ? WHERE id = ?";
    private static final String GET_PASSWORD = "SELECT [password] FROM account WHERE id = ?";

    public Account checkLogin(String username, String password) throws SQLException {
        Account acc = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(LOGIN);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String fullName = rs.getString("full_name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int points = rs.getInt("points");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    acc = new Account(id, username, "****", email, fullName, phone, address, points, isAdmin);
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
        return acc;
    }

    public Account checkLoginGoogle(String username, String email) throws SQLException {
        Account acc = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(LOGIN_GOOGLE);
                ps.setString(1, username);
                ps.setString(2, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("full_name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int points = rs.getInt("points");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    acc = new Account(id, username, null, email, fullName, phone, address, points, isAdmin);
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
        return acc;
    }

    public Account rememberMe(String username) throws SQLException {
        Account acc = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(REMEMBER_ME);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    int points = rs.getInt("points");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    String fullName = rs.getString("full_name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    acc = new Account(id, username, "***", email, fullName, phone, address, points, isAdmin);
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
        return acc;
    }

    public List<Account> getListUser(String search) throws SQLException {
        List<Account> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SHOW_USER);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("user_name");
                    String email = rs.getString("email");
                    int points = rs.getInt("points");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    list.add(new Account(id, username, "***", email, points, isAdmin));
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
        return list;
    }

    public Account checkAccount(String userName, String email) throws SQLException {
        Account acc = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_ACCOUNT);
                ps.setString(1, userName);
                ps.setString(2, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String userNameDB = rs.getString("user_name");
                    String emailDB = rs.getString("email");
                    acc = new Account(0, userNameDB, null, emailDB, 0, false, null);
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
        return acc;
    }

    public void signup(String userName, String email, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SIGNUP);
                ps.setString(1, userName);
                ps.setString(2, password);
                ps.setString(3, email);
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

    public void signupGoogle(String userName, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SIGNUP_GOOGLE);
                ps.setString(1, userName);
                ps.setString(2, email);
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

    public boolean addPoints(int points, int accountID) throws SQLException {
        boolean checkAdd = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD_POINT_WHEN_ORDER_SUCCESS);
                ps.setInt(1, points);
                ps.setInt(2, accountID);

                checkAdd = ps.executeUpdate() > 0 ? true : false;
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
        return checkAdd;
    }

    public void clearPoints(int accountID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CLEAR_POINT_IF_USER_APPLY_POINT);
                ps.setInt(1, accountID);
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

    public boolean updateProfile(String fullName, String phone, String address, String verificationCode, int userID) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_PROFILE_OF_USER);
                ps.setString(1, fullName);
                ps.setString(2, phone);
                ps.setString(3, address);
                ps.setString(4, verificationCode);
                ps.setInt(5, userID);
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

    public boolean updateEmail(String newMail, String code) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_EMAIL_BY_VERIFYCODE);
                ps.setString(1, newMail);
                ps.setString(2, code);
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

    public int getTotalUser() throws SQLException {
        int total = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(TOTAL_USERS);
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

    public Account resetPass(String username, String email) throws SQLException {
        Account acc = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(RESET_PASSWORD);
                ps.setString(1, username);
                ps.setString(2, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String accEmail = rs.getString("email");
                    String fullName = rs.getString("full_name");
                    String password = rs.getString("password");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int points = rs.getInt("points");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    acc = new Account(id, username, password, accEmail, fullName, phone, address, points, isAdmin);
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
        return acc;
    }

    public boolean updateRandomPass(String newPassword, String username, String email) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SET_RANDOM_PASS_TO_RESET_PASS);
                ps.setString(1, newPassword);
                ps.setString(2, username);
                ps.setString(3, email);
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

    public boolean changePassword(String newPassword, int accountId) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHANGE_PASSWORD);
                ps.setString(1, newPassword);
                ps.setInt(2, accountId);
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

    public String getPassword(int accountId) throws SQLException {
        String password = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_PASSWORD);
                ps.setInt(1, accountId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    password = rs.getString("password");
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
        return password;
    }
}
