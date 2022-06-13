<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<head>
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <meta name="robots" content="">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="Zenix - Crypto Admin Dashboard">
    <meta property="og:title" content="Zenix - Crypto Admin Dashboard">
    <meta property="og:description" content="Zenix - Crypto Admin Dashboard">
    <meta property="og:image" content="https://zenix.dexignzone.com/xhtml/social-image.png">
    <meta name="format-detection" content="telephone=no">
    <title>Admin Dashboard</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Custom Stylesheet -->
    <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/jquery.timepicker.css">

    <!--    <script src="js/jquery-3.2.1.min.js"></script>-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/admin/income.js"></script>
</head>

<script type="text/javascript">
    $(document).ready(() => {
        var sum = 0;
        $("tr").each((index, element) => {
            var total = 0;
            let fieldPrice = $(element).find('#fieldPrice').text();

            let foodPrice = $(element).find("#foodPrice").text();

            total += parseFloat(fieldPrice);

            total += parseFloat(foodPrice);

            if (!isNaN(total)) {
                sum += total;
            }

            $(element).find("#total").html(total);

        });


        $("#sum").html("Sum : " + sum);

    });

</script>
<body>

    <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.role.roleId ne 'AD'}">
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="sk-three-bounce">
            <div class="sk-child sk-bounce1"></div>
            <div class="sk-child sk-bounce2"></div>
            <div class="sk-child sk-bounce3"></div>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->


    <!--**********************************
        Main wrapper start
    ***********************************-->
    <div id="main-wrapper">

        <jsp:include page="navbarAdmin.jsp"></jsp:include>
            <div class="content-body">
                <div class="col-12">
                    <div class="card">

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h5 class="card-title">Income report
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <form action="AdminIncomeManagement" method="GET">
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <div>Search by field ID</div><input class="form-control" type="text" name="fieldID" value="${param.fieldID}" placeholder="...">
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="example">
                                                        <p class="mb-1">Date Range Pick</p>

                                                        <input type="text" class="form-control" name="datefilter" value="${param.datefilter}" />
                                                    </div>
                                                </div>
                                                <div class ="col-sm-2 d-flex justify-content-between">
                                                    <button type="submit" name="action" class="btn " value="SearchIncome"><i class="fa fa-search" style="font-size:12px"></i></button>
                                                </div>
                                            </div>                              
                                        </div>                                                                                  
                                    </form>
                                    <div class="table-responsive">
                                        <table class="table table-responsive-md">
                                            <thead>
                                                <tr>
                                                    <th style="width:50px;">
                                                        <div class="custom-control custom-checkbox checkbox-success check-lg mr-3">
                                                            <input type="checkbox" class="custom-control-input" id="checkAll" required="">
                                                            <label class="custom-control-label" for="checkAll"></label>
                                                        </div>
                                                    </th>
                                                    <th ><strong class="w-space-no">Booking Detail ID </strong></th>
                                                    <th ><strong class="w-space-no">Booking ID</strong></th>
                                                    <th><strong class="w-space-no">Field ID</strong></th>
                                                    <th><strong >Date</strong></th>
                                                    <th><strong class="w-space-no">Field Price</strong></th>
                                                    <th><strong class="w-space-no">Food Name</strong></th>
                                                    <th><strong class="w-space-no">Food Price</strong></th>
                                                    <th><strong>Total</strong></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach var="booking" items="${requestScope.BOOKING_DETAILS}" varStatus="counter">
                                                    <tr id="myTable">
                                                        <td>
                                                            <div class="custom-control custom-checkbox checkbox-success check-lg mr-3">
                                                                <input type="checkbox" class="custom-control-input" id="customCheckBox${counter.index}" required="">
                                                                <label class="custom-control-label" for="customCheckBox${counter.index}"></label>
                                                            </div>
                                                        </td>
                                                        <td>${booking.bookingDetailID}</td>
                                                        <td>${booking.booking.bookingId}</td>
                                                        <td><div class="d-flex align-items-center"> <span class="w-space-no">${booking.field.fieldId}</span></div></td>
                                                        <td><div class="d-flex align-items-center"> <span class="w-space-no">${booking.playDate}</span></div></td>
                                                        <td><div class="d-flex align-items-center"> <span class="w-space-no" id="fieldPrice">${booking.field.price}</span></div></td>
                                                        <td><div class="d-flex align-items-center"> <span class="w-space-no">${booking.foodDetail.food.foodName}</span></div></td>
                                                        <td><div class="d-flex align-items-center"> <span class="w-space-no" id="foodPrice">${booking.foodTotalPrice}</span></div></td>
                                                        <td><div id="total" ></div></td>
                                                    </tr>
                                                </c:forEach>

                                            </tbody>
                                        </table>
                                        <div class="d-flex justify-content-end mr-5"><h5 id="sum"></h5></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--**********************************
                    Footer end
                ***********************************-->

                <!--**********************************
                   Support ticket button start
                ***********************************-->

                <!--**********************************
                   Support ticket button end
                ***********************************-->
            </div>

        </div>
        <!--**********************************
            Main wrapper end
        ***********************************-->

        <!--**********************************
            Scripts
        ***********************************-->
        <!-- Required vendors -->

        <script src="vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
        <!-- Datatable -->

        <script data-cfasync="false" src="../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="vendor/global/global.min.js"></script>
        <script src="vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
        <script src="js/custom.min.js"></script>
        <script src="js/deznav-init.js"></script>
        <script src="js/demo.js"></script>
        <script src="js/styleSwitcher.js"></script>

        <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />


        <script type="text/javascript">
    $(function () {

        $('input[name="datefilter"]').daterangepicker({
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear'
            }
        });

        $('input[name="datefilter"]').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
        });

        $('input[name="datefilter"]').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });

    });
        </script>

</body>
</html>
