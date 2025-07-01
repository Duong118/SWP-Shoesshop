<%-- 
    Document   : forgotpass
    Created on : Jul 1, 2025, 1:50:00 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="images/favicon.svg" type="image/svg+xml">
        <title>Forgot Password Page</title>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>  
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>


    <body>

        <div class="container" style="width: 460px;">
            <div class="title-text">
                <div class="title logo">
                    <a class="navbar-brand" href="MainController?action=Home"><img src="images/favicon.svg" class="logo" alt="" width="60px"></a>
                    <div class="login">
                        Reset Password
                    </div>
                </div>
            </div>

            <div class="form-inner">   
                <form action="MainController" method="POST">    
                    <div class="pass-link">
                        <a>${requestScope.MESSAGE}</a>
                    </div>
                    <div class="field">
                        <input type="text" placeholder="Nhập Tên Tài Khoản" name="username"" required>
                    </div>
                    <div class="field">
                        <input type="text" placeholder="Email" name="email" required>
                    </div></br>
                    
                    <div class="field btn">
                        <div class="btn-layer"></div>
                        <button style = "color: #fff; height: 100%; width: 100%; z-index: 1; position: relative; background: none; border: none; padding-left: 0; border-radius: 5px; font-size: 20px; font-weight: 500; cursor: pointer" type="submit" value="ForgotPass" name="action">Khôi phục</button>
                    </div>
                    <div class="signup-link">
                        Already have an account?<a href="login.jsp">Login</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
