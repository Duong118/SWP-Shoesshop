<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Admin: Product</title>
        <style>
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
            .td.img{
                max-width: 100%;
                max-height: 100%;
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
                                class="w-full h-full py-3 px-2 border-b border-light-border"
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
                            <li class="w-full h-full py-3 px-2 border-b border-light-border bg-white">
                                <a
                                    href="ViewProductManagerController"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline bg-white"
                                    >
                                    <i class="fab fa-wpforms float-left mx-2"></i>
                                    Product
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                <!--href="ViewOrderManagerController"-->
                                <div id="menu">
                                    <a
                                        href="ViewAllOrderController"
                                        class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                        >
                                        <i class="fas fa-grip-horizontal float-left mx-2"></i>
                                        Order
                                        <span><i class="fa fa-angle-right float-right" id="icon-xoay"></i></span>
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
                            <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2">
                                <div
                                    class="mb-2 border-solid border-gray-300 rounded border shadow-sm w-full"
                                    >
                                    <div
                                        class="bg-gray-200 px-2 py-3 border-solid border-gray-200 border-b flex justify-between items-center"
                                        >
                                        Product
                                        <form class="flex" action="MainController">
                                            <div class="p-2 rounded flex items-center bg-slate-200" style="width: 500px">
                                                <input type="text" class="p-1 rounded outline-none flex-1" placeholder="Product search" name="search" value="${param.search}"/>                                            </div>
                                            <button type="submit" name="action" value="SearchProduct" class="pr-3" style="outline: none"><i class="fas fa-search mr-1"></i></button>
                                        </form>
                                        <form class="flex" id="sortForm" method="post" action="MainController">
                                            <div>Sort By
                                                <input type="hidden" name="action" value="SortProduct">
                                                <select onchange="submitForm(this)"
                                                        id="column" 
                                                        name="column">
                                                    <option value="id" ${param.column == 'id' ? 'selected' : ''}>ID</option>
                                                    <option value="name" ${param.column == 'name' ? 'selected' : ''}>Name</option> 
                                                    <option value="original_price" ${param.column == 'original_price' ? 'selected' : ''}>Price</option> 
                                                    <option value="created_date" ${param.column == 'created_date' ? 'selected' : ''}>Date</option> 
                                                </select>
                                                <label class="arrow-checkbox-label">
                                                    <input id="sortOrder" type="checkbox" class="arrow-checkbox" name="sortOrder" onchange="checking();submitForm(this);" ${param.sortOrder == 'DESC' ? 'checked' : ''}>
                                                    <span><i id="up" class="fa fa-sort-up"></i></span>
                                                    <span><i id="down" class="fa fa-sort-down"></i></span>
                                                </label>
                                            </div>
                                        </form>
                                        <a href="AddEditProductController" class="px-4 py-2 rounded block leading-7" id="add_product_swp" style="background-color: rgb(212 212 216);">Add new product</a>                                    </div>
                                        <c:if test = "${not empty requestScope.LIST_PRODUCT}">
                                            <c:if test = "${requestScope.LIST_PRODUCT!=null}">
                                            <div class="p-3">
                                                <table
                                                    class="table-responsive w-full rounded"
                                                    style="text-align: center; width: 100%"
                                                    >
                                                    <thead>
                                                        <tr>
                                                            <th class="border w-1/7 px-3 py-1">ID</th>
                                                            <th class="border w-1/5 px-10 py-1">Product Name</th>
                                                            <th class="border w-1/7 px-1 py-2">Image</th>
                                                            <th class="border w-96 px-4 py-2">Quantity</th>
                                                            <th class="border w-1/6 px-2 py-2">Price</th>
                                                            <th class="border w-96 px-5 py-2">Date</th>
                                                            <th class="border w-96 px-4 py-2">Status</th>
                                                            <th class="border w-fit px-4 py-2">Update/Delete</th>
                                                            <th class="border w-fit px-4 py-2">HOT!!!</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${LIST_PRODUCT}" var="p">
                                                            <tr <c:if test ="${p.hot}">style=" background: transparent; background-color: springgreen;"</c:if> >
                                                                <td class="border w-1/7 px-3 py-1">${p.id}</td>
                                                                <td class="border w-1/5 px-10 py-1"><a style="font-weight: bold">${p.name}</a></td>
                                                                <td class="border w-1/7 px-1 py-2" style="width: 100px; height: 1px; aspect-ratio: 2"><img src="${p.image}"></td>
                                                                <td class="border w-96 py-2">${p.quantity}</td>
                                                                <td class="border w-1/6 py-2">
                                                                    <c:set var="pri" value="${p.originalPrice}"/>
                                                                    <fmt:setLocale value="vi_VN"/>
                                                                    <fmt:formatNumber value="${pri}" type="currency"/>
                                                                </td>                                                             
                                                                <td class="border px-3 py-2"><fmt:formatDate pattern="dd/MM/yyyy" value="${p.createdDate}"/></td>
                                                                <td class="border w-96 py-2">
                                                                    <c:if test="${p.status == 'INSTOCK'}">
                                                                        <i class="fas fa-check text-green-500 mx-2"></i>
                                                                    </c:if>
                                                                    <c:if test="${p.status == 'OUTOFSTOCK'}">
                                                                        <i class="fas fa-times text-red-500 mx-2"></i>
                                                                    </c:if>
                                                                    <c:if test="${p.status == 'DISCONTINUED'}">
                                                                        <i class="fas fa-times-circle text-red-500 mx-2"></i>
                                                                    </c:if>
                                                                </td>
                                                                <td class="border w-fit py-2">
                                                                    <a
                                                                        class="bg-teal-300 rounded p-4 mx-2 text-white"
                                                                        href="MainController?action=ViewDetailProduct&id=${p.id}"
                                                                        >
                                                                        <i class="fas fa-edit"></i
                                                                        ></a>
                                                                    <a
                                                                        onclick="handalModal('centeredModal', 'block',${p.id})"
                                                                        class="bg-teal-300 rounded p-4 mx-2 text-red-500"
                                                                        href="#"
                                                                        >
                                                                        <i class="fas fa-trash"></i>
                                                                    </a>
                                                                </td>
                                                                <td class="border w-fit py-2">
                                                                    <c:choose>
                                                                        <c:when test="${p.status == 'DISCONTINUED'}">Product has been discontinued</c:when>                                                                        <c:otherwise>
                                                                            <input name="hot" type="checkbox" ${p.hot == 'true' ? 'checked' : ''} disabled="disabled">
                                                                            <a
                                                                                class="bg-teal-300 rounded p-4 mx-2 text-white"
                                                                                href="MainController?action=UpdateHot&id=${p.id}&hot=${!p.hot}"                                                               
                                                                                onclick ="return alert('Success')"
                                                                                >
                                                                                <i class="fas fa-edit"></i></a>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </c:if>
                                        </c:if>
                                        <h1 style="text-align: center">${EMPTY_LIST}</h1>
                                    </div>
                                </div>
                            </div>
                            <!--/Grid Form-->
                        </div>
                    </main>
                    <!--/Main-->
                </div>
                <!-- modal -->

                <div id="centeredModal" class="handModal">
                    <div class="overlay close-modal"></div>
                    <div class="modal modal-centered">
                        <div class="modal-content shadow-lg p-5 content-animation">
                            <div class="border-b p-2 pb-3 pt-0 mb-4">
                                <div class="flex justify-between items-center">
                                    Confirm                                    <span
                                        onclick="handalModal('centeredModal', 'none')"
                                        class="close-modal cursor-pointer px-3 py-1 rounded-full bg-gray-100 hover:bg-gray-200"
                                        >
                                        <i class="fas fa-times text-gray-700"></i>
                                    </span>
                                </div>
                            </div>
                            <!-- Modal content -->
                            <div class="flex justify-between">
                                <span>Do you want to delete this product?</span>                               
                                 <!--href="MainController?btAction=RemoveProductManager&id=${o.id}"-->
                                <!--onclick="handalModal('centeredModal', 'none')"-->
                                <a id="yes" 
                                   >Yes<i class="fas fa-check text-green-500 mx-2"></i> 
                                </a> 
                                <a href="#" onclick="handalModal('centeredModal', 'none')" 
                                   >No<i class="fas fa-ban text-red-500 mx-2"></i 
                                </a>
                            </div>        
                        </div>
                    </div>
                </div>
                <!-- modal -->
                <!--Footer-->
                <footer class="bg-grey-darkest text-white p-2">
                    <div class="flex flex-1 mx-auto">&copy; Shoes Shop</div>
                </footer>
                <!--/footer-->
            </div>
        </div>
        <!--  -->

        <!-- modal -->

        <script src="./main.js"></script>
        <script>
    function handalModal(id, display, pid) {
        console.log(pid);
        document.getElementById('yes').href = "MainController?action=DeleteProduct&id=" + pid;
        console.log(document.getElementById('yes'));
        document.getElementById(id).style.display = display;
    }

    const items = document.querySelectorAll('#item');
    const menu = document.getElementById('menu');
    const icon = document.getElementById('icon-xoay');
    const listMenu = document.getElementById('list-menu');
    console.log(listMenu);

    menu.addEventListener('click', () => {
        listMenu.classList.toggle('show-swp');
        icon.classList.toggle('show-swp-icon');
    });

    items.forEach(item => {
        console.log(item);
    });
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