/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller.blogcontroller;

import com.swp.shoeshop.dao.impldao.NewsDAOImpl;
import com.swp.shoeshop.model.News;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thuong
 */
@WebServlet(name = "CreateBlogController", urlPatterns = {"/CreateBlogController"})
public class CreateBlogController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS_ADMIN = "ViewBlogManagerController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = ERROR;

            try {
                //Post content
                String postTitle = request.getParameter("postTitle");
                String postContent = request.getParameter("postContent");
                String imageurl = request.getParameter("image");

                News addNews = new News(postTitle, postContent, imageurl);
                NewsDAOImpl dao = new NewsDAOImpl();
                if (dao.addNews(addNews) == true) {
                    url = SUCCESS_ADMIN;
                }else{
                    url = ERROR;
                }
                
            } catch (Exception ex) {
                log("Error at CreateBlogController: " + ex.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
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