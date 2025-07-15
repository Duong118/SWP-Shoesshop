

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <!-- Basic -->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">        <!-- Site Metas -->
        <title>Shoe Details - Premium Shoe Store</title>
        <meta name="keywords" content="shoe details, footwear, product information">
        <meta name="description" content="Detailed information about our premium shoes">
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

        <link rel="stylesheet" href="css/sweetalertAccWarning.css">

        <link rel="stylesheet" href="css/starrating.css">

        <link href="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/css/splide.min.css" rel="stylesheet">
        <style>
            .swp-btn{
                /*width: 94px;*/
                background: #16c6c6;
                border: none;
                cursor: pointer;
                padding: 10px 20px;
                color: #ffffff;
                font-weight: 700;
                outline: none;
            }
            .swp-btn:hover{
                background: #000000;
            }
            .swp-btn-cart{
                cursor: pointer;
                padding: 10px 20px;
                font-weight: 700;
                color: #ffffff;
                border: none;
                background: #16c6c6;
                outline: none;
            }
            .swp-btn-cart:hover{
                background: #000000;
            }
            .buy-btn{
                border-radius: 50%;
                border: none;
                color: #FFFFFF;
                background: #16c6c6;
                cursor: pointer;
                opacity: 0.8;
                width: 32px;
                height: 32px;
                opacity: 0.8;
            }
            .buy-btn:hover{
                opacity: 1;
                background: #000000;
            }
            .star-rating {
                --star-size: 20px;
                --star-color: #ffc107;
                --empty-color: #e4e5e9;

                display: inline-block;
                font-size: var(--star-size);
                line-height: 1;
                unicode-bidi: bidi-override;
                color: var(--empty-color);
                position: relative;
            }

            .star-rating::before {
                content: "★★★★★";
                color: var(--star-color);
                position: absolute;
                z-index: 1;
                top: 0;
                left: 0;
                background: linear-gradient(90deg, var(--star-color) calc(var(--rating) * 20%), var(--empty-color) calc(var(--rating) * 20%));
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
            }

            .star-rating::after {
                content: "★★★★★";
                color: var(--empty-color);
                position: absolute;
                z-index: 0;
                top: 0;
                left: 0;
            }
        </style>
        <style>
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
                        <c:if test="${sessionScope.LOGIN_USER != null}">
                            <div class="our-link">
                                <ul> 
                                    <li><a href="my-account.jsp"><i class="fa fa-user s_color"></i>Hello ${sessionScope.LOGIN_USER.username}</a></li> 
                                    <li><a href="https://facebook.com/" target="_blank"><i class="fas fa-headset"></i> Contact</a></li> 
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
                                        <i class="fab fa-opencart"></i> Welcome to Shoe shop
                                    </li>
                                    <li>
                                        <i class="fab fa-opencart"></i> Here we have all kinds of Shoes and related accessories
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
                        <a class="navbar-brand" href="HomeController?action=Home"><img src="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" class="logo" alt=""></a>
                    </div>
                    <!-- End Header Navigation -->

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="navbar-menu"> 
                        <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp"> 
                            <li class="nav-item"><a class="nav-link" href="MainController?action=Home">Home</a></li> 
                            <li class="nav-item"><a class="nav-link" href="about.jsp">Introduction</a></li> 
                            <li class="dropdown active"> 
                                <a href="MainController?action=GetFullProducts" class="nav-link">Products</a> 
                            </li> 
                            <li class="nav-item"><a class="nav-link" href="MainController?action=GetBlogs">Blog</a></li> 
                            <li class="nav-item"><a class="nav-link" href="contact-us.jsp">Contact system</a></li> 
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
                        <h2>Product Details</h2>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="ViewProductController">Shop</a></li>
                            <li class="breadcrumb-item active">Product Details</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End All Title Box -->
        <div style="display: flex; justify-content: center; margin-top: 30px">
            <h2 class="text-danger" style="font-weight: 700; display: inline-block;">${requestScope.ERROR_ADDTOCART}</h2>
        </div>
        <!-- Start Shop Detail  -->
        <div class="shop-detail-box-main">
            <div class="container">
                <div class="row">
                    <div class="col-xl-5 col-lg-5 col-md-6">
                        <div id="carousel-example-1" class="single-product-slider carousel slide" data-ride="carousel">
                            <div class="carousel-inner" role="listbox">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" src="${requestScope.PRODUCT_DETAIL.image}" alt="First slide">
                                    <h4 style="font-weight: 700; margin-top: 20px">Mô tả:</h4>
                                    <p style="color: #666666">${requestScope.PRODUCT_DETAIL.shortDescription}</p>
                                </div>

                            </div>                         

                        </div>
                    </div>
                    <div class="col-xl-7 col-lg-7 col-md-6">
                        <div class="single-product-details">
                            <h2>${requestScope.PRODUCT_DETAIL.name}</h2>
                            <div style="display: flex">
                                <c:if test="${requestScope.PRODUCT_DETAIL.originalPrice != requestScope.PRODUCT_DETAIL.discountPrice}">
                                    <h5 style="margin-bottom: 0; padding-bottom: 15px; margin-right: 10px; color: gray; font-size: 22px">
                                        <del>
                                            <c:set var="pri" value="${requestScope.PRODUCT_DETAIL.originalPrice}"/>
                                            <fmt:setLocale value="vi_VN"/>
                                            <fmt:formatNumber value="${pri}" type="currency"/>
                                        </del>
                                    </h5>
                                </c:if>
                                <h5 style="margin-bottom: 15px; padding-bottom: 0; font-size: 25px">
                                    <c:set var="pri" value="${requestScope.PRODUCT_DETAIL.discountPrice}"/>
                                    <fmt:setLocale value="vi_VN"/>
                                    <fmt:formatNumber value="${pri}" type="currency"/>
                                </h5>
                            </div>
                            <c:if test="${sessionScope.RATING !=null}">
                                <h3 class="star-rating" style="padding-bottom: 20px; --rating: ${sessionScope.RATING};"></h3><h6 class="text-muted" style ="margin: 0; padding: 0; display: inline-block; left: 85px; position: relative; bottom: 1px">(${sessionScope.RATING})</h6>
                                </c:if>
                                <c:if test="${sessionScope.RATING ==null}">
                                <h3>There are no reviews yet .-.</h3>                            
                            </c:if>
                            <!--<del>$ 60.00</del>-->
                            <p class="available-stock">
                                <c:if test="${requestScope.PRODUCT_DETAIL.status == 'INSTOCK'}"><span class="text-success" style="font-weight: 700; font-size: 18px">IN STOCK</span></c:if> 
                                <c:if test="${requestScope.PRODUCT_DETAIL.status == 'OUTOFSTOCK'}"><span class="text-danger" style="font-weight: 700; font-size: 18px">SOLD OUT</span></c:if> 
                                </p> 
                                <p>Coins will be received: ${requestScope.PRODUCT_DETAIL.point}</p> <h4>Details:</h4> 
                            <p>${requestScope.PRODUCT_DETAIL.description}</p>
                            <!-- Error message display -->
                            <c:if test="${not empty requestScope.ERROR_ADDTOCART}">
                                <div class="alert alert-danger" style="margin: 15px 0; padding: 12px; border: 1px solid #d93025; background-color: #fce8e6; color: #d93025; border-radius: 5px;">
                                    <i class="fa fa-exclamation-triangle" style="margin-right: 8px;"></i>
                                    ${requestScope.ERROR_ADDTOCART}
                                </div>
                            </c:if>
                            <form action="AddToCartController" method="post">
                                <input type="hidden" name="action" id="actionInput" value="">
                                <!-- Size Selection -->
                                <div class="form-group" style="margin: 20px 0;">
                                    <label class="control-label" style="font-weight: bold; margin-bottom: 15px; display: block; color: #333; font-size: 16px;">
                                        Size: <span style="color: red;">*</span>                                    
                                    </label><c:choose>
                                        <c:when test="${not empty requestScope.PRODUCT_SIZES}">
                                            <div class="size-selection" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(70px, 1fr)); gap: 8px; max-width: 100%;">
                                                <c:forEach items="${requestScope.PRODUCT_SIZES}" var="size" varStatus="status">
                                                    <div class="size-option" style="position: relative;">
                                                        <input type="radio" name="sizeId" value="${size.sizeId}" id="size_${size.sizeId}_${status.index}" 
                                                               class="size-radio" 
                                                               style="position: absolute; opacity: 0; pointer-events: none;" required
                                                               ${size.quantity <= 0 ? 'disabled' : ''}>
                                                        <label for="size_${size.sizeId}_${status.index}" 
                                                               class="size-label ${size.quantity <= 0 ? 'size-disabled' : ''}"
                                                               data-size-id="${size.sizeId}"
                                                               data-quantity="${size.quantity}"
                                                               style="display: block; padding: 10px 8px; border: 2px solid #ddd;
                                                               border-radius: 6px; cursor: pointer; text-align: center;
                                                               background: white; transition: all 0.3s ease;
                                                               font-weight: 600; font-size: 14px; user-select: none;
                                                               position: relative; overflow: hidden;">
                                                            <span class="size-name" style="display: block; margin-bottom: 2px;">${size.sizeName}</span>
                                                            <c:choose> 
                                                                <c:when test="${size.quantity <= 0}"> 
                                                                    <small class="stock-info stock-out" style="font-size: 9px; color: #d93025; font-weight: bold;">Out of stock</small> 
                                                                </c:when> 
                                                                <c:when test="${size.quantity <= 5}"> 
                                                                    <small class="stock-info stock-low" style="font-size: 9px; color: #ff6b35; font-weight: bold;">And ${size.quantity}</small> 
                                                                </c:when> 
                                                                <c:otherwise> 
                                                                    <small class="stock-info stock-available" style="font-size: 9px; color: #34a853;">${size.quantity} Left</small> 
                                                                </c:otherwise> 
                                                            </c:choose>                                                        </label>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <small style="color: #666; margin-top: 8px; display: block; font-style: italic;">
                                                Please select a size before adding to cart
                                            </small>
                                        </c:when>
                                        <c:otherwise>
                                            <div style="padding: 15px; background-color: #f8f9fa; border: 1px dashed #dee2e6; border-radius: 8px; text-align: center;">
                                                <p style="margin: 0; color: #6c757d; font-style: italic;">
                                                    There is currently no size information for this product. Please contact us for more details.
                                                </p>
                                            </div>
                                            <!-- Hidden input for products without sizes -->
                                            <input type="hidden" name="sizeId" value="1">
                                        </c:otherwise>
                                    </c:choose></div>

                                <!-- Quantity Selection Section -->                                <div class="quantity-section" style="margin: 25px 0;">
                                    <label class="control-label" style="font-weight: bold; margin-bottom: 10px; display: block; color: #333; font-size: 16px;">
                                        Quantity:                                    
                                    </label>
                                    <div style="display: flex; align-items: center; gap: 10px;">
                                        <button type="button" class="quantity-btn minus-btn" onclick="decreaseQuantity()" 
                                                style="width: 40px; height: 40px; border: 2px solid #ddd; background: white; border-radius: 5px;
                                                display: flex; align-items: center; justify-content: center; cursor: pointer; font-size: 18px; font-weight: bold;
                                                transition: all 0.3s;">
                                            <i class="fa fa-minus"></i>
                                        </button>
                                        <input class="quantity-input" value="1" min="1" max="999" type="number" name="buyQuantity" 
                                               id="quantityInput" readonly 
                                               onkeydown="return false;" 
                                               onpaste="return false;" 
                                               ondrop="return false;"
                                               style="width: 80px; height: 40px; text-align: center; border: 2px solid #ddd;
                                               border-radius: 5px; font-size: 16px; font-weight: bold; background: #f9f9f9;
                                               -webkit-appearance: textfield; -moz-appearance: textfield; appearance: textfield;
                                               pointer-events: none; user-select: none;">

                                        <button type="button" class="quantity-btn plus-btn" onclick="increaseQuantity()" 
                                                style="width: 40px; height: 40px; border: 2px solid #ddd; background: white; border-radius: 5px;
                                                display: flex; align-items: center; justify-content: center; cursor: pointer; font-size: 18px; font-weight: bold;
                                                transition: all 0.3s;">
                                            <i class="fa fa-plus"></i>
                                        </button>

                                        <span id="stockInfo" style="margin-left: 15px; font-size: 14px; color: #666; font-style: italic;">
                                            Please select a size to see available quantities
                                        </span>
                                    </div>
                                </div>

                                <!-- Action Buttons -->
                                <div class="action-buttons" style="margin: 30px 0;">
                                    <input type="hidden" name="id" value="${requestScope.PRODUCT_DETAIL.id}">
                                    <input type="hidden" name="name" value="${requestScope.PRODUCT_DETAIL.name}">
                                    <input type="hidden" name="price" value="${requestScope.PRODUCT_DETAIL.originalPrice}">
                                    <input type="hidden" name="discount_price" value="${requestScope.PRODUCT_DETAIL.discountPrice}">
                                    <input type="hidden" name="point" value="${requestScope.PRODUCT_DETAIL.point}">
                                    <input type="hidden" name="image" value="${requestScope.PRODUCT_DETAIL.image}">                                      <div style="display: flex; gap: 15px; flex-wrap: wrap;">
                                        <button type="submit" onclick="document.getElementById('actionInput').value = 'addtocart';" 
                                                class="btn-add-to-cart" id="addToCartBtn" disabled
                                                style="flex: 1; min-width: 200px; padding: 15px 25px; background: linear-gradient(135deg, #16c6c6, #14a8a8);
                                                color: white; border: none; border-radius: 8px; font-size: 16px; font-weight: bold;
                                                cursor: not-allowed; transition: all 0.3s; box-shadow: 0 4px 12px rgba(22, 198, 198, 0.3);
                                                opacity: 0.6;">
                                            <i class="fa fa-shopping-cart" style="margin-right: 8px;"></i>
                                            Add to cart                                        
                                        </button>

                                        <button type="submit" onclick="document.getElementById('actionInput').value = 'buynow';" 
                                                class="btn-buy-now" id="buyNowBtn" disabled
                                                style="flex: 1; min-width: 200px; padding: 15px 25px; background: linear-gradient(135deg, #ff6b35, #f55a2c);
                                                color: white; border: none; border-radius: 8px; font-size: 16px; font-weight: bold;
                                                cursor: not-allowed; transition: all 0.3s; box-shadow: 0 4px 12px rgba(255, 107, 53, 0.3);
                                                opacity: 0.6;">
                                            <i class="fa fa-bolt" style="margin-right: 8px;"></i>
                                            Buy Now                                        
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--  Related products  -->
        <div class="products-box" style="width: 100%; margin: 0 auto">
            <div class="container" style="width: 100%">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="title-all text-center">
                            <h1>Related Products</h1>                        
                        </div>
                    </div>
                </div>
                <div style="width: 100%">
                    <div class="splide recommend" role="group" aria-label="Splide Basic HTML Example">
                        <div class="splide__track">
                            <ul class="splide__list">
                                <c:forEach items="${requestScope.RELATED_PRODUCTS}" var="o">
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
                                                                <button type="submit" class="button" name="action" value="addtocart">
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
        <!-- End Related products  -->

        <div class="row my-5" style="width: 80%; margin: 0 auto">
            <div class="card card-outline-secondary my-4" style="width:100%">
                <div class="card-header">
                    <h2>Product Reviews</h2>                </div>
                <form action="MainController">                                 
                    <div class="card-body">    
                        <c:set var="cmt" scope="session" value="${sessionScope.COMMENT}"/>
                        <c:if test="${cmt != null}">
                            <c:forEach items="${cmt}" var="cmtdto">                                
                                <div class="media mb-3">
                                    <div class="mr-2"> 
                                        <img style="width:74px;height: 74px;object-fit: cover;" class="rounded-circle border p-1" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRWyuu0DuWwHd1o25ckVO098q3TDZtGC7EP4MCRpIu7cZ83PeS5pey8jp2jD5x1jTs6EI&usqp=CAU" alt="Generic placeholder image">
                                    </div>
                                    <div class="media-body">
                                        <c:if test="${cmtdto.username==''}">
                                            <small class="text-muted">Posted by Anonymous</small>
                                        </c:if>
                                        <small class="text-muted">${cmtdto.username}</small><small class="text-muted"><c:if test ="${sessionScope.LOGIN_USER.id == cmtdto.userId}"><c:set var="uid" scope="request" value="${cmtdto.userId}"/> (You)</c:if></small>
                                        <p>${cmtdto.feedbackContent}</p>
                                        <div class="star-rating" style="--rating: ${cmtdto.star};"><h6 class="text-muted" style ="margin: 0; padding: 0; display: inline-block; left: 85px; position: relative; bottom: 2px">(${cmtdto.star})</h6></div>
                                    </div>
                                    <p style="position: relative; align-self: flex-end"><fmt:formatDate pattern="dd/MM/yyyy" value="${cmtdto.date}"/></p>
                                </div>
                                <hr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${cmt == null}">
                            <p>There are currently no comments for this product. Be the first to comment</p>                            
                            <hr>
                        </c:if>                           
                        <c:if test="${sessionScope.LOGIN_USER != null}">
                            <c:if test="${sessionScope.LOGIN_USER.id != requestScope.uid}">    
                                <input type="hidden" name="userIdComment" value="${sessionScope.LOGIN_USER.id}">
                                <input type="hidden" name="fullname" value="${sessionScope.LOGIN_USER.fullName}">
                                <input type="hidden" name="id" value="${sessionScope.PRODUCT.id}">
                                <fieldset class="rating">
                                    <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="5 sao"></label>
                                    <input type="radio" id="star4half" name="rating" value="4.5" /><label class="half" for="star4half" title="4.5 sao"></label>
                                    <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="4 sao"></label>
                                    <input type="radio" id="star3half" name="rating" value="3.5" /><label class="half" for="star3half" title="3.5 sao"></label>
                                    <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="3 sao"></label>
                                    <input type="radio" id="star2half" name="rating" value="2.5" /><label class="half" for="star2half" title="2.5 sao"></label>
                                    <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="2 sao"></label>
                                    <input type="radio" id="star1half" name="rating" value="1.5" /><label class="half" for="star1half" title="1.5 sao"></label>
                                    <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="1 sao"></label>
                                    <input type="radio" id="starhalf" name="rating" value="0.5" /><label class="half" for="starhalf" title="0.5 sao"></label>
                                </fieldset> 
                                <textarea
                                    id="message"
                                    maxlength="500"
                                    name="txtComment"
                                    rows="4"
                                    style="width: -webkit-fill-available;"
                                    class="block p-4 w-full text-sm text-grey-darker bg-gray-200 rounded border border-gray-200 focus:ring-blue-500 focus:border-gray-600 focus:outline-none"
                                    placeholder="Bình luận của bạn..."
                                    required
                                    user></textarea>
                            </div>

                            <button class="swp-btn" type="submit" value="CreateFeedback" name="action" style="
                                    width: 140px;
                                    height: 47px;
                                    padding-bottom: -10px;
                                    padding-bottom: -10px;
                                    padding-bottom: 0px;
                                    border-bottom-width: 200px;
                                    margin-bottom: 20px;
                                    padding-top: 0px;
                                    margin-top: 0px;
                                    padding-left: 0px;
                                    padding-right: 0px;
                                    margin-left: 20px;
                                    margin-right: 0px;
                                    ">
                                Comment                            
                            </button>
                            <input type ="checkbox" name="ano" value ="anonymous">Bình luận dưới chế độ ẩn danh
                        </c:if>
                    </c:if>
                    <c:if test="${not empty requestScope.COMMENT_SUCCESS}">
                        <div id="msgSubmit" class="h3 text-center hidden" style="
                             margin-bottom: 20px;
                             ">${requestScope.COMMENT_SUCCESS}</div>
                    </c:if>
                </form>
                <div>
                    <c:if test="${sessionScope.LOGIN_USER == null}">                                                            
                        <a  id="swa1" class="swp-btn">
                            Leave a comment                        
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Cart -->

