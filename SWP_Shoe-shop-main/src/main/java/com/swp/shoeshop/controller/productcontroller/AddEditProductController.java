package com.swp.shoeshop.controller.productcontroller;

import com.swp.shoeshop.dao.impldao.CategoryDAOImpl;
import com.swp.shoeshop.dao.impldao.SizeDAOImpl;
import com.swp.shoeshop.model.Category;
import com.swp.shoeshop.model.ShoeSize;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller to prepare data for add/edit product page
 *
 * @author
 */
@WebServlet(name = "AddEditProductController", urlPatterns = {"/AddEditProductController"})
public class AddEditProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "add_edit_product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            CategoryDAOImpl cateDao = new CategoryDAOImpl();
            SizeDAOImpl sizeDao = new SizeDAOImpl();
            HttpSession session = request.getSession();

            // Load all categories
            List<Category> categories = cateDao.viewCate();
            session.setAttribute("VIEW_CATE", categories);

            // Load all sizes for product creation
            List<ShoeSize> allSizes = sizeDao.getAllSizes();
            request.setAttribute("ALL_SIZES", allSizes);

            // Clear any previous error messages from session
            session.removeAttribute("PRODUCT_ERROR");

            // Extract unique category types
            Set<String> categoryTypesSet = new HashSet<>();
            for (Category category : categories) {
                if (category.getType() != null && !category.getType().trim().isEmpty()) {
                    categoryTypesSet.add(category.getType());
                }
            }

            // Convert to List for JSP
            List<String> categoryTypes = new ArrayList<>(categoryTypesSet);
            request.setAttribute("CATEGORY_TYPES", categoryTypes);

            url = SUCCESS;

        } catch (Exception e) {
            log("Error at AddEditProductController: " + e.toString());
            request.setAttribute("ERROR_MESSAGE", "An error occurred while loading data: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
