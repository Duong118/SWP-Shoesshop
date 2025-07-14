package com.swp.shoeshop.controller.sizecontroller;

import com.swp.shoeshop.dao.impldao.SizeDAOImpl;
import com.swp.shoeshop.model.ShoeSize;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Size Management Controller
 * @author Thuong
 */
@WebServlet(name = "SizeManagementController", urlPatterns = {"/SizeManagementController"})
public class SizeManagementController extends HttpServlet {
    
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "size-management.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        
        try {
            String action = request.getParameter("action");
            SizeDAOImpl sizeDAO = new SizeDAOImpl();
            
            if (action == null) {
                action = "list";
            }
            
            switch (action) {
                case "list":
                    List<ShoeSize> sizes = sizeDAO.getAllSizes();
                    request.setAttribute("LIST_SIZES", sizes);
                    url = SUCCESS;
                    break;
                    
                case "add":
                    String sizeName = request.getParameter("sizeName");
                    String sizeType = request.getParameter("sizeType");
                    
                    if (sizeName != null && sizeType != null) {
                        if (sizeDAO.isDuplicateSize(sizeName, sizeType)) {
                            request.setAttribute("ERROR_MESSAGE", "Size " + sizeName + " (" + sizeType + ") đã tồn tại!");
                        } else {
                            ShoeSize newSize = new ShoeSize(sizeName, sizeType, 0, true);
                            if (sizeDAO.createSize(newSize)) {
                                request.setAttribute("SUCCESS_MESSAGE", "Thêm size thành công!");
                            } else {
                                request.setAttribute("ERROR_MESSAGE", "Thêm size thất bại!");
                            }
                        }
                    }
                    
                    List<ShoeSize> sizesAfterAdd = sizeDAO.getAllSizes();
                    request.setAttribute("LIST_SIZES", sizesAfterAdd);
                    url = SUCCESS;
                    break;
                    
                case "edit":
                    int editId = Integer.parseInt(request.getParameter("id"));
                    ShoeSize editSize = sizeDAO.getSizeById(editId);
                    
                    if (editSize != null) {
                        request.setAttribute("EDIT_SIZE", editSize);
                    }
                    
                    List<ShoeSize> sizesForEdit = sizeDAO.getAllSizes();
                    request.setAttribute("LIST_SIZES", sizesForEdit);
                    url = SUCCESS;
                    break;
                    
                case "update":
                    int updateId = Integer.parseInt(request.getParameter("id"));
                    String updateSizeName = request.getParameter("sizeName");
                    String updateSizeType = request.getParameter("sizeType");
                    
                    if (updateSizeName != null && updateSizeType != null) {
                        ShoeSize updateSize = new ShoeSize(updateId, updateSizeName, updateSizeType, 0, true);
                        if (sizeDAO.updateSize(updateSize)) {
                            request.setAttribute("SUCCESS_MESSAGE", "Cập nhật size thành công!");
                        } else {
                            request.setAttribute("ERROR_MESSAGE", "Cập nhật size thất bại!");
                        }
                    }
                    
                    List<ShoeSize> sizesAfterUpdate = sizeDAO.getAllSizes();
                    request.setAttribute("LIST_SIZES", sizesAfterUpdate);
                    url = SUCCESS;
                    break;
                    
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    
                    if (sizeDAO.isSizeInUse(deleteId)) {
                        request.setAttribute("ERROR_MESSAGE", "Không thể xóa size này vì đang được sử dụng trong sản phẩm!");
                    } else {
                        if (sizeDAO.deleteSize(deleteId)) {
                            request.setAttribute("SUCCESS_MESSAGE", "Xóa size thành công!");
                        } else {
                            request.setAttribute("ERROR_MESSAGE", "Xóa size thất bại!");
                        }
                    }
                    
                    List<ShoeSize> sizesAfterDelete = sizeDAO.getAllSizes();
                    request.setAttribute("LIST_SIZES", sizesAfterDelete);
                    url = SUCCESS;
                    break;
                    
                default:
                    List<ShoeSize> defaultSizes = sizeDAO.getAllSizes();
                    request.setAttribute("LIST_SIZES", defaultSizes);
                    url = SUCCESS;
                    break;
            }
            
        } catch (Exception e) {
            log("Error at SizeManagementController: " + e.toString());
            request.setAttribute("ERROR_MESSAGE", "Có lỗi xảy ra: " + e.getMessage());
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
