/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller;

import com.swp.shoeshop.constant.Regex;
import com.swp.shoeshop.dao.impldao.AccountDAOImpl;
import com.swp.shoeshop.model.Account;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 */
public class SignupController extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        AccountDAOImpl dao = new AccountDAOImpl();
        try {
            String userName = request.getParameter("txtRegisterUsername");
            String password = request.getParameter("txtRegisterPassword");
            String rePassword = request.getParameter("txtConfirmPassword");
            String email = request.getParameter("txtEmail");
            Pattern mailPattern = Pattern.compile(Regex.MAIL_PATTERN);
            
            Account account = dao.checkAccount(userName, email);
            
            if(password.equals(rePassword) && account == null && mailPattern.matcher(email).find()){
                dao.signup(userName, email, password);
                request.setAttribute("MESSAGE", "Đăng ký thành công");
                url = SUCCESS;
            }
            
            if(!mailPattern.matcher(email).find()) {
                request.setAttribute("SIGNUP_ERROR", "Định dạng email không hợp lệ");
            }
            
            if(password.compareTo(rePassword)!= 0){
            request.setAttribute("SIGNUP_ERROR", "Mật khẩu không khớp!"); 
            }
  
            if(account != null){
            request.setAttribute("SIGNUP_ERROR", "Tên tài khoản hoặc email đã tồn tại!"); 
            }
            
        } catch (Exception e) {
            log("Error at SigupController: " + e.toString());
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
