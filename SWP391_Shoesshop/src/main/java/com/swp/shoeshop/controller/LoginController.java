/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
 *
 * @author Linh
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
            boolean valid = false;
            //         valid = VerifyRecaptcha.verify(gRecaptchaResponse);

            AccountDAOImpl dao = new AccountDAOImpl();
            Account loginUser = dao.checkLogin(userName, password);

            if (loginUser != null && valid == true) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);

                if (remember != null) { //nếu nhấn vào nút remember

                    Cookie user = new Cookie("n4m3", request.getParameter("txtUserName"));
                    user.setMaxAge(3 * 24 * 60 * 60); //lưu 3 ngày
                    response.addCookie(user);
                }

                boolean isAdmin = loginUser.isIsAdmin();
                if (isAdmin) {
                    url = ADMIN_PAGE;
                } else {
                    url = USER_PAGE;
                }
            } else if (valid == false) {
                request.setAttribute("LOGIN_ERROR", "Chưa xác minh reCAPTCHA");
            } else {
                request.setAttribute("LOGIN_ERROR", "Sai mật khẩu hoặc tài khoản không tồn tại");
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
