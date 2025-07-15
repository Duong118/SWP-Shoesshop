<%-- 
    Document   : size-management
    Created on : Jul 15, 2025, 11:13:53 AM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Size Management - Admin</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/admin-style.css">
        <style>
            .container-fluid {
                padding: 20px;
            }
            .card {
                margin-bottom: 20px;
            }
            .btn-action {
                margin-right: 5px;
            }
            .modal-header {
                background-color: #007bff;
                color: white;
            }
            .table th {
                background-color: #f8f9fa;
            }
            .alert {
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <!-- Header -->
            <div class="row mb-4">
                <div class="col-12">
                    <h1 class="h3 mb-3 text-gray-800">Shoe Size Management</h1> 
                    <nav aria-label="breadcrumb"> 
                        <ol class="breadcrumb"> 
                            <li class="breadcrumb-item"><a href="admin.jsp">Dashboard</a></li> 
                            <li class="breadcrumb-item active" aria-current="page">Size Management</li> 
                        </ol>
                    </nav>
                </div>
            </div>

            <!-- Messages -->
            <c:if test="${not empty SUCCESS_MESSAGE}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${SUCCESS_MESSAGE}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <c:if test="${not empty ERROR_MESSAGE}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${ERROR_MESSAGE}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <!-- Add Size Form -->
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title mb-0">
                        <c:choose> 
                            <c:when test="${not empty EDIT_SIZE}"> 
                                Edit Size 
                            </c:when> 
                            <c:otherwise> 
                                Add New Size 
                            </c:otherwise> 
                        </c:choose>
                    </h5>
                </div>
                <div class="card-body">
                    <form method="post" action="SizeManagementController">
                        <c:choose>
                            <c:when test="${not empty EDIT_SIZE}">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id" value="${EDIT_SIZE.id}">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="action" value="add">
                            </c:otherwise>
                        </c:choose>

                        <div class="row"> 
                            <div class="col-md-4"> 
                                <div class="mb-3"> 
                                    <label for="sizeName" class="form-label">Size Name <span class="text-danger">*</span></label> 
                                    <input type="text" class="form-control" id="sizeName" name="sizeName" 
                                           value="${EDIT_SIZE.sizeName}" required 
                                           placeholder="For example: 39, 40, 41..."> 
                                </div> 
                            </div> 
                            <div class="col-md-4"> 
                                <div class="mb-3"> 
                                    <label for="sizeType" class="form-label">Size Type <span class="text-danger">*</span></label> 
                                    <select class="form-select" id="sizeType" name="sizeType" required> 
                                        <option value="">Select size</option> 
                                        <option value="EU" ${EDIT_SIZE.sizeType == 'EU' ? 'selected' : ''}>EU (European)</option> 
                                        <option value="US" ${EDIT_SIZE.sizeType == 'US' ? 'selected' : ''}>US (American)</option> 
                                        <option value="UK" ${EDIT_SIZE.sizeType == 'UK' ? 'selected' : ''}>UK (British)</option> 
                                        <option value="CM" ${EDIT_SIZE.sizeType == 'CM' ? 'selected' : ''}>CM (Centimeters)</option> 
                                    </select> 
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label class="form-label">&nbsp;</label>
                                    <div class="d-grid gap-2 d-md-flex">
                                        <c:choose> 
                                            <c:when test="${not empty EDIT_SIZE}"> 
                                                <button type="submit" class="btn btn-warning"> 
                                                    <i class="fas fa-save"></i> Update 
                                                </button> 
                                                <a href="SizeManagementController" class="btn btn-secondary"> 
                                                    <i class="fas fa-times"></i> Cancel 
                                                </a> 
                                            </c:when> 
                                            <c:otherwise> 
                                                <button type="submit" class="btn btn-primary"> 
                                                    <i class="fas fa-plus"></i> Add Size 
                                                </button> 
                                            </c:otherwise> 
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Size List -->
            <div class="card"> 
                <div class="card-header"> 
                    <h5 class="card-title mb-0">Size List</h5> 
                </div> 
                <div class="card-body"> 
                    <c:choose> 
                        <c:when test="${not empty LIST_SIZES}"> 
                            <div class="table-responsive"> 
                                <table class="table table-bordered table-striped"> 
                                    <thehead> 
                                        <tr> 
                                            <th width="10%">ID</th> 
                                            <th width="30%">Size Name</th> 
                                            <th width="30%">Size Type</th> 
                                            <th width="30%">Action</th> 
                                        </tr> 
                                        </thead> 
                                        <tbody>
                                            <c:forEach var="size" items="${LIST_SIZES}">
                                                <tr>
                                                    <td>${size.id}</td>
                                                    <td>
                                                        <span class="badge bg-info fs-6">${size.sizeName}</span>
                                                    </td>
                                                    <td>
                                                        <span class="badge bg-secondary">${size.sizeType}</span>
                                                    </td>
                                                    <td>
                                                        <a href="SizeManagementController?action=edit&id=${size.id}" 
                                                           class="btn btn-sm btn-outline-warning btn-action"> 
                                                            <i class="fas fa-edit"></i> Edit 
                                                        </a> 
                                                        <button type="button" 
                                                                class="btn btn-sm btn-outline-danger btn-action" 
                                                                onclick="confirmDeleteSize('${size.id}', '${size.sizeName}', '${size.sizeType}')"> 
                                                            <i class="fas fa-trash"></i> Delete 
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                </table>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center py-5">
                                <i class="fas fa-inbox fa-3x text-muted mb-3"></i>
                                <p class="text-muted">No size has been created yet.</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!-- Scripts -->
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script>
                                                                    function confirmDeleteSize(id, sizeName, sizeType) {
                                                                        var message = 'Are you sure you want to delete size "' + sizeName + ' (' + sizeType + ')"?';
                                                                        if (confirm(message)) {
                                                                            window.location.href = 'SizeManagementController?action=delete&id=' + id;
                                                                        }
                                                                    }

                                                                    // Auto hide alerts after 5 seconds
                                                                    $(document).ready(function () {
                                                                        setTimeout(function () {
                                                                            $('.alert').fadeOut('slow');
                                                                        }, 5000);
                                                                    });
        </script>
    </body>
</html>
