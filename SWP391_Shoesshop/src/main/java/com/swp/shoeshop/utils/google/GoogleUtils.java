/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.shoeshop.utils.google;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.swp.shoeshop.model.Account;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author 
 */
public class GoogleUtils {

    public static String getAccessToken(final String refreshToken) throws ClientProtocolException, IOException {
        String response = Request.Post(GoogleConstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GoogleConstant.GOOGLE_CLIENT_ID)
                        .add("redirect_uri", GoogleConstant.GOOGLE_REDIRECT_URI).add("code", refreshToken)
                        .add("grant_type", GoogleConstant.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jsonObject.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static Account getUserInfor(final String accessToken) throws ClientProtocolException, IOException {
        String link = GoogleConstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        Account account = new Gson().fromJson(response, Account.class);
        System.out.println(account);
        return account;
    }
}
