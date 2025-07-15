
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <!-- Basic -->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Site Metas -->
        <title>Forgot password</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Site Icons -->
        <link rel="shortcut icon" href="images/favicon.svg" type="image/svg+xml">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Site CSS -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive CSS -->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/custom.css">

        <link rel="stylesheet" href="css/sweetalertAccWarning.css">


        <style>
            .swp-pagination{
                display: flex;
                justify-content: center;
                width: 100%
            }
            .swp-pagination a{
                font-size: 24px;
                display: block;
                width: 40px;
                height: 40px;
                border-radius: 5px;
                text-align: center;
                transform: translateY(-56px);
                line-height: 40px
            }
            .swp-pagination-active{
                background: #b0b435;
                color: #fff;

            }
            .modal-swp{
                position: fixed;
                top:0;
                right: 0;
                bottom: 0;
                left: 0;
                background: rgba(0,0,0,0.4);
                z-index: 999999999;
                display: flex;
            }
            .modal-swp-message{
                background: white;
                width: 400px;
                margin: auto;
                border-radius: 2px;
                padding: 20px
            }
            .modal-swp-message h3{
                font-size: 20px
            }
            .modal-swp-message button{
                outline: none;
                border: none;
                border-radius: 4px;
                float: right;
                padding: 5px 20px;
                cursor: pointer;
                background: #B0B435;
                color: #fff;
            }
            .modal-swp-message button:hover{
                background: #000
            }
        </style>

    </head>

    <body>
        <div class="main-top">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="custom-select-box">
                            <select id="basic" class="selectpicker show-tick form-control" data-placeholder="$ USD">
                                <option>${sessionScope.LOGIN_USER.points} Xu</option>
                            </select>
                        </div>
                        <div class="right-phone-box">
                            <p>Hotline :- <a href="#"> +87378873548</a></p>
                        </div>
                        <c:if test="${sessionScope.LOGIN_USER != null}">
                            <div class="our-link">
                                <ul>
                                    <li><a href="my-account.jsp"><i class="fa fa-user s_color"></i>Hello ${sessionScope.LOGIN_USER.username}</a></li>
                                    <li><a href="https://facebook.com/" target="_blank"><i class="fas fa-headset"></i>Contact</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="login-box">

                                <a href="MainController?action=Logout" style="color: #FFFFFF;font-size: 14px;font-weight: 700;text-transform: uppercase">Logout <i class="fas fa-sign-out-alt"></i></a>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.LOGIN_USER == null}">
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
                                <a href="login.jsp" style="color: white;font-weight: bold">Login/</a>
                                <a href="register.jsp" style="color: white; position: absolute;font-weight: bold">Register</a>
                               
                            </div>
                        </c:if>
                        <div class="text-slid-box">
                            <div id="offer-box" class="carouselTicker">
                                <ul class="offer-box">
                                    <li>
                                        <i class="fab fa-opencart"></i> Welcome to the Shoes Shop
                                    </li>
                                    <li>
                                        <i class="fab fa-opencart"></i> We have a wide variety of shoes and related accessories.
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
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu"
                                aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                            <i class="fa fa-bars"></i>
                        </button>
                        <a class="navbar-brand" href="MainController?action=Home"><img src="images/favicon.svg" class="logo" alt=""></a>
                    </div>
                    <!-- End Header Navigation -->

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="navbar-menu">
                        <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                            <li class="nav-item"><a class="nav-link" href="MainController?action=Home">Homepage</a></li>
                            <li class="nav-item"><a class="nav-link" href="about.jsp"> Introduction</a></li>
                            <li class="dropdown">
                                <a href="MainController?action=GetFullProducts" class="nav-link">Products</a>                           
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
                                    <i class="fa fa-shopping-cart"></i>
                                    <span class="badge">${sessionScope.QUANTITY_IN_CART}</span>
                                    <p>Cart</p>
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
                    </div>
                </div>
            </div>
        </div>
        <!-- End All Title Box -->

        <!--Start form-->
        <div class="container mt-5 px-2" style="margin-bottom: 100px">
            <h1 class="m-5" style="font-size:30px; color: #16c6c6">Forgot Password</h1>
            <h3 class="m-5" style="font-size:18px; color: #16c6c6">Please enter the information below to change your password.</h3>
            <c:if test="${sessionScope.LOGIN_USER.password == null}">
                <h3 class="m-5 text-danger" style="font-size: 28px">Google accounts do not support password changes.</h3>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER.password != null}">
                <div class="col-md-8">
                    <div class="form-group">
                        <form action="MainController" method="POST">
                            <h2 style="font-size: 20px">Old password</h2>
                            <input type="password" placeholder="Old password" id="password" class="form-control" name="oldPassword" style="font-size: 16px" required/></br>
                            <h2 style="font-size: 20px">New password</h2>
                            <input type="password" placeholder="New password" id="password" class="form-control" name="newPassword" style="font-size: 16px" required/>
                            <h2 style="font-size: 20px; margin-top: 15px">Confirm new password</h2>
                            <input type="password" placeholder="Confirmed Password" id="confirmed_password" class="form-control" name="confirmedPassword" style="font-size: 16px" required/>
                            <button class="btn btn-dark mt-5" type="submit" name="action" value="ChangePassword" style="font-size: 16px">Confirm </button>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
        <!--End form-->
        <c:if test="${requestScope.MESSAGE != null}">
            <div class="container mt-2 px-lg-0">
                <h1 class="m-5" style="font-size:25px; color: green">${requestScope.MESSAGE}</h1>
            </div>
        </c:if>

        <!-- Start Footer  -->
        <!-- Start Footer  -->
        <footer>
            <div class="footer-main">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4 col-md-12 col-sm-12">
                            <div class="footer-top-box">
                                <h3>Working hours</h3>
                                <ul class="list-time">
                                    <li>Monday – Friday: 8:00 AM to 5:00 PM</li> <li>Saturday – Sunday: 10:00 AM to 8:00 PM</li>
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
                                <h3>Social media platforms</h3>
                                <p>Our social media channels</p>
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
                                        <p><i class="fas fa-map-marker-alt"></i>600 Nguyễn Văn Cừ Nối Dài,<br>An Bình, Bình Thủy, Cần Thơ</p>
                                    </li>
                                    <li>
                                        <p><i class="fas fa-phone-square"></i>Telephone: <a href="tel:+1-888705770">0763388151</a></p>
                                    </li>
                                    <li>
                                        <p><i class="fas fa-envelope"></i>Email: <a href="mailto:contactinfo@gmail.com">daihocfpt@fpt.edu.vn</a></p>
                                    </li>
                                </ul>
                            </div>
                        </div>
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
        </footer>
        <!-- End Footer  -->

        <!-- Start copyright  -->
        <div class="footer-copyright">
            <p class="footer-company">All Rights Reserved.
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
        <script src="js/jquery-ui.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/form-validator.min.js"></script>
        <script src="js/contact-form-script.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/sweetalert.min.js"></script>
        <script src="js/sweetalertAccWarning.js"></script>
    </body>
</html>
