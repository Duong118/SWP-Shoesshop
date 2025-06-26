/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.utils.json;

import com.google.gson.Gson;
import java.io.IOException;

/**
 *
 * @author
 */
public class JSONConverter {
    public static String toJSON(Object object) throws IOException{
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
