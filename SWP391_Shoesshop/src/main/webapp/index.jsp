<%-- 
    Document   : index
    Created on : Jun 26, 2025, 11:09:45 AM
    Author     : Pham Tan Linh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <!-- Basic -->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">        <!-- Site Metas -->
        <title>Premium Shoe Store - Step Into Style</title>
        <meta name="keywords" content="shoes, sneakers, boots, fashion, footwear, premium">
        <meta name="description" content="Discover the finest collection of premium shoes for every occasion">
        <meta name="author" content="Shoe Store"

              <!-- Site Icons -->
              <link rel="shortcut icon" href="images/logo.png" type="image/x-icon">
        <link rel="apple-touch-icon" href="images/logo.png">

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
        <![endif]-->
        <link href="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/css/splide.min.css" rel="stylesheet">
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
                background: #16c6c6;
                color: #fff;
            }
            .modal-swp-message button:hover{
                background: #000
            }
        </style>
        <style>
            .splide {
                margin-bottom: 55px;
            }
            .splide__track {
                overflow: unset !important;
                overflow-x: clip !important;
            }
            .splide__arrow, .splide__pagination__page.is-active {
                background: #16c6c6;
            }
            .splide__pagination {
                bottom: -2em
            }
        </style>
    </head>
    <c:if test="${requestScope.alert!=null}"><script>alert('${requestScope.alert}');</script></c:if>
    <c:if test = "${requestScope.ALERT != null}">
        <script type="text/javascript">
            var msg = "${requestScope.ALERT}";
            alert(msg);
        </script>
    </c:if>

    <body>
        <!-- Start Main Top -->
        <div class="main-top">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">                        <div class="custom-select-box">
                            <select id="basic" class="selectpicker show-tick form-control" data-placeholder="$ USD">
                                <option>${sessionScope.LOGIN_USER.points} Points</option>
                            </select>
                        </div>
                        <div class="right-phone-box">
                            <p>Call Us :- <a href="#"> +1-800-SHOES-NOW</a></p>
                        </div>
                        <c:if test="${sessionScope.LOGIN_USER != null}">
                            <div class="our-link">
                                <ul>
                                    <li><a href="my-account.jsp"><i class="fa fa-user s_color"></i> Hello ${LOGIN_USER.username}</a></li>
                                    <li><a href="https://facebook.com/" target="_blank"><i class="fas fa-headset"></i> Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="login-box">
                                <a href="MainController?action=Logout" style="color: #FFFFFF;font-size: 14px;font-weight: 700;text-transform: uppercase">Logout <i class="fas fa-sign-out-alt"></i></a>
                            </div>
                        </c:if>                        <c:if test="${sessionScope.LOGIN_USER == null}">
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
                                <a href="login.jsp" style="color: white;font-weight: bold">Sign In/</a>
                                <a href="register.jsp" style="color: white; position: absolute;font-weight: bold">Sign Up</a>

                            </div>
                        </c:if>
                        <div class="text-slid-box">
                            <div id="offer-box" class="carouselTicker">
                                <ul class="offer-box">
                                    <li>
                                        <i class="fab fa-opencart"></i> Welcome to Premium Shoe Store
                                    </li>
                                    <li>
                                        <i class="fab fa-opencart"></i> Discover our exclusive collection of premium footwear
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
                        <a class="navbar-brand" href=""><img src="images/logo.png" class="logo" alt=""></a>
                    </div>
                    <!-- End Header Navigation -->

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="navbar-menu">                        <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                            <li class="nav-item active"><a class="nav-link" href="MainController?action=Home">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="about.jsp">About Us</a></li>
                            <li class="dropdown">
                                <a href="MainController?action=GetFullProducts" class="nav-link">Shoes</a>                           
                            </li>
                            <li class="nav-item"><a class="nav-link" href="MainController?action=GetBlogs">Blog</a></li>
                            <li class="nav-item"><a class="nav-link" href="contact-us.jsp">Contact</a></li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->

                    <!-- Start Atribute Navigation -->
                    <div class="attr-nav">
                        <ul>
                            <li class="side-menu">                                <a href="MainController?action=GetCart">
                                    <i class="fa fa-shopping-cart"></i>
                                    <span class="badge">${sessionScope.QUANTITY_IN_CART}</span>
                                    <p>Shopping Cart</p>
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
        <!--         Start Slider 
        -->        <div id="slides-shop" class="cover-slides">
            <ul class="slides-container">
                <li class="text-center">
                    <img src="https://cdn.pixabay.com/photo/2016/11/19/18/06/feet-1840619_1280.jpg" alt="">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h1 class="m-b-20"><strong>Welcome to <br> Shoe Shop</strong></h1>
                                <p class="m-b-40">Specializing in providing shoe products</p>
                                <p><a class="btn hvr-hover" href="about.jsp">Learn more</a></p>
                            </div>
                        </div>
                    </div>
                </li>                <li class="text-center">
                    <img src="https://cdn.shopify.com/s/files/1/0263/6270/8027/files/tenshi-air-max-zero.jpg?v=1589159833" alt="">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h1 class="m-b-20"><strong>Welcome to <br> Premium Shoe Store</strong></h1>
                                <p class="m-b-40">Step into style with our exclusive footwear collection</p>
                                <p><a class="btn hvr-hover" href="about.jsp">Discover More</a></p>
                            </div>
                        </div>
                    </div>
                </li>                <li class="text-center">
                    <img src="https://i.ebayimg.com/images/g/J~YAAOSwaqFneS16/s-l1200.jpg" alt="">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h1 class="m-b-20"><strong>Premium Footwear <br> For Every Occasion</strong></h1>
                                <p class="m-b-40">From casual sneakers to formal dress shoes</p>
                                <p><a class="btn hvr-hover" href="about.jsp">Shop Now</a></p>
                            </div>
                        </div>
                    </div>
                </li>                <li class="text-center">
                    <img src="https://static.ftshp.digital/img/p/6/1/3/8/6/61386.jpg" alt="">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h1 class="m-b-20"><strong>Athletic Excellence <br> Performance Shoes</strong></h1>
                                <p class="m-b-40">Professional athletic footwear for peak performance</p>
                                <p><a class="btn hvr-hover" href="about.jsp">Explore Collection</a></p>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="slides-navigation">
                <a href="#" class="next"><i class="fa fa-angle-right" aria-hidden="true"></i></a>
                <a href="#" class="prev"><i class="fa fa-angle-left" aria-hidden="true"></i></a>
            </div>
        </div><!--
         End Slider -->

        <!--  Products popular  -->
        <div class="products-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">                        <div class="title-all text-center">
                            <h1>Featured Shoes</h1>

                        </div>

                    </div>
                </div>
                <div>
                    <div class="splide hot_bird" role="group" aria-label="Splide Basic HTML Example">
                        <div class="splide__track">
                            <ul class="splide__list">
                                <c:forEach items="${requestScope.TOP7_HOT_BIRD}" var="o">
                                    <li class="splide__slide">
                                        <div class="product-card"  style="box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px; margin: 0 auto">
                                            <div class="product-front">
                                                <img src="${o.image}" alt="" />
                                                <div class="image_overlay"></div>
                                                <div class="view_details"><a href="MainController?action=Detail&pid=${o.id}">View details</a></div>
                                                <div class="stats">
                                                    <div class="stats-container">
                                                        <span class="product_name">${o.name}</span><br>
                                                        <span class="product_price">
                                                            <c:if test="${o.originalPrice != o.discountPrice}">
                                                                <del style="margin-right: 5px">
                                                                    <c:set var="pri" value="${o.originalPrice}"/>
                                                                    <fmt:setLocale value="vi_VN"/>
                                                                    <fmt:formatNumber value="${pri}" type="currency"/>
                                                                </del>
                                                            </c:if>
                                                            <span style="background: #16c6c6; color: white; padding: 5px; border-radius: 5px">
                                                                <c:set var="pri" value="${o.discountPrice}"/>
                                                                <fmt:setLocale value="vi_VN"/>
                                                                <fmt:formatNumber value="${pri}" type="currency"/>
                                                            </span>
                                                        </span>
                                                        <p style="margin-bottom: 20px">
                                                            <c:if test="${o.status == 'INSTOCK'}"><span class="text-success" style="font-weight: 700; font-size: 16px">In stock</span></c:if>
                                                            <c:if test="${o.status == 'OUTOFSTOCK'}"><span class="text-danger" style="font-weight: 700; font-size: 16px">Out of stock</span></c:if>
                                                            </p>
                                                            <div class="product-options">
                                                                <form action="AddToCartController" method="POST">
                                                                    <input type="hidden" name="id" value="${o.id}">
                                                                <input type="hidden" name="name" value="${o.name}">
                                                                <input type="hidden" name="price" value="${o.originalPrice}">
                                                                <input type="hidden" name="discount_price" value="${o.discountPrice}">
                                                                <input type="hidden" name="point" value="${o.point}">
                                                                <input type="hidden" name="image" value="${o.image}">
                                                                <input type="hidden" name="quantity" value="${o.quantity}">
                                                                <input type="hidden" name="buyQuantity" value="1"/>
                                                                <button type="submit" class="button" name="action" value="home">
                                                                    Add to cart
                                                                    <div class="button__horizontal"></div>
                                                                    <div class="button__vertical"></div>
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="splide hot_cage" role="group" aria-label="Splide Basic HTML Example">
                        <div class="splide__track">
                            <ul class="splide__list">
                                <c:forEach items="${requestScope.TOP7_HOT_CAGE}" var="o">
                                    <li class="splide__slide">
                                        <div class="product-card"  style="box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px; margin: 0 auto">
                                            <div class="product-front">
                                                <img src="${o.image}" alt="" />
                                                <div class="image_overlay"></div>
                                                <div class="view_details"><a href="MainController?action=Detail&pid=${o.id}">View details</a></div>
                                                <div class="stats">
                                                    <div class="stats-container">
                                                        <span class="product_name">${o.name}</span><br>
                                                        <span class="product_price">
                                                            <c:if test="${o.originalPrice != o.discountPrice}">
                                                                <del style="margin-right: 5px">
                                                                    <c:set var="pri" value="${o.originalPrice}"/>
                                                                    <fmt:setLocale value="vi_VN"/>
                                                                    <fmt:formatNumber value="${pri}" type="currency"/>
                                                                </del>
                                                            </c:if>
                                                            <span style="background: #16c6c6; color: white; padding: 5px; border-radius: 5px">
                                                                <c:set var="pri" value="${o.discountPrice}"/>
                                                                <fmt:setLocale value="vi_VN"/>
                                                                <fmt:formatNumber value="${pri}" type="currency"/>
                                                            </span>
                                                        </span>
                                                        <p style="margin-bottom: 20px">
                                                            <c:if test="${o.status == 'INSTOCK'}"><span class="text-success" style="font-weight: 700; font-size: 16px">In stock</span></c:if>
                                                            <c:if test="${o.status == 'OUTOFSTOCK'}"><span class="text-danger" style="font-weight: 700; font-size: 16px">Out of stock</span></c:if>
                                                            </p>
                                                            <div class="product-options">
                                                                <form action="AddToCartController" method="POST">
                                                                    <input type="hidden" name="id" value="${o.id}">
                                                                <input type="hidden" name="name" value="${o.name}">
                                                                <input type="hidden" name="price" value="${o.originalPrice}">
                                                                <input type="hidden" name="discount_price" value="${o.discountPrice}">
                                                                <input type="hidden" name="point" value="${o.point}">
                                                                <input type="hidden" name="image" value="${o.image}">
                                                                <input type="hidden" name="quantity" value="${o.quantity}">
                                                                <input type="hidden" name="buyQuantity" value="1"/>
                                                                <button type="submit" class="button" name="action" value="home">
                                                                    Add to cart
                                                                    <div class="button__horizontal"></div>
                                                                    <div class="button__vertical"></div>
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Products popular  -->
        <!--  New Products  -->
        <!-- End New Products   -->




        <!-- Start Categories  -->
        <div class="categories-shop">
            <div class="container">                <div class="title-all text-center">
                    <h1>Shop by Category</h1>
                </div>
                <div class="row justify-content-center display-flex  ">                    <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
                        <div class="shop-cat-box">
                            <img class="img-fluid" style="height: 250px" src="https://images.unsplash.com/photo-1549298916-b41d501d3772?w=500" alt="" />
                            <a class="btn hvr-hover" href="MainController?action=CategoryType&type=Sneakers">Sneakers</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
                        <div class="shop-cat-box">
                            <img class="img-fluid" style="height: 250px" src="https://images.unsplash.com/photo-1608256246200-53e635b5b65f?w=500" alt="" />
                            <a class="btn hvr-hover" href="MainController?action=CategoryType&type=Boots">Boots</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
                        <div class="shop-cat-box">
                            <img class="img-fluid" style="height: 250px" src="https://images.unsplash.com/photo-1533867617858-e7b97e060509?w=500" alt="" />
                            <a class="btn hvr-hover" href="MainController?action=CategoryType&type=Formal">Formal Shoes</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
                        <div class="shop-cat-box">
                            <img class="img-fluid" style="height: 250px" src="https://i.ytimg.com/vi/f8KeA2Q3ZgM/maxresdefault.jpg" alt="" />
                            <a class="btn hvr-hover" href="MainController?action=CategoryType&type=Phụ kiện khác">Other accessories</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <c:forEach items="${requestScope.LATEST_PRODUCTS}" var="pd">
        <li class="splide__slide">
            <div class="col-lg-12 col-md-12 special-grid best-seller">
                <div class="products-single fix">
                    <div class="box-img-hover">
                        <img src="${pd.image}" class="img-fluid" alt="Image" style="width: 100%; height: 200px; object-fit: cover">
                    </div>
                    <div class="why-text">
                        <h4>${pd.name}</h4>
                    </div>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>
