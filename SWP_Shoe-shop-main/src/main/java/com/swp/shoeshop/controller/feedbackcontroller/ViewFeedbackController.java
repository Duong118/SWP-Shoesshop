/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller.feedbackcontroller;

import com.swp.shoeshop.dao.impldao.FeedbackDAOImpl;
import com.swp.shoeshop.model.Feedback;
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
@WebServlet(name = "ViewFeedbackController", urlPatterns = {"/ViewFeedback"})

public class ViewFeedbackController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "comment.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            FeedbackDAOImpl dao = new FeedbackDAOImpl();
            List<Feedback> fb = dao.viewFeedback();

            if (fb.size() > 0) {
                request.getSession().setAttribute("VIEW_FEEDBACK_EMPTY", null);
                request.setAttribute("VIEW_FEEDBACK", fb);
                url = SUCCESS;
            } else {
                request.getSession().setAttribute("VIEW_FEEDBACK_EMPTY", "Chưa có feedback nào cả .-.");
                request.getSession().setAttribute("VIEW_FEEDBACK", null);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewFeedbackController: " + e.toString());
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