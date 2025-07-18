
package com.swp.shoeshop.controller.productcontroller;

import com.swp.shoeshop.dao.impldao.CategoryDAOImpl;
import com.swp.shoeshop.dao.impldao.ProductDAOImpl;
import com.swp.shoeshop.model.Category;
import com.swp.shoeshop.model.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ViewProductManagerController", urlPatterns = {"/ViewProductManagerController"})

public class ViewProductManagerController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        CategoryDAOImpl cateDao = new CategoryDAOImpl();
        try {
            List<Category> cates = cateDao.viewCate();
            session.setAttribute("VIEW_CATE", cates);
            ProductDAOImpl dao = new ProductDAOImpl();
            List<Product> p = dao.viewAllProductWithQuantity();

            if (p.size() > 0) {
                request.setAttribute("EMPTY_LIST", null);
                request.setAttribute("LIST_PRODUCT", p);
                url = SUCCESS;
            } else {
                request.setAttribute("EMPTY_LIST", "Chưa có sản phẩm nào cả .-.");
                request.setAttribute("LIST_PRODUCT", null);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewProductManagerController: " + e.toString());
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