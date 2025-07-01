package com.swp.shoeshop.controller;

import com.swp.shoeshop.dao.impldao.AccountDAOImpl;
import com.swp.shoeshop.model.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.bytebuddy.utility.RandomString;

public class ForgotPassController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "forgotpass.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");

            AccountDAOImpl accDAO = new AccountDAOImpl();
            Account account = accDAO.resetPass(username, email);

            if (account != null) {
                if (account.getPassword() == null || account.getPassword().isEmpty()) {
                    request.setAttribute("MESSAGE", "Google Account does not support this function");
                    url = SUCCESS;
                    return;
                }

                String newPassword = RandomString.make(20);
                boolean checkUpdate = accDAO.updateRandomPass(newPassword, username, email);

                if (checkUpdate) {
                    // Gửi email bị loại bỏ — có thể hiển thị mật khẩu mới nếu cần
                    request.setAttribute("MESSAGE", "Recovery successful. Your new password: " + newPassword);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Password reset failed. Please try again.");
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("MESSAGE", "Account name or email does not exist");
                url = SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("MESSAGE", "An error occurred: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Forgot Password Controller without reCAPTCHA and email sending.";
    }
}
