<%@page import="java.util.List"%>
<%@page import="dto.BookingHistoryDTO"%>
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
<<<<<<< HEAD
        <nav
            class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow"
            >
            <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="user.jsp"
               >Company name</a
            >
            <button
                class="navbar-toggler position-absolute d-md-none collapsed"
                type="button"
                data-toggle="collapse"
                data-target="#sidebarMenu"
                aria-controls="sidebarMenu"
                aria-expanded="false"
                aria-label="Toggle navigation"
                >
                <span class="navbar-toggler-icon"></span>
            </button>
            <%
                String search = request.getParameter("search");
                if (search == null) {
                    search = "";
                }
            %>
            <form class="col-md-8 row" action="MainController">
                <input class="form-control form-control-dark w-100 col-md-6" name="search" type="text" placeholder="Search" value="<%=search%>">
                <select class="col-md-2 w-100" id="branche" name="address">
                    <option value="CT">tỉnh/thành</option>
                    <option value="CT1">Hồ Chí Minh</option>
                    <option value="CT2">Hà Nội</option>
                    <option value="CT3">Đà Nẵng</option>
                    <option value="CT4">Cần Thơ</option>
                    <option value="CT5">Thủ Đức</option>
                </select>
                <input class="col-md-2 w-100" type="submit" name="action" value="SearchHistory">
            </form>
=======
        <jsp:include page="navbarAdmin.jsp"></jsp:include>
        <jsp:include page="layoutAdmin.jsp"></jsp:include>
>>>>>>> 485726483d8aa1b8fc811b16454928eb283a11d7

                    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                        <div
                            class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
                            >
                            <h1 class="h2">Dashboard</h1>
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
<<<<<<< HEAD
                    </div>
                    <h2>Section title</h2>
                    <div class="table-responsive">
                        <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>BookingID</th>
                                    <th>Date</th>
                                    <th>Field</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
=======
                        <h2>Section title</h2>


                        <form action="MainController">
                            <input type="text" name="search" placeholder="search">
                            <select name="address">
                                <option>tinh/thanh</option>
                                <option value="HCM">HCM</option>
                                <option value="HN">HN</option>
                                <option value="DN">DN</option>
                                <option value="NT">NT</option>
                            </select>
                            <input type="submit" name="action" value="Search"> 
                        </form>
                        <div class="table-responsive">
                            <table class="table table-striped table-sm">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>BookingID</th>
                                        <th>Date</th>
                                        <th>Field</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
>>>>>>> 485726483d8aa1b8fc811b16454928eb283a11d7
                            <%
                                List<BookingHistoryDTO> list = (List<BookingHistoryDTO>) request.getAttribute("LIST_BOOKING_HISTORY");
                                if (list != null) {
                                    if (list.size() > 0) {
                                        int count = 0;
                                        for (BookingHistoryDTO listBooking : list) {
                                            if (listBooking.getStatus() == true) {
                                                count++;
                            %>
                            <tbody>
                                <tr>
                                    <td><%= count%></td>
                                    <td><%= listBooking.getBookingID()%></td>
                                    <td><%= listBooking.getBookingDate()%></td>
                                    <td><%= listBooking.getFieldName()%></td>
                                    <td><%= listBooking.getPrice()%></td>
                                </tr>
                                <%
                                                }
                                            }
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </main>
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
