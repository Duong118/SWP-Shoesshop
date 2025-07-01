/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.model;

import com.swp.shoeshop.constant.OrderStatus;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 
 */
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer userId;
    private int productId;
    private String productName;
    private int price;
    private int quantity;
    private int sizeId; // Added for product size tracking
    private String sizeName; // Added for displaying size name
    private int total;
    private Date date;
    private String username;
    private String phone;
    private String address;
    private String email;
    private int paymentId;
    private Integer totalPoints;
    private String applyPoints;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
    }

    public Order(Integer totalPoints, String applyPoints) {
        this.totalPoints = totalPoints;
        this.applyPoints = applyPoints;
    }

    public Order(int total, Date date) {
        this.total = total;
        this.date = date;
    }

    public Order(int productId, int quantity, int total) {
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
    }    

    public Order(int id, int userId, int total, Date date, String username, String phone, String address, String email, int paymentId, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.date = date;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.paymentId = paymentId;
        this.status = status;
    }

    public Order(int id, int productId, String productName, int quantity, int price) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Order(int id, int productId, String productName, int quantity, int price, String status) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.status = OrderStatus.valueOf(status);
    }

    public Order(int id, int userId, String productName, int price, int quantity, int total, Date date, String username, String phone, String address, String email, int paymentId, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.paymentId = paymentId;
        this.status = status;
    }

    public Order(int id, int userId, int total, Date date, String username, String phone, String address, String email, int paymentId, OrderStatus status, Integer totalPoints, String applyPoints) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.date = date;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.paymentId = paymentId;
        this.status = status;
        this.applyPoints = applyPoints;
        this.totalPoints = totalPoints;
    }

    // Constructor for order detail with size information
    public Order(int id, int productId, String productName, int quantity, int price, int sizeId, String sizeName) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.sizeId = sizeId;
        this.sizeName = sizeName; // Initialize sizeName
    }

    // Constructor for order detail with size information and status
    public Order(int id, int productId, String productName, int quantity, int price, int sizeId, String sizeName, String status) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.status = OrderStatus.valueOf(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(String status) {
        this.status = OrderStatus.valueOf(status);
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getApplyPoints() {
        return applyPoints;
    }

    public void setApplyPoints(String applyPoints) {
        this.applyPoints = applyPoints;
    }

}
