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
    <title>Field Owner Dashboard </title>
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
    <!--*******************
        Preloader end
    ********************-->


    <!--**********************************
        Main wrapper start
    ***********************************-->
    <div id="main-wrapper">

        <jsp:include page="navbarFieldOwner.jsp"></jsp:include>
            <div class="content-body">
                <div class="col-12">
                    <div class="card">

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Food Management</h4>
                                    </div>

                                    <div class="card-body">
                                        <form action="MainController">
                                            <div class="form-group col-md-6">
                                                <label><strong>Search food by name</strong></label>
                                                <div class="row">
                                                    <div class ="col-md-6">
                                                        <input name="searchFoodByManager" type="text" class="form-control" placeholder="Type here to search" value="${param.searchFoodByManager}">                                                                                             
                                                </div>
                                                <div class ="col-md-6">
                                                    <button type="submit" name="action" class="btn btn-rounded btn-warning" value="SearchFoodByManager">SEARCH</button>
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
                                                    <th><strong>Food ID</strong></th>
                                                    <th><strong>Food Name</strong></th>
                                                    <th><strong>Image</strong></th>
                                                    <th><strong>Category</strong></th>
                                                    <th><strong>Status</strong></th>                                                  
                                                    <th><strong>Action</strong></th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <c:forEach var="food" items="${requestScope.VIEW_FOOD}">
                                                    <tr>
                                                        <td>${food.foodId}</td>
                                                        <td>${food.foodName}</td>
                                                        <td>
                                                            <img width="200" height="auto" src="${food.image}">
                                                        </td>
                                                        <td>${food.foodCate.foodCateName}</td>
                                                        <td>${food.status}</td>

                                                        <td>
                                                            <div class="d-flex">

                                                                <a href="MainController?action=UpdateFoodByManager&foodId=${food.foodId}" class="btn btn-warning shadow btn-xs sharp"><i class="fa fa-pencil"></i></a>
                                                                <a href="MainController?action=DeleteFoodByManager&foodId=${food.foodId}" class="btn btn-danger shadow btn-xs sharp"><i class="fa fa-trash"></i></a>

                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                                </tr>
                                        </table>
                                        <ul class="pagination pagination-sm pagination-gutter">
                                            <c:forEach begin="1" end="${END_PAGE}" var ="page">                                               
                                                <li class="page-item <c:if test="${param.index eq page}"> active </c:if>" ><a class="page-link" href="MainController?action=ViewFoodList&index=${page}">${page}</a>
                                            </c:forEach>
                                        </ul>       


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
</body>
</html>
