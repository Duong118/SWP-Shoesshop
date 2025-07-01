/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller.feedbackcontroller;

import com.swp.shoeshop.dao.impldao.FeedbackDAOImpl;
import com.swp.shoeshop.model.Feedback;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author 
 */
public class CreateFeedback extends HttpServlet {

    private static final String ERROR = "shop-detail.jsp";
    private static final String SUCCESS = "DetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            FeedbackDAOImpl dao = new FeedbackDAOImpl();
            int id = Integer.parseInt(request.getParameter("userIdComment"));
            int pId = Integer.parseInt(request.getParameter("id"));
            String checked = request.getParameter("ano");
            String username = "";
            if (checked==null){
                username = request.getParameter("fullname");
                if(username==null) username="";
            }
            String feedbackContent = request.getParameter("txtComment");
            String rate = request.getParameter("rating");
            double star = 0;
            if(rate!=null){
            star = Double.parseDouble(rate);
            } else if(rate==null) {
                star =0;
            }
              Feedback fb = new Feedback(id, pId, username, feedbackContent, star);
                boolean checkInsert = dao.createFeedback(fb);
                if (checkInsert) {
                    request.setAttribute("COMMENT_SUCCESS", "Thêm bình luận thành công!!!");
                    session.setAttribute("PID",pId);
                    url = SUCCESS;
                }

        } catch (Exception e) {
            log("Error at CreateFeedback" + e.toString());

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
