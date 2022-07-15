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
    <link rel="icon" href="data:image/png;base64,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">

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
        <c:if test="${sessionScope.LOGIN_USER.role.roleId eq 'MA'}">
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

                                                        <button type="submit" class="btn btn-rounded btn-success" name = "action" value="CreateFood"><span class="btn-icon-left text-success "><i class="fa fa-plus color-info"></i>
                                                            </span>Add new food</button>
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

                                                            <td>
                                                                <div class="d-flex">

                                                                    <a href="MainController?action=AddFoodToField&foodId=${food.foodId}" class="btn btn-info shadow btn-xs sharp ml-1"><i class="fa fa-plus"></i></a>

                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                    </tr>
                                            </table>
                                            <ul class="pagination pagination-sm pagination-gutter">
                                                <c:forEach begin="1" end="${END_PAGE}" var ="page">     
                                                    <c:if test = "${action.action eq ViewFoodList}">
                                                        <li class="page-item <c:if test="${param.index eq page}"> active </c:if>" ><a class="page-link" href="MainController?action=ViewFoodList&index=${page}">${page}</a>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:forEach begin="1" end="${END_PAGE_SEARCH}" var ="page">     
                                                        <c:if test = "${action.action eq SearchFoodByManager}">
                                                        <li class="page-item <c:if test="${param.index eq page}"> active </c:if>" ><a class="page-link" href="MainController?action=SearchFoodByManager&searchFoodByManager=${param.searchFoodByManager}&index=${page}">${page}</a>
                                                        </c:if>
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
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER.role.roleId eq 'AD'}">
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

                                                        <button type="submit" class="btn btn-rounded btn-success" name = "action" value="CreateFood"><span class="btn-icon-left text-success "><i class="fa fa-plus color-info"></i>
                                                            </span>Add new food</button>
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

                                                                    <a href="MainController?action=DeleteFoodByManager&foodId=${food.foodId}" class="btn btn-danger shadow btn-xs sharp ml-1"><i class="fa fa-trash"></i></a>
                                                                    <a href="MainController?action=ActiveFood&foodId=${food.foodId}" class="btn btn-success shadow btn-xs sharp ml-1"><i class="fa fa-check-square"></i></a>

                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                    </tr>
                                            </table>
                                            <ul class="pagination pagination-sm pagination-gutter">
                                                <c:forEach begin="1" end="${END_PAGE}" var ="page">     
                                                    <c:if test = "${action.action eq ViewFoodList}">
                                                        <li class="page-item <c:if test="${param.index eq page}"> active </c:if>" ><a class="page-link" href="MainController?action=ViewFoodList&index=${page}">${page}</a>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:forEach begin="1" end="${END_PAGE_SEARCH}" var ="page">     
                                                        <c:if test = "${action.action eq SearchFoodByManager}">
                                                        <li class="page-item <c:if test="${param.index eq page}"> active </c:if>" ><a class="page-link" href="MainController?action=SearchFoodByManager&searchFoodByManager=${param.searchFoodByManager}&index=${page}">${page}</a>
                                                        </c:if>
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
        </c:if>
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
