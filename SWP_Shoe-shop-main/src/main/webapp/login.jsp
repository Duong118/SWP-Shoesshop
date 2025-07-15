<%-- 
    Document   : login
    Created on : Jul 1, 2025, 1:05:38 PM
    Author     : linh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In - Premium Shoe Store</title>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>        
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <meta name="google-signin-client_id" content="226401211406-p2srasojviolamjpeshcstagc2lls5k2.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>

        <style>
            .show{
                cursor: pointer;
                position: absolute;
                right: 0;
                padding: 4px;
                z-index: 124;
                padding-top: 6px;
            }
        </style>
    </head>
    <body>      

        <div class="container" style="width: 460px;height: 700px;">           
            <div class="title-text">
                <div class="title logo">
                    <a class="navbar-brand" href="MainController?action=Home"><img src="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" class="logo" alt="" width="60px"></a>
                    <div class="login">
                        Login
                    </div>
                </div>
            </div>


            <div class="form-inner">
                <c:if test="${requestScope.alert!=null}"><script>alert('${requestScope.alert}');</script></c:if>
                <c:set var="cookie" value="${pageContext.request.cookies}"/>

                <form action="MainController" class="login" method="POST">
                    <div class="pass-link">
                        <a>${requestScope.LOGIN_ERROR}</a>
                    </div>
                    <div class="pass-link">
                        <a>${requestScope.MESSAGE}</a>
                    </div>                    <div class="field">
                        <input type="text" placeholder="Username" name="txtUserName" value="${param.txtUserName}" required>
                    </div>
                    <div class="field">
                        <i class="fa fa-eye" id="show" value="show" type = "button"></i>
                        <input type="password" placeholder="Password" name="txtPassword" value="${param.txtPassword}" required>
                    </div>
                    <div class="signup-link" style="font-size: 16px; margin: 15px 0">
                        Forgot password? <a href="forgotpass.jsp">Click here</a>
                    </div>
                    <div style="margin-bottom: 20px">
                        <input type="checkbox" ${(cookie.n4m3 != null ? 'checked':'')} name="remember" value="ON" style="margin-top: 20px"> Remember me</br>
                    </div>
                    
                    <div class="field btn">
                        <div class="btn-layer"></div>
                        <button style = "color: #fff; height: 100%; width: 100%; z-index: 1; position: relative; background: none; border: none; padding-left: 0; border-radius: 5px; font-size: 20px; font-weight: 500; cursor: pointer" type="submit" name="action" value="Login">Sign In</button>
                    </div>

                    <div class="signup-link" style="margin-top: 10px !important">
                        Don't Have an Account? <a href="register.jsp"> Register Now</a>                    </div>
                </form>

            </div>

        </div>
        <script language="javascript">
            const loginText = document.querySelector(".title-text .login");
            const loginForm = document.querySelector("form.login");
            const loginBtn = document.querySelector("label.login");
            const signupBtn = document.querySelector("label.signup");
            const signupLink = document.querySelector("form .signup-link a");
            signupBtn.onclick = (() => {
                loginForm.style.marginLeft = "-50%";
                loginText.style.marginLeft = "-50%";
            });
            loginBtn.onclick = (() => {
                loginForm.style.marginLeft = "0%";
                loginText.style.marginLeft = "0%";
            });
            signupLink.onclick = (() => {
                signupBtn.click();
                return false;
            });

            document.getElementById('show').onclick = function () {
                let password = document.getElementById('password');
                password.type = password.type === 'password' ? 'text' : 'password';
                this.classList.toggle('fa-eye-slash');

            };
        </script>  
    </body>
</html>