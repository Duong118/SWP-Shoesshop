
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <link rel="shortcut icon" href="images/logo.png" type="image/x-icon">
        <!-- Css -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="./dist/styles.css" />
        <link rel="stylesheet" href="./dist/all.css" />
        <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i"
            rel="stylesheet"
            />
        <title>Order Detail</title>
        <style>
            @keyframes show-hide {
                from {
                    opacity: 0;
                }
                to {
                    opacity: 1;
                }
            }
            .show {
                display: table-row;
            }
            .hide {
                display: none;
            }


            .handModal {
                position: fixed;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                z-index: 1050;
                overflow-x: hidden;
                overflow-y: auto;
                outline: 0;
                display: none;
            }

            .handModal .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5);
                z-index: -1;
            }

            .list-reset li {
                padding: 10px 6px !important;
            }

            @media(max-width: 1024px) {
                .card-2 {
                    justify-content: flex-start !important;
                    padding: 24px 24px 24px 80px !important;
                }
            }

            @keyframes modal {
                from {
                    transform: translateY(-30%);
                    opacity: 0;
                }
                to {
                    transform: translateY(0);
                    opacity: 1;
                }
            }
            .content-animation {
                animation: modal ease-out 0.2s;
            }
        </style>
    </head>

    <body>
        <!--Container -->
        <div class="mx-auto bg-grey-lightest">
            <!--Screen-->
            <div class="min-h-screen flex flex-col">
                <!--Header Section Starts Here-->
                <header class="bg-nav">
                    <div class="flex justify-between">
                        <div class="p-1 mx-3 inline-flex items-center cursor-pointer">
                            <i
                                class="fas fa-bars pr-2 text-white"
                                onclick="sidebarToggle()"
                                ></i>
                            <h1 class="text-white p-2">Shoes Shop</h1>
                        </div>
                        <div class="p-1 flex flex-row items-center">

                        </div>
                    </div>
                </header>
                <!--/Header-->

                <div class="flex flex-1">
                    <!--Sidebar-->
                    <aside
                        id="sidebar"
                        class="bg-side-nav w-1/2 md:w-1/6 lg:w-1/6 border-r border-side-nav hidden md:block lg:block"
                        >
                        <div class="flex"></div>
                        <ul class="list-reset flex flex-col">
                            <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                <a
                                    href="Dashboard"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                    >
                                    <i class="fas fa-tachometer-alt float-left mx-2"></i>
                                    Dashboard
                                    <span><i class="fas fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                <a
                                    href="ViewProductManagerController"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                    >
                                    <i class="fab fa-wpforms float-left mx-2"></i>
                                    Product
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li
                                class="w-full h-full py-3 px-2 border-b border-light-border bg-white"
                                >
                                <a
                                    href="ViewAllOrderController"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline bg-white"
                                    >
                                    <i class="fas fa-grip-horizontal float-left mx-2"></i>
                                    Order
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                <a
                                    href="ViewFeedback"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                    >
                                    <i class="fas fa-table float-left mx-2"></i>
                                    Comment
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                <a
                                    href="ViewBlogManagerController"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                    >
                                    <i class="fab fa-uikit float-left mx-2"></i>
                                    Article
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>

                            <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                <a
                                    href="sendNotiEmail.jsp"

                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                    >
                                    <i class="fas fa-paper-plane float-left mx-2"></i>
                                    Send Email
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-300-border">
                                <a
                                    href="MainController?action=Logout"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                    >
                                    <i class="fas fa-square-full float-left mx-2"></i>
                                    Logout
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                        </ul>
                    </aside>
                    <!--/Sidebar-->
                    <!--Main-->
                    <main class="bg-white-500 flex-1 p-3 overflow-hidden">
                        <div class="flex flex-col" style="max-width: 1256px; margin: 0 auto">
                            <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2">
                                <div
                                    class="mb-2 border-solid border-gray-300 rounded border shadow-sm w-full"
                                    >
                                    <div
                                        class="bg-gray-200 px-2 py-3 border-solid border-gray-200 border-b flex justify-between items-center"
                                        >
                                        Shopping Cart
                                        <div class="d-inline-block">
                                            <c:if test="${requestScope.STATUS == 'PROCESSING'}">
                                                <a href="MainController?action=ChangeOrderStatusForAdmin&method=approved&id=${ID}" onclick="return confirm('Confirm approval of this order?')" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-2 rounded">Confirm order</a>
                                                <a href="MainController?action=ChangeOrderStatusForAdmin&method=reject&id=${ID}" onclick="return confirm('Confirm cancellation of this order?')" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-2 rounded">Cancel order</a>
                                            </c:if>
                                            <c:if test="${requestScope.STATUS == 'APPROVED'}">
                                                <a href="MainController?action=ChangeOrderStatusForAdmin&method=accept&id=${ID}" onclick="return confirm('Confirm order has been delivered?')" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-2 rounded">Confirm order has been delivered</a>
                                                <a href="MainController?action=ChangeOrderStatusForAdmin&method=reject&id=${ID}" onclick="return confirm('Confirm cancel this order?')" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-2 rounded">Cancel order</a>
                                            </c:if>
                                        </div>

                                    </div>
                                    <div class="p-3">
                                        <table
                                            class="table-responsive w-full rounded"
                                            style="text-align: center"
                                            >
                                            <thead style="background-color: whitesmoke">
                                                <tr>
                                                    <th class="border w-2/4 px-4 py-2">Product Name</th>
                                                    <th class="border w-fit px-4 py-2">Quantity</th>
                                                    <th class="border w-1/5 px-4 py-2">Unit Price</th>
                                                    <th class="border w-1/5 px-4 py-2">Total</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.VIEW_ORDER_DETAIL}" var="o">
                                                    <tr class="row-info" id="order" >
                                                        <td class="border w-2/4 p-2" style="text-align: left">${o.productName}</td>
                                                        <td class="border w-fit p-2" style="text-align: right">${o.quantity}</td>
                                                        <td class="border w-1/5 p-2" style="text-align: right">
                                                            <c:set var="price" value="${o.price}"/>
                                                            <fmt:setLocale value="vi_VN"/>
                                                            <fmt:formatNumber value="${price}" type="currency"/></td>
                                                        <td class="border w-1/5 p-2" style="text-align: right"><fmt:formatNumber value="${o.quantity * price}" type="currency"/></td>
                                                    </tr>
                                                </c:forEach>
                                                <!--  -->
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!--/Grid Form-->
                        </div>
                        <section class="gradient-custom">
                            <c:set var="o" value="${requestScope.ORDER_INFO}"></c:set>
                                <div class="container">
                                    <div class="row d-flex justify-content-center align-items-center">
                                        <div class="col-lg-12 col-xl-12">
                                            <div class="card" style="border-radius: 10px; background-color: transparent">
                                                <div class="card-header border-0 px-4 py-5"
                                                     style="background-color: #3F495E;">
                                                    <h5 class="d-flex align-items-center justify-content-end text-white text-uppercase mb-0">Total order: 
                                                        <span class="h2 mb-0 ms-2"><fmt:formatNumber value="${o.total}" type="currency"/></span> 
                                                </h5> 
                                            </div> 
                                            <div class="d-flex flex-wrap"> 
                                                <div class="card-1 card-body pt-4 pb-4 pl-20" style="min-width: 50%"> 
                                                    <div class=" pt-2"> 
                                                        <p class="fw-bold mb-0">Order details</p> 
                                                    </div> 

                                                    <div class="pt-2"> 
                                                        <p class="text-muted mb-0">Order code: ${o.id}</p> 
                                                    </div> 

                                                    <div> 
                                                        <p class="text-muted mb-0"><c:choose> 
                                                                <c:when test="${o.status=='SUCCESS'}">Confirmation Date: </c:when>
                                                                <c:otherwise>Order Date: </c:otherwise>
                                                            </c:choose> <fmt:formatDate pattern="dd/MM/yyyy" value="${o.date}" /></p>
                                                    </div>

                                                    <div>
                                                        <p class="text-muted mb-0">Payment Method: <c:if test="${o.paymentId == 1}">COD</c:if><c:if test="${o.paymentId == 2}">Online Payment</c:if></p>
                                                        </div>
                                                        <div>
                                                                <p class="text-muted mb-0">Total Points: ${o.totalPoints}</p>
                                                    </div>
                                                    <div>
                                                        <p class="text-muted mb-0">Use Points: <c:if test="${o.applyPoints == 'YES'}">Yes</c:if><c:if test="${o.applyPoints == 'NO'}">No</c:if></p>
                                                        </div>
                                                        <div>
                                                            <p class="text-muted mb-3">Order Status:
                                                            <c:choose>
                                                                <c:when test = "${o.status == 'PROCESSING'}">Pending</c:when>
                                                                <c:when test = "${o.status == 'SUCCESS'}">Successful</c:when>
                                                                <c:when test = "${o.status == 'APPROVED'}">Approved</c:when>
                                                                <c:otherwise>Cancelled</c:otherwise>
                                                            </c:choose>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="card-2 card-body pt-4 pb-4 pr-20" style="display: flex; justify-content: flex-end; width: 50%"> 
                                                    <div style="display: inline-block"> 
                                                        <div class=" pt-2" style="display: inline-block"> 
                                                            <p class="fw-bold mb-0" style="display: inline-block"><span class="fw-bold me-2">Customer information</span></p> 
                                                        </div> 
                                                        <br/> 
                                                        <div class=" pt-2" style="display: inline-block"> 
                                                            <p class="text-muted mb-0" style="display: inline-block"><span class="fw-bold me-2">Customer name:</span>${o.username}</p> 
                                                        </div> 
                                                        <br/> 
                                                        <div style="display: inline-block"> 
                                                            <p class="text-muted mb-0" style="display: inline-block"><span class="fw-bold me-2">Address:</span>${o.address}</p> 
                                                        </div> 
                                                        <br/> 
                                                        <div style="display: inline-block"> 
                                                            <p class="text-muted mb-0" style="display: inline-block"><span class="fw-bold me-2">Email:</span>${o.email}</p> 
                                                        </div> 
                                                        <br/> 
                                                        <div style="display: inline-block"> 
                                                            <p class="text-muted mb-5" style="display: inline-block"><span class="fw-bold me-2">Phone number:</span> 
                                                                <c:set value="${o.phone}" var="phone"/> 
                                                                <c:out value="${fn:substring(phone, 0, 4)}.${fn:substring(phone, 4, 7)}.${fn:substring(phone, 7, fn:length(phone))}"/> 
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </main>
                    <!--/Main-->
                </div>
                <!--Footer-->
                <footer class="bg-grey-darkest text-white p-2">
                    <div class="flex flex-1 mx-auto">&copy;Shoes Shop</div>
                </footer>
                <!--/footer-->
            </div>
        </div>
        <!--  -->
        <script src="./main.js"></script>
        <script>

        </script>
    </body>
</html>