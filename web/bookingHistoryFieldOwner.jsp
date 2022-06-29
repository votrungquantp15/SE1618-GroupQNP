<%-- 
    Document   : bookingHistoryFieldOwner
    Created on : Jun 19, 2022, 8:32:04 AM
    Author     : NITRO 5
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="">
        <meta name="author" content="">
        <meta name="robots" content="">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <meta name="description" content="Zenix - Crypto Admin Dashboard">
        <meta property="og:title" content="Zenix - Crypto Admin Dashboard">
        <meta property="og:description" content="Zenix - Crypto Admin Dashboard">
        <meta property="og:image" content="https://zenix.dexignzone.com/xhtml/social-image.png">
        <meta name="format-detection" content="telephone=no">
        <title>Booking History</title>
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
        <!-- Datatable -->
        <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
        <!-- Custom Stylesheet -->
        <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
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

            <jsp:include page="navbarFieldOwner.jsp"></jsp:include>
                <div class="content-body">
                    <div class="col-12">
                        <div class="card">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Boooking History</h4>
                                            <h4 style="color: #17e379"><strong>${DELETE_SUCCESS}</strong></h4> 
                                        <h4 style="color: #ff2457"><strong>${DELETE_UNSUCCESS}</strong></h4> 
                                    </div>
                                    <div class="card-body">
                                        <form action="MainController" method="GET">
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-3">    
                                                        <input class="form-control" type="text" name="search" value="${param.search}" placeholder="Search by user name">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <div class="example">
                                                            <input type="text" class="form-control" name="datefilter" value="${param.datefilter}" placeholder="Choose Date To Search" />
                                                        </div>
                                                    </div>

                                                    <div class="col-sm-4">

                                                        <select class="form-control" name="status">
                                                            <option value="" <c:if test="${param.status == null}">selected</c:if>>Show all</option>
                                                            <option value="On-Going" <c:if test="${param.status eq 'On-Going'}">selected</c:if>>On-Going</option>
                                                            <option value="Played" <c:if test="${param.status eq 'Played'}">selected</c:if>>Played</option>
                                                            <option value="Canceled" <c:if test="${param.status eq 'Canceled'}">selected</c:if>>Canceled</option>
                                                            </select>
                                                        </div>
                                                        <div class ="col-sm-2 d-flex justify-content-between">
                                                            <button type="submit" name="action" class="btn btn-rounded btn-warning" value="SearchBooking"><i class="fa fa-search "></i></button>
                                                        </div>
                                                    </div>                              
                                                </div>                                                                                  
                                            </form>

                                            <div class="table-responsive">
                                                <table class="table table-responsive-sm">
                                                <c:if test="${requestScope.LIST_BOOKING_HISTORY == null}">
                                                    <strong>No Result</strong>
                                                </c:if>
                                                <thead style="background-color: #fcd15b">
                                                    <tr>
                                                        <th style="width:80px;"><strong>#</strong></th>
                                                        <th><strong>Booking ID</strong></th>
                                                        <th><strong>Booking Date</strong></th>
                                                        <th><strong>Booker</strong></th>
                                                        <th><strong>Total Price</strong></th>
                                                        <th><strong>Status</strong></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${requestScope.LIST_BOOKING_HISTORY != null}">
                                                        <c:if test="${not empty requestScope.LIST_BOOKING_HISTORY}">
                                                            <c:set var="counter" scope="page" value="0"/>
                                                            <c:forEach var="booking" items="${requestScope.LIST_BOOKING_HISTORY}">
                                                                <c:set var="counter" scope="page" value="${counter + 1}"/>
                                                                <tr>
                                                                    <td><strong>${counter}</strong></td>
                                                                    <td>${booking.bookingId}</a></td>
                                                                    <td>${booking.bookingDate}</td>
                                                                    <td>${booking.user.fullName}</td>
                                                                    <td>${booking.totalPrice}$</td>
                                                                    <td>${booking.status}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                        <ul class="pagination">
                                            <c:forEach var="i" begin="1" end="${END_PAGE}">
                                                <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                                    <a href="MainController?action=SearchBooking&index=${i}<c:if test="${param.datefilter != null}">&search=${param.search}&status=${param.status}&datefilter=${param.datefilter}</c:if>" class="page-link">${i}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="footer">
                        <div class="copyright">
                            <p>Copyright © Designed &amp; Developed by <a href="../index.htm" target="_blank">DexignZone</a> 2021</p>
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
