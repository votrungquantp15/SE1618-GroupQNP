<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h2>Booking Detail</h2>
            <c:if test="${requestScope.BOOKING_DETAIL != null}">
                <img src="${requestScope.BOOKING_DETAIL.field.image}" 
                     alt="${requestScope.BOOKING_DETAIL.field.fieldName}"><br>
                <label>booking</label><br>
                ${requestScope.BOOKING_DETAIL.booking.bookingId}<br>
                ${requestScope.BOOKING_DETAIL.booking.bookingDate}<br>
                ${requestScope.BOOKING_DETAIL.booking.user.fullName}<br>
                <label>Field</label><br>
                ${requestScope.BOOKING_DETAIL.field.fieldName}<br>
                ${requestScope.BOOKING_DETAIL.field.description}<br>
                ${requestScope.BOOKING_DETAIL.field.fieldCate.fieldCateName}<br>
                ${requestScope.BOOKING_DETAIL.field.user.fullName}<br>
                ${requestScope.BOOKING_DETAIL.field.location.locationName}<br>
                ${requestScope.BOOKING_DETAIL.fieldPrice}<br>
                <label>Time</label><br>
                ${requestScope.BOOKING_DETAIL.slot.timeStart}<br>
                ${requestScope.BOOKING_DETAIL.slot.timeEnd}<br>
                ${requestScope.BOOKING_DETAIL.booking.bookingDate}<br>
                ${requestScope.BOOKING_DETAIL.playDate}<br>
                <label>Food</label><br>
                ${requestScope.BOOKING_DETAIL.food.foodName}<br>
                ${requestScope.BOOKING_DETAIL.foodTotalPrice}<br>
                ${requestScope.BOOKING_DETAIL.foodTotalQuantity}<br>
            </c:if>

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
    </body>
</html>
