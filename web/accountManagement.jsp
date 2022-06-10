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
    <title>Admin Dashboard </title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Datatable -->
    <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <!-- Custom Stylesheet -->
    <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body>

    <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.role.roleId ne 'AD'}">
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
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
                                        <form action="MainController">
                                            <div class="form-group col-md-6">
                                                <label><strong>Search user by name</strong></label>
                                                <div class="row">
                                                    <div class ="col-md-6">
                                                        <input name="searchAccountByAdmin" type="text" class="form-control" placeholder="Type here to search" value="${param.searchAccountByAdmin}">                                                                                             
                                                </div>
                                                <div class ="col-md-6">
                                                    <button type="submit" name="action" class="btn btn-rounded btn-warning" value="SearchAccountByAdmin">SEARCH</button>
                                                </div>
                                            </div>                              
                                        </div>                                                                                  
                                    </form>
                                    <p style="color: red">${requestScope.ERROR_MESSAGE} </p>   
                                    <p style="color: green">${requestScope.DELETE_SUCCESS} </p>
                                    <p style="color: red">${requestScope.SEARCH_FAILED} </p>
                                    <p style="color: red">${requestScope.DELETE_INACTIVE} </p>
                                    <div class="table-responsive">
                                        <table class="table table-responsive-md">
                                            <thead>
                                                <tr>
                                                    <th style="width:80px;"><strong>#</strong></th>
                                                    <th><strong>User ID</strong></th>
                                                    <th><strong>Full Name</strong></th>
                                                    <th><strong>Address</strong></th>
                                                    <th><strong>City</strong></th>
                                                    <th><strong>Birthday</strong></th>
                                                    <th><strong>Phone</strong></th>
                                                    <th><strong>Email</strong></th>
                                                    <th><strong>Account Name</strong></th>                                                                                                                                    
                                                    <th><strong>Role Name</strong></th>
                                                    <th><strong>Status</strong></th>
                                                    <th><strong>Action</strong></th>

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
                                                        <td>${user.city.cityName}</td>
                                                        <td>${user.birth}</td>
                                                        <td>${user.phone}</td>
                                                        <td>${user.email}</td>
                                                        <td>${user.accName}</td>                                                         
                                                        <td>${user.role.roleId}</td>                                                       
                                                        <td>${user.status}</td>
                                                        <td>
                                                            <div class="d-flex">

                                                                <a href="MainController?action=UpdateAccountByAdmin&userID=${user.userID}" class="btn btn-warning shadow btn-xs sharp"><i class="fa fa-pencil"></i></a>
                                                                <a href="MainController?action=DeleteAccountByAdmin&userID=${user.userID}" class="btn btn-danger shadow btn-xs sharp"><i class="fa fa-trash"></i></a>

                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                                </tr>
                                        </table>


                                    </div>
                                </div>
                            </div>
                        </div>
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
