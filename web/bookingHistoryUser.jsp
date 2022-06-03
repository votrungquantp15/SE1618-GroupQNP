<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.Booking"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta
            name="author"
            content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
            />
        <meta name="generator" content="Hugo 0.88.1" />
        <title>Booking History Page</title>
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
            integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
            crossorigin="anonymous"
            />
        <link
            rel="canonical"
            href="https://getbootstrap.com/docs/4.6/examples/dashboard/"
            />
        <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
            crossorigin="anonymous"
        ></script>
        <link href="./styles/adminDashboard.css" />
        <!-- Bootstrap core CSS -->

        <!-- Favicons -->
        <meta name="theme-color" content="#563d7c" />

        <!-- Custom styles for this template -->
        <link href="dashboard.css" rel="stylesheet" />
    </head>
    <body>

        <jsp:include page="navbarAdmin.jsp"></jsp:include>
        <jsp:include page="layoutUser.jsp"></jsp:include>
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3">
                    <h1 class="h2">Dash Board</h1>
                    <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group mr-2">
                            <button type="button" class="btn btn-sm btn-outline-secondary">
                                Share
                            </button>
                            <button type="button" class="btn btn-sm btn-outline-secondary">
                                Export
                            </button>
                        </div>
                        <button
                            type="button"
                            class="btn btn-sm btn-outline-secondary dropdown-toggle"
                            >
                            <span data-feather="calendar"></span>
                            This week
                        </button>
                    </div>
                </div>
            <c:if test="${requestScope.LIST_BOOKING_HISTORY != null}">
                <c:if test="${not empty requestScope.LIST_BOOKING_HISTORY}">
                    <h2>Booking History Table</h2>
                    <div class="table-responsive">
                        <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Số</th>
                                    <th>Mã Đơn</th>
                                    <th>Người đặt</th>
                                    <th>Tổng tiền</th>
                                    <th>Ngày tạo đơn</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="list" items="${requestScope.LIST_BOOKING_HISTORY}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${list.bookingId}</td>
                                        <td>${list.user.fullName}</td>
                                        <td>${list.totalPrice}</td>
                                        <td>${list.bookingDate}</td>
                                        <td>
                                            <c:url var="Detail" value="MainController">
                                                <c:param name="action" value="SearchBookingDetail"></c:param>
                                                <c:param name="bookingID" value="${list.bookingId}"></c:param>
                                            </c:url>
                                            <a href="${Detail}">
                                                <button data-toggle="modal" data-target="#bookingDetailModal">Detail</button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </c:if>
        </main>
<button data-toggle="modal" data-target="#bookingDetailModal">Detail</button>
        <div id="bookingDetailModal" class="modal fade" role="dialog">
            <div class="modal-dialog modal-lg" role="content">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Booking Details</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-row">
                            <c:if test="${requestScope.LIST_BOOKING_HISTORY != null}">
                                <c:if test="${not empty requestScope.LIST_BOOKING_HISTORY}">
                                    <c:forEach var="listDetail" items="${requestScope.LIST_BOOKING_DETAIL}">
                                        <div class="form-group col-sm-4">
                                            <h6>Mã Đơn</h6>
                                            ${listDetail.booking.bookingId}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Người Đặt</h6>
                                            ${listDetail.user.userName}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Tổng Tiền</h6>
                                            ${listDetail.booking.totalPrice}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Ngày Tạo Đơn</h6>
                                            ${listDetail.booking.bookingDate}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Tên Sân</h6>
                                            ${listDetail.field.fieldName}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Chủ Sân</h6>
                                            ${listDetail.field.user.userName}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Kiểu Sân</h6>
                                            ${listDetail.field.fieldCate.fieldCateName}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Địa Chỉ</h6>
                                            ${listDetail.field.location.locationName}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Thời Gian Bắt Đầu</h6>
                                            ${listDetail.slot.timeStart}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Thời Gian Kết Thúc</h6>
                                            ${listDetail.slot.timeEnd}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Tiền Sân</h6>
                                            ${listDetail.bookingDetail.fieldPrice}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Tên Đồ Ăn</h6>
                                            ${listDetail.food.foodName}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Tổng Tiền Đồ Ăn</h6>
                                            ${listDetail.bookingDetail.foodPrice}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Số Lượng</h6>
                                            ${listDetail.bookingDetail.foodQuantity}
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <h6>Ngày Chơi</h6>
                                            ${listDetail.bookingDetail.playDate}
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </div>
                        <div class="form-row">
                            <button type="button" class="btn btn-secondary btn-sm ml-auto"
                                    data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
        <script>
            window.jQuery ||
                    document.write(
                            '<script src="/docs/4.6/assets/js/vendor/jquery.slim.min.js"><\/script>'
                            );
        </script>
        <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
            crossorigin="anonymous"
        ></script>

        <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
        <script src="dashboard.js"></script>
        <script type="text/javascript">
            document.getElementById('branche').onchange = function () {
                localStorage.setItem('selectedtem', document.getElementById('branche').value);
            };

            if (localStorage.getItem('selectedtem')) {
                document.getElementById(localStorage.getItem('selectedtem')).selected = true;
            }
        </script>
    </body>
</html>
