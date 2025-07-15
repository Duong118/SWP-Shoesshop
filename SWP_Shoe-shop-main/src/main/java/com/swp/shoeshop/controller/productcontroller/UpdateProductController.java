package com.swp.shoeshop.controller.productcontroller;

import com.swp.shoeshop.constant.ProductStatus;
import com.swp.shoeshop.constant.Regex;
import com.swp.shoeshop.dao.impldao.CategoryDAOImpl;
import com.swp.shoeshop.dao.impldao.ProductDAOImpl;
import com.swp.shoeshop.model.Category;
import com.swp.shoeshop.model.Product;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thuong
 */
@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {

    private static final String ERROR = "edit_product.jsp";
    private static final String SUCCESS = "ViewDetailProductController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        ProductDAOImpl proDao = new ProductDAOImpl();
        CategoryDAOImpl cateDao = new CategoryDAOImpl();
        HttpSession session = request.getSession();
        try {
            List<Category> cates = cateDao.viewCate();
            session.setAttribute("VIEW_CATE", cates);
            int id = (Integer) session.getAttribute("ID");
            
            // Initialize validation flag
            boolean checkValidation = true;
            
            // Parse numeric parameters with null checks
            String originalPriceStr = request.getParameter("originalPrice");
            String quantityStr = request.getParameter("quantity");
            String pointStr = request.getParameter("point");
            
            int originalPrice = 0;
            int quantity = 0;
            int point = 0;
            
            try {
                originalPrice = (originalPriceStr != null && !originalPriceStr.trim().isEmpty()) 
                    ? Integer.parseInt(originalPriceStr.trim()) : 0;
            } catch (NumberFormatException e) {
                request.setAttribute("PRODUCT_ERROR", "Giá gốc không hợp lệ");
                checkValidation = false;
            }
            
            try {
                quantity = (quantityStr != null && !quantityStr.trim().isEmpty()) 
                    ? Integer.parseInt(quantityStr.trim()) : 0;
            } catch (NumberFormatException e) {
                request.setAttribute("PRODUCT_ERROR", "Số lượng không hợp lệ");
                checkValidation = false;
            }
            
            try {
                point = (pointStr != null && !pointStr.trim().isEmpty()) 
                    ? Integer.parseInt(pointStr.trim()) : 0;
            } catch (NumberFormatException e) {
                request.setAttribute("PRODUCT_ERROR", "Điểm không hợp lệ");
                checkValidation = false;
            }
            
            String name = request.getParameter("name");
            String img = request.getParameter("image");
            String discount = request.getParameter("dis");
            int discountPrice;
            
            // Validate required string parameters
            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("PRODUCT_ERROR", "Tên sản phẩm không được để trống");
                checkValidation = false;
            } else {
                name = name.trim();
            }
            
            if (img == null || img.trim().isEmpty()) {
                request.setAttribute("PRODUCT_ERROR", "Link hình ảnh không được để trống");
                checkValidation = false;
            } else {
                img = img.trim();
            }
            String description = request.getParameter("description");
            String shortdescript = request.getParameter("shortDescription");
            String video = request.getParameter("vid");
            //Xử lý cateId
            String catetype = request.getParameter("categoryType");
            String catename = request.getParameter("categoryName");
            if (catename != null) {
                catename = catename.trim();
            }
            
            int cateid = 0;
            if (catename != null && !catename.equals("OTHER")) {
                List<Category> catetypes = cateDao.getByCateType(catetype);
                for (Category cate : catetypes) {
                    String cname = cate.getName();
                    if (cname.equals(catename)) {
                        cateid = cate.getId();
                        break;
                    }
                }
            } else if (catename != null && catename.equals("OTHER")) {
                String newcatename = request.getParameter("newCategoryName");
                if (newcatename != null && !newcatename.trim().isEmpty()) {
                    catename = newcatename.trim();
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
                        } else if (!checknewcate) {
                            request.setAttribute("PRODUCT_ERROR", "Có lỗi với phân loại sản phẩm");
                            checkValidation = false;
                        }
                    } else {
                        request.setAttribute("PRODUCT_ERROR", "Loại sản phẩm này đã tồn tại");
                        checkValidation = false;
                    }
                } else {
                    request.setAttribute("PRODUCT_ERROR", "Vui lòng nhập tên loại sản phẩm mới");
                    checkValidation = false;
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
            if (discount != null) {
                String discountPriceStr = request.getParameter("discountPrice");
                try {
                    discountPrice = (discountPriceStr != null && !discountPriceStr.trim().isEmpty()) 
                        ? Integer.parseInt(discountPriceStr.trim()) : originalPrice;
                } catch (NumberFormatException e) {
                    request.setAttribute("PRODUCT_ERROR", "Giá giảm không hợp lệ");
                    checkValidation = false;
                    discountPrice = originalPrice;
                }
                if (discountPrice > originalPrice || discountPrice < 0) {
                    request.setAttribute("PRODUCT_ERROR", "Giá giảm không hợp lệ rùi");
                    request.setAttribute("checked", true);
                    checkValidation = false;
                }
            } else {
                discountPrice = originalPrice;
            }
            if (name != null && (name.length() > 200 || name.length() < 2)) {
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
            if (img != null) {
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
            }
            String videolink = request.getParameter("videolink");
            if (videolink != null) {
                videolink = videolink.trim();
            }
            if (video != null) {
                request.setAttribute("checkedvideo", true);
                if(videolink!=null && !videolink.equals("")){
                    Pattern utubePattern = Pattern.compile(Regex.YOUTUBE_PATTERN);
                    if (!utubePattern.matcher(videolink).find()) {
                        request.setAttribute("PRODUCT_ERROR", "Vui lòng nhập link video hợp lệ!");
                        checkValidation = false;
                    }
                }
            }

            if (checkValidation) {
                // Use the existing constructor that matches the available parameters
                Product product = new Product(id, name, img, originalPrice, discountPrice, description, shortdescript, point, cateid, status);
                boolean checkUpdate = proDao.checkUpdate(product);
                if (checkUpdate) {
                    request.setAttribute("UPDATE_SUCCESS", "Chỉnh sửa sản phẩm thành công!!!");
                    url = SUCCESS;
                    request.setAttribute("PRODUCT", product);
                    Category cateup = new Category(cateid, catename, catetype);
                    request.setAttribute("CATE", cateup);
                    request.setAttribute("PRODUCT_ERROR", null);
                } else {
                    request.setAttribute("UPDATE_SUCCESS", null);
                    request.setAttribute("PRODUCT", product);
                    Category cateup = new Category(cateid, catename, catetype);
                    request.setAttribute("CATE", cateup);
                    request.setAttribute("PRODUCT_ERROR", "Ủa là update dữ chưa z");
                }
            } else {
                request.setAttribute("UPDATE_SUCCESS", null);
                Product product = new Product(id, name, img, originalPrice, discountPrice, description, shortdescript, point, cateid, status);
                request.setAttribute("PRODUCT", product);
                Category cateup = new Category(cateid, catename, catetype);
                request.setAttribute("CATE", cateup);
            }
        } catch (Exception e) {
            log("Error at UpdateProductController: " + e.toString());
            request.setAttribute("PRODUCT_ERROR", "Có lỗi xảy ra rồi .-." + e.toString());

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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }