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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TrucBQCE181355
 */
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "MainController?action=Detail&pid=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            CartDAOImpl cartDAO = new CartDAOImpl();
            ProductDAOImpl productDAO = new ProductDAOImpl();

            int pid = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            int discount = Integer.parseInt(request.getParameter("discount_price"));
            String image = request.getParameter("image");
            int point = Integer.parseInt(request.getParameter("point"));
            int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));

            // Get size information
            String sizeIdParam = request.getParameter("sizeId");
            if (sizeIdParam == null || sizeIdParam.trim().isEmpty()) {
                request.setAttribute("ERROR_ADDTOCART", "Please select a size before adding to cart");
                url = SUCCESS + Integer.toString(pid);
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            int sizeId = Integer.parseInt(sizeIdParam);

            String action = request.getParameter("action");

            // Get available quantity for this specific product and size
            int availableQuantity = productDAO.getQuantityByProductAndSize(pid, sizeId);

            if (availableQuantity <= 0) {
                request.setAttribute("ERROR_ADDTOCART", "The product in this size is currently out of stock");
                url = SUCCESS + Integer.toString(pid);
            } else if (buyQuantity > availableQuantity) {
                request.setAttribute("ERROR_ADDTOCART", "Requested quantity (" + buyQuantity + ") exceeds available stock (" + availableQuantity + ") for this size");
                url = SUCCESS + Integer.toString(pid);
            } else {
                Cart cart = new Cart();
                if (account != null) {
                    cart = cartDAO.getCartByAccID(account.getId());
                    if (cart == null) {
                        int cartID = cartDAO.saveCart(account.getId());
                        cart = new Cart(cartID, account.getId());
                    }
                }
                CartItem cartItem = new CartItem(new Product(pid, name, image, price, discount, point), buyQuantity, sizeId, "");
                int amountOfCartItem = 0;
                if (account != null) {
                    // For logged-in users, check quantity for specific product and size
                    amountOfCartItem = cartDAO.getCurrentCartItemAmountBySize(pid, sizeId, cart.getId());
                } else {
                    // For guest users, check cart cookie
                    CartCookieHandler ccd = (CartCookieHandler) session.getAttribute("CART_COOKIE");
                    if (ccd != null) {
                        ArrayList<CartItem> items = ccd.getItemList(request, true);
                        for (CartItem item : items) {
                            if (item.getProduct().getId() == pid && item.getSizeId() == sizeId) {
                                amountOfCartItem = item.getAmount();
                                break;
                            }
                        }
                    }
                }

                int totalQuantityAfterAdd = amountOfCartItem + buyQuantity;
                if (totalQuantityAfterAdd > availableQuantity) {
                    request.setAttribute("ERROR_ADDTOCART", "Quantity in cart (" + amountOfCartItem + ") + quantity to add (" + buyQuantity + ") exceeds available stock (" + availableQuantity + ") for this size");
                    url = SUCCESS + Integer.toString(pid);
                } else {
                    if (account != null) {
                        cartDAO.saveCartItemWithSize(cartItem, cart.getId());
                        List<CartItem> ltmp = cartDAO.getCartItemList(cart.getId());
                        session.setAttribute("QUANTITY_IN_CART", ltmp.size());
                    } else {
                        CartCookieHandler ccd = (CartCookieHandler) session.getAttribute("CART_COOKIE");
                        if (ccd == null) {
                            ccd = new CartCookieHandler("my_cart", 3, "/swp-shoeshop");
                            request.getSession().setAttribute("CART_COOKIE", ccd);
                        }
                        ccd.addCartItemToCookie(cartItem, request, response, true);
                        List<CartItem> ltmp = ccd.getItemList(request, false);
                        session.setAttribute("QUANTITY_IN_CART", ltmp.size());
                    }
                    if (account == null) {
                        request.setAttribute("NEED_CHECK", "false");
                    }
                    if (action != null) {
                        switch (action) {
                            case "cart":
                                url = "MainController?action=GetCart";
                                break;
                            case "buynow":
                                url = "GetCartController?action=buynow";
                                break;
                            case "addtocart":
                                url = SUCCESS + Integer.toString(pid);
                                break;
                            case "home":
                                url = "MainController?action=Home";
                                break;
                            default:
                                url = SUCCESS + Integer.toString(pid);
                                break;
                        }
                    } else {
                        // If action is null, default to product detail page
                        url = SUCCESS + Integer.toString(pid);
                    }
                }
            }

        } catch (Exception e) {
            log(e.toString());
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
