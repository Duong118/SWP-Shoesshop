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
public class UpdateCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "GetCartController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            String[] idArray = request.getParameterValues("id");
            String[] amountArray = request.getParameterValues("amount");
            String[] sizeIdArray = request.getParameterValues("sizeId");
            ArrayList<String> errorArray = new ArrayList<>();

            ProductDAOImpl prodDao = new ProductDAOImpl();
            if (idArray != null) {
                for (int i = 0; i < idArray.length; i++) {
                    int quantity = prodDao.getQuantity(Integer.parseInt(idArray[i]));
                    if (Integer.parseInt(amountArray[i]) > quantity) {
                        errorArray.add(prodDao.getName(Integer.parseInt(idArray[i])));
                    }
                }
            }

            int size = errorArray.size();
            if (size > 0) {
                String error;
                switch (size) {
                    case 1:
                        error = errorArray.get(0);
                        break;
                    case 2:
                        error = errorArray.get(0) + " and " + errorArray.get(1);
                        break;
                    default:
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < size - 1; i++) {
                            sb.append(errorArray.get(i)).append(", ");
                        }
                        sb.deleteCharAt(sb.lastIndexOf(",")).append(" and ").append(errorArray.get(size - 1));
                        error = sb.toString();
                        break;
                }
                request.setAttribute("ERROR_AMOUNT", error + " exceed(s) available stock quantity");
            }

            ArrayList<Integer> newIDList = new ArrayList<>();
            ArrayList<Integer> newAmountList = new ArrayList<>();
            ArrayList<Integer> newSizeIdList = new ArrayList<>();
            if (idArray != null) {
                for (int i = 0; i < idArray.length; i++) {
                    boolean flag = false;
                    String prodName = prodDao.getName(Integer.parseInt(idArray[i]));
                    for (String name : errorArray) {
                        if (name.equals(prodName)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        newIDList.add(Integer.parseInt(idArray[i]));
                        newAmountList.add(Integer.parseInt(amountArray[i]));
                        if (sizeIdArray != null && i < sizeIdArray.length) {
                            newSizeIdList.add(Integer.parseInt(sizeIdArray[i]));
                        }
                    }
                }
            }

            if (account == null) {
                request.setAttribute("NEED_CHECK", "false");
                CartCookieHandler ccd = (CartCookieHandler) session.getAttribute("CART_COOKIE");
                ArrayList<CartItem> tmp = ccd.getItemList(request, true);
                if (tmp != null && !tmp.isEmpty()) {
                    if (!newIDList.isEmpty()) {
                        ccd.updateAmount(newIDList, newAmountList, request, response, true);
                    }
                }
            } else { // user is logged in
                CartDAOImpl dao = new CartDAOImpl();
                Cart tmp = dao.getCartByAccID(account.getId());
                if (tmp != null && !tmp.getItems().isEmpty()) {
                    if (!newIDList.isEmpty()) {
                        for (int i = 0; i < newIDList.size(); i++) {
                            int id = newIDList.get(i);
                            int amount = newAmountList.get(i);
                            int sizeId = i < newSizeIdList.size() ? newSizeIdList.get(i) : 0;
                            
                            for (CartItem it : tmp.getItems()) {
                                if (it.getProduct().getId() == id && 
                                    (sizeId == 0 || it.getSizeId() == sizeId) && 
                                    it.getAmount() != amount) {
                                    if (sizeId > 0) {
                                        dao.updateAmountWithSize(tmp.getId(), it.getProduct().getId(), sizeId, amount);
                                    } else {
                                        dao.updateAmount(tmp.getId(), it.getProduct().getId(), amount);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            url = SUCCESS;
        } catch (Exception e) {
            log("Error at UpdateCartController", e);
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
