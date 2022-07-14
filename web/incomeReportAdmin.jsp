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
    <title>Admin Income Dashboard</title>
    <!-- Favicon icon -->
    <link rel="icon" href="data:image/png;base64,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">

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

            total += parseFloat(fieldPrice);

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
                                                <div class ="col-sm-2">
                                                    <button type="submit" name="action" class="btn btn-rounded btn-warning mt-4" value="SearchIncome"><i class="fa fa-search"></i></button>
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
                                                        <td><div id="total" ></div></td>
                                                    </tr>
                                                </c:forEach>

                                            </tbody>
                                        </table>
                                        <div class="d-flex justify-content-end mr-5"><h5 id="sum"></h5></div>
                                    </div>
                                    <c:choose>
                                        <c:when test="${requestScope.BOOKING_DETAILS != null}">
                                            <ul class="pagination">
                                                <c:forEach var="i" begin="1" end="${END_PAGE}">
                                                    <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                                        <a href="MainController?action=GetAllIncome&index=${i}" class="page-link">${i}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
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

        <!--        <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>-->
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
