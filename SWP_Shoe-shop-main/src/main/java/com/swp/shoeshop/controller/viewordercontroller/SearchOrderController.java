/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller.viewordercontroller;

import com.swp.shoeshop.dao.impldao.OrderDAOImpl;
import com.swp.shoeshop.model.Order;
import com.swp.shoeshop.utils.stringutil.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author linh
 */
@WebServlet(name = "SearchOrderController", urlPatterns = {"/SearchOrderController"})

public class SearchOrderController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "order.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            OrderDAOImpl dao = new OrderDAOImpl();
            List<Order> listOrd = dao.viewAllOrder();
            String search = request.getParameter("search").toLowerCase();
            String searchfilter = StringUtil.removeAccent(request.getParameter("search")).trim().toLowerCase();
            if (search == null || search.trim().isEmpty()) {
                request.setAttribute("VIEW_ORDER", listOrd);
                request.getSession().setAttribute("VIEW_ORDER_EMPTY", null);
                url = SUCCESS;
            } else {
                List<Order> listOrdFilter = new ArrayList<>();
                if (search.equals(searchfilter) &&  (!search.matches("\\d+"))) {
                    for (Order product : listOrd) {
                        String name = StringUtil.removeAccent(product.getUsername()).toLowerCase();
                        if (name.contains(search)) {
                            listOrdFilter.add(product);
                        }
                    }
                } else  listOrdFilter = dao.getListOrderbyNameorID(search);
                if (listOrdFilter.isEmpty()) {
                    request.getSession().setAttribute("VIEW_ORDER_EMPTY", "Không tìm thấy kết quả nào phù hợp hết .-.");
                    request.getSession().setAttribute("VIEW_ORDER", null);
                    url = SUCCESS;
                } else {
                    request.setAttribute("VIEW_ORDER", listOrdFilter);
                    request.getSession().setAttribute("VIEW_ORDER_EMPTY", null);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at SearchOrderController: " + e.toString());
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
