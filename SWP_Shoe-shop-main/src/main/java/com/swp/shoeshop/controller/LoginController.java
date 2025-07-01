package com.swp.shoeshop.controller;

import com.swp.shoeshop.dao.impldao.AccountDAOImpl;
import com.swp.shoeshop.model.Account;
//import com.swp.shoeshop.utils.recaptcha.VerifyRecaptcha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author linh
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADMIN_PAGE = "Dashboard";
    private static final String USER_PAGE = "HomeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            String userName = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            String remember = request.getParameter("remember");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

            AccountDAOImpl dao = new AccountDAOImpl();
            Account loginUser = dao.checkLogin(userName, password);

            if (loginUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);

                if (remember != null) {
                    Cookie user = new Cookie("n4m3", userName);
                    user.setMaxAge(3 * 24 * 60 * 60); // lưu trong 3 ngày
                    response.addCookie(user);
                }

                if (loginUser.isIsAdmin()) {
                    url = ADMIN_PAGE;
                } else {
                    url = USER_PAGE;
                }
            } else {
                request.setAttribute("LOGIN_ERROR", "Sai mật khẩu hoặc tài khoản không tồn tại");
            }
        } catch (Exception e) {
            // Có thể log lỗi ra console hoặc file
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // Xử lý phương thức GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Xử lý phương thức POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login Controller Servlet";
    }
}
