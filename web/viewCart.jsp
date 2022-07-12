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
        <title>Your Cart Page</title>
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
                                            <h4 class="card-title">Your Items</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-responsive-sm">
                                                <c:if test="${empty sessionScope.CART}">
                                                    <h4 style="color: #ff2457"><strong>Cart Empty</strong></h4> 
                                                </c:if>
                                                <c:if test="${sessionScope.CART != null}">
                                                    <c:if test="${not empty sessionScope.CART}">
                                                        <thead style="background-color: #fcd15b">
                                                            <tr>                                                
                                                                <th></th>
                                                                <th><strong>Field Name</strong></th>
                                                                <th><strong>Time</strong></th>
                                                                <th><strong>Total Price</strong></th>
                                                                <th><strong>Play Date</strong></th>
                                                                <th><strong>Action</strong></th>

                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="cart" items="${sessionScope.CART.getCart().values()}">                                                       
                                                                <tr>
                                                                    <td><img src="${cart.field.image}" height="200px" width="280px"/></td>
                                                                    <td>${cart.field.fieldName}</td>
                                                                    <td>${cart.slotDetail.slot.timeStart} - ${cart.slotDetail.slot.timeEnd}</td>
                                                                    <td>${cart.field.price}$</td>
                                                                    <td>${cart.playDate}</td>
                                                                    <td><a href="${DeleteCartItem}" type="button" class="btn btn-primary"><i class="fa fa-trash"></i></a></td>
                                                                
                                                                </tr>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                                <div class="float-right">
                                                <button class="btn btn-primary btn-lg" type="submit" name="action" value="CheckOut"><i class="fa fa-credit-card mr-2"></i>Check Out</button>
                                                <a href="MainController?action=Print&index=1" class="btn btn-primary btn-lg" >Back</button></a>

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
