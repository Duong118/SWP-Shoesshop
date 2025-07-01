/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller;

import com.swp.shoeshop.dao.impldao.CartDAOImpl;
import com.swp.shoeshop.model.Account;
import com.swp.shoeshop.model.Cart;
import com.swp.shoeshop.model.CartItem;
import com.swp.shoeshop.utils.cookiehandle.CartCookieHandler;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TrucBQCE181355
 */
public class GetCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "cart.jsp";
    private static final String CHECKOUT = "checkout.jsp";

    public GetCartController() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            String action = request.getParameter("action"); // get action from AddToCartController
            Cart cart = null;
            if (account != null) { // if user is logged in
                CartDAOImpl cartDAO = new CartDAOImpl();
                cart = cartDAO.getCartByAccID(account.getId());
                if (cart != null) {
                    List<CartItem> cartItemList = cartDAO.getCartItemList(cart.getId());
                    int total = cart.getTotalPrice();
                    int totalQuantity = cart.getTotalQuantity();
                    int totalPoint = cart.getTotalPoints();
                    session.setAttribute("TOTAL_QUANTITY", totalQuantity);
                    session.setAttribute("TOTAL_POINTS", totalPoint);
                    session.setAttribute("TOTAL", total);
                    session.setAttribute("CART", cartItemList);
                } else {
                    // remove cart-related session attributes if cart is null
                    if (session.getAttribute("TOTAL_QUANTITY") != null) {
                        session.removeAttribute("TOTAL_QUANTITY");
                    }
                    if (session.getAttribute("TOTAL_POINTS") != null) {
                        session.removeAttribute("TOTAL_POINTS");
                    }
                    if (session.getAttribute("TOTAL") != null) {
                        session.removeAttribute("TOTAL");
                    }
                    if (session.getAttribute("CART") != null) {
                        session.removeAttribute("CART");
                    }
                }
                if (action.equals("buynow")) {
                    url = CHECKOUT;
                } else {
                    url = SUCCESS;
                }
            } else {
                CartCookieHandler ccd = (CartCookieHandler) session.getAttribute("CART_COOKIE");
                if (ccd != null) {
                    boolean needCheck = true;
                    String needCheckStr = (String) request.getAttribute("NEED_CHECK");
                    if (needCheckStr != null) {
                        needCheck = Boolean.parseBoolean(needCheckStr);
                        request.removeAttribute("NEED_CHECK");
                    }
                    List<CartItem> cartItemList = ccd.getItemList(request, needCheck);
                    if (!(cartItemList == null || cartItemList.isEmpty())) {
                        cart = new Cart(cartItemList);
                        int total = cart.getTotalPrice();
                        int totalQuantity = cart.getTotalQuantity();
                        int totalPoint = cart.getTotalPoints();
                        session.setAttribute("TOTAL_QUANTITY", totalQuantity);
                        session.setAttribute("TOTAL_POINTS", totalPoint);
                        session.setAttribute("TOTAL", total);
                        session.setAttribute("CART", cartItemList);
                    } else {
                        // remove cart-related session attributes if guest cart is empty
                        if (session.getAttribute("TOTAL_QUANTITY") != null) {
                            session.removeAttribute("TOTAL_QUANTITY");
                        }
                        if (session.getAttribute("TOTAL_POINTS") != null) {
                            session.removeAttribute("TOTAL_POINTS");
                        }
                        if (session.getAttribute("TOTAL") != null) {
                            session.removeAttribute("TOTAL");
                        }
                        if (session.getAttribute("CART") != null) {
                            session.removeAttribute("CART");
                        }
                    }
                } else {
                    // remove cart-related session attributes if no cookie cart found
                    if (session.getAttribute("TOTAL_QUANTITY") != null) {
                        session.removeAttribute("TOTAL_QUANTITY");
                    }
                    if (session.getAttribute("TOTAL_POINTS") != null) {
                        session.removeAttribute("TOTAL_POINTS");
                    }
                    if (session.getAttribute("TOTAL") != null) {
                        session.removeAttribute("TOTAL");
                    }
                    if (session.getAttribute("CART") != null) {
                        session.removeAttribute("CART");
                    }
                }
                if (action.equals("buynow")) {
                    url = CHECKOUT;
                } else {
                    url = SUCCESS;
                }
            }
            // handling for non-logged-in users (store cart in cookie)
        } catch (Exception e) {
            System.out.println(e);
            log("Error: ", e);
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
