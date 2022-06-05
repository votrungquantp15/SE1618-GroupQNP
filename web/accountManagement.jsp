<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



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
    <title>Zenix -   Dashboard </title>
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

        <jsp:include page="navbarAdmin.jsp"></jsp:include>
            <div class="content-body">
                <div class="col-12">
                    <div class="card">

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Account Management</h4>
                                    </div>
                                    <div class="card-body">

                                        <div class="table-responsive">
                                            <table class="table table-responsive-md">
                                                <thead>
                                                    <tr>
                                                        <th style="width:80px;"><strong>#</strong></th>
                                                        <th><strong>User ID</strong></th>
                                                        <th><strong>Full Name</strong></th>
                                                        <th><strong>Address</strong></th>
                                                        <th><strong>Birthday</strong></th>
                                                        <th><strong>Phone</strong></th>
                                                        <th><strong>Email</strong></th>
                                                        <th><strong>Account Name</strong></th>                                                                                        
                                                        <th><strong>Role ID</strong></th>
                                                        <th><strong>Password</strong></th>
                                                        <th><strong>Status</strong></th>
                                                        <th><strong>Action</strong></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                    <c:forEach var="user" items="${requestScope.VIEW_ACCOUNT}" varStatus="counter">
                                                    <tr>
                                                        <td><strong>${counter.count}</strong></td>
                                                        <td>${user.userID}</td>
                                                        <td>${user.fullName}</td>
                                                        <td>${user.address}</td>
                                                        <td>${user.birth}</td>
                                                        <td>${user.phone}</td>
                                                        <td>${user.email}</td>
                                                        <td>${user.accName}</td>                                               
                                                        <td>${user.role.roleId}</td>
                                                        <td>${user.password}</td>
                                                        <td>${user.status}</td>
                                                        <td>
                                                            <div class="d-flex">
                                                                <a href="MainController?action=SearchAccountByAdmin&searchAccountByAdmin=${user.userID}" class="btn btn-primary shadow btn-xs sharp mr-1"><i class="fa fa-pencil"></i></a>
                                                                <a href="MainController?action=DeleteAccountByAdmin&userID=${user.userID}" class="btn btn-danger shadow btn-xs sharp"><i class="fa fa-trash"></i></a>

                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                                </tr>
                                        </table>
                                        
                                        <p style="color: red">${requestScope.ERROR_MESSAGE} </p>   
                                        <p style="color: green">${requestScope.DELETE_SUCCESS} </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>




                <div class="footer">
                    <div class="copyright">
                        <p>Copyright Â© Designed &amp; Developed by <a href="../index.htm" target="_blank">DexignZone</a> 2021</p>
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
</body>
</html>