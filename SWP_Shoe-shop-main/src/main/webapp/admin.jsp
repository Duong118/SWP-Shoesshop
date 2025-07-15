<%-- 
    Document   : admin
    Created on : Jun 29, 2025, 8:16:16 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="shortcut icon" href="images/logo.png" type="image/x-icon">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <meta
            name="keywords"
            content="tailwind,tailwindcss,tailwind css,css,starter template,free template,admin templates, admin template, admin dashboard, free tailwind templates, tailwind example"
            />
        <!-- Css -->
        <link rel="stylesheet" href="./dist/styles.css" />
        <link rel="stylesheet" href="./dist/all.css" />
        <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i"
            rel="stylesheet"
            />
        <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">

        <title>Admin</title>    

        <style>
            .show-swp{
                display: block;
            }
            .show-swp-icon{
                transform: rotate(90deg)
            }
        </style>
        <style type="text/css">
            .labels {
                font-size: smaller;
            }
        </style>

    </head>

    <body>
        <!--Container -->
        <div class="mx-auto bg-grey-400">
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
                            <h1 class="text-white p-2">Shoe shop</h1>
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
                                class="w-full h-full py-3 px-2 border-b border-light-border bg-white"
                                >
                                <a
                                    href="Dashboard"
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline bg-white"
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
                                <a 
                                    href="SizeManagementController" 
                                    class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline" 
                                    > 
                                    <i class="fas fa-ruler float-left mx-2"></i> 
                                    Size Management 
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
                    <main class="bg-white-300 flex-1 p-3 overflow-hidden">
                        <div class="flex flex-col">
                            <!-- Stats Row Starts Here -->
                            <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2">
                                <div
                                    class="shadow bg-blue-500 border hover:bg-blue-dark border-blue-darkest mb-2 p-2 md:w-1/4 mx-2;"
                                    >
                                    <div class="p-4 flex flex-col">
                                        <a href="#" class="no-underline text-white text-2xl">
                                            Total Users:<br>
                                            ${sessionScope.USERS}
                                        </a>
                                        <a href="#" class="no-underline text-white text-mg">
                                            Updated: <time>${sessionScope.TIME}</time>
                                        </a>
                                    </div>
                                </div>
                                <style></style>
                                <div
                                    class="shadow bg-blue-500 border hover:bg-blue-dark border-blue-darkest mb-2 p-2 md:w-1/4 mx-2;"
                                    >
                                    <div class="p-4 flex flex-col">
                                        <a href="ViewProductManagerController" class="no-underline text-white text-2xl">
                                            Total Products:<br>
                                            ${sessionScope.PRODUCTS}

                                        </a>
                                        <a href="ViewProductManagerController" class="no-underline text-white text-mg">
                                            Updated: <time>${sessionScope.TIME}</time>
                                        </a>
                                    </div>
                                </div>

                                <div
                                    class="shadow bg-blue-500 border hover:bg-blue-dark border-blue-darkest mb-2 p-2 md:w-1/4 mx-2;"
                                    >
                                    <div class="p-4 flex flex-col">
                                        <a href="ViewBlogManagerController" class="no-underline text-white text-2xl">
                                            Total Blogs:<br>
                                            ${sessionScope.BLOGS}
                                        </a>
                                        <a href="ViewBlogManagerController" class="no-underline text-white text-mg">
                                            Updated: <time>${sessionScope.TIME}</time>
                                        </a>
                                    </div>
                                </div>

                                <div
                                    class="shadow bg-blue-500 border hover:bg-blue-dark border-blue-darkest mb-2 p-2 md:w-1/4 mx-2;"
                                    >
                                    <div class="p-4 flex flex-col">
                                        <a href="ViewAllOrderController" class="no-underline text-white text-2xl">
                                            Complete Orders:<br>
                                            ${sessionScope.ORDERS}

                                        </a>
                                        <a href="ViewAllOrderController" class="no-underline text-white text-mg">
                                            Updated: <time>${sessionScope.TIME}</time>
                                        </a>
                                    </div>
                                </div>

                                <div
                                    class="shadow bg-blue-500 border hover:bg-blue-dark border-blue-darkest mb-2 p-2 md:w-1/4 mx-2;"
                                    >
                                    <div class="p-4 flex flex-col">
                                        <a href="ViewOrderByStatusController?status=SUCCESS" class="no-underline text-white text-2xl">
                                            Total Sales: <br>
                                            <c:set var="pri" value="${sessionScope.SALES}"/>
                                            <fmt:setLocale value="vi_VN"/>
                                            <fmt:formatNumber value="${pri}" type="currency"/>
                                        </a>
                                        <a href="ViewOrderByStatusController?status=SUCCESS" class="no-underline text-white text-mg">
                                            Updated: <time>${sessionScope.TIME}</time>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js"></script>
                            <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
                            <br>
                            <h1 class= "tittle">Dashboard</h1>
                            <!-- SalesChart -->
                            <div id="chartContainer" style="display: flex; flex-wrap: wrap">

                                <style>
                                    .chartBox {
                                        width: 750px;
                                        padding: 20px;
                                        border-radius: 20px;
                                        border: solid 3px rgba(54, 162, 235, 1);
                                        background: white;
                                        margin: 10px;
                                        display: inline-block;
                                    }
                                    button {
                                        margin: 20px;
                                    }
                                    .custom-btn {
                                        width: 130px;
                                        height: 40px;
                                        color: #fff;
                                        border-radius: 5px;
                                        padding: 10px 25px;
                                        font-family: 'Lato', sans-serif;
                                        font-weight: 500;
                                        background: transparent;
                                        cursor: pointer;
                                        transition: all 0.3s ease;
                                        position: relative;
                                        display: inline-block;
                                        box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
                                            7px 7px 20px 0px rgba(0,0,0,.1),
                                            4px 4px 5px 0px rgba(0,0,0,.1);
                                        outline: none;
                                    }
                                    .btn-3 {
                                        background: rgb(0,172,238);
                                        background: linear-gradient(0deg, rgba(0,172,238,1) 0%, rgba(2,126,251,1) 100%);
                                        width: 130px;
                                        height: 40px;
                                        line-height: 42px;
                                        padding: 0;
                                        border: none;

                                    }
                                    .btn-3 span {
                                        position: relative;
                                        display: block;
                                        width: 100%;
                                        height: 100%;
                                    }
                                    .btn-3:before,
                                    .btn-3:after {
                                        position: absolute;
                                        content: "";
                                        right: 0;
                                        top: 0;
                                        background: rgba(2,126,251,1);
                                        transition: all 0.3s ease;
                                    }
                                    .btn-3:before {
                                        height: 0%;
                                        width: 2px;
                                    }
                                    .btn-3:after {
                                        width: 0%;
                                        height: 2px;
                                    }
                                    .btn-3:hover{
                                        background: transparent;
                                        box-shadow: none;
                                    }
                                    .btn-3:hover:before {
                                        height: 100%;
                                    }
                                    .btn-3:hover:after {
                                        width: 100%;
                                    }
                                    .btn-3 span:hover{
                                        color: rgba(2,126,251,1);
                                    }
                                    .btn-3 span:before,
                                    .btn-3 span:after {
                                        position: absolute;
                                        content: "";
                                        left: 0;
                                        bottom: 0;
                                        background: rgba(2,126,251,1);
                                        transition: all 0.3s ease;
                                    }
                                    .btn-3 span:before {
                                        width: 2px;
                                        height: 0%;
                                    }
                                    .btn-3 span:after {
                                        width: 0%;
                                        height: 2px;
                                    }
                                    .btn-3 span:hover:before {
                                        height: 100%;
                                    }
                                    .btn-3 span:hover:after {
                                        width: 100%;
                                    }
                                    .doughnutChart {
                                        width: 400px;
                                        height: 400px;
                                        padding: 20px;
                                        border-radius: 20px;
                                        border: solid 3px rgba(54, 162, 235, 1);
                                        background: white;
                                        display: inline-flex;
                                        margin: 10px;
                                        aspect-ratio: 1;
                                        flex-wrap: wrap;
                                    }
                                    .frame {
                                        position: relative;
                                        width: 100%;
                                        text-align: center;
                                    }
                                    .tittle {
                                        position: relative;
                                        text-align: center;
                                        color: #353535;
                                        font-size: 30px;
                                        font-family: "Cormorant Garamond", serif;
                                        width: inherit;
                                        margin: 10px;
                                    }
                                    .dailyChart {
                                        width: 750px;
                                        padding: 20px;
                                        border-radius: 20px;
                                        border: solid 3px rgba(54, 162, 235, 1);
                                        background: white;
                                        margin: 10px;
                                        display: inline-block;
                                    }
                                    .bestSeller {
                                        width: 400px;
                                        display: flex;
                                        height: min-content;
                                        position: relative;
                                        margin: 10px;
                                        border-width: thick;
                                        flex-wrap: wrap;
                                    }
                                </style>   
                                <div class="chartBox">
                                    <div class="frame">
                                        <h1 class="title">Revenue Statistics</h1>                                        
                                        <button class="custom-btn btn-3" onclick="timeFrame(this)" value="day"><span>Daily</span></button>
                                        <button class="custom-btn btn-3" onclick="timeFrame(this)" value="week"><span>Weekly</span></button>
                                        <button class="custom-btn btn-3" onclick="timeFrame(this)" value="month"><span>Monthly</span></button>
                                        <button class="custom-btn btn-3" onclick="timeFrame(this)" value="year"><span>Annually</span></button>
                                    </div>
                                    <canvas id="salesChart" ></canvas>

                                </div>
                                <script>
                                    // setup 
                                    const day = [${daily}];
                                    const week = [${weekly}];
                                    const month = [${monthly}];
                                    const year = [${annually}];
                                    const data = {
                                        datasets: [{
                                                label: 'Sales',
                                                data: day,
                                                backgroundColor: [
                                                    'rgba(255, 26, 104, 0.2)',
                                                    'rgba(54, 162, 235, 0.2)',
                                                    'rgba(255, 206, 86, 0.2)',
                                                    'rgba(75, 192, 192, 0.2)',
                                                    'rgba(153, 102, 255, 0.2)',
                                                    'rgba(255, 159, 64, 0.2)',
                                                    'rgba(0, 0, 0, 0.2)'
                                                ],
                                                borderColor: [
                                                    'rgba(255, 26, 104, 1)',
                                                    'rgba(54, 162, 235, 1)',
                                                    'rgba(255, 206, 86, 1)',
                                                    'rgba(75, 192, 192, 1)',
                                                    'rgba(153, 102, 255, 1)',
                                                    'rgba(255, 159, 64, 1)',
                                                    'rgba(0, 0, 0, 1)'
                                                ],
                                                borderWidth: 1
                                            }]
                                    };
                                    // config 
                                    const config = {
                                        type: 'bar',
                                        data,
                                        options: {
                                            responsive: true,
                                            scales: {
                                                x: {
                                                    fontSize: 14,
                                                    type: 'time',
                                                    time: {
                                                        unit: 'day',
                                                        tooltipFormat: 'dd-MM-yyyy'
                                                    },
                                                    title: {
                                                        display: true,
                                                        text: 'Time'
                                                    }
                                                },
                                                y: {
                                                    beginAtZero: true,
                                                    title: {
                                                        display: true,
                                                        text: 'Sales'
                                                    }
                                                }
                                            },
                                            plugins: {
                                                tooltip: {
                                                    callbacks: {
                                                        label: function (context) {
                                                            let label = context.dataset.label || '';
                                                            if (label) {
                                                                label += ': ';
                                                            }
                                                            label += context.parsed.y;
                                                            return label;
                                                        }
                                                    },
                                                    displayColors: false,
                                                    backgroundColor: 'rgba(0, 0, 0, 0.8)',
                                                    titleFont: {size: 16},
                                                    bodyFont: {size: 14},
                                                    padding: 10,
                                                    cornerRadius: 10,
                                                    intersect: false,
                                                    mode: 'index',
                                                    position: 'nearest',
                                                    axis: 'x',
                                                    animation: {
                                                        duration: 1
                                                    },
                                                    time: {
                                                        tooltipFormat: 'YYYY-MM-DD',
                                                        // set time to false to disable time display in tooltip
                                                        display: false
                                                    }
                                                }
                                            }
                                        }
                                    };
                                    // render init block
                                    const salesChart = new Chart(
                                            document.getElementById('salesChart'),
                                            config
                                            );
                                    function timeFrame(period) {
                                        if (period.value === 'day') {
                                            salesChart.config.options.scales.x.time.unit = period.value;
                                            salesChart.config.data.datasets[0].data = day;
                                            salesChart.config.options.scales.x.time.tooltipFormat = 'dd-MM-yyyy';

                                        }
                                        if (period.value === 'week') {
                                            salesChart.config.options.scales.x.time.unit = period.value;
                                            salesChart.config.data.datasets[0].data = week;
                                            salesChart.config.options.scales.x.time.isoWeekday = 1;
                                            salesChart.config.options.scales.x.time.tooltipFormat = 'dd-MM-yyyy';

                                        }
                                        if (period.value === 'month') {
                                            salesChart.config.options.scales.x.time.unit = period.value;
                                            salesChart.config.data.datasets[0].data = month;
                                            salesChart.config.options.scales.x.time.displayFormats.month = 'MMM yy';
                                            salesChart.config.options.scales.x.time.tooltipFormat = 'MMM yyyy';

                                        }
                                        if (period.value === 'year') {
                                            salesChart.config.options.scales.x.time.unit = period.value;
                                            salesChart.config.data.datasets[0].data = year;
                                            salesChart.config.options.scales.x.time.tooltipFormat = 'yyyy';
                                        }
                                        salesChart.update();
                                    }
                                </script>

                                <!-- Doughnut Chart -->
                                <div class="doughnutChart">
                                    <c:if test="${requestScope.RATING==null}"><a style="margin-left:10px">${requestScope.NOFB}</a></c:if>
                                    <canvas id="feedback-chart" style="display:inline-block;
                                            max-width: 400px;
                                            max-height: 400px;"></canvas>
                                    <c:set var="feedback" value="${requestScope.RATING}"/>
                                </div>
                                <script>
                                    var fb = ${feedback};
                                    var chart = new Chart(document.getElementById('feedback-chart'), {
                                        type: 'doughnut',
                                        data: {
                                            labels: ['Positive', 'Neutral', 'Negative'],
                                            datasets: [{
                                                    data: fb,
                                                    backgroundColor: ['#36A2EB', '#FFCE56', '#FF6384']
                                                }]
                                        },
                                        options: {
                                            responsive: true,
                                            plugins: {
                                                legend: {
                                                    position: 'bottom'
                                                },
                                                title: {
                                                    display: true,
                                                    text: 'Thống Kê Đánh Giá',
                                                    font: {
                                                        size: 24,
                                                        weight: 'bold'
                                                    }
                                                }
                                            }
                                        }
                                    });
                                </script>
                                <!-- DailySalesChart -->
                                <div class="dailyChart">
                                    <canvas id="dailyChart" style="display:inline-block;
                                            max-width: 800px;
                                            max-height: 450px;"></canvas>
                                    <script>
                                        var labels = ${labels};
                                        var values = ${values};
                                        var lastValues = ${lastValues};
                                        var ctx = document.getElementById("dailyChart").getContext("2d");
                                        var chart = new Chart(ctx, {
                                            type: 'line',
                                            data: {
                                                labels: labels,
                                                datasets: [{
                                                        label: 'Daily Sales this Month',
                                                        data: values,
                                                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                                                        borderColor: 'blue',
                                                        borderWidth: 1
                                                    },
                                                    {
                                                        label: 'Daily Sales last Month',
                                                        data: lastValues,
                                                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                                                        borderColor: 'red',
                                                        borderWidth: 1
                                                    }]
                                            },
                                            options: {
                                                responsive: true,
                                                borderColor: '#333',
                                                borderWidth: 2,
                                                backgroundColor: 'rgba(255,255,255,0.8)',
                                                legend: {
                                                    labels: {
                                                        fontColor: '#333',
                                                        fontSize: 16
                                                    }
                                                },
                                                scales: {
                                                    x: {
                                                        beginAtZero: true,
                                                        fontColor: '#333',
                                                        fontSize: 16,
                                                        fontWeight: 'bold',
                                                        title: {
                                                            display: true,
                                                            text: 'Date'
                                                        },
                                                    },
                                                    y: {
                                                        beginAtZero: true,
                                                        fontColor: '#333',
                                                        fontSize: 16,
                                                        fontWeight: 'bold',
                                                        title: {
                                                            display: true,
                                                            text: 'Sales'
                                                        }
                                                    }
                                                },
                                                plugins: {
                                                    title: {
                                                        display: true,
                                                        text: 'Daily Sales in ${MONTH} vs ${LASTMONTH}',
                                                        font: {
                                                            size: 24,
                                                            weight: 'bold'
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                    </script>
                                </div>
                                <c:if test = "${not empty requestScope.BEST_SELLER}">
                                    <c:if test = "${requestScope.BEST_SELLER!=null}">
                                        <div class="bestSeller">
                                            <h1 class="tittle" style>Best Selling Products</h1>
                                            <table
                                                class="table-responsive w-full rounded"
                                                style="text-align: center"
                                                >
                                                <thead>
                                                    <tr>
                                                        <th class="border w-1/6 px-4 py-2">Image</th>
                                                        <th class="border w-1/8 px-4 py-2">Products</th>
                                                        <th class="border w-1/6 px-4 py-2">Sold</th>
                                                        <th class="border w-1/8 px-4 py-2">Revenue</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="o" items="${requestScope.BEST_SELLER}">
                                                    <tr class="cursor-pointer row-info" id="order" data-href="SearchProductController?search=${o.name}">
                                                        <td class="border w-1/7 px-1 py-2" style="width: 100px; height: 1px;"><img src="${o.image}"></td>
                                                        <td class="border w-fit py-2">${o.name}</td>
                                                        <td class="border w-1/6 py-2">${o.quantity}</td>
                                                        <td class="border w-1/6 py-2">
                                                    <c:set var="pri" value="${o.originalPrice}"/>
                                                    <fmt:setLocale value="vi_VN"/>
                                                    <fmt:formatNumber value="${pri}" type="currency"/>
                                                    </td>        
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                </div>

                </main>
                <!--/Main-->
            </div>
            <!--Footer-->
            <footer class="bg-grey-darkest text-white p-2">
                <div class="flex flex-1 mx-auto">&copy; Shoes Shop</div>

            </footer>
            <!--/footer-->
        </div>
    </div>
    <script src="./main.js"></script>
    <script>
                                        const items = document.querySelectorAll('#item');
                                        const menu = document.getElementById('menu');
                                        const icon = document.getElementById('icon-xoay');
                                        const listMenu = document.getElementById('list-menu');
                                        menu.addEventListener('click', () => {
                                            listMenu.classList.toggle('show-swp');
                                            icon.classList.toggle('show-swp-icon');
                                        });
    </script>
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

</body>
</html>
