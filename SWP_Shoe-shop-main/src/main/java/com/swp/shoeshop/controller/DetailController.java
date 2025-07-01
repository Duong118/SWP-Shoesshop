/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller;

import com.swp.shoeshop.dao.impldao.ProductDAOImpl;
import com.swp.shoeshop.dao.impldao.ProductSizeDAOImpl;
import com.swp.shoeshop.model.Product;
import com.swp.shoeshop.model.ProductSize;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author linh
 */
public class DetailController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "shop-detail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        List<Product> listRelatedProducts = null;
        try {
            String tmp = request.getParameter("pid");
            int productID = 0;
            if (tmp == null) {
                Integer proID = (Integer) session.getAttribute("PROID");
                productID = proID.intValue();
            } else {
                productID = Integer.parseInt(tmp);
            }
            ProductDAOImpl dao = new ProductDAOImpl();
            Product product = dao.detailOfOneProduct(productID);
            request.setAttribute("PRODUCT_DETAIL", product);
            session.setAttribute("PRODUCT", product);
            session.setAttribute("PROID", productID);
            
            // Load product sizes using DAO
            ProductSizeDAOImpl productSizeDAO = new ProductSizeDAOImpl();
            List<ProductSize> productSizes = productSizeDAO.getProductSizesByProductId(productID);
            request.setAttribute("PRODUCT_SIZES", productSizes);
            
            url = SUCCESS;

            url = SUCCESS;

//>>>>>>> dev
        } catch (Exception e) {
            log("Error at DetailController: " + e.toString());
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
