/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.model;

import java.sql.Date;

/**
 *
 * @author 
 */
public class Feedback {
    private int userId;
    private int productId;
    private String username;
    private String feedbackContent;
    private double star;
    private Date date;
    private String proname;

    public Feedback() {
    }

    public Feedback(int userId, int productId, String username, String feedbackContent, double star, Date date, String proname) {
        this.userId = userId;
        this.productId = productId;
        this.username = username;
        this.feedbackContent = feedbackContent;
        this.star = star;
        this.date = date;
        this.proname = proname;
    }

    public Feedback(int userId, String username, String feedbackContent, double star, Date date) {
        this.userId = userId;
        this.username = username;
        this.feedbackContent = feedbackContent;
        this.star = star;
        this.date = date;
    }

    public Feedback(int userId, int productId, String username, String feedbackContent, double star) {
        this.userId = userId;
        this.productId = productId;
        this.username = username;
        this.feedbackContent = feedbackContent;
        this.star = star;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }
    
}
