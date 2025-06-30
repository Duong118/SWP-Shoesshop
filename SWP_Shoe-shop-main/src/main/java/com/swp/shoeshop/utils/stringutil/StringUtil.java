/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swp.shoeshop.utils.stringutil;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *

 * @author ThuongNVCE181966


 */
public class StringUtil {
public static String removeAccent(String s) {
  
  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
  return pattern.matcher(temp).replaceAll("");
 }
}