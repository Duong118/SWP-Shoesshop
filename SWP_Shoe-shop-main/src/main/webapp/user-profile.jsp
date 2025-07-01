<%-- 
    Document   : user-profile
    Created on : Jul 1, 2025, 1:54:05 PM
    Author     : Admin
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
        <meta name="viewport" content="width=device-width, initial-scale=1">        <!-- Site Metas -->
        <title>User Profile - Premium Shoe Store</title>
        <meta name="keywords" content="user profile, account settings, personal information">
        <meta name="description" content="Manage your personal profile and preferences">
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
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .pw {
                position: relative;
                display: flex;
                align-items: center;
            }

            #show{
                cursor: pointer;
                position: absolute;
                right: 5px;
                padding: 4px;
                z-index: 124;
                padding-top: 13px;
            }

            #password {
                position: absolute;
                right: 0;
            }

            #submit {
                margin-top: 60px;
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
                            <select id="basic" class="selectpicker show-tick form-control" data-placeholder="$ Xu">
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
                                <a href="login.jsp" style="color: white;font-weight: bold">Login/</a> 
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
                        <a class="navbar-brand" href="index.jsp"><img src="https://p7.hiclipart.com/preview/483/759/632/nike-air-max-nike-free-air-force-shoe-nike.jpg" class="logo" alt=""></a>
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
                                    <p>Giỏ hàng</p>
                                </a>
                            </li>
                        </ul>
                    </div>

                </div>

            </nav>
            <!-- End Navigation -->
        </header>

        <div class="all-title-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>Account Information</h2>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                            <li class="breadcrumb-item active"> Account </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End All Title Box -->

        <!-- Start Info  -->
        <div class="contact-box-main">
            <div class="container">
                <div class="row">

                    <div class="col-lg-8 col-sm-12">
                        <div class="contact-form-right">
                            <c:set var="errors" value="${requestScope.INSERT_CONTACT_ERRORS}"/>
                            <c:set var="successMsg" value="${requestScope.INSERT_UPDATE_ACCOUNT_SUCCESS}"/>
                            <c:if test="${not empty successMsg}">
                                <div id="msgSubmit" class="h3 text-center hidden">${successMsg}</div>
                            </c:if>
                            <div class="row w-100">
                                <c:if test="${checkLogin == null}">
                                    <div id="msgSubmit" class="h3 text-center hidden">You need to login or register to use this feature</div>                                </c:if>

                                <c:if test="${checkLogin != null}">

                                    <div style="display: flex; justify-content: center; margin-top: 30px">
                                        <h2 class="text-danger" style="font-weight: 700; display: inline-block;">${requestScope.ERROR}</h2>
                                    </div>
                                    <div style="display: flex; justify-content: center; margin-top: 30px">
                                        <h2 class="text-success" style="font-weight: 700; display: inline-block;">${requestScope.SUCCESS}</h2>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <h2>User Name:</h2>
                                            <input  class="form-control" id="username" type="text" name="username" value="${sessionScope.LOGIN_USER.username}" readonly/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <form action="MainController" method="POST" class="w-100">
                                        <div class="col-md-12">
                                            <div class="form-group"> 
                                                <h2>Full Name:</h2> 
                                                <input type="text" placeholder="Full name" id="fullname" value="${sessionScope.LOGIN_USER.fullName}" class="form-control" name="fullname" /> 
                                                <input type="hidden" id="id" value="${sessionScope.LOGIN_USER.id}" class="form-control" name="id" /> 
                                                <div class="help-block with-errors"></div> 
                                            </div> 
                                        </div> 
                                        <div class="col-md-12"> 
                                            <div class="form-group"> 
                                                <h2>Phone number:</h2> 
                                                <input type="text" placeholder="Phone number" id="phonenumber" value="${sessionScope.LOGIN_USER.phone}" class="form-control" name="phone" /> 
                                                <div class="help-block with-errors"></div> 
                                            </div> 
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <h2>Email:</h2>
                                                <c:if test="${sessionScope.LOGIN_USER.password == null}">
                                                    <input type="email" placeholder="Email" id="email" value="${sessionScope.LOGIN_USER.email}" class="form-control" name="email" readonly="" />
                                                </c:if>
                                                <c:if test="${sessionScope.LOGIN_USER.password != null}">
                                                    <input type="email" placeholder="Email" id="email" value="${sessionScope.LOGIN_USER.email}" class="form-control" name="email" />
                                                </c:if>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <h2>Address:</h2>
                                                <input type="text" placeholder="Nhập địa chỉ của bạn" id="address" value="${sessionScope.LOGIN_USER.address}" class="form-control" name="address" />
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <c:if test="${sessionScope.LOGIN_USER.password != null}">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <h2>Password:</h2>
                                                    <div class="mt-3 pw">
                                                        <i class="far fa-eye" id="show" type = "button"></i>
                                                        <input type="password" placeholder="Password" id="password" value="${sessionScope.LOGIN_USER.password}" class="form-control" name="password" />
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="submit-button text-center">                                               
                                            <button class="btn hvr-hover" id="submit" value="UpdateProfile" name="action" type="submit">Update information</button>                                            <div id="msgSubmit" class="h3 text-center hidden"></div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <c:if test="${checkLogin != null}">
                        <div class="col-lg-4 col-sm-12">
                            <img class="rounded-circle border p-1" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRWyuu0DuWwHd1o25ckVO098q3TDZtGC7EP4MCRpIu7cZ83PeS5pey8jp2jD5x1jTs6EI&usqp=CAU" alt="Generic placeholder image">
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- End Info -->

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
        <script>
            document.getElementById('show').onclick = function () {
                let password = document.getElementById('password');
                password.type = password.type === 'password' ? 'text' : 'password';
                this.classList.toggle('fa-eye-slash');
            };
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
