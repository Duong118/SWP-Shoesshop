/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swp.shoeshop.controller.productcontroller;

import com.swp.shoeshop.dao.impldao.ProductDAOImpl;
import com.swp.shoeshop.model.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.swp.shoeshop.utils.stringutil.StringUtil;

/**
 *
 * @author Linh
 */
@WebServlet(name = "SearchProductController", urlPatterns = {"/SearchProductController"})
public class SearchProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAOImpl dao = new ProductDAOImpl();
            List<Product> listProd = dao.viewAllProduct();
            String search = request.getParameter("search").toLowerCase();
            String searchfilter = StringUtil.removeAccent(request.getParameter("search")).trim().toLowerCase();
            if (search == null || search.trim().isEmpty()) {
                request.setAttribute("LIST_PRODUCT", listProd);
                request.getSession().setAttribute("EMPTY_LIST", null);
                url = SUCCESS;
            } else {
                List<Product> listProdFilter = new ArrayList<>();
                if (search.equals(searchfilter)) {
                    for (Product product : listProd) {
                        String name = StringUtil.removeAccent(product.getName()).toLowerCase();
                        if (name.contains(search)) {
                            listProdFilter.add(product);
                        }
                    }
                } else {
                    listProdFilter = dao.getListProductbyName(search);
                }
                if (listProdFilter.isEmpty()) {
                    request.getSession().setAttribute("EMPTY_LIST", "No matching results found .-.");
                    request.getSession().setAttribute("LIST_PRODUCT", null);
                    url = SUCCESS;
                } else {
                    request.setAttribute("LIST_PRODUCT", listProdFilter);
                    request.getSession().setAttribute("EMPTY_LIST", null);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at SearchProductController: " + e.toString());
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
