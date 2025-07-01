<%-- 
    Document   : managerblog
    Created on : Jul 1, 2025, 1:51:17 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="shortcut icon" href="images/logo.png" type="image/x-icon">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <!-- Css -->
        <link rel="stylesheet" href="./dist/styles.css" />
        <link rel="stylesheet" href="./dist/all.css" />
        <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i"
            rel="stylesheet"
            />
        <title>Admin: Blog</title>
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
                            <h1 class="text-white p-2">Shop Shoe</h1>
                        </div>
                        <div class="p-1 flex flex-row items-center">
                            <!--              <img
                                            class="inline-block h-8 w-8 rounded-full"
src="https://avatars0.githubusercontent.com/u/4323180?s=460&v=4"
                                            alt=""
                                          />-->
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
                                    Bình luận
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-light-border bg-white">
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
                                    Sent Email
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-300-border">
                                <a
                                    href="MainController?action=Logout"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                    >
                                    <i class="fas fa-square-full float-left mx-2"></i>
                                    Log out
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
                                        Active
                                        <form class="flex" action="MainController">
                                            <div class="p-2 rounded flex items-center bg-slate-200" style="width: 500px">
                                                <!--<input type="text" class="p-1 rounded outline-none flex-1" placeholder="" name="search"/>-->
                                            </div>
                                            <!--<button type="submit" name="action=" value="SearchProductManager" class="pr-3" style="outline: none"><i class="fas fa-search mr-1"></i></button>-->
                                        </form>
                                        <a href="createblog.jsp" class="px-4 py-2 rounded block leading-7" id="add_product_swp" style="background-color: rgb(212 212 216);">Add new post</a>
                                    </div>
                                    <div class="p-3 ">
                                        <table
                                            class="table-responsive w-full rounded"
                                            style="text-align: center"
                                            >
                                            <thead>
                                                <tr>
                                                    <th class="border w-1/5 px-10 py-1">Article Title</th>
                                                    <th class="border w-96 px-10 py-2">Date Written</th>
                                                    <th class="border w-96 px-10 py-2">Image</th>
                                                    <th class="border w-96 px-4 py-2">Content</th>
                                                    <th class="border w-fit px-4 py-2">Options</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach items="${VIEW_LIST_BLOG}" var="b">
                                                    <tr>
                                                        <td class="border w-1/5 px-10 py-1">${b.title}</td>
                                                        <td class="border w-96 py-2">${b.date}</td>
                                                        <td class="border w-1/5 h-fit py-0"><img src=${b.image}></td>
                                                        <td class="border w-1/4 py-2">${b.newsContent}</td>
                                                        <td class="border w-fit px-4 py-2">
                                                            <a
                                                                class="bg-teal-300 rounded p-1 mx-1 text-white"
                                                                href="MainController?action=ViewEditBlog&postId=${b.id}"
                                                                >
                                                                <i class="fas fa-edit"></i
                                                                ></a>

                                                            <a id="yesRemove" class="bg-teal-300 rounded p-1 mx-1 text-red-500"
                                                               href="MainController?action=RemoveBlog&postId=${b.id}"
                                                               onclick="return confirm('Do you want to delete this post?')">
                                                                <i class="fas fa-trash-alt"></i>
                                                            </a>                                                         
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!--/Grid Form-->
                        </div>
                    </main>
                    <!--/Main-->
                </div>
                <!-- modal -->


                <!-- modal -->
                <!--Footer-->
                <footer class="bg-grey-darkest text-white p-2">

                    <div class="flex flex-1 mx-auto">&copy; Shop Shoe</div>

                </footer>
                <!--/footer-->
            </div>
        </div>
        <!--  -->

        <!-- modal -->

        <script src="./main.js"></script>
        <script>
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

    </body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="shortcut icon" href="images/logo.png" type="image/x-icon">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <!-- Css -->
        <link rel="stylesheet" href="./dist/styles.css" />
        <link rel="stylesheet" href="./dist/all.css" />
        <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i"
            rel="stylesheet"
            />
        <title>Admin: Blog</title>
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
                            <h1 class="text-white p-2">Shop Shoe</h1>
                        </div>
                        <div class="p-1 flex flex-row items-center">
                            <!--              <img
                                            class="inline-block h-8 w-8 rounded-full"
src="https://avatars0.githubusercontent.com/u/4323180?s=460&v=4"
                                            alt=""
                                          />-->
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
                            <li class="w-full h-full py-3 px-2 border-b border-light-border bg-white">
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
                                    Sent Email
                                    <span><i class="fa fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-300-border">
                                <a
                                    href="MainController?action=Logout"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
                                    >
                                    <i class="fas fa-square-full float-left mx-2"></i>
                                    Log out
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
                                        Active
                                        <form class="flex" action="MainController">
                                            <div class="p-2 rounded flex items-center bg-slate-200" style="width: 500px">
                                                <!--<input type="text" class="p-1 rounded outline-none flex-1" placeholder="" name="search"/>-->
                                            </div>
                                            <!--<button type="submit" name="action=" value="SearchProductManager" class="pr-3" style="outline: none"><i class="fas fa-search mr-1"></i></button>-->
                                        </form>
                                        <a href="createblog.jsp" class="px-4 py-2 rounded block leading-7" id="add_product_swp" style="background-color: rgb(212 212 216);">Add new post</a>
                                    </div>
                                    <div class="p-3 ">
                                        <table
                                            class="table-responsive w-full rounded"
                                            style="text-align: center"
                                            >
                                            <thead>
                                                <tr>
                                                    <th class="border w-1/5 px-10 py-1">Article Title</th>
                                                    <th class="border w-96 px-10 py-2">Date Written</th>
                                                    <th class="border w-96 px-10 py-2">Image</th>
                                                    <th class="border w-96 px-4 py-2">Content</th>
                                                    <th class="border w-fit px-4 py-2">Options</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach items="${VIEW_LIST_BLOG}" var="b">
                                                    <tr>
                                                        <td class="border w-1/5 px-10 py-1">${b.title}</td>
                                                        <td class="border w-96 py-2">${b.date}</td>
                                                        <td class="border w-1/5 h-fit py-0"><img src=${b.image}></td>
                                                        <td class="border w-1/4 py-2">${b.newsContent}</td>
                                                        <td class="border w-fit px-4 py-2">
                                                            <a
                                                                class="bg-teal-300 rounded p-1 mx-1 text-white"
                                                                href="MainController?action=ViewEditBlog&postId=${b.id}"
                                                                >
                                                                <i class="fas fa-edit"></i
                                                                ></a>

                                                            <a id="yesRemove" class="bg-teal-300 rounded p-1 mx-1 text-red-500"
                                                               href="MainController?action=RemoveBlog&postId=${b.id}"
                                                               onclick="return confirm('Do you want to delete this post?')">
                                                                <i class="fas fa-trash-alt"></i>
                                                            </a>                                                         
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!--/Grid Form-->
                        </div>
                    </main>
                    <!--/Main-->
                </div>
                <!-- modal -->


                <!-- modal -->
                <!--Footer-->
                <footer class="bg-grey-darkest text-white p-2">

                    <div class="flex flex-1 mx-auto">&copy; Shop Shoe</div>

                </footer>
                <!--/footer-->
            </div>
        </div>
        <!--  -->

        <!-- modal -->

        <script src="./main.js"></script>
        <script>
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

    </body>
</html>