</div>
</div>
</div>
</div>
</div>
End Products  -->



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
                        <form class="newsletter-box" action="NotifyEmailController">
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
                        <h3>Social Networks</h3>
                        <p>Social Networks We Use</p>
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
                                <p><i class="fas fa-phone-square"></i>Phone: <a href="tel:+1-888705770">076 33 88 151 </a></p>
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
<c:if test="${requestScope.SHOW_MODAL != null}">
    <div class="modal-swp">
        <div class="modal-swp-message">
            <h3>${requestScope.SHOW_MODAL}</h3>
            <button>Đóng</button>
        </div>
    </div>
</c:if>
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
<script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/js/splide.min.js"></script>
<script>
            document.addEventListener('DOMContentLoaded', () => {
                let tmp = 3;

                if (window.matchMedia('(max-width: 768px)').matches) {
                    tmp = 1;
                } else if (window.matchMedia('(max-width: 992px)').matches) {
                    tmp = 2;
                }

                var hotBird = new Splide('.hot_bird', {
                    perPage: tmp,
                    perMove: 1,
                    rewind: true
                });

                var hotCage = new Splide('.hot_cage', {
                    perPage: tmp,
                    perMove: 1,
                    rewind: true
                });

                var hotBirdCheck = document.querySelectorAll('.hot_bird .splide__list .splide__slide');
                if (hotBirdCheck.length > 0)
                    hotBird.mount();
                var hotCageCheck = document.querySelectorAll('.hot_cage .splide__list .splide__slide');
                if (hotCageCheck.length > 0)
                    hotCage.mount();

                window.addEventListener('resize', function () {

                    if (window.matchMedia('(max-width: 768px)').matches) {
                        tmp = 1;
                    } else if (window.matchMedia('(max-width: 992px)').matches) {
                        tmp = 2;
                    } else {
                        tmp = 3;
                    }

                    hotBird.options.perPage = tmp;
                    hotCage.options.perPage = tmp;

                    hotBird.refresh();
                    hotCage.refresh();
                });
            });
</script>
<script>
    function cardHover() {
        const prodList = document.querySelectorAll('.product-card');
        prodList.forEach(node => {
            node.addEventListener('mouseover', function () {
                node.classList.add('animate');
            });
            node.addEventListener('mouseout', function () {
                node.classList.remove('animate');
            });
        });
    }
    cardHover();
    let listProductName = document.querySelectorAll('.stats-container .product_name');
    listProductName.forEach(name => {
        if (name.innerHTML.length >= 26) {
            name.style = 'font-size: 15.5px !important';
        }
    });
</script>
<script>
    document.querySelector('.modal-swp-message button').addEventListener('click', () => {
        document.querySelector('.modal-swp').style.display = 'none';
    });
</script>

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
