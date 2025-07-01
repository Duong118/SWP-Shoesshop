/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.model;

import com.swp.shoeshop.constant.ProductStatus;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 
 */
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String image;
    private int originalPrice;
    private int discountPrice;
    private String description;
    private String shortDescription;
    private int point;
    private int cateId;
    private Date createdDate;
    private boolean hot;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public Product() {
    }

    public Product(int id, String name, String image, int originalPrice, int discountPrice, String description, String shortDescription, int point, int cateId, Date createdDate, ProductStatus status, boolean hot) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.point = point;
        this.cateId = cateId;
        this.createdDate = createdDate;
        this.status = status;
        this.hot = hot;
    }

    public Product(String name, String image, int originalPrice) {
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
    }
    

    public Product(String name, String image, int originalPrice, int discountPrice, String description, String shortDescription, int point, int cateId, ProductStatus status) {
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.point = point;
        this.cateId = cateId;
        this.status = status;
    }

    public Product(int id, String name, String image, int originalPrice, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.description = description;
    }

    public Product(int id, String name, String image, int originalPrice, int discountPrice, int cateId, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.cateId = cateId;
        this.status = ProductStatus.valueOf(status);
    }

    public Product(int id, String name, String image, int originalPrice, int discountPrice, String description, String shortDescription, int point, int cateId, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.point = point;
        this.cateId = cateId;
        this.status = ProductStatus.valueOf(status);
    }

    public Product(int id, String name, String image, int originalPrice, int discountPrice, String description, String shortDescription, int point, int cateId, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.point = point;
        this.cateId = cateId;
        this.status = status;
    }

    public Product(int id, String name, String image, int originalPrice, int point) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.point = point;
    }
    
    public Product(int id, String name, String image, int originalPrice, int discountPrice, int point) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.point = point;
    }

    public Product(int id, String name, String image, int originalPrice, int discountPrice, int point, Date createdDate, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.point = point;
        this.createdDate = createdDate;
        this.status = status;
    }
    
    //For recommendations section
    public Product(String name, String image, int originalPrice, int discountPrice, int id){
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
    }

    public Product(String name, String image, int originalPrice, int discountPrice, String description, String shortDescription, int point) {
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.point = point;
    }

    public Product(int id, String name, String image, int originalPrice, int discountPrice, int point, int cateId, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.point = point;
        this.cateId = cateId;
        this.status = ProductStatus.valueOf(status);
    }
    
    // Additional constructors needed by various controllers and DAOs
    
    // Constructor with 10 parameters for CreateProductController
    public Product(String name, String image, int originalPrice, int discountPrice, String description, 
                  String shortDescription, int point, int cateId, int catId, ProductStatus status) {
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.point = point;
        this.cateId = cateId;
        this.status = status;
    }
    
    // Constructor with 11 parameters for UpdateProductController
    public Product(int id, String name, String image, int originalPrice, int discountPrice, String description,
                  String shortDescription, int point, int cateId, int catId, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.point = point;
        this.cateId = cateId;
        this.status = status;
    }
    
    // Constructor with 9 parameters for ProductRecommendDAOImpl (with quantity)
    public Product(int id, String name, String image, int originalPrice, int discountPrice, 
                  int quantity, int point, int cateId, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.point = point;
        this.cateId = cateId;
        this.status = ProductStatus.valueOf(status);
        // Note: quantity is deprecated but kept for backwards compatibility
    }
    
    // Constructor for DashboardController bestseller display
    public Product(String name, String image, int total, int sold) {
        this.name = name;
        this.image = image;
        this.originalPrice = total;  // Using originalPrice to store total value
        this.discountPrice = sold;   // Using discountPrice to store sold quantity
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

}
