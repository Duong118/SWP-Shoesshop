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
public class ShoeSize implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sizeName;
    private String sizeType;
    private int sortOrder;
    private boolean isActive;

    public ShoeSize() {
    }

    public ShoeSize(int id, String sizeName, String sizeType, int sortOrder, boolean isActive) {
        this.id = id;
        this.sizeName = sizeName;
        this.sizeType = sizeType;
        this.sortOrder = sortOrder;
        this.isActive = isActive;
    }

    public ShoeSize(String sizeName, String sizeType, int sortOrder, boolean isActive) {
        this.sizeName = sizeName;
        this.sizeType = sizeType;
        this.sortOrder = sortOrder;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ShoeSize{" + "id=" + id + ", sizeName=" + sizeName + ", sizeType=" + sizeType + ", sortOrder=" + sortOrder + ", isActive=" + isActive + '}';
    }
}
