<%-- 
    Document   : register
    Created on : Jun 26, 2025, 11:13:17 AM
    Author     : Linh
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" type="image/x-icon">
        <title>Sign Up - Premium Shoe Store</title>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>  
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>


    <body>

        <div class="container" style="width: 460px;">
            <div class="title-text">
                <div class="title logo">
                    <a class="navbar-brand" href="MainController?action=Home"><img src="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" class="logo" alt="" width="60px"></a>                    <div class="login">
                        Sign Up
                    </div>
                </div>
            </div>

            <div class="form-inner">   
                <form action="MainController" method="POST">    
                    <div class="pass-link">
                        <a>${requestScope.SIGNUP_ERROR}</a>
                    </div>
                    <div class="field">
                        <input type="text" placeholder="Nhập Tên Tài Khoản" name="txtRegisterUsername" value ="${param.txtRegisterUsername}" required>
                    </div>

                    <div class="field">
                        <input type="password" placeholder="Nhập Mật Khẩu" name="txtRegisterPassword" required>
                    </div>

                    <div class="field">
                        <input type="password" placeholder="Nhập Lại Mật Khẩu" name="txtConfirmPassword" required>
                    </div>

                    <div class="field">
                        <input type="text" placeholder="Email" name="txtEmail" value="${param.txtEmail}" required>
                    </div></br>


                    <!-- reCAPTCHA -->
                    <div class="g-recaptcha" data-callback="recaptchaCallback" data-sitekey="6LdV2rYiAAAAAHIWwgyEDv4N4a99m0EBDXPM-5vs">
                    </div>
                    <div class="field btn">
                        <div class="btn-layer"></div> 
                        <button style = "color: #fff; height: 100%; width: 100%; z-index: 1; position: relative; background: none; border: none; padding-left: 0; border-radius: 5px; font-size: 20px; font-weight: 500; cursor: pointer" type="submit" value="Signup" name="action">Sign up</button> 
                    </div> 
                    <div class="signup-link"> 
                        Already Have an Account?<a href="login.jsp">Sign In</a> 
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
