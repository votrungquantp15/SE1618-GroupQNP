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

                                <div class="card mt-3">
                                    <div class="card-header"> Invoice  <span class="float-right">
                                            <strong>Status:</strong> Pending</span> </div>
                                    <div class="card-body">
                                        <div class="row mb-5">
                                            <div class="mt-4 col-xl-3 col-lg-3 col-md-6 col-sm-6">
                                                <h6>From:</h6>
                                                <div> <strong>Webz Poland</strong> </div>
                                                <div>Madalinskiego 8</div>
                                                <div>71-101 Szczecin, Poland</div>
                                                <div>Email: <a href="/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="e68f888089a69183849cc885898bc8968a">[email&#160;protected]</a></div>
                                                <div>Phone: +48 444 666 3333</div>
                                            </div>
                                            <div class="mt-4 col-xl-3 col-lg-3 col-md-6 col-sm-6">
                                                <h6>To:</h6>
                                                <div> <strong>Bob Mart</strong> </div>
                                                <div>Attn: Daniel Marek</div>
                                                <div>43-190 Mikolow, Poland</div>
                                                <div>Email: <a href="/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="204d4152454b6044414e49454c0e434f4d">[email&#160;protected]</a></div>
                                                <div>Phone: +48 123 456 789</div>
                                            </div>
                                            <div class="mt-4 col-xl-6 col-lg-6 col-md-12 col-sm-12 d-flex justify-content-lg-end justify-content-md-center justify-content-xs-start">
                                                <div class="row align-items-center">
                                                    <div class="col-sm-9"> 
                                                        <div class="brand-logo mb-3">
                                                            <img class="logo-abbr mr-2" width="50" src="images/logo.png" alt="">
                                                            <img class="logo-compact" width="110" src="https://scontent.fsgn3-1.fna.fbcdn.net/v/t1.15752-9/290492666_701393540961163_1266621146650458002_n.png?_nc_cat=104&ccb=1-7&_nc_sid=ae9488&_nc_ohc=pYEFD_pAt-kAX_zPoBv&_nc_ht=scontent.fsgn3-1.fna&oh=03_AVKtheb73FXw4nH6gCnAdrY4CQmgB1pH7GnmrAgmVVh8ug&oe=62F2D34D" alt="">
                                                        </div>
                                                        <span> <strong class="d-block"></strong>
                                                            <strong>Thanh toán bằng cách quét mã momo:</strong></span><br>
                                                        <small class="text-muted">Xác nhận khi đã thanh toán xong</small>
                                                    </div>
                                                    <div class="col-sm-3 mt-3"> <img src="images/qr.png" alt="" class="img-fluid width110"> </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="table-responsive">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th class="center">#</th>
                                                        <th>Field Name</th>
                                                        <th>Description</th>
                                                        <th class="right">Unit Cost</th>
                                                        <th class="center">Date</th>
                                                        <th class="right">Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:set var="total" value="0"/>
                                                <c:forEach var="cart" items="${sessionScope.CART.getCart().values()}" varStatus="count"> 
                                                    <c:set var="total" value="${total + cart.field.price}"></c:set>
                                                    <tr>
                                                        <td class="center">${count.index}</td>
                                                        <td>${cart.field.fieldName}</td>
                                                                    <td>${cart.slotDetail.slot.timeStart} - ${cart.slotDetail.slot.timeEnd}</td>
                                                                    <td>${cart.field.price}$</td>
                                                                    <td>${cart.playDate}</td>
                                                                    <td>${cart.field.price}$</td>
                                                                    
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4 col-sm-5"> </div>
                                            <div class="col-lg-4 col-sm-5 ml-auto">
                                                <table class="table table-clear">
                                                    <tbody>
                                                        <tr>
                                                            <td class="left"><strong>Subtotal</strong></td>
                                                            <td class="right">${total}$</td>
                                                        </tr>
                                                        
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <form action="MainController" >
                                            <button style="margin-left: 900px" class="btn btn-primary btn-lg"type="submit" name="action" value="Payment"><i class="fa fa-credit-card mr-2"></i>Xác nhận</button>
                                        </form>
                                        
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
