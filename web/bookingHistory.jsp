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
            <form class="col-md-8 row" action="MainController">
                <input class="form-control form-control-dark w-100 col-md-6" name="search" type="text" placeholder="Search" aria-label="Search">
                <select class="col-md-2 w-100" name="address">
                    <option>tỉnh/thành</option>
                    <option value="CT1">Hồ Chí Minh</option>
                    <option value="CT2">Hà Nội</option>
                    <option value="CT3">Đà Nẵng</option>
                    <option value="CT4">Cần Thơ</option>
                    <option value="CT5">Thủ Đức</option>
                </select>
                <input class="col-md-2 w-100" type="submit" name="action" value="SearchHistory">
            </form>

            <ul class="navbar-nav px-3">
                <li class="nav-item text-nowrap">
                    <a class="nav-link" href="login.html">Sign out</a>
                </li>
            </ul>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <nav
                    id="sidebarMenu"
                    class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
                    >
                    <div class="sidebar-sticky pt-3">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="home"></span>
                                    Profile <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file"></span>
                                    Orders
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="shopping-cart"></span>
                                    Products
                                </a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="bookingHistory.jsp">
                                    <span data-feather="users"></span>
                                    History
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="bar-chart-2"></span>
                                    Reports
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="layers"></span>
                                    Integrations
                                </a>
                            </li>
                        </ul>

                        <h6
                            class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"
                            >
                            <span>Saved reports</span>
                            <a
                                class="d-flex align-items-center text-muted"
                                href="#"
                                aria-label="Add a new report"
                                >
                                <span data-feather="plus-circle"></span>
                            </a>
                        </h6>
                        <ul class="nav flex-column mb-2">
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file-text"></span>
                                    Current month
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file-text"></span>
                                    Last quarter
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file-text"></span>
                                    Social engagement
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file-text"></span>
                                    Year-end sale
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>

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
    </body>
</html>
