/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author huynh
 */
public class ProductSize implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int productId;
    private int sizeId;
    private int quantity;
    private boolean isAvailable;
    
    // Additional fields for joined data
    private String sizeName;
    private String sizeType;
    private int sortOrder;

    public ProductSize() {
    }

    public ProductSize(int id, int productId, int sizeId, int quantity, boolean isAvailable) {
        this.id = id;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
    }

    public ProductSize(int productId, int sizeId, int quantity, boolean isAvailable) {
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
    }

    public ProductSize(int id, int productId, int sizeId, int quantity, boolean isAvailable, 
                      String sizeName, String sizeType, int sortOrder) {
        this.id = id;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
        this.sizeName = sizeName;
        this.sizeType = sizeType;
        this.sortOrder = sortOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "ProductSize{" + "id=" + id + ", productId=" + productId + ", sizeId=" + sizeId + 
               ", quantity=" + quantity + ", isAvailable=" + isAvailable + ", sizeName=" + sizeName + 
               ", sizeType=" + sizeType + ", sortOrder=" + sortOrder + '}';
    }
}
