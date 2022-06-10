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
    <title>Booking Detail</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Datatable -->
    <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <!-- Custom Stylesheet -->
    <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

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

        <jsp:include page="navbarUser.jsp"></jsp:include>
            <div class="content-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                    <c:if test="${requestScope.BOOKING_DETAIL == null}">
                                        <strong>No Result</strong>
                                    </c:if>
                                    <c:if test="${requestScope.BOOKING_DETAIL != null}">
                                        <c:if test="${not empty requestScope.BOOKING_DETAIL}">
                                            <div class="col-xl-3 col-lg-6  col-md-6 col-xxl-5 ">
                                                <div class="fade show active">
                                                    <img class="img-fluid" src="${requestScope.BOOKING_DETAIL.field.image}" alt="">
                                                </div>
                                            </div>

                                            <div class="col-xl-9 col-lg-6  col-md-6 col-xxl-7 col-sm-12">
                                                <div class="product-detail-content">
                                                    <!--Product details-->
                                                    <div class="new-arrival-content pr row">
                                                        <div class="col-12 col-md-6">
                                                            <div class="card">
                                                                <div class="card-header">
                                                                    <h3><strong>Booking</strong></h3>
                                                                </div>
                                                                <div class="card-body">
                                                                    <p>Booking ID: ${requestScope.BOOKING_DETAIL.booking.bookingId}</p>
                                                                    <p>Booking Date: ${requestScope.BOOKING_DETAIL.booking.bookingDate}</p>
                                                                    <p>Booker: ${requestScope.BOOKING_DETAIL.booking.user.fullName}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-12 col-md-6">
                                                            <div class="card-header">
                                                                <h3><strong>Field</strong></h3>
                                                            </div>
                                                            <div class="card-body">
                                                                <p>Field Name: ${requestScope.BOOKING_DETAIL.field.fieldName}</p>
                                                                <p>Description: ${requestScope.BOOKING_DETAIL.field.description}</p>
                                                                <p>Field Cate: ${requestScope.BOOKING_DETAIL.field.fieldCate.fieldCateName}</p>
                                                                <p>Field Owner: ${requestScope.BOOKING_DETAIL.field.user.fullName}</p>
                                                                <p>Address: ${requestScope.BOOKING_DETAIL.field.location.locationName}</p>
                                                                <p>Field Cate: ${requestScope.BOOKING_DETAIL.field.fieldCate.fieldCateName}</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="new-arrival-content pr row">
                                                        <div class="col-12 col-md-6">
                                                            <div class="card">
                                                                <div class="card-header">
                                                                    <h3><strong>Time</strong></h3>
                                                                </div>
                                                                <div class="card-body">
                                                                    <p>Time Start: ${requestScope.BOOKING_DETAIL.slotDetail.slot.timeStart}</p>
                                                                    <p>Time End: ${requestScope.BOOKING_DETAIL.slotDetail.slot.timeEnd}</p>
                                                                    <p>Creating Date: ${requestScope.BOOKING_DETAIL.booking.bookingDate}</p>
                                                                    <p>Play Date: ${requestScope.BOOKING_DETAIL.playDate}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-12 col-md-6">
                                                            <div class="card">
                                                                <div class="card-header">
                                                                    <h3><strong>Food</strong></h3>
                                                                </div>
                                                                <div class="card-body">
                                                                    <p>Food Name: ${requestScope.BOOKING_DETAIL.foodDetail.food.foodName}</p>
                                                                    <p>Food Total Price: ${requestScope.BOOKING_DETAIL.foodTotalPrice}</p>
                                                                    <p>Food Total Quantity: ${requestScope.BOOKING_DETAIL.foodTotalQuantity}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:if> 
            </div>
        </div>
    </div>




    <div class="footer">
        <div class="copyright">
            <p>Copyright © Designed &amp; Developed by <a href="../index.htm" target="_blank">DexignZone</a> 2021</p>
        </div>
    </div>
</div>
<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->
<!-- Required vendors -->
<script data-cfasync="false" src="../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="vendor/global/global.min.js"></script>
<script src="vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
<!-- Datatable -->
<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="js/plugins-init/datatables.init.js"></script>
<script src="js/custom.min.js"></script>
<script src="js/deznav-init.js"></script>
<script src="js/demo.js"></script>
<script src="js/styleSwitcher.js"></script>
</body>
</html>
