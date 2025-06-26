/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.utils.cookiehandle;

import com.swp.shoeshop.model.CartItem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CartCookieHandler {

    private String name;
    private Cookie cookie;
    private ArrayList<CartItem> itemList;

    public CartCookieHandler() {
    }

    public CartCookieHandler(Cookie cookie, HttpServletRequest request) {
        this.cookie = cookie;
        checkListCookie(request);
    }

    public CartCookieHandler(String name, int days, String path) throws Exception {
        try {
            if (name == null) {
                throw new IllegalArgumentException("Name cannot be null.");
            }
            this.name = name;
            this.name = Base64.getEncoder().encodeToString(this.name.getBytes("UTF-8"));
            this.name = this.name.replaceAll("[^a-zA-Z0-9]", "_");
            this.cookie = new Cookie(this.name, "");
            this.cookie.setMaxAge(24 * 60 * 60 * days);
            this.cookie.setPath(path);
            this.cookie.setHttpOnly(true);
            this.itemList = new ArrayList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public ArrayList<CartItem> getItemList(HttpServletRequest request, boolean needCheck) {
        if (needCheck) {
            checkListCookie(request);
        }
        return this.itemList;
    }

    private String encryptData() throws IOException {
        byte[] dataEncrypt;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this.itemList);
            dataEncrypt = baos.toByteArray();
        }
        String tmp = Base64.getEncoder().encodeToString(dataEncrypt);
        return tmp;
    }

    private void setCartItemList(Cookie cookie) throws IOException {
        if (cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            byte[] cookieData = Base64.getDecoder().decode(cookie.getValue());
            try (ByteArrayInputStream bais = new ByteArrayInputStream(cookieData);
                    ObjectInputStream ois = new ObjectInputStream(bais)) {
                this.itemList = (ArrayList<CartItem>) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Error deserializing object from cookie", e);
            }
        }
    }

    private boolean checkListCookie(HttpServletRequest request) {
        ArrayList<CartItem> copiedList = new ArrayList(this.itemList);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals(this.name)) {
                    if ((this.cookie.getValue() != null && !this.cookie.getValue().equals(ck.getValue())) || (ck.getValue() != null && !ck.getValue().equals(this.cookie.getValue()))) {
                        boolean validData = true;
                        try {
                            setCartItemList(ck);
                        } catch (IOException ex) {
                            this.itemList = copiedList;
                            validData = false;
                        }
                        if (validData) {
                            this.cookie.setValue(ck.getValue());
                        }
                        check = true;
                    }
                    return check;
                }
            }
        }
        if (this.itemList == null || !this.itemList.isEmpty()) {
            this.itemList.clear();
        }
        return check;
    }

    public void addCartItemToCookie(CartItem item, HttpServletRequest request, HttpServletResponse response, boolean check) throws IOException {
        if (check) {
            checkListCookie(request);
        }
        boolean flag = false;
        for (CartItem cartItem : itemList) {
            if (cartItem.getProduct().getId() == item.getProduct().getId()) {
                cartItem.setAmount(cartItem.getAmount() + item.getAmount());
                flag = true;
                break;
            }
        }
        if (!flag) {
            this.itemList.add(item);
        }
        this.cookie.setValue(encryptData());
        response.addCookie(this.cookie);
    }

    public void updateAmount(int proId, int newAmount, HttpServletRequest request, HttpServletResponse response, boolean check) throws IOException {
        if (check) {
            checkListCookie(request);
        }
        for (CartItem cartItem : this.itemList) {
            if (cartItem.getProduct().getId() == proId) {
                cartItem.setAmount(newAmount);
                break;
            }
        }
        this.cookie.setValue(encryptData());
        response.addCookie(this.cookie);
    }

    public boolean updateAmount(ArrayList<Integer> proId, ArrayList<Integer> newAmount, HttpServletRequest request, HttpServletResponse response, boolean check) throws IOException {
        if (proId.size() != newAmount.size()) {
            return false;
        }
        if (check) {
            checkListCookie(request);
        }
        for (int i = 0; i < this.itemList.size(); i++) {
            for (int j = 0; j < proId.size(); j++) {
                if (proId.get(j) == this.itemList.get(i).getProduct().getId() && newAmount.get(j) != this.itemList.get(i).getAmount()) {
                    this.itemList.get(i).setAmount(newAmount.get(j));
                    break;
                }
            }
        }
        this.cookie.setValue(encryptData());
        response.addCookie(this.cookie);
        return true;
    }


    public void deleleCartItem(int proId, HttpServletRequest request, HttpServletResponse response, boolean check) throws IOException {
        if (check) {
            checkListCookie(request);
        }
        for (CartItem cartItem : this.itemList) {
            if (cartItem.getProduct().getId() == proId) {
                this.itemList.remove(cartItem);
                break;
            }
        }
        this.cookie.setValue(encryptData());
        response.addCookie(this.cookie);
    }

    public void deleteCookie(HttpServletResponse response) {
        this.cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
