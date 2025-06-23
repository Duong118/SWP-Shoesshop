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
        <title>Create Product</title>
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

    <body <c:if test="${checked}"> onload="myDiscount()"</c:if>
                                   <c:if test="${checkedvideo}"> onload="myVideo()"</c:if>
                                   <c:if test="${isDuplicated}"> onload="toggleInput();toggleSelect()"</c:if>
                                   <c:if test="${PRODUCT_ERROR!=null}"> onload="toggleInput();toggleSelect()"</c:if>
                                       >
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
                                                           <li class="w-full h-full py-3 px-2 border-b border-light-border bg-white">
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
                                                           <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                                               <a
                                                                   href="ViewBlogManagerController"
                                                                   class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"
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
                                                                        Add a new product
                                                                   </div>                               
                                                                   <div class="p-3">
                                                                       <form class="w-full" action="MainController" onsubmit="return cateAlert()">
                                                                           <div class="flex flex-wrap -mx-3 mb-6">
                                                                               <!-- full input -->
                                                                               <div class="w-full px-3 mb-2">
                                                                                   <div class="pass-link">
                                                                                       <a>${requestScope.PRODUCT_ERROR}</a>
                                                                               </div>
                                                                           </div>
                                                                           <div class="w-full md:w-full px-3" style="display:inline-block">
                                                                               <label
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   for="name"
                                                                                   >
                                                                                   Product name
                                                                               </label>
                                                                               <input
                                                                                   class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   id="name"
                                                                                   name="name"
                                                                                   type="text"
placeholder="Enter the product name"         
                                                                                   value ="${param.name}"
                                                                                   required;
                                                                                   >
                                                                           </div>
                                                                           <!-- double input -->
                                                                           <div class="w-full md:w-1/4 px-3" style="display:inline-block">
                                                                               <label
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   for="quantity"
                                                                                   >
                                                                                   Quantity
                                                                               </label>
                                                                               <input
                                                                                   class="appearance-none block w-full bg-gray-200 text-grey-darker border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   id="quantity"
                                                                                   type="number"
                                                                                   name="quantity"
                                                                                   placeholder="Enter the product quantity"
                                                                                   value="${param.quantity}"
                                                                                   required;
                                                                                   >
                                                                           </div>
                                                                           <div
                                                                               class="w-full md:w-1/4 px-3 mb-6 md:mb-0 relative"
                                                                               >
                                                                               <label
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
for="originalPrice"
                                                                                   >
                                                                                   Price
                                                                               </label>
                                                                               <input
                                                                                   class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   id="originalPrice"
                                                                                   type="number"
                                                                                   name="originalPrice"
                                                                                   placeholder="Enter the product price"
                                                                                   required
                                                                                   value="${param.originalPrice}"
                                                                                   >
                                                                           </div>
                                                                           <div class="w-full md:w-1/4 px-3" style="display:inline-block">
                                                                               <label
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   for="point"
                                                                                   required;
                                                                                   >
                                                                                   Coin
                                                                               </label>
                                                                               <input
                                                                                   class="appearance-none block w-full bg-gray-200 text-grey-darker border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   id="point"
                                                                                   type="number"
                                                                                   name="point"
placeholder="Enter the coin value for the product"
                                                                                   required;
                                                                                   value="${param.point}"
                                                                                   >
                                                                           </div> 
                                                                           <div
                                                                               class="w-full md:w-1/4 px-3 mb-6 md:mb-0 relative"                                                                                                       
                                                                               >
                                                                               <label
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   for="discountPrice"
                                                                                   >
                                                                                   Discount price
                                                                                   <input type="checkbox" name="dis" id="dis" onclick="myDiscount()" value="checked"<c:if test="${checked}">checked</c:if>>
                                                                                   </label>
                                                                                   <input
                                                                                       class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                       id ="discountPrice"
                                                                                       type="number"
                                                                                       name="discountPrice"                                     
                                                                                       placeholder="Enter the discount price"
                                                                                       style="display:none"
                                                                                       value="${param.discountPrice}"
                                                                                   >
                                                                           </div>
                                                                           <!--  -->
<br>
                                                                           <div class="w-full md:w-1/4 px-3 mb-6 md:mb-0">
                                                                               <label class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1" for="catetype">
                                                                                   Category
                                                                               </label>
                                                                               <div class="relative">
                                                                                   <select class="block appearance-none w-full bg-grey-200 border border-grey-200 text-grey-darker py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-grey" id="catetype" name="catetype">
                                                                                       <option value="" ${empty param.catetype ? 'selected' : ''} hidden>Chọn</option>
                                                                                       <option value="Chim" ${param.catetype == 'Chim' ? 'selected' : ''}>Chim</option>
                                                                                       <option value="Lồng" ${param.catetype == 'Lồng' ? 'selected' : ''}>Lồng</option>
                                                                                       <option value="Thức ăn" ${param.catetype == 'Thức ăn' ? 'selected' : ''}>Thức ăn</option>
                                                                                       <option value="Phụ kiện khác" ${param.catetype == 'Phụ kiện khác' ? 'selected' : ''}>Phụ kiện khác</option>
                                                                                   </select>
                                                                               </div>
                                                                           </div>
                                                                           <div class="w-full md:w-1/4 px-3 mb-6 md:mb-0" id="categoryname" style="visibility: hidden">
                                                                               <label class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1" for="catename">
                                                                                   Classification <p id="smallCategory" style="display: inline-block"></p>
                                                                               </label>
                                                                               <div class="relative">
