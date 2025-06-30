/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author TrucBQCE181355
 */
public class Cart {

    private int id;
    private int accountID;
    private List<CartItem> items = new ArrayList();

    public Cart() {
    }

    public Cart(int id, int accountID, List<CartItem> cartItems) {
        this.id = id;
        this.accountID = accountID;
        this.items = cartItems;
    }

    public Cart(int id, int accountID) {
        this.id = id;
        this.accountID = accountID;
    }
    
    public Cart(List<CartItem> cartItems) {
        this.items = cartItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountID() {
        return accountID;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void removeItem(int productId) {
        for (Iterator<CartItem> iterator = items.iterator(); iterator.hasNext();) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == productId) {
                iterator.remove();
                break;
            }
        }
    }

    public void clear() {
        items.clear();
    }

    public CartItem getItemByProductId(int productId) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                return item;
            }
        }
        return null;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (CartItem item : items) {
            totalQuantity += item.getAmount();
        }
        return totalQuantity;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getProduct().getDiscountPrice()* item.getAmount();
        }
        return totalPrice;
    }

    public int getTotalPoints() {
        int totalPoint = 0;
        for (CartItem item : items) {
            totalPoint += item.getProduct().getPoint() * item.getAmount();
        }
        return totalPoint;
    }

    public int getTotalPriceAppliedPoints(int points) {
        int totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getProduct().getDiscountPrice()* item.getAmount();
        }
        return totalPrice - points;
    }
}
