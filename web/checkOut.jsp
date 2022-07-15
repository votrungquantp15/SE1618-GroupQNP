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
        <title>Check Out Page</title>
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
        <!-- Datatable -->
        <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
        <!-- Custom Stylesheet -->
        <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>


        <header class="header_area">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <a class="navbar-brand logo_h" href="MainController?action=Print&index=1"><img src="https://logopond.com/logos/18c31fb8cfe3ce15b964939a13c369a5.png" alt=""></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                        <ul class="nav navbar-nav menu_nav ml-auto">
                            <li class="nav-item active"><a class="nav-link" href="MainController?action=Print&index=1">Trang chủ</a></li>
                            <li class="nav-item"><a class="nav-link" href="MainController?action=ViewCart">Giỏ hàng</a></li>
                            <li class="nav-item submenu dropdown">
                                <c:choose>
                                    <c:when test="${sessionScope.LOGIN_USER == null}">
                                        <a href="login.jsp" class="nav-link">Đăng nhập</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.LOGIN_USER.fullName}</a>
                                        <ul class="dropdown-menu">
                                            <li class="nav-item"><a href="MainController?action=ProfileUser&id=${sessionScope.LOGIN_USER.userID}" class="nav-link">Hồ sơ</a></li>
                                            <li class="nav-item"><a href="MainController?action=SearchBooking&userID=${sessionScope.LOGIN_USER.userID}&index=1&status=" class="nav-link">Lịch sử đặt</a></li>
                                            <li class="nav-item"><a href="MainController?action=Logout" class="nav-link">Đăng xuất</a></li>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                            </li> 
                        </ul>
                    </div> 
                </nav>
            </div>
        </header>
        <div id="main-wrapper">
            <div class="content-body">
                <div class="col-12">
                    <div class="card">
                        <div class="row">
                            <div class="col-lg-12">

                                <div class="card">
                                    <div class="card-body">
                                        <h4 style="color: red">${ADD_FAIL}</h4>
                                        <h4 style="color: green">${ADD_SUCCESS}</h4>
                                        <div class="table-responsive">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th><strong>Field Name</strong></th>
                                                        <th><strong>Time</strong></th>
                                                        <th><strong>Price</strong></th>
                                                        <th><strong>Play Date</strong></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set var="total" value="0"/>
                                                    <c:forEach var="cart" items="${CART.getCart().values()}">
                                                        <c:set var="total" value="${total + cart.field.price}"/>
                                                        <tr>
                                                            <td><img src="${cart.field.image}" height="200px" width="280px"/></td>
                                                            <td>${cart.field.fieldName}</td>
                                                            <td>${cart.slotDetail.slot.timeStart} - ${cart.slotDetail.slot.timeEnd}</td>
                                                            <td>${cart.field.price}$</td>
                                                            <td>${cart.playDate}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4 col-sm-5"> </div>
                                            <div class="col-lg-4 col-sm-5 ml-auto">

                                                <h3 class="float-right mr-5"><strong>Total: $${total}</strong></h3>
                                                <div class="float-right">   
                                                    <c:if test="${CART != null}">
                                                        <a href="MainController?action=Confirm&total=${total}" class="btn btn-primary btn-lg">Confirm</a>
                                                        <a href="MainController?action=ViewCart" class="btn btn-primary btn-lg"><i class="fa fa-shopping-cart mr-2"></i>Back To Cart</a>
                                                    </c:if>
                                                    <c:if test="${CART == null}">
                                                        <a href="MainController?action=Print&index=1" class="btn btn-primary btn-lg"><i class="fa fa-home mr-2"></i>Home</a>
                                                    </c:if>
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
    </div>
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
