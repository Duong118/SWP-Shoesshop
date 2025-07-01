/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.model;

import java.io.Serializable;

/**
 *
 * @author TrucBQCE181355
 */
public class CartItem implements Serializable {

    private Product product;
    private int amount;
    private int sizeId;
    private String sizeName;

    public CartItem() {
    }

    public CartItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public CartItem(Product product, int amount, int sizeId, String sizeName) {
        this.product = product;
        this.amount = amount;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public void increaseQuantity() {
        this.amount += 1;
    }

    public void decreaseQuantity() {
        if (this.amount > 0) {
            this.amount -= 1;
        } else {
            throw new IllegalArgumentException("Quantity can not be negative!");
        }
    }
}
