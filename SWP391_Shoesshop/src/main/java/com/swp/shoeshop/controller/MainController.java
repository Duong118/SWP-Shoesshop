/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Linh
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String HOME = "Home";
    private static final String HOME_CONTROLLER = "HomeController";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";

    private static final String SIGNUP = "Signup";
    private static final String SIGNUP_CONTROLLER = "SignupController";

    private static final String CATEGORY_TYPE = "CategoryType";
    private static final String CATEGORY_TYPE_CONTROLLER = "CategoryTypeController";

    private static final String PRODUCT_BY_CATEID = "ProductsByCateID";
    private static final String PRODUCT_BY_CATEID_CONTROLLER = "ProductsByCateIDController";

    private static final String DETAIL = "Detail";
    private static final String DETAIL_CONTROLLER = "DetailController";

    private static final String ADD_TO_CART = "AddToCart";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartController";

    private static final String BUY_NOW = "BuyNow";
    private static final String BUY_NOW_CONTROLLER = "BuyNowController";

    private static final String GET_CART = "GetCart";
    private static final String GET_CART_CONTROLLER = "GetCartController";

    private static final String CHECK_OUT = "Checkout";
    private static final String CHECK_OUT_CONTROLLER = "CheckoutController";

    private static final String VIEWORDERBYSTATUS = "ViewOrderByStatus";
    private static final String VIEWORDERBYSTATUS_CONTROLLER = "ViewOrderByStatusController";

    private static final String VIEWORDERDETAIL = "ViewOrderDetail";
    private static final String VIEWORDERDETAIL_CONTROLLER = "ViewOrderDetailController";

    private static final String VIEWALLORDER = "ViewAllOrder";
    private static final String VIEWALLORDER_CONTROLLER = "ViewAllOrderController";

    private static final String VIEWFEEDBACK = "ViewFeedback";
    private static final String VIEWFEEDBACK_CONTROLLER = "ViewFeedbackController";

    private static final String MANAGE_ORDER_STATUS = "ChangeOrderStatusForAdmin";
    private static final String MANAGE_ORDER_STATUS_CONTROLLER = "ChangeOrderStatusForAdminController";

    private static final String VIEWPRODUCTMANAGER = "ViewProductManager";
    private static final String VIEWPRODUCTMANAGER_CONTROLLER = "ViewProductManagerController";

    private static final String SEARCHPRODUCT = "SearchProduct";
    private static final String SEARCHPRODUCT_CONTROLLER = "SearchProductController";

    private static final String DELETEPRODUCT = "DeleteProduct";
    private static final String DELETEPRODUCT_CONTROLLER = "DeleteProductController";

    private static final String CREATEPRODUCT = "CreateProduct";
    private static final String CREATEPRODUCT_CONTROLLER = "CreateProductController";

    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";

    private static final String VIEWDETAILPRODUCT = "ViewDetailProduct";
    private static final String VIEWDETAILPRODUCT_CONTROLLER = "ViewDetailProductController";

    private static final String UPDATEPRODUCT = "UpdateProduct";
    private static final String UPDATEPRODUCT_CONTROLLER = "UpdateProductController";

    private static final String SORTPRODUCT = "SortProduct";
    private static final String SORTPRODUCT_CONTROLLER = "SortProductController";

    private static final String UPDATE_PROFILE = "UpdateProfile";
    private static final String UPDATE_PROFILE_CONTROLLER = "UpdateProfileController";

    //Blog
    private static final String GET_BLOG = "GetBlogs";
    private static final String GET_BLOG_MANAGER_CONTROLLER = "GetBlogManagerController";

    private static final String VIEW_BLOG_DETAILS = "ViewBlog";
    private static final String VIEW_BLOG_DETAILS_CONTROLLER = "ViewBlogDetailsController";

    private static final String VIEW_EDIT_BLOG = "ViewEditBlog";
    private static final String VIEW_EDIT_BLOG_CONTROLLER = "ViewEditBlogController";

    private static final String CREATE_BLOG = "CreateBlog";
    private static final String CREATE_BLOG_CONTROLLER = "CreateBlogController";

    private static final String EDIT_BLOG = "EditBlog";
    private static final String EDIT_BLOG_CONTROLLER = "EditBlogController";

    private static final String REMOVE_BLOG = "RemoveBlog";
    private static final String REMOVE_BLOG_CONTROLLER = "RemoveBlogController";

    private static final String UPDATE_CART = "UpdateCart";
    private static final String UPDATE_CART_CONTROLLER = "UpdateCartController";

    private static final String DELETEFEEDBACK = "DeleteFeedback";
    private static final String DELETEFEEDBACK_CONTROLLER = "DeleteFeedback";

    private static final String CREATEFEEDBACK = "CreateFeedback";
    private static final String CREATEFEEDBACK_CONTROLLER = "CreateFeedback";

    private static final String UPDATEHOT = "UpdateHot";
    private static final String UPDATEHOTPRODUCT = "UpdateHotProduct";

    private static final String NOTIFY_EMAIL = "NotifyEmail";
    private static final String NOTIFY_EMAIL_CONTROLLER = "NotifyEmailController";

    private static final String SORT_PRODUCT_BY_PRICE = "SortByPrice";
    private static final String SORT_PRODUCT_BY_PRICE_CONTROLLER = "SortByPriceController";

    private static final String SEARCHORDER = "SearchOrder";
    private static final String SEARCHORDER_CONTROLLER = "SearchOrderController";

    private static final String SORTORDER = "SortOrder";
    private static final String SORTORDER_CONTROLLER = "SortOrderController";

    private static final String ADD_RECOMMENED_CATEGORY = "AddRec";
    private static final String ADD_RECOMMENED_CATEGORY_CONTROLLER = "AddRecommendedCategoryController";

    private static final String REMOVE_FROM_CART = "RemoveFromCart";
    private static final String REMOVE_FROM_CART_CONTROLLER = "RemoveFromCartController";

    private static final String SEND_NOTI_EMAIL = "SendNotiEmail";
    private static final String SEND_NOTI_EMAIL_CONTROLLER = "SendEmailForAdminController";

    private static final String CHECK_ORDER_FOR_USER = "CheckOrderUser";
    private static final String CHECK_ORDER_FOR_USER_CONTROLLER = "CheckOrderUserController";

    private static final String CHECK_ORERDETAIL_FOR_USER = "CheckOrderDetailUser";
    private static final String CHECK_ORERDETAIL_FOR_USER_CONTROLLER = "CheckOrderDetailUserController";

    private static final String DASHBOARD = "Dashboard";
    private static final String DASHBOARD_CONTROLLER = "DashboardController";

    private static final String GET_FULL_PRODUCTS = "GetFullProducts";
    private static final String GET_FULL_PRODUCTS_CONTROLLER = "GetFullProductsController";

    private static final String FORGOT_PASS = "ForgotPass";
    private static final String FORGOT_PASS_CONTROLLER = "ForgotPassController";

    private static final String CHANGE_PASSWORD = "ChangePassword";
    private static final String CHANGE_PASSWORD_CONTROLLER = "ChangePasswordController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (HOME.equals(action)) {
                url = HOME_CONTROLLER;
            } else if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (SIGNUP.equals(action)) {
                url = SIGNUP_CONTROLLER;
            } else if (CATEGORY_TYPE.equals(action)) {
                url = CATEGORY_TYPE_CONTROLLER;
            } else if (PRODUCT_BY_CATEID.equals(action)) {
                url = PRODUCT_BY_CATEID_CONTROLLER;
            } else if (DETAIL.equals(action)) {
                url = DETAIL_CONTROLLER;
            } else if (ADD_TO_CART.equals(action)) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (BUY_NOW.equals(action)) {
                url = BUY_NOW_CONTROLLER;
            } else if (GET_CART.equals(action)) {
                url = GET_CART_CONTROLLER;
            } else if (CHECK_OUT.equals(action)) {
                url = CHECK_OUT_CONTROLLER;
            } else if (VIEWORDERBYSTATUS.equals(action)) {
                url = VIEWORDERBYSTATUS_CONTROLLER;
            } else if (VIEWORDERDETAIL.equals(action)) {
                url = VIEWORDERDETAIL_CONTROLLER;
            } else if (VIEWALLORDER.equals(action)) {
                url = VIEWALLORDER_CONTROLLER;
            } else if (VIEWFEEDBACK.equals(action)) {
                url = VIEWFEEDBACK_CONTROLLER;
            } else if (MANAGE_ORDER_STATUS.equals(action)) {
                url = MANAGE_ORDER_STATUS_CONTROLLER;
            } else if (VIEWPRODUCTMANAGER.equals(action)) {
                url = VIEWPRODUCTMANAGER_CONTROLLER;
            } else if (SEARCHPRODUCT.equals(action)) {
                url = SEARCHPRODUCT_CONTROLLER;
            } else if (DELETEPRODUCT.equals(action)) {
                url = DELETEPRODUCT_CONTROLLER;
            } else if (CREATEPRODUCT.equals(action)) {
                url = CREATEPRODUCT_CONTROLLER;
            } else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (VIEWDETAILPRODUCT.equals(action)) {
                url = VIEWDETAILPRODUCT_CONTROLLER;
            } else if (UPDATEPRODUCT.equals(action)) {
                url = UPDATEPRODUCT_CONTROLLER;
            } else if (SORTPRODUCT.equals(action)) {
                url = SORTPRODUCT_CONTROLLER;
            } else if (UPDATE_PROFILE.equals(action)) {
                url = UPDATE_PROFILE_CONTROLLER;
            } else if (UPDATE_CART.equals(action)) {
                url = UPDATE_CART_CONTROLLER;
            } else if (UPDATE_CART.equals(action)) {
                url = UPDATE_CART_CONTROLLER;
            } else if (GET_BLOG.equals(action)) {
                url = GET_BLOG_MANAGER_CONTROLLER;
            } else if (GET_BLOG.equals(action)) {
                url = GET_BLOG_MANAGER_CONTROLLER;
            } else if (VIEW_BLOG_DETAILS.equals(action)) {
                url = VIEW_BLOG_DETAILS_CONTROLLER;
            } else if (VIEW_BLOG_DETAILS_CONTROLLER.equals(action)) {
                url = VIEW_BLOG_DETAILS_CONTROLLER;
            } else if (CREATE_BLOG.equals(action)) {
                url = CREATE_BLOG_CONTROLLER;
            } else if (VIEW_EDIT_BLOG.equals(action)) {
                url = VIEW_EDIT_BLOG_CONTROLLER;
            } else if (EDIT_BLOG.equals(action)) {
                url = EDIT_BLOG_CONTROLLER;
            } else if (REMOVE_BLOG.equals(action)) {
                url = REMOVE_BLOG_CONTROLLER;
            } else if (DELETEFEEDBACK.equals(action)) {
                url = DELETEFEEDBACK_CONTROLLER;
            } else if (CREATEFEEDBACK.equals(action)) {
                url = CREATEFEEDBACK_CONTROLLER;
            } else if (UPDATEHOT.equals(action)) {
                url = UPDATEHOTPRODUCT;
            } else if (NOTIFY_EMAIL.equals(action)) {
                url = NOTIFY_EMAIL_CONTROLLER;
            } else if (SORT_PRODUCT_BY_PRICE.equals(action)) {
                url = SORT_PRODUCT_BY_PRICE_CONTROLLER;
            } else if (SEARCHORDER.equals(action)) {
                url = SEARCHORDER_CONTROLLER;
            } else if (SORTORDER.equals(action)) {
                url = SORTORDER_CONTROLLER;
            } else if (ADD_RECOMMENED_CATEGORY.equals(action)) {
                url = ADD_RECOMMENED_CATEGORY_CONTROLLER;
            } else if (REMOVE_FROM_CART.equals(action)) {
                url = REMOVE_FROM_CART_CONTROLLER;
            } else if (DASHBOARD.equals(action)) {
                url = DASHBOARD_CONTROLLER;
            } else if (SEND_NOTI_EMAIL.equals(action)) {
                url = SEND_NOTI_EMAIL_CONTROLLER;
            } else if (CHECK_ORDER_FOR_USER.equals(action)) {
                url = CHECK_ORDER_FOR_USER_CONTROLLER;
            } else if (GET_FULL_PRODUCTS.equals(action)) {
                url = GET_FULL_PRODUCTS_CONTROLLER;
            } else if (CHECK_ORERDETAIL_FOR_USER.equals(action)) {
                url = CHECK_ORERDETAIL_FOR_USER_CONTROLLER;
            } else if (FORGOT_PASS.equals(action)) {
                url = FORGOT_PASS_CONTROLLER;
            } else if (CHANGE_PASSWORD.equals(action)) {
                url = CHANGE_PASSWORD_CONTROLLER;
            } else {
                request.setAttribute("alert", "Your function is not supported!");
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
