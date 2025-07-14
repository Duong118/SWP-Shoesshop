
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <link rel="shortcut icon" href="images/logo.png" type="image/x-icon">
        <!-- Css -->
        <link rel="stylesheet" href="./dist/styles.css" />
        <link rel="stylesheet" href="./dist/all.css" />
        <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i"
            rel="stylesheet"
            />
        <title>Admin: Order</title>
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

            .row-info:hover {
                background-color: rgb(226 232 240);
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
            #add_product_swp:hover{
                background-color: rgb(161 161 170) !important;
                color: #FFFFFF;
            }

            .show-swp{
                display: block;
            }
            .show-swp-icon{
                transform: rotate(90deg)
            }
            .arrow-checkbox-label {
                display: inline-block;
                position: relative;
                cursor: pointer;
            }

            .arrow-checkbox {
                display: none;
            }

            .fa-sort-up:before{
                top: 10px;
                position: absolute;
            }

            #up{
                display: none;
            }

            #up{
                display: inline-block;
            }
            .fa-sort-down:before{
                position: absolute;
                bottom: 5px;
            }
        </style>
    </head>

    <body onload="checking()">
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
                        <ul class="list-reset flex flex-col">
                            <li
                                class="w-full h-full py-3 px-2 border-b border-light-border "
                                >
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
                            <li class="w-full h-full py-3 px-2 border-b border-light-border bg-white"> 
                                <!--href="ViewOrderManagerController"--> 
                                <div id="menu"> 
                                    <a 
                                        href="ViewAllOrderController" 
                                        class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline" 
                                        > 
                                        <i class="fas fa-grip-horizontal float-left mx-2"></i> 
                                        Order 
                                        <span><i class="fa fa-angle-right float-right" id="icon-rotation"></i></span> 
                                    </a> 
                                </div> 
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
                                    Sign out 
                                    <span><i class="fa fa-angle-right float-right"></i></span> 
                                </a> 
                            </li> 
                        </ul>
                    </aside>
                    <!--/Sidebar-->
                    <!--Main-->
                    <main class="bg-white-500 flex-1 p-3 overflow-hidden">
                        <div class="flex flex-col">
                            <!-- Stats Row Starts Here -->
                            <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2">
                                <div
                                    class="shadow bg-gray-500 border-l-8 hover:bg-success-dark border-success-dark mb-2 p-2 md:w-1/5 mx-2"
                                    >
                                    <div class="p-4 flex flex-col"> 
                                        <a href="ViewAllOrderController" class="no-underline text-white text-2xl"> 
                                            All orders 
                                        </a> 
                                    </div> 
                                </div> 

                                <div 
                                    class="shadow bg-gray-500 border-l-8 hover:bg-info-dark border-info-dark mb-2 p-2 md:w-1/5 mx-2" 
                                    > 
                                    <div class="p-4 flex flex-col"> 
                                        <a href="ViewOrderByStatusController?status=PROCESSING" class="no-underline text-white text-2xl"> 
                                            Waiting for approval 
                                        </a> 
                                    </div> 
                                </div> 

                                <div 
                                    class="shadow bg-gray-500 border-l-8 hover:bg-warning-dark border-warning-dark mb-2 p-2 md:w-1/5 mx-2" 
                                    > 
                                    <div class="p-4 flex flex-col"> 
                                        <a href="ViewOrderByStatusController?status=APPROVED" class="no-underline text-white text-2xl"> 
                                            Approved 
                                        </a> 
                                    </div> 
                                </div> 

                                <div 
                                    class="shadow bg-gray-500 border-l-8 hover:bg-success-dark border-success-dark mb-2 p-2 md:w-1/5 mx-2" 
                                    > 
                                    <div class="p-4 flex flex-col"> 
                                        <a href="ViewOrderByStatusController?status=SUCCESS" class="no-underline text-white text-2xl"> 
                                            Order successful 
                                        </a> 

                                        </a> 
                                    </div> 
                                </div> 
                                <div 
                                    class="shadow-lg bg-gray-500 border-l-8 hover:bg-red-vibrant-dark border-red-vibrant-dark mb-2 p-2 md:w-1/5 mx-2" 
                                    > 
                                    <div class="p-4 flex flex-col"> 
                                        <a href="ViewOrderByStatusController?status=FAIL" class="no-underline text-white text-2xl"> 
                                            Order<br>failed 
                                        </a> 
                                    </div> 
                                </div> 
                            </div>
                        </div>
                        <div class="bg-gray-200 px-2 py-1 border-solid border-gray-200 border-b flex items-center" style="margin-left: 8px; margin-right: 8px"> 
                            <form class="flex" action="MainController"> 
                                <div class="p-2 rounded flex items-center bg-slate-200" style="width: 500px"> 
                                    <input type="text" class="p-1 rounded outline-none flex-1" placeholder="Search for orders" name="search"/> 
                                </div> 
                                <button type="submit" name="action" value="SearchOrder" class="pr-3" style="outline: none"><i class="fas fa-search mr-1"></i></button> 
                            </form> 
                            <form class="flex" id="sortForm" method="post" action="MainController"> 
                                <div style="justify-content: flex-start">Sort By 
                                    <input type="hidden" name="action" value="SortOrder"> 
                                    <select onchange="submitForm(this)" 
                                            id="column" 
                                            name="column"> 
                                        <option value="id" ${param.column == 'id' ? 'selected' : ''}>Order code</option> 
                                        <option value="name" ${param.column == 'name' ? 'selected' : ''}>Customer name</option> 
                                        <option value="total" ${param.column == 'total' ? 'selected' : ''}>Order total</option> 
                                        <option value="date" ${param.column == 'date' ? 'selected' : ''}>Date</option> 
                                    </select>
                                    <label class="arrow-checkbox-label">
                                        <input id="sortOrder" type="checkbox" class="arrow-checkbox" name="sortOrder" onchange="checking();submitForm(this);" ${param.sortOrder == 'DESC' ? 'checked' : ''}>
                                        <span><i id="up" class="fa fa-sort-up"></i></span>
                                        <span><i id="down" class="fa fa-sort-down"></i></span>
                                    </label>
                                </div>
                            </form>
                        </div>
                        <c:if test = "${not empty requestScope.VIEW_ORDER}">
                            <c:if test = "${requestScope.VIEW_ORDER!=null}">
                                <div class="flex flex-col">
                                    <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2">
                                        <div
                                            class="mb-2 border-solid border-gray-300 rounded border shadow-sm w-full"
                                            >
                                            <div
                                                class="bg-gray-200 px-2 py-3 border-solid border-gray-200 border-b"
                                                >
                                                List of invoices
                                                <div class="p-3">
                                                    <table
                                                        class="table-responsive w-full rounded"
                                                        style="text-align: center"
                                                        >
                                                        <thead>
                                                            <tr>
                                                                <th class="border w-fit px-4 py-2">Order code</th>
                                                                <th class="border w-1/6 px-4 py-2">Customer name</th>
                                                                <th class="border w-1/6 px-4 py-2">Address</th>
                                                                <th class="border w-1/8 px-4 py-2">Phone number</th>
                                                                <th class="border w-1/11 px-4 py-2">Payment</th>
                                                                <th class="border w-1/8 px-4 py-2">
                                                                    <c:choose>
                                                                        <c:when test="${param.status=='SUCCESS'}">Confirmed Date</c:when>
                                                                        <c:otherwise>Order Date</c:otherwise>
                                                                    </c:choose>
                                                                </th>
                                                                <th class="border w-1/8 px-4 py-2">Order Total</th>
                                                                <th class="border w-1/8 px-4 py-2">Status</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="o" items="${requestScope.VIEW_ORDER}">
                                                                <tr class="cursor-pointer row-info" id="order" data-href="ViewOrderDetailController?id=${o.id}">
                                                                    <td class="border w-fit py-2">${o.id}</td>
                                                                    <td class="border w-1/6 py-2">${o.username}</td>
                                                                    <td class="border w-1/6 py-2">${o.address}</td>
                                                                    <td class="border w-1/8 py-2">${o.phone}</td>
                                                                    <td class="border w-1/11 py-2">
                                                                        <c:choose>
                                                                            <c:when test = "${o.paymentId == 1}">COD</c:when>
                                                                            <c:otherwise>ONLINE</c:otherwise>
                                                                        </c:choose>
                                                                    </td>
                                                                    <td class="border w-1/8 py-2"><fmt:formatDate pattern="dd/MM/yyyy" value="${o.date}" /></td>
                                                                    <td class="border w-1/8 py-2">
                                                                        <fmt:setLocale value="vi_VN"/>
                                                                        <fmt:formatNumber value="${o.total}" type="currency"/></td>
                                                                    <td class="border w-1/8 py-2">
                                                                        <c:choose>
                                                                            <c:when test = "${o.status == 'PROCESSING'}">Pending</c:when>
                                                                            <c:when test = "${o.status == 'SUCCESS'}">Successful</c:when>
                                                                            <c:when test = "${o.status == 'APPROVED'}">Approved</c:when>
                                                                            <c:otherwise>Cancelled</c:otherwise>
                                                                        </c:choose>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:if>
                                            </c:if>
                                            <h1 style="text-align: center">${VIEW_ORDER_EMPTY}</h1>
                                        </div>
                                    </div>
                                </div>
                                <!--/Grid Form-->
                            </div>
                        </div>
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

        <!-- modal -->

        <script src="./main.js"></script>
        <script>
                                            function linkRow() {
                                                const rows = document.querySelectorAll("tr[data-href]");
                                                rows.forEach(row => {
                                                    row.addEventListener("click", () => {
                                                        window.location.href = row.dataset.href;
                                                    });
                                                });
                                            }
                                            document.addEventListener("DOMContentLoaded", linkRow());
        </script>
        <script>
            function submitForm(sortOrder) {
                var form = document.getElementById("sortForm");
                form.submit();
            }
            function checking() {
                var sort = document.getElementById("sortOrder");
                var arrowUp = document.getElementById("up");
                var arrowDown = document.getElementById("down");

                if (sort.checked) {
                    arrowUp.style.display = "inline-block";
                    arrowDown.style.display = "none";
                    sort.value = "DESC";
                } else {
                    arrowUp.style.display = "none";
                    arrowDown.style.display = "inline-block";
                    sort.value = "ASC";
                }
            }
        </script>
    </body>
</html>