<select class="block appearance-none w-full bg-grey-200 border border-grey-200 text-grey-darker py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-grey" id="catename" name="catename">
                                                                                       <option hidden>Choose</option>
                                                                                       <c:forEach var="ct" items="${sessionScope.VIEW_CATE}">
                                                                                           <option class="catenameopt" value="${ct.name}" 
                                                                                                   <c:choose>
                                                                                                       <c:when test ="${param.catename=='Khác' && newCate==true}">selected</c:when>
                                                                                                       <c:otherwise>${param.catename == ct.name ? 'selected' : ''}</c:otherwise> 
                                                                                                   </c:choose>
                                                                                                   >${ct.name}</option>
                                                                                       </c:forEach>
                                                                                       <option value="Khác" id="other">Other</option>
                                                                                   </select>
                                                                               </div>
                                                                           </div>
                                                                           <div class="w-full md:w-1/4 px-3 mb-6 md:mb-0" style="visibility: hidden" id="newcatename">
                                                                               <label class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1" for="newcate">
                                                                                   Other category
                                                                               </label>
                                                                               <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600" 
                                                                                      type="text" name="newcatename" id="newcate" value = "${param.newcatename}" placeholder="Nhập loại khác" 
                                                                                      >
                                                                           </div>
<div
                                                                               class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-grey-darker"
                                                                               >                                                  
                                                                           </div>
                                                                           <!--  -->
                                                                           <!-- img -->
                                                                           <div class="w-full md:w-1/2 px-3">
                                                                               <label
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   for="img"
                                                                                   >
                                                                                    Image
                                                                               </label>
                                                                               <input
                                                                                   class="appearance-none inline-block w-full bg-gray-200 text-grey-darker border border-gray-200 rounded py-3 px- leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   id="img"
                                                                                   type="text"
                                                                                   name="img"
                                                                                   value="${param.img}"
                                                                                   placeholder="Enter the product image link"
                                                                                   style=" padding-left: 1rem; padding-right: 1rem;"
                                                                                   required;
                                                                                   >
                                                                           </div>
                                                                           <div class="w-full md:w-1/2 px-3">
                                                                               <label
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   for="video"
>
                                                                                   Video
                                                                                   <input type="checkbox" name="vid" id="vid" onclick="myVideo()" value="checked"<c:if test="${checked}">checked</c:if>>
                                                                                   </label>
                                                                                   <input
                                                                                       class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                       id="videolink"
                                                                                       type="text"
                                                                                       name="videolink"
                                                                                       value="${param.videolink}"
                                                                                   placeholder="Enter the product video link"
                                                                                   style=" padding-left: 1rem; padding-right: 1rem; display: none"
                                                                                   >
                                                                           </div>
                                                                           <!-- end double input -->
                                                                           <div class="w-full flex flex-wrap md:w-1/3 -mx-3 mb-2 px-3" style="margin-left: 0px">                                                  
                                                                               <br>
                                                                               <label
                                                                                   for="shortdescript"
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   style="margin-top: 10px"
                                                                                   >Product description</label>
                                                                               <textarea
                                                                                   name="shortdescript"
                                                                                   id="shortdescript"
                                                                                   rows="4"
class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   placeholder="Product description"
                                                                                   required
                                                                                   >${param.shortdescript}</textarea>
                                                                           </div>
                                                                           <div class="w-full flex flex-wrap md:w-2/3 -mx-3 mb-2 px-3" style="margin-left: 12px">                                                  
                                                                               <label
                                                                                   for="description"
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   style="margin-top: 10px"
                                                                                   >Product details</label>
                                                                               <textarea
                                                                                   name="description"
                                                                                   id="description"
                                                                                   rows="4"
                                                                                   class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   placeholder="Product description"
                                                                                   required
                                                                                   >${param.description}</textarea>
                                                                           </div>
                                                                           <div class="w-full md:w-full px-3" style="display:inline-block">
                                                                               <button
                                                                                   type="submit" name="action" value="CreateProduct"
                                                                                   class="text-white bg-blue-700 hover:bg-blue-800 font-medium rounded text-sm px-2 py-1 mb-1 block float-right"
>
                                                                                   Confirm</button>
                                                                           </div>
                                                                           <!--/Grid Form-->
                                                                       </div>
                                                                   </form>
                                                               </div>
                                                           </div>
                                                       </div>
                                                   </div>
                                               </main>
                                           </div>


                                           <!--/Main-->
                                           <!--Footer-->
                                           <footer class="bg-grey-darkest text-white p-2">
                                               <div class="flex flex-1 mx-auto">&copy; Shoes Shop</div>
                                           </footer>
                                           <!--/footer-->
                                       </div>
                                   </div>

                                   <script src="main.js"></script>
                                   <script src ="js/addeditpro.js"></script>
    </body>
</html>