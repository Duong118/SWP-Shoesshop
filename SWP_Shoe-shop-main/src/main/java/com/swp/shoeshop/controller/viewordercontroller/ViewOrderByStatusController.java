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
 * @author Duong
 */
@WebServlet(name = "ViewOrderController", urlPatterns = {"/ViewOrderController"})
public class ViewOrderController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "order.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            OrderDAOImpl dao = new OrderDAOImpl();
            List<Order> orders;

            String statusParam = request.getParameter("status");
            if (statusParam != null && !statusParam.trim().isEmpty()) {
                OrderStatus status = OrderStatus.valueOf(statusParam);
                orders = dao.viewOrder(status);
            } else {
                orders = dao.viewAllOrder();
            }

            if (orders != null && !orders.isEmpty()) {
                request.getSession().setAttribute("VIEW_ORDER_EMPTY", null);
                request.setAttribute("VIEW_ORDER", orders);
                url = SUCCESS;
            } else {
                request.getSession().setAttribute("VIEW_ORDER_EMPTY", "Chưa có đơn hàng nào cả .-.");
                request.getSession().setAttribute("VIEW_ORDER", null);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewOrderController: " + e.toString());
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
        return "Controller to view orders (by status or all)";
    }
}
