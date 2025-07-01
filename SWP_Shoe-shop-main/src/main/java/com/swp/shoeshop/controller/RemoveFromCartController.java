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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TrucBQCE181355
 */
public class RemoveFromCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "GetCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int pId = Integer.parseInt(request.getParameter("id"));
            String sizeIdParam = request.getParameter("sizeId");
            int sizeId = (sizeIdParam != null && !sizeIdParam.isEmpty()) ? Integer.parseInt(sizeIdParam) : 0;
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            Cart cart = null;
            if (account != null) { // logged-in user
                CartDAOImpl cartDAO = new CartDAOImpl();
                cart = cartDAO.getCartByAccID(account.getId());
                if (cart != null) {
                    boolean checkRemove = false;
                    if (sizeId > 0) {
                        checkRemove = cartDAO.removeOneCartItemsWithSize(cart.getId(), pId, sizeId);
                    } else {
                        checkRemove = cartDAO.removeOneCartItems(cart.getId(), pId);
                    }
                    if (checkRemove) {
                        cart = cartDAO.getCartByAccID(account.getId());
                        if (cart.getItems().isEmpty()) {
                            cartDAO.clearCart(account.getId());
                            session.removeAttribute("QUANTITY_IN_CART");
                        } else {
                            int quantityInCart = (int) session.getAttribute("QUANTITY_IN_CART");
                            session.setAttribute("QUANTITY_IN_CART", --quantityInCart);
                        }
                        url = SUCCESS;
                    }
                }
            } else { // guest user (not logged in)
                request.setAttribute("NEED_CHECK", "false");
                CartCookieHandler ccd = (CartCookieHandler) session.getAttribute("CART_COOKIE");
                ArrayList<CartItem> tmp = ccd.getItemList(request, true);
                for (CartItem cartItem : tmp) {
                    if (cartItem.getProduct().getId() == pId && 
                        (sizeId == 0 || cartItem.getSizeId() == sizeId)) {
                        ccd.deleleCartItem(pId, request, response, true);
                        if (tmp.isEmpty()) {
                            ccd.deleteCookie(response);
                            session.removeAttribute("CART_COOKIE");
                            session.removeAttribute("QUANTITY_IN_CART");
                        } else {
                            int quantityInCart = (int) session.getAttribute("QUANTITY_IN_CART");
                            session.setAttribute("QUANTITY_IN_CART", --quantityInCart);
                        }
                        break;
                    }
                }
                url = SUCCESS;
            }

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
