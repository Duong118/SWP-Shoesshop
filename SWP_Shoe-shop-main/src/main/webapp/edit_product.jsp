<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Edit Product</title>
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

            .size-checkbox {
                display: none;
            }

            .size-label {
                display: inline-block;
                padding: 8px 12px;
                margin: 5px;
                border: 2px solid #ddd;
                border-radius: 5px;
                cursor: pointer;
                transition: all 0.3s ease;
                background-color: #f8f9fa;
            }

            .size-option {
                transition: all 0.3s ease;
                cursor: pointer;
            }

            .size-option:hover {
                background-color: #f8f9fa;
            }

            .size-option .size-checkbox:checked + .size-label {
                background-color: #007bff;
                color: white;
            }

            .size-label {
                cursor: pointer;
                margin: 0;
            }

            .size-checkbox:checked + .size-label {
                background-color: #007bff;
                color: white;
                border-color: #007bff;
            }

            .quantity-input {
                width: 80px;
                margin-left: 10px;
                display: none;
            }

            .size-checkbox:checked ~ .quantity-input {
                display: inline-block;
            }

            .form-section {
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0,0,0,0.1);
                margin-bottom: 20px;
                overflow: hidden;
            }

            .section-header {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: white;
                padding: 15px 20px;
                margin: 0;
                font-weight: 600;
            }

            .image-preview {
                max-width: 200px;
                max-height: 200px;
                border-radius: 8px;
                border: 2px solid #ddd;
                margin-top: 10px;
                display: none;
            }

            .btn-custom {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                border: none;
                color: white;
                padding: 12px 30px;
                border-radius: 25px;
                font-weight: 600;
                transition: all 0.3s ease;
            }

            .btn-custom:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(0,0,0,0.2);
                color: white;
            }

            .error-message {
                color: #dc3545;
                font-weight: 500;
                margin-bottom: 15px;
            }

            .success-message {
                color: #28a745;
                font-weight: 500;
                margin-bottom: 15px;
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
                            <i class="fas fa-bars pr-2 text-white" onclick="sidebarToggle()"></i>
                            <h1 class="text-white p-2">Premium Shoe Shop</h1>
                        </div>
                        <div class="p-1 flex flex-row items-center">
                            <!-- User info can be added here -->
                        </div>
                    </div>
                </header>
                <!--/Header-->

                <div class="flex flex-1">
                    <!--Sidebar-->
                    <aside id="sidebar" class="bg-side-nav w-1/2 md:w-1/6 lg:w-1/6 border-r border-side-nav hidden md:block lg:block">
                        <div class="flex"></div>
                        <ul class="list-reset flex flex-col">
                            <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                <a href="Dashboard" class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
                                    <i class="fas fa-tachometer-alt float-left mx-2"></i>
                                    Dashboard
                                    <span><i class="fas fa-angle-right float-right"></i></span>
                                </a>
                            </li>
                            <li class="w-full h-full py-3 px-2 border-b border-light-border bg-white">
                                <a href="ViewProductManagerController" class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline bg-white"> 
                                    <i class="fab fa-wpforms float-left mx-2"></i> 
                                    Product 
                                    <span><i class="fa fa-angle-right float-right"></i></span> 
                                </a> 
                            </li> 
                            <li class="w-full h-full py-3 px-2 border-b border-light-border"> 
                                <div id="menu"> 
                                    <a href="ViewAllOrderController" class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"> 
                                        <i class="fas fa-grip-horizontal float-left mx-2"></i> 
                                        Order 
                                        <span><i class="fa fa-angle-right float-right"></i></span> 
                                    </a> 
                                </div> 
                            </li> 
                            <li class="w-full h-full py-3 px-2 border-b border-light-border"> 
                                <a href="ViewBlogManagerController" class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"> 
                                    <i class="fas fa-file-alt float-left mx-2"></i> 
                                    Blog 
                                    <span><i class="fa fa-angle-right float-right"></i></span> 
                                </a> 
                            </li> 
                            <li class="w-full h-full py-3 px-2 border-b border-light-border"> 
                                <a href="ViewFeedbackController" class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"> 
                                    <i class="fas fa-comments float-left mx-2"></i> 
                                    Evaluate 
                                    <span><i class="fa fa-angle-right float-right"></i></span> 
                                </a> 
                            </li> 
                            <li class="w-full h-full py-3 px-2 border-b border-light-border"> 
                                <a href="GetSizeManagerController" class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"> 
                                    <i class="fas fa-ruler float-left mx-2"></i> 
                                    Size management 
                                    <span><i class="fa fa-angle-right float-right"></i></span> 
                                </a> 
                            </li> 
                            <li class="w-full h-full py-3 px-2"> 
                                <a href="LogoutController" class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline"> 
                                    <i class="fas fa-sign-out-alt float-left mx-2"></i> 
                                    Sign out 
                                    <span><i class="fa fa-angle-right float-right"></i></span> 
                                </a> 
                            </li>
                        </ul>
                    </aside>
                    <!--/Sidebar-->
                    <!--Main-->
                    <main class="bg-white-500 flex-1 p-3 overflow-auto">
                        <div class="container-fluid">
                            <!-- Header -->
                            <div class="row mb-4">
                                <div class="col-12">
                                    <h2 class="text-primary">Edit Product</h2>
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="Dashboard">Dashboard</a></li>
                                            <li class="breadcrumb-item"><a href="ViewProductManagerController">Products</a></li>
                                            <li class="breadcrumb-item active">Edit</li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>

                            <!-- Error/Success Messages -->
                            <c:if test="${not empty requestScope.PRODUCT_ERROR}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <i class="fas fa-exclamation-triangle me-2"></i>
                                    ${requestScope.PRODUCT_ERROR}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                </div>
                            </c:if>
                            <c:if test="${not empty requestScope.UPDATE_SUCCESS}">
                                <div class="alert alert-success alert-dismissible fade show" role="alert">
                                    <i class="fas fa-check-circle me-2"></i>
                                    ${requestScope.UPDATE_SUCCESS}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                </div>
                            </c:if>

                            <form action="MainController" method="post" id="updateProductForm">
                                <input type="hidden" name="action" value="UpdateProduct">
                                <input type="hidden" name="productId" value="${PRODUCT.id}">

                                <!-- Basic Information Section -->
                                <div class="form-section">
                                    <h4 class="section-header"> 
                                        <i class="fas fa-info-circle me-2"></i>Basic information 
                                    </h4> 
                                    <div class="p-4"> 
                                        <div class="row"> 
                                            <div class="col-md-8"> 
                                                <div class="mb-3"> 
                                                    <label for="name" class="form-label fw-bold">Product name <span class="text-danger">*</span></label> 
                                                    <input type="text" class="form-control" id="name" name="name" 
                                                           value="${PRODUCT.name}" placeholder="Enter product name" required> 
                                                </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                                <div class="mb-3"> 
                                                    <label for="point" class="form-label fw-bold">Bonus points <span class="text-danger">*</span></label> 
                                                    <input type="number" class="form-control" id="point" name="point"
                                                           value="${PRODUCT.point}" placeholder="Enter bonus points" min="0" required>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="mb-3">
                                                    <label for="originalPrice" class="form-label fw-bold">Original price <span class="text-danger">*</span></label> 
                                                    <div class="input-group"> 
                                                        <input type="number" class="form-control" id="originalPrice" name="originalPrice" 
                                                               value="${PRODUCT.originalPrice}" placeholder="Enter original price" min="0" required> 
                                                        <span class="input-group-text">VND</span> 
                                                    </div> 
                                                </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                                <div class="mb-3"> 
                                                    <label for="discountPrice" class="form-label fw-bold"> 
                                                        Promotional price 
                                                        <input type="checkbox" id="hasDiscount" onchange="toggleDiscountPrice()" 
                                                               ${PRODUCT.discountPrice < PRODUCT.originalPrice ? 'checked' : ''}> 
                                                    </label> 
                                                    <div class="input-group"> 
                                                        <input type="number" class="form-control" id="discountPrice" name="discountPrice" 
                                                               value="${PRODUCT.discountPrice}" placeholder="Enter promotional price" min="0" 
                                                               style="${PRODUCT.discountPrice < PRODUCT.originalPrice ? 'display: block;' : 'display: none;'}"> 
                                                        <span class="input-group-text" id="discountPriceUnit" 
                                                              style="${PRODUCT.discountPrice < PRODUCT.originalPrice ? 'display: block;' : 'display: none;'}">VND</span> 
                                                    </div> 
                                                </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                                <div class="mb-3"> 
                                                    <label class="form-label fw-bold">Product status</label> 
                                                    <div class="form-check"> 
                                                        <input class="form-check-input" type="checkbox" id="isHot" name="isHot" 
                                                               ${PRODUCT.hot ? 'checked' : ''}> 
                                                        <label class="form-check-label" for="isHot"> 
                                                            <i class="fas fa-fire text-danger me-2"></i>Hot product 
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Category Section -->
                                <div class="form-section">
                                    <h4 class="section-header"> 
                                        <i class="fas fa-tags me-2"></i>Product classification 
                                    </h4> 
                                    <div class="p-4"> 
                                        <div class="row"> 
                                            <div class="col-md-6"> 
                                                <div class="mb-3"> 
                                                    <label for="categoryType" class="form-label fw-bold">Category type <span class="text-danger">*</span></label> 
                                                    <select class="form-select" id="categoryType" name="categoryType" onchange="updateCategoryOptions()" required> 
                                                        <option value="">Select category type</option> 
                                                        <c:forEach var="type" items="${requestScope.CATEGORY_TYPES}"> 
                                                            <option value="${type}" ${CATE.type == type ? 'selected' : ''}>${type}</option> 
                                                        </c:forEach> 
                                                    </select> 
                                                </div> 
                                            </div> 
                                            <div class="col-md-6"> 
                                                <div class="mb-3"> 
                                                    <label for="categoryName" class="form-label fw-bold">Category name <span class="text-danger">*</span></label> 
                                                    <select class="form-select" id="categoryName" name="categoryName" required> 
                                                        <option value="">Select category</option> 
                                                        <c:forEach var="category" items="${sessionScope.VIEW_CATE}"> 
                                                            <option value="${category.name}" data-type="${category.type}" data-id="${category.id}" 
                                                                    ${CATE.name == category.name ? 'selected' : ''}>${category.name}</option>
                                                        </c:forEach>
                                                        <option value="OTHER">Other (Enter new)</option>
                                                    </select>
                                                </div>
                                                <div class="mb-3" id="newCategoryDiv" style="display: none;">
                                                    <label for="newCategoryName" class="form-label fw-bold">New category name</label>
                                                    <input type="text" class="form-control" id="newCategoryName" name="newCategoryName" placeholder="Enter new category name">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Image & Description Section -->
                                <div class="form-section">
                                    <h4 class="section-header"> 
                                        <i class="fas fa-image me-2"></i>Image & Description 
                                    </h4> 
                                    <div class="p-4"> 
                                        <div class="row"> 
                                            <div class="col-md-6"> 
                                                <div class="mb-3"> 
                                                    <label for="image" class="form-label fw-bold">Image path <span class="text-danger">*</span></label> 
                                                    <input type="url" class="form-control" id="image" name="image" 
                                                           value="${PRODUCT.image}" placeholder="Enter image URL" 
                                                           onchange="previewImage()" required> 
                                                    <div class="mt-2"> 
                                                        <img id="imagePreview" class="image-preview" src="${PRODUCT.image}" 
                                                             style="${not empty PRODUCT.image ? 'display: block;' : 'display: none;'}"> 
                                                    </div> 
                                                </div> 
                                            </div> 
                                            <div class="col-md-6"> 
                                                <div class="mb-3"> 
                                                    <label for="shortDescription" class="form-label fw-bold">Short description <span class="text-danger">*</span></label> 
                                                    <textarea class="form-control" id="shortDescription" name="shortDescription" 
                                                              rows="4" placeholder="Enter a short description of the product" required>${PRODUCT.shortDescription}</textarea> 
                                                </div> 
                                            </div> 
                                        </div> 

                                        <div class="row"> 
                                            <div class="col-12"> 
                                                <div class="mb-3"> 
                                                    <label for="description" class="form-label fw-bold">Detailed description <span class="text-danger">*</span></label> 
                                                    <textarea class="form-control" id="description" name="description"
                                                              rows="6" placeholder="Enter detailed product description" required>${PRODUCT.description}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Size & Quantity Section -->
                                <div class="form-section">
                                    <h4 class="section-header">
                                        <i class="fas fa-ruler-combined me-2"></i>Size & Quantity
                                    </h4>
                                    <div class="p-4">
                                        <div class="alert alert-info">
                                            <i class="fas fa-info-circle me-2"></i>
                                            <strong>Instructions:</strong> Select the available sizes and enter the corresponding quantity for each size.
                                        </div>
                                        <div id="sizeQuantityContainer">
                                            <div class="row">
                                                <c:forEach var="size" items="${requestScope.AVAILABLE_SIZES}">
                                                    <c:set var="currentQuantity" value="0" />
                                                    <c:set var="isSelected" value="false" />

                                                    <!-- Check if this size exists for current product -->
                                                    <c:forEach var="productSize" items="${requestScope.PRODUCT_SIZES}">
                                                        <c:if test="${productSize.sizeId == size.id}">
                                                            <c:set var="currentQuantity" value="${productSize.quantity}" />
                                                            <c:set var="isSelected" value="${productSize.quantity > 0}" />
                                                        </c:if>
                                                    </c:forEach>

                                                    <div class="col-md-3 col-sm-4 col-6 mb-3">
                                                        <div class="border rounded p-3 text-center size-option">
                                                            <input type="checkbox" class="size-checkbox" id="size_${size.id}" 
                                                                   name="selectedSizes" value="${size.id}" 
                                                                   ${isSelected ? 'checked' : ''}
                                                                   data-size-id="${size.id}">
                                                            <label for="size_${size.id}" class="size-label w-100 mb-2">
                                                                <strong>${size.sizeName}</strong><br>
                                                                <small class="text-muted">${size.sizeType}</small>
                                                            </label>
                                                            <input type="number" class="form-control form-control-sm quantity-input" 
                                                                   id="quantity_${size.id}" name="quantities[${size.id}]" 
                                                                   value="${currentQuantity}" min="0" max="9999"
                                                                   placeholder="Số lượng" ${!isSelected ? 'disabled' : ''}>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Action Buttons -->
                                <div class="form-section">
                                    <div class="p-4">
                                        <div class="d-flex justify-content-between"> 
                                            <a href="ViewProductManagerController" class="btn btn-secondary"> 
                                                <i class="fas fa-arrow-left me-2"></i>Go back 
                                            </a> 
                                            <div> 
                                                <button type="button" class="btn btn-outline-primary me-2" onclick="resetForm()"> 
                                                    <i class="fas fa-undo me-2"></i>Reset 
                                                </button> 
                                                <button type="submit" class="btn btn-custom"> 
                                                    <i class="fas fa-save me-2"></i>Save changes 
                                                </button> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </main>
                    <!--/Main-->
                </div>
            </div>
            <!--/Container-->
        </div>

        <!-- JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="main.js"></script>

        <script>
    // Initialize page
    document.addEventListener('DOMContentLoaded', function () {
        initializePage();
        loadProductSizes();
    });

    function initializePage() {
        // Initialize category options
        updateCategoryOptions();

        // Initialize discount price visibility
        toggleDiscountPrice();
    }

    function updateCategoryOptions() {
        const categoryType = document.getElementById('categoryType').value;
        const categoryName = document.getElementById('categoryName');
        const options = categoryName.querySelectorAll('option[data-type]');

        // Hide all options first
        options.forEach(option => {
            if (option.dataset.type && option.dataset.type !== categoryType) {
                option.style.display = 'none';
            } else if (option.dataset.type === categoryType) {
                option.style.display = 'block';
            }
        });

        // Show/hide new category input
        handleNewCategoryOption();
    }

    function handleNewCategoryOption() {
        const categoryName = document.getElementById('categoryName');
        const newCategoryDiv = document.getElementById('newCategoryDiv');

        categoryName.addEventListener('change', function () {
            if (this.value === 'OTHER') {
                newCategoryDiv.style.display = 'block';
                document.getElementById('newCategoryName').required = true;
            } else {
                newCategoryDiv.style.display = 'none';
                document.getElementById('newCategoryName').required = false;
            }
        });
    }

    function toggleDiscountPrice() {
        const hasDiscount = document.getElementById('hasDiscount');
        const discountPrice = document.getElementById('discountPrice');
        const discountPriceUnit = document.getElementById('discountPriceUnit');

        if (hasDiscount.checked) {
            discountPrice.style.display = 'block';
            discountPriceUnit.style.display = 'block';
            discountPrice.required = true;
        } else {
            discountPrice.style.display = 'none';
            discountPriceUnit.style.display = 'none';
            discountPrice.required = false;
            discountPrice.value = '';
        }
    }

    function previewImage() {
        const imageUrl = document.getElementById('image').value;
        const preview = document.getElementById('imagePreview');

        if (imageUrl.trim() !== '') {
            preview.src = imageUrl;
            preview.style.display = 'block';
            preview.onerror = function () {
                this.style.display = 'none';
            };
        } else {
            preview.style.display = 'none';
        }
    }

    function toggleQuantityInput(sizeId) {
        const checkbox = document.getElementById('size_' + sizeId);
        const quantityInput = document.getElementById('quantity_' + sizeId);

        if (checkbox.checked) {
            quantityInput.disabled = false;
            quantityInput.focus();
            if (quantityInput.value == 0) {
                quantityInput.value = 1;
            }
        } else {
            quantityInput.disabled = true;
            quantityInput.value = 0;
        }
    }

    function resetForm() {
        if (confirm('Bạn có chắc chắn muốn đặt lại form? Tất cả thay đổi sẽ bị mất.')) {
            document.getElementById('updateProductForm').reset();
            // Reset size checkboxes and quantities
            document.querySelectorAll('.size-checkbox').forEach(checkbox => {
                checkbox.checked = false;
                const quantityInput = document.getElementById('quantity_' + checkbox.value);
                if (quantityInput) {
                    quantityInput.disabled = true;
                    quantityInput.value = 0;
                }
            });
        }
    }

    // Form validation
    document.getElementById('updateProductForm').addEventListener('submit', function (e) {
        e.preventDefault();

        // Validate required fields
        const requiredFields = ['name', 'originalPrice', 'point', 'categoryType', 'categoryName', 'image', 'shortDescription', 'description'];
        let isValid = true;

        requiredFields.forEach(field => {
            const element = document.getElementById(field);
            if (!element.value.trim()) {
                element.classList.add('is-invalid');
                isValid = false;
            } else {
                element.classList.remove('is-invalid');
            }
        });

        // Validate discount price
        const hasDiscount = document.getElementById('hasDiscount').checked;
        const originalPrice = parseFloat(document.getElementById('originalPrice').value);
        const discountPrice = parseFloat(document.getElementById('discountPrice').value);

        if (hasDiscount && discountPrice >= originalPrice) {
            alert('Giá khuyến mãi phải nhỏ hơn giá gốc!');
            document.getElementById('discountPrice').classList.add('is-invalid');
            isValid = false;
        } else {
            document.getElementById('discountPrice').classList.remove('is-invalid');
        }

        // Validate at least one size is selected
        const selectedSizes = document.querySelectorAll('input[name="selectedSizes"]:checked');
        if (selectedSizes.length === 0) {
            alert('Vui lòng chọn ít nhất một size cho sản phẩm!');
            isValid = false;
        }

        if (isValid) {
            // Show loading state
            const submitBtn = document.querySelector('button[type="submit"]');
            const originalText = submitBtn.innerHTML;
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Đang lưu...';
            submitBtn.disabled = true;

            // Submit form
            this.submit();
        }
    });

    // Sidebar toggle functionality
    function sidebarToggle() {
        const sidebar = document.getElementById('sidebar');
        sidebar.classList.toggle('hidden');
    }

    // Initialize page
    document.addEventListener('DOMContentLoaded', function () {
        // Add event listeners for size checkboxes
        document.querySelectorAll('.size-checkbox').forEach(checkbox => {
            checkbox.addEventListener('change', function () {
                const sizeId = this.getAttribute('data-size-id');
                toggleQuantityInput(sizeId);
            });
        });
    });
        </script>
    </body>
</html>
