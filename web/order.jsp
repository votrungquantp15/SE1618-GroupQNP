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
                                    <div class="card-header"> Invoice <strong>01/01/01/2018</strong> <span class="float-right">
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
                                                            <img class="logo-compact" width="110" src="images/logo-text.png" alt="">
                                                        </div>
                                                        <span>Please send exact amount: <strong class="d-block">0.15050000 BTC</strong>
                                                            <strong>1DonateWffyhwAjskoEwXt83pHZxhLTr8H</strong></span><br>
                                                        <small class="text-muted">Current exchange rate 1BTC = $6590 USD</small>
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
                                                        <th>Item</th>
                                                        <th>Description</th>
                                                        <th class="right">Unit Cost</th>
                                                        <th class="center">Qty</th>
                                                        <th class="right">Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="center">1</td>
                                                        <td class="left strong">Origin License</td>
                                                        <td class="left">Extended License</td>
                                                        <td class="right">$999,00</td>
                                                        <td class="center">1</td>
                                                        <td class="right">$999,00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="center">2</td>
                                                        <td class="left">Custom Services</td>
                                                        <td class="left">Instalation and Customization (cost per hour)</td>
                                                        <td class="right">$150,00</td>
                                                        <td class="center">20</td>
                                                        <td class="right">$3.000,00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="center">3</td>
                                                        <td class="left">Hosting</td>
                                                        <td class="left">1 year subcription</td>
                                                        <td class="right">$499,00</td>
                                                        <td class="center">1</td>
                                                        <td class="right">$499,00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="center">4</td>
                                                        <td class="left">Platinum Support</td>
                                                        <td class="left">1 year subcription 24/7</td>
                                                        <td class="right">$3.999,00</td>
                                                        <td class="center">1</td>
                                                        <td class="right">$3.999,00</td>
                                                    </tr>
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
                                                            <td class="right">$8.497,00</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="left"><strong>Discount (20%)</strong></td>
                                                            <td class="right">$1,699,40</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="left"><strong>VAT (10%)</strong></td>
                                                            <td class="right">$679,76</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="left"><strong>Total</strong></td>
                                                            <td class="right"><strong>$7.477,36</strong><br>
                                                                <strong>0.15050000 BTC</strong></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
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
