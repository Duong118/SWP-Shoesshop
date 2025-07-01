/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller;

import com.swp.shoeshop.dao.impldao.CartDAOImpl;
import com.swp.shoeshop.dao.impldao.ProductDAOImpl;
import com.swp.shoeshop.model.Account;
import com.swp.shoeshop.model.Cart;
import com.swp.shoeshop.model.CartItem;
import com.swp.shoeshop.model.Product;
import com.swp.shoeshop.utils.cookiehandle.CartCookieHandler;
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
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})

public class HomeController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAOImpl dao = new ProductDAOImpl();
            List<Product> top7HotBird = dao.getTop7HotProducts("Chim");
            List<Product> top7HotCage = dao.getTop7HotProducts("Lồng");
            Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
            boolean checkCartProducts = request.getSession().getAttribute("NEED_CHECK_CART_PRODUCT") != null
                    ? (boolean) request.getSession().getAttribute("NEED_CHECK_CART_PRODUCT") : true;
            if (checkCartProducts) {
                if (account == null) {
                    CartCookieHandler ccd = (CartCookieHandler) request.getSession().getAttribute("CART_COOKIE");
                    if (ccd == null) {
                        ccd = new CartCookieHandler("my_cart", 3, "/swp-shoeshop");
                        ArrayList<CartItem> tmp = ccd.getItemList(request, true);
                        if (!tmp.isEmpty()) {
                            request.getSession().setAttribute("CART_COOKIE", ccd);
                            request.getSession().setAttribute("QUANTITY_IN_CART", tmp.size());
                        } else {
                            if (request.getSession().getAttribute("QUANTITY_IN_CART") != null) {
                                request.getSession().removeAttribute("QUANTITY_IN_CART");
                            }
                        }
                    }
                } else {
                    CartDAOImpl cartDao = new CartDAOImpl();
                    Cart cart = cartDao.getCartByAccID(account.getId());
                    if (cart != null) {
                        request.getSession().setAttribute("QUANTITY_IN_CART", cart.getItems().size());
                    } else {
                        if (request.getSession().getAttribute("QUANTITY_IN_CART") != null) {
                            request.getSession().removeAttribute("QUANTITY_IN_CART");
                        }
                    }
                }
            }
            if (top7HotBird.size() > 0) {
                request.setAttribute("TOP7_HOT_BIRD", top7HotBird);
                request.setAttribute("TOP7_HOT_CAGE", top7HotCage);
            }
            url = SUCCESS;
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
