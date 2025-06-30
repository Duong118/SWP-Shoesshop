/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller;

import com.swp.shoeshop.model.Product;
import com.swp.shoeshop.utils.json.JSONConverter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 */
public class SortByPriceController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "shop.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ServletContext context = getServletContext(); 
            List<Product> listProByCate = (List<Product>)context.getAttribute("LIST_PRODUCTS");
            String method = request.getParameter("method");
            if (method != null && method.equals("asc")) {
                Comparator compare = new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getDiscountPrice() - o2.getDiscountPrice();
                    }
                };
                Collections.sort(listProByCate, compare);
                request.setAttribute("VIEW_PRODUCTS", listProByCate);
                request.setAttribute("VIEW_PRODUCTS_JSON", JSONConverter.toJSON(listProByCate));
                url = SUCCESS;
            }

            if (method != null && method.equals("desc")) {
                Comparator compare = new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o2.getDiscountPrice() - o1.getDiscountPrice();
                    }
                };
                Collections.sort(listProByCate, compare);
                //Collections.sort(listProByCate, Collections.reverseOrder(compare));
                request.setAttribute("VIEW_PRODUCTS", listProByCate);
                request.setAttribute("VIEW_PRODUCTS_JSON", JSONConverter.toJSON(listProByCate));
                url = SUCCESS;
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
