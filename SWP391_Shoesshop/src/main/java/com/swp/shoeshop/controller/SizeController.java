/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swp.shoeshop.controller;

import com.swp.shoeshop.dao.impldao.ShoeSizeDAOImpl;
import com.swp.shoeshop.dao.impldao.ProductSizeDAOImpl;
import com.swp.shoeshop.model.ShoeSize;
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
 * @author Admin
 */
@WebServlet(name = "SizeController", urlPatterns = {"/SizeController"})
public class SizeController extends HttpServlet {

    private static final String MANAGE_SIZE = "size-manager.jsp";
    private static final String GET_PRODUCT_SIZES = "GetProductSizesController";    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = MANAGE_SIZE;
        
        try {
            if ("addSize".equals(action)) {
                addSize(request);
                url = MANAGE_SIZE;
            } else if ("deleteSize".equals(action)) {
                deleteSize(request);
                url = MANAGE_SIZE;
            } else if ("getProductSizes".equals(action)) {
                getProductSizes(request);
                url = GET_PRODUCT_SIZES;
            }
            
            // Always load all sizes for display
            loadAllSizes(request);
        } catch (Exception ex) {
            log("Error at SizeController: " + ex.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }
    
    private void addSize(HttpServletRequest request) {
        String sizeName = request.getParameter("sizeName");
        String sizeType = request.getParameter("sizeType");
        int sortOrder = Integer.parseInt(request.getParameter("sortOrder"));
        
        ShoeSizeDAOImpl sizeDAO = new ShoeSizeDAOImpl();
        
        // Check for duplicate size name
        if (sizeDAO.isDuplicateSizeName(sizeName)) {
            request.setAttribute("ERROR", "Size name already exists!");
            return;
        }
        
        ShoeSize shoeSize = new ShoeSize();
        shoeSize.setSizeName(sizeName);
        shoeSize.setSizeType(sizeType);
        shoeSize.setSortOrder(sortOrder);
        shoeSize.setActive(true);
        
        boolean result = sizeDAO.addShoeSize(shoeSize);
        if (result) {
            request.setAttribute("MESSAGE", "Size added successfully!");
        } else {
            request.setAttribute("ERROR", "Failed to add size!");
        }
    }
    
    private void deleteSize(HttpServletRequest request) {
        int sizeId = Integer.parseInt(request.getParameter("id"));
        
        ShoeSizeDAOImpl sizeDAO = new ShoeSizeDAOImpl();
        boolean result = sizeDAO.deleteShoeSize(sizeId);
        
        if (result) {
            request.setAttribute("MESSAGE", "Size deleted successfully!");
        } else {
            request.setAttribute("ERROR", "Failed to delete size!");
        }
    }
    
    private void getProductSizes(HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        
        ProductSizeDAOImpl productSizeDAO = new ProductSizeDAOImpl();
        List<ProductSize> productSizes = productSizeDAO.getProductSizesByProductId(productId);
        
        request.setAttribute("PRODUCT_SIZES", productSizes);
    }
    
    private void loadAllSizes(HttpServletRequest request) {
        ShoeSizeDAOImpl sizeDAO = new ShoeSizeDAOImpl();
        List<ShoeSize> sizes = sizeDAO.getAllActiveShoeSizes();
        request.setAttribute("SHOE_SIZES", sizes);
    }    @Override
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
