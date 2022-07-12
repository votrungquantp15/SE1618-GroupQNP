<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <div id="main-wrapper">

            <jsp:include page="navbarUser.jsp"></jsp:include>
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
                                        <form action="MainController" method="POST">
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-4">
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
                                                            <input type="hidden" name="index" value="${param.index}">
                                                        <button type="submit" name="action" class="btn btn-rounded btn-warning" value="SearchBooking"><i class="fa fa-search "></i></button>
                                                    </div>
                                                </div>                              
                                            </div>                                                                                  
                                        </form>

                                        <div class="table-responsive">
                                            <table class="table table-responsive-sm">
                                                <c:if test="${empty requestScope.LIST_BOOKING_HISTORY}">
                                                    <h4 style="color: #ff2457"><strong>No Result</strong></h4> 
                                                        </c:if>
                                                        <c:if test="${requestScope.LIST_BOOKING_HISTORY != null}">
                                                            <c:if test="${not empty requestScope.LIST_BOOKING_HISTORY}">
                                                        <thead style="background-color: #fcd15b">
                                                            <tr>
                                                                <th style="width:80px;"><strong>#</strong></th>
                                                                <th><strong>Booknig ID</strong></th>
                                                                <th><strong>Booking Date</strong></th>
                                                                <th><strong>Booker</strong></th>
                                                                <th><strong>Total Price</strong></th>
                                                                <th><strong>Status</strong></th>
                                                                <th class="d-flex justify-content-center"><strong>Delete</strong></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>

                                                            <c:set var="counter" scope="page" value="${param.index * 10 - 10}"/>
                                                            <c:forEach var="booking" items="${requestScope.LIST_BOOKING_HISTORY}">
                                                                <c:if test="${booking.status ne 'Delete'}">
                                                                    <c:set var="counter" scope="page" value="${counter + 1}"/>
                                                                    <tr>
                                                                        <td><strong>${counter}</strong></td>
                                                                        <c:url var="BookingDetail" value="MainController">
                                                                            <c:param name="action" value="SearchBookingDetail"></c:param>
                                                                            <c:param name="bookingID" value="${booking.bookingId}"></c:param>
                                                                        </c:url>
                                                                        <td><a href="${BookingDetail}">${booking.bookingId}</a></td>
                                                                        <td>${booking.bookingDate}</td>
                                                                        <td>${booking.user.fullName}</td>
                                                                        <td>${booking.totalPrice}$</td>
                                                                        <td>${booking.status}</td>
                                                                        <c:url var="DeleteBooking" value="MainController">
                                                                            <c:param name="action" value="DeleteBooking"></c:param>
                                                                            <c:param name="bookingID" value="${booking.bookingId}"></c:param>
                                                                            <c:param name="bookingStatus" value="${booking.status}"></c:param>
                                                                            <c:param name="search" value="${param.search}"></c:param>
                                                                            <c:param name="status" value="${param.status}"></c:param>
                                                                            <c:param name="index" value="${param.index}"></c:param>
                                                                        </c:url>
                                                                        <td>
                                                                            <div class="d-flex justify-content-center">
                                                                                <c:if test="${booking.status ne 'On-Going'}">
                                                                                    <button type="button" class="btn btn-primary shadow btn-xs sharp" data-toggle="modal" data-target="#basicModalDelete${counter}" title="Delete"><i class="fa fa-trash"></i></button>
                                                                                    <div class="modal fade" id="basicModalDelete${counter}">
                                                                                        <div class="modal-dialog" role="document">
                                                                                            <div class="modal-content">
                                                                                                <div class="modal-header"  style="background-color: #fcd15b">
                                                                                                    <h3 class="modal-title">Delete Booking ${booking.bookingId}</h3>
                                                                                                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                                                                                                    </button>
                                                                                                </div>
                                                                                                <div class="modal-body" style="background-color: #f7f3e6">
                                                                                                    <h3>Do you want to Delete ${booking.bookingId}</h3>
                                                                                                </div>
                                                                                                <div class="modal-footer" style="background-color: #f7f3e6">
                                                                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                                                                                    <a href="${DeleteBooking}" type="button" class="btn btn-primary">Confirm</a>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                                <c:if test="${booking.status eq 'On-Going'}">
                                                                                    <button type="button" class="btn btn-primary shadow btn-xs sharp" data-toggle="modal" data-target="#basicModalCancel${counter}" title="Cancel"><i class="fa fa-trash"></i></button>
                                                                                    <div class="modal fade" id="basicModalCancel${counter}">
                                                                                        <div class="modal-dialog" role="document">
                                                                                            <div class="modal-content">
                                                                                                <div class="modal-header"  style="background-color: #fcd15b">
                                                                                                    <h3 class="modal-title">Cancel Booking ${booking.bookingId}</h3>
                                                                                                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                                                                                                    </button>
                                                                                                </div>
                                                                                                <div class="modal-body" style="background-color: #f7f3e6">
                                                                                                    <h3>Do you want to Cancel ${booking.bookingId}</h3>
                                                                                                </div>
                                                                                                <div class="modal-footer" style="background-color: #f7f3e6">
                                                                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                                                                                    <a href="${DeleteBooking}" type="button" class="btn btn-primary">Confirm</a>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </div>
                                                                        </td>
                                                                    </tr>

                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                        <c:if test="${not empty requestScope.LIST_BOOKING_HISTORY}">
                                            <ul class="pagination">
                                                <c:if test="${param.index ne null && param.index >=2}">
                                                    <li class="page-item">
                                                        <a href="MainController?action=SearchBooking&index=${param.index - 1}&status=${param.status}<c:if test="${param.datefilter != null}">&datefilter=${param.datefilter}</c:if>" class="page-link"><</a>
                                                        </li>
                                                </c:if>
                                                <c:forEach var="i" begin="1" end="${END_PAGE}">
                                                    <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                                        <a href="MainController?action=SearchBooking&index=${i}&status=${param.status}<c:if test="${param.datefilter != null}">&datefilter=${param.datefilter}</c:if>" class="page-link">${i}</a>
                                                        </li>
                                                </c:forEach>
                                                <c:if test="${param.index ne null && param.index < END_PAGE}">
                                                    <li class="page-item">
                                                        <a href="MainController?action=SearchBooking&index=${param.index + 1}&status=${param.status}<c:if test="${param.datefilter != null}">&datefilter=${param.datefilter}</c:if>" class="page-link">></a>
                                                        </li>
                                                </c:if>
                                            </ul>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

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
