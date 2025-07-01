<%-- 
    Document   : blogdetails
    Created on : Jun 30, 2025, 9:24:17 PM
    Author     : Thuong
--%>


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
        <title>${sessionScope.POST_DETAILS.title}</title>        <meta name="keywords" content="shoe fashion, footwear trends, style guide">
        <meta name="description" content="Detailed article about shoe fashion and trends">
        <meta name="author" content="Premium Shoe Store">

        <!-- Site Icons -->
        <link rel="shortcut icon" href="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" type="image/x-icon">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Site CSS -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive CSS -->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/custom.css">

        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>
        <!-- Start Main Top -->
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

                                <a href="MainController?action=Logout" style="color: white">Logout</a>
                            </div>
                        </c:if>
                        <c:if test="${checkLogin == null}" >
                            <div class="our-link">
                                <ul>
                                    <li><a href="#"><i class="fa fa-user s_color"></i> Account</a></li>
                                    <li><a href="https://facebook.com/" target="_blank"><i class="fas fa-headset"></i> Contact</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="login-box" style="margin-right: 20px">
                                <a href="login.jsp" style="color: white; font-size: 19;font-weight: bold">Login/</a>
                                <a href="login.jsp" style="color: white; position: absolute;font-size: 19;font-weight: bold">Register</a>

                            </div>
                        </c:if>
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
                        <a class="navbar-brand" href="index.jsp"><img src="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" class="logo" alt=""></a>
                    </div>
                    <!-- End Header Navigation -->

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="navbar-menu">
                        <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                            <li class="nav-item"><a class="nav-link" href="MainController?action=Home">Homepage</a></li>
                            <li class="nav-item"><a class="nav-link" href="about.jsp">Introduction</a></li>
                            <li class="dropdown">
                                <a href="MainController?action=GetFullProducts" class="nav-link">Products</a>                           
                            </li>
                            <li class="nav-item active"><a class="nav-link" href="MainController?action=GetBlogs">Blog</a></li>
                            <li class="nav-item"><a class="nav-link" href="contact-us.jsp">Contact</a></li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->

                    <!-- Start Atribute Navigation -->
                    <div class="attr-nav">
                        <ul>
                            <li class="search"><a href="#"><i class="fa fa-search"></i></a></li>
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
                <!-- Start Side Menu -->
                <div class="side">
                    <a href="#" class="close-side"><i class="fa fa-times"></i></a>
                    <li class="cart-box">
                        <ul class="cart-list">
                            <li>
                                <a href="#" class="photo"><img src="images/img-pro-01.jpg" class="cart-thumb" alt="" /></a>
                                <h6><a href="#">Delica omtantur </a></h6>
                                <p>1x - <span class="price">$80.00</span></p>
                            </li>
                            <li>
                                <a href="#" class="photo"><img src="images/img-pro-02.jpg" class="cart-thumb" alt="" /></a>
                                <h6><a href="#">Omnes ocurreret</a></h6>
                                <p>1x - <span class="price">$60.00</span></p>
                            </li>
                            <li>
                                <a href="#" class="photo"><img src="images/img-pro-03.jpg" class="cart-thumb" alt="" /></a>
                                <h6><a href="#">Agam facilisis</a></h6>
                                <p>1x - <span class="price">$40.00</span></p>
                            </li>
                            <li class="total">
                                <a href="#" class="btn btn-default hvr-hover btn-cart">View Cart</a>
                                <span class="float-right"><strong>Total amount:</strong>: $180.00</span>
                            </li>
                        </ul>
                    </li>
                </div>
                <!-- End Side Menu -->
            </nav>
            <!-- End Navigation -->
        </header>
        <!-- End Main Top -->

        <!-- Start Top Search -->
        <div class="top-search">
            <div class="container">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-search"></i></span>
                    <input type="text" class="form-control" placeholder="Search">
                    <span class="input-group-addon close-search"><i class="fa fa-times"></i></span>
                </div>
            </div>
        </div>
        <!-- End Top Search -->

        <!-- Start All Title Box -->
        <div class="all-title-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>Blog</h2>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.jsp">Homepage</a></li>
                            <li class="breadcrumb-item active"><a href="GetBlogManagerController">Blog</a></li>
                            <li class="breadcrumb-item active">${sessionScope.POST_DETAILS.title}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End All Title Box -->

        <!-- Start Gallery  -->
        <div class="products-box">
            <div class="container">
                <h1>${sessionScope.POST_DETAILS.title}</h1>
                <p>${sessionScope.POST_DETAILS.date}</p>
                <img src="${sessionScope.POST_DETAILS.image}" style=" height: 300px; width: 450px; left: 30%; position: relative;" alt="">
                <!--<h1 data-tag="h1" class="sc-hBMUJo ipJWM article-header" style=""></h1>-->
                <div>
                    ${sessionScope.POST_DETAILS.newsContent}
                </div>        
            </div>
        </div>
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
        <script src="js/form-validator.min.js"></script>
        <script src="js/contact-form-script.js"></script>
        <script src="js/custom.js"></script>
    </body>

</html>
