package com.swp.shoeshop.controller.productcontroller;

import com.swp.shoeshop.dao.impldao.CategoryDAOImpl;
import com.swp.shoeshop.dao.impldao.ProductDAOImpl;
import com.swp.shoeshop.dao.impldao.ProductSizeDAOImpl;
import com.swp.shoeshop.model.Category;
import com.swp.shoeshop.model.Product;
import com.swp.shoeshop.model.ProductSize;
import com.swp.shoeshop.constant.ProductStatus;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller to handle product creation with single size
 *
 * @author
 */
@WebServlet(name = "CreateProductController", urlPatterns = {"/CreateProductController"})
public class CreateProductController extends HttpServlet {

    private static final String ERROR = "add_edit_product.jsp";
    private static final String SUCCESS = "ViewProductManagerController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        try {
            log("CreateProductController: Processing request started");

            // Get form parameters
            String name = request.getParameter("name");
            String image = request.getParameter("img");
            String originalPriceStr = request.getParameter("originalPrice");
            String discountPriceStr = request.getParameter("discountPrice");
            String description = request.getParameter("description");
            String shortDescription = request.getParameter("shortdescript");
            String pointStr = request.getParameter("point");
            String cateType = request.getParameter("catetype");
            String cateName = request.getParameter("catename");
            String newCateName = request.getParameter("newcatename");
            String selectedSize = request.getParameter("selectedSize");
            String sizeQuantity = request.getParameter("sizeQuantity");

            // Validate required fields
            if (name == null || name.trim().isEmpty()
                    || image == null || image.trim().isEmpty()
                    || originalPriceStr == null || originalPriceStr.trim().isEmpty()
                    || description == null || description.trim().isEmpty()
                    || shortDescription == null || shortDescription.trim().isEmpty()
                    || pointStr == null || pointStr.trim().isEmpty()
                    || cateType == null || cateType.trim().isEmpty()) {

                session.setAttribute("PRODUCT_ERROR", "Please fill in the product information!");
                response.sendRedirect("AddEditProductController");
                return;
            }

// Validate size selection
            if (selectedSize == null || selectedSize.trim().isEmpty()) {
                session.setAttribute("PRODUCT_ERROR", "Please select a size for the product!");
                response.sendRedirect("AddEditProductController");
                return;
            }

// Validate size quantity
            if (sizeQuantity == null || sizeQuantity.trim().isEmpty()) {
                session.setAttribute("PRODUCT_ERROR", "Please enter a quantity for the selected size!");
                response.sendRedirect("AddEditProductController");
                return;
            }

            // Parse numeric values
            int originalPrice = Integer.parseInt(originalPriceStr);
            int discountPrice = 0;
            if (discountPriceStr != null && !discountPriceStr.trim().isEmpty()) {
                discountPrice = Integer.parseInt(discountPriceStr);
            }
            int point = Integer.parseInt(pointStr);
            int sizeId = Integer.parseInt(selectedSize);
            int quantity = Integer.parseInt(sizeQuantity);

            // Get or create category
            CategoryDAOImpl cateDao = new CategoryDAOImpl();
            int cateId = 0;

            if ("Khác".equals(cateName) && newCateName != null && !newCateName.trim().isEmpty()) {
                // Create new category
                Category newCategory = new Category(newCateName.trim(), cateType);
                if (cateDao.createCate(newCategory)) {
                    // Get the ID of newly created category
                    for (Category cat : cateDao.viewCate()) {
                        if (cat.getName().equals(newCateName.trim()) && cat.getType().equals(cateType)) {
                            cateId = cat.getId();
                            break;
                        }
                    }
                }
            } else if (cateName != null && !cateName.trim().isEmpty()) {
                // Find existing category
                for (Category cat : cateDao.viewCate()) {
                    if (cat.getName().equals(cateName)) {
                        cateId = cat.getId();
                        break;
                    }
                }
            }

            if (cateId == 0) {
                session.setAttribute("PRODUCT_ERROR", "Could not determine product category!");
                response.sendRedirect("AddEditProductController");
                return;
            }

            // Determine product status based on quantity
            ProductStatus status = quantity > 0 ? ProductStatus.INSTOCK : ProductStatus.OUTOFSTOCK;

            // Create product
            Product product = new Product(
                    name.trim(),
                    image.trim(),
                    originalPrice,
                    discountPrice,
                    description.trim(),
                    shortDescription.trim(),
                    point,
                    cateId,
                    status
            );
            product.setCreatedDate(Date.valueOf(LocalDate.now()));
            product.setHot(false);

            // Insert product
            ProductDAOImpl productDao = new ProductDAOImpl();
            boolean productCreated = productDao.addProduct(product);

            if (!productCreated) {
                session.setAttribute("PRODUCT_ERROR", "Could not create product!");
                response.sendRedirect("AddEditProductController");
                return;
            }

            // Get the created product ID by finding the product with exact matching data and latest date
            int productId = 0;
            for (Product p : productDao.viewAllProduct()) {
                if (p.getName().equals(name.trim())
                        && p.getImage().equals(image.trim())
                        && p.getOriginalPrice() == originalPrice
                        && p.getCateId() == cateId) {
                    // Get the latest product with same data
                    if (productId == 0 || p.getId() > productId) {
                        productId = p.getId();
                    }
                }
            }

            if (productId == 0) {
                log("Cannot find created product. Looking for: name=" + name.trim() + ", image=" + image.trim() + ", price=" + originalPrice + ", cateId=" + cateId);
                session.setAttribute("PRODUCT_ERROR", "Unable to get the product ID just created!");
                response.sendRedirect("AddEditProductController");
                return;
            }

            log("Found product ID: " + productId + " for product: " + name.trim());

            // Insert product size relationship
            ProductSizeDAOImpl productSizeDao = new ProductSizeDAOImpl();
            ProductSize productSize = new ProductSize(
                    productId,
                    sizeId,
                    quantity,
                    true // available
            );

            log("Attempting to insert ProductSize: productId=" + productId + ", sizeId=" + sizeId + ", quantity=" + quantity);

            boolean productSizeCreated = productSizeDao.addProductSize(productSize);

            log("ProductSize creation result: " + productSizeCreated);

            if (!productSizeCreated) {
                session.setAttribute("PRODUCT_ERROR", "Error saving product size information!");
                response.sendRedirect("AddEditProductController");
                return;
            }

// Success - redirect to product manager 
            response.sendRedirect(SUCCESS);

        } catch (NumberFormatException e) {
            session.setAttribute("PRODUCT_ERROR", "Invalid numeric data!");
            response.sendRedirect("AddEditProductController");
            return;
        } catch (Exception e) {
            log("Error at CreateProductController: " + e.toString());
            session.setAttribute("PRODUCT_ERROR", "An error occurred: " + e.getMessage());
            response.sendRedirect("AddEditProductController");
            return;
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
