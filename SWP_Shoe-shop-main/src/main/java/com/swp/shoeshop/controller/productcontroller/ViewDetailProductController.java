/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swp.shoeshop.controller.productcontroller;

import com.swp.shoeshop.dao.impldao.CategoryDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.swp.shoeshop.dao.impldao.ProductDAOImpl;
import com.swp.shoeshop.model.Category;
import com.swp.shoeshop.model.Product;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author linh
 */
@WebServlet(name = "ViewDetailProductController", urlPatterns = {"/ViewDetailProductController"})
public class ViewDetailProductController extends HttpServlet {

    private static final String ERROR = "edit_product.jsp";
    private static final String SUCCESS = "edit_product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        CategoryDAOImpl cateDao = new CategoryDAOImpl();
        try {
            List<Category> cates = cateDao.viewCate();
            session.setAttribute("VIEW_CATE", cates);
            int id = Integer.parseInt(request.getParameter("id"));
            session.setAttribute("ID", id);
            ProductDAOImpl proDAO = new ProductDAOImpl();
            Product product = proDAO.detailOfOneProduct(id);
            int cateid = product.getCateId();
            CategoryDAOImpl cateDAO = new CategoryDAOImpl();
            Category cate = cateDAO.getNameType(cateid);
            request.setAttribute("PRODUCT", product);
            request.setAttribute("CATE", cate);

            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ViewDetailProductController at: " + e.toString());
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
