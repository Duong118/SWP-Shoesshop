<%-- 
    Document   : cart
    Created on : Jul 1, 2025, 1:43:25 PM
    Author     : Admin
--%>


<%@page import="com.swp.shoeshop.model.Cart"%>
<%@page import="com.swp.shoeshop.model.CartItem"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!-- Basic -->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">        <!-- Site Metas -->
        <title>Shopping Cart - Premium Shoe Store</title>
        <meta name="keywords" content="shopping cart, shoes, checkout">
        <meta name="description" content="Review your selected shoes before checkout">
        <meta name="author" content="Premium Shoe Store">

        <!-- Site Icons -->
        <link rel="shortcut icon" href="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" type="image/x-icon">
        <link rel="apple-touch-icon" href="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Site CSS -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive CSS -->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/custom.css">

        <link rel="stylesheet" href="css/sweetalertAccWarning.css">
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->        <style>
            .update-cart{
                border: none;
                outline: none;
                color:#FFFFFF;
                background:#000000;
                font-weight: 500;
                cursor: pointer;
                height: 50px;
                padding:0 24px;
                text-transform: uppercase;
                margin-left: 358px;
                transition: all 0.3s ease 0s;
            }
            .update-cart:hover{
                background: #16c6c6;
            }

            /* Size column styling */
            .size-pr {
                text-align: center;
                vertical-align: middle;
                width: 80px;
                min-width: 80px;
            }

            .size-pr span {
                white-space: nowrap;
                font-family: 'Arial', sans-serif;
            }

            /* Responsive table adjustments */
            @media (max-width: 768px) {
                .size-pr {
                    width: 60px;
                    min-width: 60px;
                }
                .size-pr span {
                    padding: 2px 4px;
                    font-size: 12px;
                }
            }
        </style>
    </head>

    <body>
        <!-- Start Main Top -->
        <div class="main-top">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="custom-select-box">
                            <select id="basic" class="selectpicker show-tick form-control" data-placeholder="$ USD">
                                <option>${sessionScope.LOGIN_USER.points} Coin</option>
                            </select>
                        </div>
                        <div class="right-phone-box">
                            <p>Hotline :- <a href="#"> +87378873548</a></p>
                        </div>
                        <c:set var="checkLogin" scope="session" value="${sessionScope.LOGIN_USER}"/>
                        <c:if test="${checkLogin != null}">
                            <div class="our-link">
                                <ul>
                                    <li><a href="my-account.jsp"><i class="fa fa-user s_color"></i> ${sessionScope.LOGIN_USER.username}</a></li>
                                    <li><a href="https://facebook.com/" target="_blank"><i class="fas fa-headset"></i> Contact</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="login-box">

                                <a href="MainController?action=Logout" style="color: #FFFFFF;font-size: 14px;font-weight: 700;text-transform: uppercase">Logout <i class="fas fa-sign-out-alt"></i></a>
                            </div>
                        </c:if>
                        <c:if test="${checkLogin == null}" >
                            <div class="our-link">
                                <ul>
                                    <li>
                                        <button id="swa" style="color: white; background-color: black; text-transform: uppercase; font-weight: bold">
                                            <i class="fa fa-user s_color"></i> 
                                            Account
                                        </button>
                                    </li>
                                    <li><a href="https://facebook.com/" target="_blank"><i class="fab fa-facebook"></i> Facebook</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="login-box" style="margin-right: 20px">
                                <a href="login.jsp" style="color: white;font-weight: bold">Log in/</a>
                                <a href="register.jsp" style="color: white; position: absolute;font-weight: bold">Register</a>
                            </div>
                        </c:if>
                        <div class="text-slid-box">
                            <div id="offer-box" class="carouselTicker">
                                <ul class="offer-box">
                                    <li>
                                        <i class="fab fa-opencart"></i> Welcome to the shoe shop
                                    </li>
                                    <li>
                                        <i class="fab fa-opencart"></i> Here we have all kinds of shoes and related accessories
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Main Top -->

        <!-- Start Main Top -->
        <header class="main-header">
            <!-- Start Navigation -->
            <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
                <div class="container">
                    <!-- Start Header Navigation -->
                    <div class="navbar-header">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                            <i class="fa fa-bars"></i>
                        </button>
                        <a class="navbar-brand" href="/NestSA/"><img src="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" class="logo" alt=""></a>
                    </div>
                    <!-- End Header Navigation -->

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="navbar-menu">
                        <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                            <li class="nav-item"><a class="nav-link" href="MainController?action=Home">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="about.jsp">Introduce</a></li>
                            <li class="dropdown">
                                <a href="MainController?action=GetFullProducts" class="nav-link">Product</a>                           
                            </li>
                            <li class="nav-item"><a class="nav-link" href="MainController?action=GetBlogs">Blog</a></li>
                            <li class="nav-item"><a class="nav-link" href="contact-us.jsp">Contact</a></li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->

                    <!-- Start Atribute Navigation -->
                    <div class="attr-nav">
                        <ul>
                            <li class="side-menu">
                                <a href="MainController?action=GetCart">
                                    <i class="fa fa-shopping-cart active"></i>
                                    <span class="badge">${sessionScope.QUANTITY_IN_CART}</span>
                                    <p class="active">Shopping Cart</p>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- End Atribute Navigation -->
                </div>
            </nav>
            <!-- End Navigation -->
        </header>
        <!-- End Main Top -->
        <!-- Start All Title Box -->
        <div class="all-title-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>Cart</h2>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="">Shop</a></li>
                            <li class="breadcrumb-item active">Cart</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End All Title Box -->
        <c:if test="${sessionScope.CART != null}">
            <!-- Start Cart  -->
            <c:if test="${requestScope.ERROR_AMOUNT != null}">
                <div style="display: flex; justify-content: center; margin-top: 20px">
                    <h3 class="text-danger" style="display: inline-block; font-weight: 700">${requestScope.ERROR_AMOUNT}</h3>
                </div>
            </c:if>
            <div class="cart-box-main">
                <div class="container">
                    <form action="MainController">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="table-main table-responsive">
                                    <table class="table">                                        <thead>
                                            <tr>
                                                <th>Image</th>
                                                <th>Product Name</th>
                                                <th>Size</th>
                                                <th>Unit Price</th>
                                                <th class="text-center">Quantity</th>
                                                <th>Total Price</th>
                                                <th>Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="item" items="${sessionScope.CART}">                                                <tr>
                                                    <td class="thumbnail-img">
                                                        <a href="#">
                                                            <img class="img-fluid" src="${item.getProduct().getImage()}" alt="" />
                                                        </a>
                                                    </td>
                                                    <td class="name-pr">
                                                        <a href="MainController?action=Detail&pid=${item.getProduct().getId()}">
                                                            ${item.getProduct().getName()}
                                                        </a>
                                                    </td>
                                                    <td class="size-pr">
                                                        <span style="display: inline-block; padding: 4px 8px; background-color: #f8f9fa; border: 1px solid #dee2e6; border-radius: 4px; font-size: 14px; font-weight: 500; color: #495057;">
                                                            ${item.getSizeName() != null ? item.getSizeName() : 'N/A'}
                                                        </span>
                                                    </td>
                                                    <td class="price-pr">
                                                        <p>
                                                            <c:set var="pri" value="${item.getProduct().getDiscountPrice()}"/>
                                                            <fmt:setLocale value="vi_VN"/>
                                                            <fmt:formatNumber value="${pri}" type="currency"/></p>
                                                    </td>                                                    <td class="quantity-box">
                                                        <input type="hidden" name="id" value="${item.getProduct().getId()}"/>
                                                        <input type="hidden" name="sizeId" value="${item.getSizeId()}"/>
                                                        <input type="number" size="4"  name="amount" value="${item.getAmount()}" min="1" step="1" class="c-input-text qty text"/>
                                                    </td>
                                                    <td class="total-pr">
                                                        <p>
                                                            <c:set var="priTotal" value="${item.getAmount() * item.getProduct().getDiscountPrice()}" />
                                                            <fmt:setLocale value="vi_VN"/>
                                                            <fmt:formatNumber value="${priTotal}" type="currency"/></p>
                                                    </td>
                                                    <td class="remove-pr">
                                                        <a href="MainController?action=RemoveFromCart&id=${item.getProduct().getId()}&sizeId=${item.getSizeId()}">
                                                            <i class="fas fa-times"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <h3 style="width: 100%; display: block; text-align: right">Tổng số lượng sản phẩm: ${sessionScope.TOTAL_QUANTITY}</h3>

                        <div class="row my-5" style="display: flex;
                             justify-content: space-between;">
                            <div></div>
                            <div style="display: inline-block !important">
                                <div class="update-box" style="display: inline-block !important">
                                    <button type="submit" name="action" value="UpdateCart" class="update-cart">
                                        Update cart                                    </button>
                                    <!--<input value="Cập nhật giỏ hàng" type="submit">-->
                                </div>
                            </div>
                        </div>
                    </form>

                    <div class="row my-5">
                        <div class="col-lg-8 col-sm-12"></div>
                        <div class="col-lg-4 col-sm-12">
                            <div class="order-box">
                                <h2>Order summary</h2>
                                <div class="d-flex gr-total">
                                    <h5 s>Total Cart</h5>                                    
                                    <div class="ml-auto h5" style="font-size: 16px">
                                        <c:set var="finalTotal" value="${sessionScope.TOTAL}"/>
                                        <fmt:setLocale value="vi_VN"/>
                                        <fmt:formatNumber value="${finalTotal}" type="currency"/></div>
                                </div>
                                <c:if test="${sessionScope.LOGIN_USER != null}">
                                    <div class="d-flex gr-total">
                                        <h5>Total coins to receive</h5>                                        
                                        <div class="ml-auto h5" style="font-size: 16px">${sessionScope.TOTAL_POINTS} xu</div>
                                    </div>
                                </c:if>
                                <hr> </div>
                        </div>
                        <div class="col-12 d-flex shopping-box"><a href="checkout.jsp" class="ml-auto btn hvr-hover">Pay</a> </div>
                    </div>

                </div>
            </div>
        </c:if>
        <!-- End Cart -->


        <!-- Start Footer  -->
        <footer>
            <div class="footer-main">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4 col-md-12 col-sm-12">
                            <div class="footer-top-box">
                                <h3>Business Hours</h3>
                                <ul class="list-time">
                                    <li>Monday - Friday: 08.00am to 05.00pm</li> <li>Saturday - Sunday: 10.00am to 08.00pm</li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-12 col-sm-12">
                            <div class="footer-top-box">
                                <h3>Contact</h3>
                                <form class="newsletter-box" action="NotifyEmailController" >
                                    <div class="form-group">
                                        <input class="" type="email" name="Email" placeholder="Email Address*" />
                                        <i class="fa fa-envelope"></i>
                                    </div>
                                    <button class="btn hvr-hover" type="submit">Enter</button>
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-12 col-sm-12">
                            <div class="footer-top-box">
                                <h3>Social Media</h3>
                                <p>Social Media We Use</p>
                                <ul>
                                    <li><a href="#"><i class="fab fa-facebook" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-twitter" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-linkedin" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-google-plus" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fa fa-rss" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-pinterest-p" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-whatsapp" aria-hidden="true"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-lg-4 col-md-12 col-sm-12">
                            <div class="footer-link-contact">
                                <h4>Contact us</h4>                                
                                <ul>
                                    <li>
                                        <p><i class="fas fa-map-marker-alt"></i>600 Nguyễn Văn Cừ Nối Dài,<br/>An Bình, Bình Thủy,<br/>Cần Thơ.</p>
                                    </li>
                                    <li>
                                        <p><i class="fas fa-phone-square"></i>Phone: <a href="tel:+1-888705770">076 33 88 151</a></p>
                                    </li>
                                    <li>
                                        <p><i class="fas fa-envelope"></i>Email: <a href="mailto:contactinfo@gmail.com">daihocfpt@fpt.edu.vn</a></p>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-8 col-md-12 col-sm-12">
                            <div class="footer-link-contact">
                                <iframe 
                                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3929.0533542574994!2d105.72985131138422!3d10.012451790052275!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31a0882139720a77%3A0x3916a227d0b95a64!2sFPT%20University!5e0!3m2!1svi!2s!4v1750843383851!5m2!1svi!2s" 
                                    width="100%" 
                                    height="230" 
                                    style="border:0;" 
                                    allowfullscreen="" 
                                    loading="lazy" 
                                    referrerpolicy="no-referrer-when-downgrade">
                                </iframe>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- End Footer  -->

        <!-- Start copyright  -->
        <div class="footer-copyright">
            <p class="footer-company">All Rights Reserved. &copy; 2023 Design By : Team FE
        </div>
        <!-- End copyright  -->

        <a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>

        <!-- ALL JS FILES -->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- ALL PLUGINS -->
        <script src="js/jquery.superslides.min.js"></script>
        <script src="js/bootstrap-select.js"></script>
        <script src="js/inewsticker.js"></script>
        <script src="js/bootsnav.js."></script>
        <script src="js/images-loded.min.js"></script>
        <script src="js/isotope.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/baguetteBox.min.js"></script>
        <script src="js/form-validator.min.js"></script>
        <script src="js/contact-form-script.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/sweetalert.min.js"></script>
        <script src="js/sweetalertAccWarning.js"></script>       

        <!-- Messenger Plugin chat Code -->
        <div id="fb-root"></div>

        <!-- Your Plugin chat code -->
        <div id="fb-customer-chat" class="fb-customerchat">
        </div>

        <script>
            var chatbox = document.getElementById('fb-customer-chat');
            chatbox.setAttribute("page_id", "109808202035372");
            chatbox.setAttribute("attribution", "biz_inbox");
        </script>

        <!-- Your SDK code -->
        <script>
            window.fbAsyncInit = function () {
                FB.init({
                    xfbml: true,
                    version: 'v16.0'
                });
            };

            (function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = 'https://connect.facebook.net/vi_VN/sdk/xfbml.customerchat.js';
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>

    </body>

</html>
