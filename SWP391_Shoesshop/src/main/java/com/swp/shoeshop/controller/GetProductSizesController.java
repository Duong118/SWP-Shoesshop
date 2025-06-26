/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swp.shoeshop.controller;

import com.swp.shoeshop.dao.impldao.ProductSizeDAOImpl;
import com.swp.shoeshop.model.ProductSize;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thuong
 */
@WebServlet(name = "GetProductSizesController", urlPatterns = {"/GetProductSizesController"})
public class GetProductSizesController extends HttpServlet {    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            ProductSizeDAOImpl productSizeDAO = new ProductSizeDAOImpl();
            List<ProductSize> productSizes = productSizeDAO.getProductSizesByProductId(productId);
            request.setAttribute("PRODUCT_SIZES", productSizes);
            
        } catch (Exception ex) {
            log("Error at GetProductSizesController: " + ex.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("shop-detail.jsp");
            rd.forward(request, response);
        }    }

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
}
