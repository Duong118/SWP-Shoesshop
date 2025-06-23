/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.birdwebshop.constant;

/**
 *
 * @author Thuong
 */
public class Regex {
    public static final String MAIL_PATTERN = "^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]{1,}(\\.[a-zA-Z]{1,})+$";
    public static final String IMAGE_URL_PATTERN = "^https?://(?:www\\.)?[\\w\\d-]+(?:\\.[\\w\\d-]+)*(?::\\d+)?(?:/[^\\s]*)?$";                                                                     
    public static final String YOUTUBE_PATTERN = "(?:https?:\\/\\/)?(?:www\\.)?youtu\\.?be(?:\\.com)?\\/?.*(?:watch|embed)?(?:.*v=|v\\/|\\/)([\\w\\-_]+)\\&?";
}
