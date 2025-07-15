/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.swp.shoeshop.controller;

import com.swp.shoeshop.dao.impldao.AccountDAOImpl;
import com.swp.shoeshop.dao.impldao.FeedbackDAOImpl;
import com.swp.shoeshop.dao.impldao.NewsDAOImpl;
import com.swp.shoeshop.dao.impldao.OrderDAOImpl;
import com.swp.shoeshop.dao.impldao.ProductDAOImpl;
import com.swp.shoeshop.model.Feedback;
import com.swp.shoeshop.model.Order;
import com.swp.shoeshop.model.Product;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/Dashboard"})
public class DashboardController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        AccountDAOImpl accDAO = new AccountDAOImpl();
        ProductDAOImpl proDAO = new ProductDAOImpl();
        OrderDAOImpl ordDAO = new OrderDAOImpl();
        NewsDAOImpl blogDAO = new NewsDAOImpl();
        FeedbackDAOImpl cmtDAO = new FeedbackDAOImpl();
        try {
            HttpSession session = request.getSession();
            int totalUser = accDAO.getTotalUser();
            session.setAttribute("USERS", totalUser);
            int totalProduct = proDAO.getTotalProduct();
            session.setAttribute("PRODUCTS", totalProduct);

            int totalBlog = blogDAO.getTotalBlog();
            session.setAttribute("BLOGS", totalBlog);

            int totalOrder = ordDAO.getTotalOrder();
            session.setAttribute("ORDERS", totalOrder);

            int totalSales = ordDAO.getTotalSales();
            session.setAttribute("SALES", totalSales);

            //Total Sales In this Month
            LocalDate date = LocalDate.now();
            int m = date.getMonthValue();
            String temp = date.getMonth().toString();
            String month = temp.substring(0, 1) + temp.substring(1).toLowerCase();
            request.setAttribute("MONTH", month);
            Map<String, List<Integer>> resultMap = ordDAO.getTotalbyDay(m);
            request.setAttribute("labels", resultMap.get("labels"));
            request.setAttribute("values", resultMap.get("values"));

            //Total Sales In this Month
            int lastm = m - 1;
            String lastTemp = date.minusMonths(1).getMonth().toString();
            String lastMonth = lastTemp.substring(0, 1) + lastTemp.substring(1).toLowerCase();
            request.setAttribute("LASTMONTH", lastMonth);
            Map<String, List<Integer>> lastResultMap = ordDAO.getTotalbyDay(lastm);
            request.setAttribute("lastValues", lastResultMap.get("values"));

            //Feedback Chart
            int positive = 0;
            int neutral = 0;
            int negative = 0;
            List<Feedback> fbs = cmtDAO.viewFeedback();
            if (fbs.size() > 0) {
                for (Feedback fb : fbs) {
                    double rating = fb.getStar();
                    if (rating > 2.5) {
                        positive += 1;
                    } else if (rating < 2.5) {
                        negative += 1;
                    } else {
                        neutral += 1;
                    }
                }
                int totalcmt = fbs.size();
                String feedback = "[" + positive + "," + neutral + "," + negative + "]";
                request.setAttribute("RATING", feedback);
            } else {
                request.setAttribute("NOFB", "Chưa có feedback nào cả .-.");
            }

            //Daily, Weekly, Monthly, Annually Chart
            //Daily
            List<Order> ords = ordDAO.getDailySale();
            List<String> daily = new ArrayList<>();
            SimpleDateFormat dayformatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar.set(Calendar.HOUR_OF_DAY, 00); 
            calendar.set(Calendar.MINUTE, 00); // set the minute to 59
            calendar.set(Calendar.SECOND, 0); // set the second to 0
            String dateStr;
            String data = null;
            Calendar cal = Calendar.getInstance();

            if (dayformatter.format(calendar.getTime()).equals(dayformatter.format(cal.getTime()))) {
                for (int i = 1; i <= 3; i++) {
                    dateStr = dayformatter.format(cal.getTime());
                    data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: 0 }";
                    for (Order ord : ords) {
                        if (dayformatter.format(ord.getDate()).equals(dayformatter.format(cal.getTime()))) {
                            data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: " + ord.getTotal() + " }";
                        }
                    }
                cal.add(Calendar.DAY_OF_YEAR, -1);
                    daily.add(data);
                }
            } else {
            while (calendar.getTime().before(Calendar.getInstance().getTime())) {
                dateStr = dayformatter.format(calendar.getTime());
                data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: 0 }";
                for (Order ord : ords) {
                    if (dayformatter.format(ord.getDate()).equals(dayformatter.format(calendar.getTime()))) {
                        data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: " + ord.getTotal() + " }";
                    }
                }
                daily.add(data);
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            }
            request.setAttribute("daily", String.join(", ", daily));

            //Weekly
            ords = ordDAO.getWeeklySale();
            List<String> weekly = new ArrayList<>();
            calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            int count = 1;
            while (count <= 4) {
                dateStr = dayformatter.format(calendar.getTime());
                data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: 0 }";
                for (Order ord : ords) {
                    if (dayformatter.format(ord.getDate()).equals(dayformatter.format(calendar.getTime()))) {
                        data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: " + ord.getTotal() + " }";
                    }
                }
                weekly.add(data);
                calendar.add(Calendar.DAY_OF_YEAR, -7);
                count += 1;
            }
            request.setAttribute("weekly", String.join(", ", weekly));

            //Monthly
            ords = ordDAO.getMonthlySale();
            List<String> monthly = new ArrayList<>();
            calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            int currentYear = calendar.get(Calendar.YEAR);
            count = 1;
            while (count <= 12) {
                dateStr = dayformatter.format(calendar.getTime());
                data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: 0 }";
                for (Order ord : ords) {
                    if (dayformatter.format(ord.getDate()).equals(dayformatter.format(calendar.getTime()))) {
                        data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: " + ord.getTotal() + " }";
                    }
                }
                monthly.add(data);
                int currentMonth = calendar.get(Calendar.MONTH);
                if (currentMonth == 0) { // January
                    calendar.set(Calendar.MONTH, 11); // December of previous year
                    calendar.set(Calendar.YEAR, currentYear - 1); // Previous year
                } else {
                    calendar.add(Calendar.MONTH, -1); // Previous month
                }
                count += 1;
            }
            request.setAttribute("monthly", String.join(", ", monthly));
            //Annually
            ords = ordDAO.getAnnuallySale();
            List<String> annually = new ArrayList<>();
            calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, 0);
            SimpleDateFormat yearformatter = new SimpleDateFormat("yyyy");
            currentYear = calendar.get(Calendar.YEAR);
            for (count = 1; count <= 3; count++) {
                dateStr = dayformatter.format(calendar.getTime());
                data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: 0 }";
                for (Order ord : ords) {
                    if (yearformatter.format(ord.getDate()).equals(yearformatter.format(calendar.getTime()))) {
                        data = "{ x: Date.parse('" + dateStr + " GMT+0700')" + ", y: " + ord.getTotal() + " }";
                    }
                }
                calendar.set(Calendar.YEAR, currentYear -= 1);
                annually.add(data);
            }
            request.setAttribute("annually", String.join(", ", annually));

            //Bestsellers
            ords = ordDAO.getBestsellers();
            List<Product> bestseller = new ArrayList<>();
            for(Order ord: ords) {
                Product pro = proDAO.detailOfOneProduct(ord.getProductId());
                String img = pro.getImage();
                String name = pro.getName();
                int sold = ord.getQuantity();
                int total = ord.getTotal();
                bestseller.add(new Product(name, img, total, sold));            
            }
            request.setAttribute("BEST_SELLER", bestseller);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.now();
            session.setAttribute("TIME", dtf.format(localTime));
            url = SUCCESS;
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
