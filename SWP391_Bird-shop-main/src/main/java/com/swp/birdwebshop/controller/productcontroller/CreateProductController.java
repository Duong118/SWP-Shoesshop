/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swp.birdwebshop.controller.productcontroller;

import com.swp.birdwebshop.constant.ProductStatus;
import com.swp.birdwebshop.constant.Regex;
import com.swp.birdwebshop.dao.impldao.CategoryDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.swp.birdwebshop.dao.impldao.ProductDAOImpl;
import com.swp.birdwebshop.model.Category;
import com.swp.birdwebshop.model.Product;
import java.awt.Image;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Milo
 */
@WebServlet(name = "CreateProductController", urlPatterns = {"/CreateProductController"})
public class CreateProductController extends HttpServlet {

    private static final String ERROR = "add_edit_product.jsp";
    private static final String SUCCESS = "ViewProductManagerController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductDAOImpl proDao = new ProductDAOImpl();
        CategoryDAOImpl cateDao = new CategoryDAOImpl();
        HttpSession session = request.getSession();

        try {
            List<Category> cates = cateDao.viewCate();
            session.setAttribute("VIEW_CATE", cates);
            int originalPrice = Integer.parseInt(request.getParameter("originalPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int point = Integer.parseInt(request.getParameter("point"));
            String name = request.getParameter("name").trim();
            String img = request.getParameter("img").trim();
            String discount = request.getParameter("dis");
            int discountPrice;
            String description = request.getParameter("description");
            String shortdescript = request.getParameter("shortdescript");
            //Xử lý cateId
            String catetype = request.getParameter("catetype");
            String catename = request.getParameter("catename");
            int cateid = 0;
            if (!catename.equals("Khác")) {
                for (Category cate : cates) {
                    String cname = cate.getName();
                    if (cname.equals(catename)) {
                        cateid = cate.getId();
                        break;
                    }
                }
            } else if (catename.equals("Khác")) {
                catename = request.getParameter("newcatename").trim();
                boolean checkCateDuplicate = cateDao.checkCateDuplicate(catename);
                request.setAttribute("isDuplicated", checkCateDuplicate);
                if (!checkCateDuplicate) {
                    Category cate = new Category(catename, catetype);
                    boolean checknewcate = cateDao.createCate(cate);
                    if (checknewcate) {
                        cateid = cateDao.getNewCateId();
                        cates = cateDao.viewCate();
                        session.setAttribute("VIEW_CATE", cates);
                        request.setAttribute("newCate", true);
                        request.setAttribute("catename", catename);
                    } else if (!checknewcate) {
                        request.setAttribute("PRODUCT_ERROR", "Có lỗi với phân loại sản phẩm");
                    }
                } else {
                    request.setAttribute("PRODUCT_ERROR", "Loại sản phẩm này đã tồn tại");
                }
            }
            //Xử lý status
            ProductStatus status = null;
            if (quantity > 0) {
                status = ProductStatus.INSTOCK;
            } else if (quantity == 0) {
                status = ProductStatus.OUTOFSTOCK;
            }

            //validate
            boolean checkProDuplicate = proDao.checkDuplicate(name);
            boolean checkValidation = true;
            if (discount != null) {
                discountPrice = Integer.parseInt(request.getParameter("discountPrice"));
                if (discountPrice > originalPrice || discountPrice < 0) {
                    request.setAttribute("PRODUCT_ERROR", "Giá giảm không hợp lệ rùi");
                    request.setAttribute("checked", true);
                    checkValidation = false;
                }
            } else {
                discountPrice = originalPrice;
            }
            if (checkProDuplicate) {
                request.setAttribute("PRODUCT_ERROR", "Trùng tên sản phẩm rùi .-.");
                checkValidation = false;
            }
            if (name.length() > 200 || name.length() < 2) {
                request.setAttribute("PRODUCT_ERROR", "Tên sản phẩm không hợp lệ");
                checkValidation = false;
            }
            if (originalPrice < 999) {
                request.setAttribute("PRODUCT_ERROR", "Giá không hợp lệ rùi");
                checkValidation = false;
            }
            if (quantity < 0) {
                request.setAttribute("PRODUCT_ERROR", "Số lượng phải lớn hơn 0 nha");
                checkValidation = false;
            }
            if (point < 0) {
                request.setAttribute("PRODUCT_ERROR", "Điểm phải lớn hơn 0 nha");
                checkValidation = false;
            }
            Pattern urlPattern = Pattern.compile(Regex.IMAGE_URL_PATTERN);
            if (!urlPattern.matcher(img).find()) {
                request.setAttribute("PRODUCT_ERROR", "Vui lòng nhập link hình ảnh hợp lệ!");
                checkValidation = false;
            } else {
                try {
                    Image image = ImageIO.read(new URL(img));
                    if (image == null) {
                        request.setAttribute("PRODUCT_ERROR", "Không tìm thấy link hình ảnh này");
                        checkValidation = false;
                    }
                } catch (IOException e) {
                    request.setAttribute("PRODUCT_ERROR", "Lỗi khi đọc hình ảnh từ URL");
                    checkValidation = false;
                }
            }

            if (checkValidation) {
                Product product = new Product(name, img, originalPrice, discountPrice, description, shortdescript, point, cateid, 0, status);
                boolean checkInsert = proDao.createProduct(product);
                if (checkInsert) {
                    session.setAttribute("CREATE_SUCCESS", "Thêm sản phẩm thành công!!!");
                    request.setAttribute("CREATE_ERROR", null);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at CreateProductController" + e.toString());

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
