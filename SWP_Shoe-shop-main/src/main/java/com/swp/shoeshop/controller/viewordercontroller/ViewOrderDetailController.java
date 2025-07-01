/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swp.shoeshop.controller.viewordercontroller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.swp.shoeshop.dao.impldao.OrderDAOImpl;
import com.swp.shoeshop.model.Order;
import java.util.ArrayList;

/**
 *
 * @author LamVHCE171024
 */
@WebServlet(name = "ViewOrderDetailController", urlPatterns = {"/ViewOrderDetailController"})
public class ViewOrderDetailController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "order_detail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            OrderDAOImpl dao = new OrderDAOImpl();
            List<Order> detail = dao.viewOrderDetail(id);
            String status = dao.getStatusOfOrder(id); //lấy status của order, nếu là PROCESSING mới hiện nút xác nhận/hủy
           Order orderInfo = dao.getOrderByID(id);
            request.setAttribute("ORDER_INFO", orderInfo);
            if (detail.size() > 0) {
                request.setAttribute("VIEW_ORDER_DETAIL", detail);
                HttpSession session = request.getSession();
                session.setAttribute("VIEW_ORDER_DETAIL", detail);
                session.setAttribute("ID", id);
                request.setAttribute("STATUS", status);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewOrderDetailController at: " + e.toString());
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
