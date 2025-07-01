package com.swp.shoeshop.controller;

import com.swp.shoeshop.constant.Regex;
import com.swp.shoeshop.dao.impldao.AccountDAOImpl;
import com.swp.shoeshop.model.Account;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.bytebuddy.utility.RandomString;

@WebServlet(urlPatterns = "/update")
public class UpdateProfileController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "user-profile.jsp";
    private static final String VERIFY = "verify_code.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            AccountDAOImpl accDAO = new AccountDAOImpl();
            HttpSession session = request.getSession();
            Object attribute = session.getAttribute("NEW_MAIL");
            String newMail = "";
            boolean checkUpdate = false;
            if (attribute != null && attribute instanceof String) {
                newMail = (String) attribute;
            }
            if (!newMail.isEmpty()) {
                checkUpdate = accDAO.updateEmail(newMail, code);
            }
            if (checkUpdate) {
                request.setAttribute("MESSAGE", "Cập nhật email thành công");
                url = VERIFY;
                Account account = (Account) session.getAttribute("LOGIN_USER");
                account.setEmail(newMail);
                session.setAttribute("LOGIN_USER", account);
            } else {
                request.setAttribute("MESSAGE", "Mã xác nhận đã hết hạn, vui lòng thử lại");
                url = VERIFY;
            }

        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String fullName = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            Pattern mailPattern = Pattern.compile(Regex.MAIL_PATTERN);
            if (!mailPattern.matcher(email).find()) {
                request.setAttribute("ERROR", "Định dạng email không hợp lệ");
                url = SUCCESS;
                return;
            }

            AccountDAOImpl accDAO = new AccountDAOImpl();
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");

            if (email != null && !account.getEmail().equals(email)) {
                String randomCode = RandomString.make(40);
                boolean check = accDAO.updateProfile(fullName, phone, address, randomCode, id);

                // Không gửi email, chỉ cập nhật session và thông báo
                session.setAttribute("NEW_MAIL", email);

                account.setFullName(fullName);
                account.setPhone(phone);
                account.setAddress(address);
                session.setAttribute("LOGIN_USER", account);
                request.setAttribute("SUCCESS", "Cập nhật thành công. (Đang chờ xác nhận email mới)");
                url = SUCCESS;
            } else {
                accDAO.updateProfile(fullName, phone, address, null, id);
                account.setFullName(fullName);
                account.setPhone(phone);
                account.setAddress(address);
                session.setAttribute("LOGIN_USER", account);
                request.setAttribute("SUCCESS", "Cập nhật thành công");
                url = SUCCESS;
            }

        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
