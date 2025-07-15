
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

            /* Size selection styles */
            .size-grid {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(50px, 1fr));
                gap: 8px;
                max-width: 500px;
            }

            .size-option {
                width: 100%;
            }

            .size-label {
                display: block;
                width: 50px;
                height: 40px;
                line-height: 40px;
                text-align: center;
                border: 2px solid #d1d5db;
                border-radius: 6px;
                cursor: pointer;
                transition: all 0.2s ease;
                font-weight: 500;
                font-size: 14px;
                background-color: white;
            }

            .size-checkbox:checked + .size-label,
            .size-radio:checked + .size-label {
                background-color: #3b82f6;
                color: white;
                border-color: #3b82f6;
                transform: scale(1.05);
            }

            .size-label:hover {
                border-color: #3b82f6;
                background-color: #f8fafc;
            }

            .size-checkbox:checked + .size-label:hover,
            .size-radio:checked + .size-label:hover {
                background-color: #2563eb;
            }

            .size-quantity-input {
                width: 100%;
                padding: 8px 12px;
                border: 1px solid #d1d5db;
                border-radius: 6px;
                font-size: 14px;
                transition: border-color 0.2s ease;
            }

            .size-quantity-input:focus {
                outline: none;
                border-color: #3b82f6;
                box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
            }

            .size-quantities-section {
                background: linear-gradient(to bottom, #f8fafc, #f1f5f9);
                border: 1px solid #e2e8f0;
                border-radius: 8px;
                padding: 16px;
            }

            .quantity-grid {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
                gap: 12px;
            }

            .quantity-item {
                background: white;
                padding: 10px;
                border-radius: 6px;
                border: 1px solid #e2e8f0;
                box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>

    <body <c:if test="${checked}"> onload="myDiscount()"</c:if>
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
                                                                       Add new product                                                                   </div>                               
                                                                   <div class="p-3">
                                                                       <form class="w-full" action="CreateProductController" method="POST" onsubmit="return cateAlert() && validateSizeSelection()">
                                                                           <div class="flex flex-wrap -mx-3 mb-6">
                                                                               <!-- full input -->
                                                                               <div class="w-full px-3 mb-2">
                                                                                   <div class="pass-link">
                                                                                       <a style="color: red;">${requestScope.PRODUCT_ERROR}${sessionScope.PRODUCT_ERROR}</a>
                                                                               </div>
                                                                           </div>
                                                                           <div class="w-full md:w-full px-3" style="display:inline-block">
                                                                               <label
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   for="name"
                                                                                   >
                                                                                   Product Name
                                                                               </label>
                                                                               <input
                                                                                   class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   id="name"
                                                                                   name="name"
                                                                                   type="text"
                                                                                   placeholder="Nhập tên của sản phẩm"         
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
                                                                                   placeholder="Nhập số lượng sản phẩm"
                                                                                   value="${param.quantity}"
                                                                                   required;
                                                                                   >
                                                                           </div>
                                                                           <!-- Size Selection Section -->
                                                                           <div class="w-full px-3 mb-6">
                                                                               <label class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-3">
                                                                                   Size Shoes <span style="color: red;">*</span>
                                                                                   <small class="text-gray-600 normal-case block mt-1">(Chọn một size và nhập số lượng)</small>
                                                                               </label>

                                                                               <!-- Size Selection as Radio Buttons -->
                                                                               <div class="size-grid mb-4">
                                                                                   <c:forEach var="size" items="${ALL_SIZES}">
                                                                                       <div class="size-option">
                                                                                           <input type="radio" 
                                                                                                  id="size_${size.id}" 
                                                                                                  name="selectedSize" 
                                                                                                  value="${size.id}"
                                                                                                  class="hidden size-radio"
                                                                                                  onchange="updateSizeQuantity(this, '${size.sizeName}')">
                                                                                           <label for="size_${size.id}" class="size-label">
                                                                                               ${size.sizeName}
                                                                                           </label>
                                                                                       </div>
                                                                                   </c:forEach>
                                                                               </div>

                                                                               <!-- Single Size Quantity Input -->
                                                                               <div id="size-quantity" class="size-quantities-section" style="display: none;">
                                                                                   <h4 class="text-sm font-semibold mb-3 text-gray-700 flex items-center">
                                                                                       <svg class="w-4 h-4 mr-2" fill="currentColor" viewBox="0 0 20 20">
                                                                                       <path d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
                                                                                       </svg>
                                                                                       Quantity for selected size:
                                                                                   </h4>
                                                                                   <div class="quantity-item" style="max-width: 200px;">
                                                                                       <label id="size-quantity-label" class="block text-xs font-medium mb-2 text-gray-600 uppercase tracking-wide">
                                                                                           Size
                                                                                       </label>
                                                                                       <input type="number" 
                                                                                              name="sizeQuantity" 
                                                                                              id="sizeQuantityInput"
                                                                                              class="size-quantity-input" 
                                                                                              placeholder="Số lượng" 
                                                                                              min="1" 
                                                                                              value="1"
                                                                                              required>
                                                                                   </div>
                                                                               </div>
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
                                                                                   placeholder="Nhập giá của sản phẩm"
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
                                                                                   placeholder="Nhập xu cho sản phẩm"
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
                                                                                   Price reduction
                                                                                   <input type="checkbox" name="dis" id="dis" onclick="myDiscount()" value="checked"<c:if test="${checked}">checked</c:if>>
                                                                                   </label>
                                                                                   <input
                                                                                       class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                       id ="discountPrice"
                                                                                       type="number"
                                                                                       name="discountPrice"                                     
                                                                                       placeholder="Nhập giá discount"
                                                                                       style="display:none"
                                                                                       value="${param.discountPrice}"
                                                                                   >
                                                                           </div>
                                                                           <!--  -->
                                                                           <br>
                                                                           <div class="w-full md:w-1/4 px-3 mb-6 md:mb-0">
                                                                               <label class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1" for="catetype">
                                                                                   Classify
                                                                               </label>
                                                                               <div class="relative">
                                                                                   <select class="block appearance-none w-full bg-grey-200 border border-grey-200 text-grey-darker py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-grey" id="catetype" name="catetype">
                                                                                       <option value="" ${empty param.catetype ? 'selected' : ''} hidden>Chọn</option>
                                                                                       <c:forEach var="type" items="${requestScope.CATEGORY_TYPES}">
                                                                                           <option value="${type}" ${param.catetype == type ? 'selected' : ''}>${type}</option>
                                                                                       </c:forEach>
                                                                                   </select>
                                                                               </div>
                                                                           </div>
                                                                           <div class="w-full md:w-1/4 px-3 mb-6 md:mb-0" id="categoryname" style="visibility: hidden">
                                                                               <label class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1" for="catename">
                                                                                   Classify <p id="smallCategory" style="display: inline-block"></p>
                                                                               </label>
                                                                               <div class="relative">
                                                                                   <select class="block appearance-none w-full bg-grey-200 border border-grey-200 text-grey-darker py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-grey" id="catename" name="catename">
                                                                                       <option hidden>Chọn</option>
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
                                                                                   Other
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
                                                                                   Ảnh
                                                                               </label>
                                                                               <input
                                                                                   class="appearance-none inline-block w-full bg-gray-200 text-grey-darker border border-gray-200 rounded py-3 px- leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   id="img"
                                                                                   type="text"
                                                                                   name="img"
                                                                                   value="${param.img}"
                                                                                   placeholder="Nhập liên kết đến ảnh của sản phẩm"
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
                                                                                   placeholder="Nhập liên kết đến video của sản phẩm"
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
                                                                                   >Mô tả sản phẩm</label>
                                                                               <textarea
                                                                                   name="shortdescript"
                                                                                   id="shortdescript"
                                                                                   rows="4"
                                                                                   class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   placeholder="Mô tả sản phẩm"
                                                                                   required
                                                                                   >${param.shortdescript}</textarea>
                                                                           </div>
                                                                           <div class="w-full flex flex-wrap md:w-2/3 -mx-3 mb-2 px-3" style="margin-left: 12px">                                                  
                                                                               <label
                                                                                   for="description"
                                                                                   class="block uppercase tracking-wide text-gray-900 text-sm font-medium mb-1"
                                                                                   style="margin-top: 10px"
                                                                                   >Chi tiết sản phẩm</label>
                                                                               <textarea
                                                                                   name="description"
                                                                                   id="description"
                                                                                   rows="4"
                                                                                   class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white-500 focus:border-gray-600"
                                                                                   placeholder="Mô tả sản phẩm"
                                                                                   required
                                                                                   >${param.description}</textarea>
                                                                           </div>
                                                                           <div class="w-full md:w-full px-3" style="display:inline-block">
                                                                               <button
                                                                                   type="submit" name="action" value="CreateProduct"
                                                                                   class="text-white bg-blue-700 hover:bg-blue-800 font-medium rounded text-sm px-2 py-1 mb-1 block float-right"
                                                                                   >
                                                                                   Xác nhận</button>
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
                                               <div class="flex flex-1 mx-auto">&copy;Shoes Shop</div>
                                           </footer>
                                           <!--/footer-->
                                       </div>
                                   </div>

                                   <script>
                                       let selectedSize = null;

                                       function updateSizeQuantity(radio, sizeName) {
                                           const sizeId = radio.value;
                                           const sizeQuantityDiv = document.getElementById('size-quantity');
                                           const sizeQuantityLabel = document.getElementById('size-quantity-label');
                                           const sizeQuantityInput = document.getElementById('sizeQuantityInput');

                                           if (radio.checked) {
                                               selectedSize = sizeId;

                                               // Update label and show quantity input
                                               sizeQuantityLabel.textContent = `Size ${sizeName}`;
                                               sizeQuantityInput.value = '1'; // Default quantity

                                               // Show size quantity section with animation
                                               sizeQuantityDiv.style.display = 'block';
                                               setTimeout(() => {
                                                   sizeQuantityDiv.style.opacity = '1';
                                                   sizeQuantityDiv.style.transform = 'translateY(0)';
                                               }, 10);

                                               // Hide the main quantity input row since we'll use size-specific quantity
                                               const quantityRow = document.getElementById('quantity').closest('.w-full');
                                               quantityRow.style.display = 'none';
                                               document.getElementById('quantity').removeAttribute('required');
                                           }
                                       }

                                       // Validate form before submission
                                       function validateSizeSelection() {
                                           if (!selectedSize) {
                                               alert('Vui lòng chọn một size cho sản phẩm!');
                                               return false;
                                           }

                                           // Validate that quantity is entered
                                           const quantityInput = document.getElementById('sizeQuantityInput');
                                           if (!quantityInput || !quantityInput.value || quantityInput.value <= 0) {
                                               alert('Vui lòng nhập số lượng hợp lệ cho size đã chọn!');
                                               return false;
                                           }

                                           return true;
                                       }
                                   </script>
                                   <script src="main.js"></script>
                                   <script src ="js/addeditpro.js"></script>
    </body>
</html>