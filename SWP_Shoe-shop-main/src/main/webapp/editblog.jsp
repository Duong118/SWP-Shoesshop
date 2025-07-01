<%-- 
    Document   : editblog
    Created on : Jul 1, 2025, 1:49:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <script src="ckeditor/ckeditor.js" type="text/javascript"></script>
        <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i"
            rel="stylesheet"
            />
        <title>Post Management</title>
        <style>
            .price-curren {
                top: 50%;
                transform: translateY(-32%);
                right: 12px;
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
                                    Products
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
                                    href="#"
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
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline bg-white"
                                    >
                                    <i class="fab fa-uikit float-left mx-2"></i>
                                    Post
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
                                    href="#"
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
                    <main
                        class="bg-white-500 flex-1 p-3 overflow-hidden flex justify-center items-center"
                        >
                        <div class="flex flex-col">
                            <!--Grid Form-->

                            <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2">
                                <div
                                    class="mb-2 border-solid border-gray-300 rounded border shadow-sm w-full"
                                    >
                                    <div
                                        class="bg-gray-200 px-2 py-3 border-solid border-gray-200 border-b"
                                        >
                                        Edit the post
                                    </div>
                                    <div class="p-3">
                                        <form class="w-full" action="MainController" method="GET">
                                            <div class="flex flex-wrap -mx-3 mb-6">
                                                <!-- full input -->
                                                <div class="w-full px-3 mb-2">
                                                    <label
                                                        class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                        for="grid-password"
                                                        >
                                                        Article title
                                                    </label>
                                                    <input
                                                        class="appearance-none block w-full bg-grey-200 text-grey-darker border border-grey-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-grey"
                                                        id="grid-password"
                                                        name="postTitle"
                                                        type="text"
                                                        value="${requestScope.EDIT_BLOG.title}"
                                                        placeholder="Enter the name of the article"
                                                        required
                                                        />
                                                </div>
                                                <!-- double input -->
                                                <div class="flex flex-wrap -mx-3 pl-3 w-full mt-4">
                                                    <div class="w-full md:w-1/5 px-3 mb-6 md:mb-0">


                                                    </div>
                                                </div>
                                                <div class="flex flex-wrap -mx-3 pl-3 w-full mt-4">

                                                    <div class="w-full md:w-2/5 px-3">
                                                        <label
                                                            class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                            for="grid-last-name"
                                                            >
                                                            Image
                                                        </label>
                                                        <input
                                                            class="appearance-none block w-full bg-gray-200 text-grey-darker border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                            id="grid-last-name"
                                                            type="text"
                                                            name="image"
                                                            style="width: 700px;"
                                                            value="${requestScope.EDIT_BLOG.image}"
                                                            placeholder="Enter link to image"
                                                            required=""
                                                            />
                                                    </div>
                                                </div>
                                                <!-- end double input -->
                                            </div>
                                            <div class="flex flex-wrap -mx-3 mb-2 px-3">
                                                <label
                                                    for="message"
                                                    class="block mb-2 text-sm font-medium text-gray-900"
                                                    >Product description</label
                                                ><br>

                                            </div>
                                            <textarea
                                                name="postContent"
                                                id="editor1"
                                                placeholder="Your message..."
                                                >${requestScope.EDIT_BLOG.newsContent}</textarea>
                                            <input type ="hidden" name="postId" value="${requestScope.EDIT_BLOG.id}"/>

                                            <button type="submit" name="action" value="EditBlog" 
                                                    style="top: 100%;"
                                                    class="text-white bg-red-700 hover:bg-red-800 font-medium rounded text-sm px-5 py-3 mb-2 block float-right mt-4"
                                                    ><a href="ViewBlogManagerController"
                                                onclick="return confirm('Do you want to unsave this article?')">
                                                    Cancel 
                                                </a>
                                            </button>
                                            <button type="submit" name="action" value="EditBlog" style="position: relative; right: 40%;"
                                                    class="text-white bg-blue-700 hover:bg-blue-800 font-medium rounded text-sm px-5 py-3 mb-2 block float-right mt-4"
                                                    onclick="return confirm('Do you want to save this article?')">
                                                Save the post
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!--/Grid Form-->
                        </div>
                    </main>
                    <!--/Main-->
                </div>
                <!--Footer-->
                <footer class="bg-grey-darkest text-white p-2">
                    <div class="flex flex-1 mx-auto">&copy;Shop Shoe</div>
                </footer>
                <!--/footer-->
            </div>
        </div>

        <script src="./main.js"></script>
        <script>
                                                        CKEDITOR.replace('editor1');
        </script>
    </body>
</html>