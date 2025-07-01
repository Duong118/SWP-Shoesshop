/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swp.shoeshop.controller.viewordercontroller;

import com.swp.shoeshop.constant.OrderStatus;
import com.swp.shoeshop.dao.impldao.OrderDAOImpl;
import com.swp.shoeshop.model.Order;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 */
@WebServlet(name = "ViewOrderByStatusController", urlPatterns = {"/ViewOrderByStatusController"})

public class ViewOrderByStatusController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "order.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            OrderStatus status = OrderStatus.valueOf(request.getParameter("status"));
            OrderDAOImpl dao = new OrderDAOImpl();
            List<Order> o = dao.viewOrder(status);

            if (o.size() > 0) {
                request.getSession().setAttribute("VIEW_ORDER_EMPTY", null);
                request.setAttribute("VIEW_ORDER", o);
                url = SUCCESS;
            } else {
                request.getSession().setAttribute("VIEW_ORDER_EMPTY", "Chưa có đơn hàng nào cả .-.");
                request.getSession().setAttribute("VIEW_ORDER", null);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewOrderByStatusController: " + e.toString());
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