<!-- Start Footer  -->
<footer>
    <div class="footer-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-12 col-sm-12">
                    <div class="footer-top-box"> 
                        <h3>Working hours</h3> 
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
                        <h4>Contact us</h4>                        <ul>
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
    <p class="footer-company">All Rights Reserved. &copy; 2025 Design By : Team GR6
</div>
<!-- End copyright  -->

<a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>

<!-- ALL JS FILES -->
<script>
    document.getElementById('swa1').onclick =
            function () {
                swal({
                    title: "Lưu ý!!!",
                    text: "Bạn cần có tài khoản để sử dụng chức năng này!",
                    icon: "warning",
                    buttons: {
                        cancel: "Hủy",
                        login: {
                            text: "Đăng Nhập",
                            value: "login"
                        },
                        signup: {
                            text: "Đăng Ký",
                            value: "signup"
                        }
                    }
                })
                        .then((login) => {
                            switch (login) {

                                case "login":
                                    window.location = "login.jsp";
                                    break;

                                case "signup":
                                    window.location = "register.jsp";
                                    break;
                            }
                        });
            };
</script>
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
<script>
    function add() {
        const x = document.getElementById('increase').value;
        document.getElementById('increase').value = Number(x) + 1;
    }
    function subtract() {
        const x = document.getElementById('increase').value;
        if (x == 1) {
        } else {
            document.getElementById('increase').value = Number(x) - 1;
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/js/splide.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        let tmp = 3;

        if (window.matchMedia('(max-width: 768px)').matches) {
            tmp = 1;
        } else if (window.matchMedia('(max-width: 992px)').matches) {
            tmp = 2;
        }

        var recommend = new Splide('.recommend', {
            perPage: tmp,
            perMove: 1,
            rewind: true
        });

        var recommendTest = document.querySelectorAll('.recommend .splide__list .splide__slide');
        if (recommendTest.length > 0)
            recommend.mount();

        window.addEventListener('resize', function () {
            if (window.matchMedia('(max-width: 768px)').matches) {
                tmp = 1;
            } else if (window.matchMedia('(max-width: 992px)').matches) {
                tmp = 2;
            } else {
                tmp = 3;
            }

            recommend.options.perPage = tmp;

            recommend.refresh();
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

<style>
    /* Enhanced Size Selection Styles */
    .size-selection {
        margin: 15px 0;
    }

    .size-option {
        display: inline-block;
        margin: 5px;
        position: relative;
    }

    .size-label {
        display: block;
        padding: 12px 16px;
        border: 2px solid #ddd;
        border-radius: 8px;
        cursor: pointer;
        text-align: center;
        min-width: 60px;
        background: white;
        transition: all 0.3s ease;
        font-weight: 600;
        user-select: none;
        position: relative;
    }

    .size-label:hover {
        border-color: #16c6c6 !important;
        background-color: #f0f8f8 !important;
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(22, 198, 198, 0.2);
    }
    .size-option input[type="radio"]:checked + .size-label,
    .size-label.selected {
        border-color: #16c6c6 !important;
        background-color: #16c6c6 !important;
        background: #16c6c6 !important;
        color: white !important;
        transform: translateY(-2px) !important;
        box-shadow: 0 4px 12px rgba(22, 198, 198, 0.4) !important;
    }

    .size-option input[type="radio"]:checked + .size-label small,
    .size-label.selected small,
    .size-label.selected .size-name,
    .size-label.selected .stock-info,
    .size-label.selected .stock-available,
    .size-label.selected .stock-low,
    .size-label.selected .stock-out {
        color: white !important;
    }

    /* Ensure non-selected labels return to default state */
    .size-label:not(.selected):not(:hover) {
        border-color: #ddd !important;
        background-color: white !important;
        background: white !important;
        color: inherit !important;
        transform: translateY(0) !important;
        box-shadow: none !important;
    }

    .size-disabled {
        opacity: 0.6 !important;
        cursor: not-allowed !important;
        background-color: #f8f9fa !important;
        border-color: #dee2e6 !important;
    }

    .size-disabled:hover {
        border-color: #dee2e6 !important;
        background-color: #f8f9fa !important;
        transform: none !important;
        box-shadow: none !important;
    }

    .size-disabled::after {
        content: '';
        position: absolute;
        top: 50%;
        left: 10%;
        right: 10%;
        height: 2px;
        background: #dc3545;
        transform: translateY(-50%) rotate(-15deg);
    }

    .stock-available {
        color: #28a745;
        font-weight: 500;
    }

    .stock-out {
        color: #dc3545;
        font-weight: 600;
    }

    .stock-low {
        color: #ffc107;
        font-weight: 500;
    }

    /* New styles for improved add to cart functionality */
    .quantity-btn {
        transition: all 0.2s ease;
    }
    .quantity-btn:hover {
        background: #f0f0f0 !important;
        border-color: #16c6c6 !important;
        color: #16c6c6;
    }
    .quantity-btn:active {
        transform: scale(0.95);
    }
    .quantity-btn:disabled {
        opacity: 0.5;
        cursor: not-allowed;
        background: #f5f5f5 !important;
        border-color: #ddd !important;
        color: #999 !important;
    }

    .btn-add-to-cart:hover {
        background: linear-gradient(135deg, #14a8a8, #16c6c6) !important;
        transform: translateY(-2px);
        box-shadow: 0 6px 16px rgba(22, 198, 198, 0.4) !important;
    }
    .btn-add-to-cart:active {
        transform: translateY(0);
    }
    .btn-add-to-cart:disabled {
        opacity: 0.6;
        cursor: not-allowed;
        transform: none !important;
        box-shadow: none !important;
    }

    .btn-buy-now:hover {
        background: linear-gradient(135deg, #f55a2c, #ff6b35) !important;
        transform: translateY(-2px);
        box-shadow: 0 6px 16px rgba(255, 107, 53, 0.4) !important;
    }
    .btn-buy-now:active {
        transform: translateY(0);
    }
    .btn-buy-now:disabled {
        opacity: 0.6;
        cursor: not-allowed;
        transform: none !important;
        box-shadow: none !important;
    }

    /* Size selection styling */
    .size-label {
        transition: all 0.3s ease !important;
    }

    .size-label:hover:not(.size-disabled) {
        border-color: #16c6c6 !important;
        background: #f0fdfd !important;
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(22, 198, 198, 0.2);
    }

    .size-label.selected {
        border-color: #16c6c6 !important;
        background: linear-gradient(135deg, #16c6c6, #14a8a8) !important;
        color: white !important;
        transform: translateY(-1px);
        box-shadow: 0 6px 12px rgba(22, 198, 198, 0.4);
    }

    .size-label.selected .size-name,
    .size-label.selected .stock-info {
        color: white !important;
    }

    .size-disabled {
        background: #f5f5f5 !important;
        border-color: #e0e0e0 !important;
        color: #999 !important;
        cursor: not-allowed !important;
        opacity: 0.6;
    }

    .size-disabled:hover {
        transform: none !important;
        box-shadow: none !important;
    }        </style>
<script>
    // Size data from server - generated safely
    var productSizes = [
    <c:forEach items="${requestScope.PRODUCT_SIZES}" var="size" varStatus="status">
    {
    sizeId: ${size.sizeId},
            sizeName: '${size.sizeName}',
            quantity: ${size.quantity}
    }<c:if test="${!status.last}">,</c:if>
    </c:forEach>
    ];

    // Quantity control functions
    function decreaseQuantity() {
        var quantityInput = document.getElementById('quantityInput');
        var currentValue = parseInt(quantityInput.value);

        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
            updateQuantityButtons();
        }
    }

    function increaseQuantity() {
        var quantityInput = document.getElementById('quantityInput');
        var currentValue = parseInt(quantityInput.value);
        var maxValue = parseInt(quantityInput.getAttribute('max')) || 999;

        if (currentValue < maxValue) {
            quantityInput.value = currentValue + 1;
            updateQuantityButtons();
        } else {
            alert('Số lượng tối đa cho size này là ' + maxValue);
        }
    }

    function updateQuantityButtons() {
        var quantityInput = document.getElementById('quantityInput');
        var minusBtn = document.querySelector('.minus-btn');
        var plusBtn = document.querySelector('.plus-btn');
        var currentValue = parseInt(quantityInput.value);
        var maxValue = parseInt(quantityInput.getAttribute('max')) || 999;

        // Update minus button
        if (currentValue <= 1) {
            minusBtn.style.opacity = '0.5';
            minusBtn.style.cursor = 'not-allowed';
        } else {
            minusBtn.style.opacity = '1';
            minusBtn.style.cursor = 'pointer';
        }

        // Update plus button
        if (currentValue >= maxValue) {
            plusBtn.style.opacity = '0.5';
            plusBtn.style.cursor = 'not-allowed';
        } else {
            plusBtn.style.opacity = '1';
            plusBtn.style.cursor = 'pointer';
        }
    }

    function updateActionButtons() {
        var selectedSize = document.querySelector('input[name="sizeId"]:checked');
        var addToCartBtn = document.getElementById('addToCartBtn');
        var buyNowBtn = document.getElementById('buyNowBtn');
        var stockInfo = document.getElementById('stockInfo');

        var isAnyAvailable = false;

        // Check if any size has stock > 0
        for (var i = 0; i < productSizes.length; i++) {
            if (productSizes[i].quantity > 0) {
                isAnyAvailable = true;
                break;
            }
        }

        if (!isAnyAvailable) {
            // All sizes out of stock
            addToCartBtn.disabled = true;
            buyNowBtn.disabled = true;
            addToCartBtn.style.opacity = '0.4';
            addToCartBtn.style.cursor = 'not-allowed';
            buyNowBtn.style.opacity = '0.4';
            buyNowBtn.style.cursor = 'not-allowed';
            addToCartBtn.innerHTML = '<i class="fa fa-ban" style="margin-right: 8px;"></i>Hết hàng';
            buyNowBtn.innerHTML = '<i class="fa fa-ban" style="margin-right: 8px;"></i>Hết hàng';
            return;
        }

        if (!selectedSize) {
            // No size selected
            addToCartBtn.disabled = true;
            buyNowBtn.disabled = true;
            addToCartBtn.style.opacity = '0.6';
            addToCartBtn.style.cursor = 'not-allowed';
            buyNowBtn.style.opacity = '0.6';
            buyNowBtn.style.cursor = 'not-allowed';
            addToCartBtn.innerHTML = '<i class="fa fa-shopping-cart" style="margin-right: 8px;"></i>Thêm vào giỏ hàng';
            buyNowBtn.innerHTML = '<i class="fa fa-bolt" style="margin-right: 8px;"></i>Mua ngay';
        } else {
            var sizeId = parseInt(selectedSize.value);
            var sizeData = null;

            // Find size data
            for (var i = 0; i < productSizes.length; i++) {
                if (productSizes[i].sizeId === sizeId) {
                    sizeData = productSizes[i];
                    break;
                }
            }

            if (sizeData && sizeData.quantity > 0) {
                // Size selected and has stock
                addToCartBtn.disabled = false;
                buyNowBtn.disabled = false;
                addToCartBtn.style.opacity = '1';
                addToCartBtn.style.cursor = 'pointer';
                buyNowBtn.style.opacity = '1';
                buyNowBtn.style.cursor = 'pointer';
                addToCartBtn.innerHTML = '<i class="fa fa-shopping-cart" style="margin-right: 8px;"></i>Thêm vào giỏ hàng';
                buyNowBtn.innerHTML = '<i class="fa fa-bolt" style="margin-right: 8px;"></i>Mua ngay';
            } else {
                // Size selected but out of stock
                addToCartBtn.disabled = true;
                buyNowBtn.disabled = true;
                addToCartBtn.style.opacity = '0.4';
                addToCartBtn.style.cursor = 'not-allowed';
                buyNowBtn.style.opacity = '0.4';
                buyNowBtn.style.cursor = 'not-allowed';
                addToCartBtn.innerHTML = '<i class="fa fa-ban" style="margin-right: 8px;"></i>Size này hết hàng';
                buyNowBtn.innerHTML = '<i class="fa fa-ban" style="margin-right: 8px;"></i>Size này hết hàng';
            }
        }
    }
    function updateStockInfo() {
        var selectedSize = document.querySelector('input[name="sizeId"]:checked');
        var stockInfo = document.getElementById('stockInfo');
        var quantityInput = document.getElementById('quantityInput');

        if (selectedSize) {
            var sizeId = parseInt(selectedSize.value);
            var sizeData = null;

            // Find size data
            for (var i = 0; i < productSizes.length; i++) {
                if (productSizes[i].sizeId === sizeId) {
                    sizeData = productSizes[i];
                    break;
                }
            }

            if (sizeData) {
                quantityInput.setAttribute('max', sizeData.quantity);

                // Reset quantity if it exceeds available stock
                if (parseInt(quantityInput.value) > sizeData.quantity) {
                    quantityInput.value = Math.min(parseInt(quantityInput.value), sizeData.quantity);
                }

                // Update stock info display
                if (sizeData.quantity === 0) {
                    stockInfo.innerHTML = '<span style="color: #d93025; font-weight: bold;">Hết hàng</span>';
                } else if (sizeData.quantity <= 5) {
                    stockInfo.innerHTML = '<span style="color: #ff6b35; font-weight: bold;">Chỉ còn ' + sizeData.quantity + ' sản phẩm</span>';
                } else {
                    stockInfo.innerHTML = '<span style="color: #34a853;">Còn ' + sizeData.quantity + ' sản phẩm</span>';
                }

                updateQuantityButtons();
            }
        } else {
            stockInfo.textContent = 'Vui lòng chọn size để xem số lượng có sẵn';
            quantityInput.setAttribute('max', '999');
        }

        // Always update action buttons when stock info changes
        updateActionButtons();
    }// Handle size change
    function handleSizeChange(event) {
        console.log('Size change triggered:', event.target.value);

        updateStockInfo();

        // Remove error highlighting if exists
        var sizeSelection = document.querySelector('.size-selection');
        if (sizeSelection) {
            sizeSelection.style.border = 'none';
            sizeSelection.style.padding = '0';
        }

        // Update size label styling - clear all first
        var allLabels = document.querySelectorAll('.size-label');
        allLabels.forEach(function (label) {
            label.classList.remove('selected');
        });

        // Apply to selected - use proper element relationship
        var selectedRadio = event.target;
        var selectedLabel = selectedRadio.nextElementSibling;
        if (selectedLabel && selectedLabel.classList.contains('size-label')) {
            selectedLabel.classList.add('selected');
        }
    }

    // Initialize when page loads
    document.addEventListener('DOMContentLoaded', function () {
        console.log('DOM loaded, initializing size selection...');

        // Add comprehensive form validation
        var addToCartForm = document.querySelector('form[action="AddToCartController"]');
        if (addToCartForm) {
            addToCartForm.addEventListener('submit', function (e) {
                var sizeRadios = document.querySelectorAll('input[name="sizeId"]');
                var isAnySizeSelected = false;

                for (var i = 0; i < sizeRadios.length; i++) {
                    if (sizeRadios[i].checked) {
                        isAnySizeSelected = true;
                        break;
                    }
                }

                if (!isAnySizeSelected) {
                    e.preventDefault();

                    // Show error highlighting
                    var sizeSelection = document.querySelector('.size-selection');
                    if (sizeSelection) {
                        sizeSelection.style.border = '2px solid #d93025';
                        sizeSelection.style.padding = '10px';
                        sizeSelection.style.borderRadius = '8px';

                        // Scroll to size selection
                        sizeSelection.scrollIntoView({behavior: 'smooth', block: 'center'});
                    }

                    alert('Vui lòng chọn size trước khi thêm vào giỏ hàng!');
                    return false;
                }

                // Check if selected size is in stock
                var selectedSizeRadio = null;
                for (var i = 0; i < sizeRadios.length; i++) {
                    if (sizeRadios[i].checked) {
                        selectedSizeRadio = sizeRadios[i];
                        break;
                    }
                }

                if (selectedSizeRadio && selectedSizeRadio.disabled) {
                    e.preventDefault();
                    alert('Size đã chọn hiện đã hết hàng. Vui lòng chọn size khác!');
                    return false;
                }

                // Check quantity
                var quantityInput = document.querySelector('input[name="buyQuantity"]');
                var quantity = parseInt(quantityInput.value);
                var maxQuantity = parseInt(quantityInput.getAttribute('max')) || 999;

                if (quantity > maxQuantity) {
                    e.preventDefault();
                    alert('Số lượng tối đa có thể mua cho size này là ' + maxQuantity + '!');
                    quantityInput.value = maxQuantity;
                    return false;
                }

                if (quantity <= 0) {
                    e.preventDefault();
                    alert('Số lượng phải lớn hơn 0!');
                    quantityInput.value = 1;
                    return false;
                }

                // Show loading state
                var submitButtons = addToCartForm.querySelectorAll('button[type="submit"]');
                for (var i = 0; i < submitButtons.length; i++) {
                    var btn = submitButtons[i];
                    btn.disabled = true;
                    var originalText = btn.innerHTML;
                    btn.innerHTML = '<i class="fa fa-spinner fa-spin"></i> Đang xử lý...';

                    // Create closure to preserve button reference
                    (function (button, text) {
                        setTimeout(function () {
                            button.disabled = false;
                            button.innerHTML = text;
                        }, 3000);
                    })(btn, originalText);
                }
            });
        }                  // Enhanced size selection handling with proper toggle
        var sizeContainer = document.querySelector('.size-selection');
        if (sizeContainer) {
            // Use click delegation for reliable selection
            sizeContainer.addEventListener('click', function (e) {
                var label = e.target.closest('.size-label');
                if (label && !label.classList.contains('size-disabled')) {
                    var radioId = label.getAttribute('for');
                    var radio = document.getElementById(radioId);

                    if (radio && !radio.disabled) {
                        var wasChecked = radio.checked;

                        // Always clear all selections first
                        var allRadios = sizeContainer.querySelectorAll('input[type="radio"]');
                        var allLabels = sizeContainer.querySelectorAll('.size-label');

                        allRadios.forEach(function (r) {
                            r.checked = false;
                        });

                        allLabels.forEach(function (l) {
                            l.classList.remove('selected');
                            // Force remove any inline styles that might override
                            l.style.borderColor = '';
                            l.style.backgroundColor = '';
                            l.style.color = '';
                        });

                        // Toggle logic: if was checked, keep it deselected, otherwise select it
                        if (!wasChecked) {
                            radio.checked = true;
                            label.classList.add('selected');
                            console.log('Selected size:', radio.value);
                        } else {
                            console.log('Size deselected');
                        }

                        // Update UI
                        updateStockInfo();
                        updateActionButtons();

                        // Remove any error highlighting
                        sizeContainer.style.border = 'none';
                        sizeContainer.style.padding = '0';
                    }
                }
            });
        }

        // Add hover effects for quantity buttons
        var quantityBtns = document.querySelectorAll('.quantity-btn');
        for (var i = 0; i < quantityBtns.length; i++) {
            var btn = quantityBtns[i];

            btn.addEventListener('mouseenter', function () {
                if (this.style.cursor !== 'not-allowed') {
                    this.style.borderColor = '#16c6c6';
                    this.style.transform = 'scale(1.05)';
                }
            });

            btn.addEventListener('mouseleave', function () {
                if (this.style.cursor !== 'not-allowed') {
                    this.style.borderColor = '#ddd';
                    this.style.transform = 'scale(1)';
                }
            });
        }
        // Initialize quantity buttons state
        updateQuantityButtons();

        // Initialize stock info display
        updateStockInfo();

        // Initialize action buttons state
        updateActionButtons();
    });
</script>

</body>

</html>