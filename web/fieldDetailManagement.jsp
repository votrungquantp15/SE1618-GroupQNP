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
    <title>Field Detail Page</title>
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
                                        <h4 class="card-title">Fields Management</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</button>
                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item" href="#">Action</a>
                                                    <a class="dropdown-item" href="#">Another action</a>
                                                    <a class="dropdown-item" href="#">Something else here</a>
                                                    <div role="separator" class="dropdown-divider"></div>
                                                    <a class="dropdown-item" href="#">Separated link</a>
                                                </div>
                                            </div>
                                            <input class="col-sm-3" type="text" class="form-control" aria-label="Text input with dropdown button" placeholder="Search something...">
                                            <div class="input-group-append">
                                                <button class="btn btn-secondary" type="button">Search</button>
                                            </div>
                                        </div>
                                        <div class="table-responsive">
                                            <table class="table table-responsive">
                                                <thead>
                                                    <tr>
                                                        <th style="width:80px;"><strong>#</strong></th>
                                                        <th><strong>Field ID</strong></th>
                                                        <th><strong>Field Name</strong></th>
                                                        <th><strong>Field Description</strong></th>
                                                        <th><strong>Field Image</strong></th>
                                                        <th><strong>CategoryFieldID</strong></th>
                                                        <th><strong>UserID</strong></th>
                                                        <th><strong>LocationID</strong></th>
                                                        <th><strong>CityName</strong></th>
                                                        <th><strong>Status</strong></th>
                                                        <th><strong>Action</strong></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                    <c:forEach var="field" items="${requestScope.FIELD_DETAIL}" varStatus="counter">
                                                <form action="MainController"> 
                                                    <tr>
                                                        <td><strong>${counter.count}</strong></td>
                                                        <td>${field.fieldId}</td>
                                                        <td><input title="${field.fieldName}" type="text" name="fieldName" value="${field.fieldName}" required=""></td>
                                                        <td><input title="${field.description}" type="text" name="description" value="${field.description}" required=""/></td>
                                                        <td><input title="${field.image}" type="text" name="image" value="${field.image}" required=""/></td>
                                                        <td><input title="Input what you want to update" type="text" name="categoryFieldId" value="${field.fieldCate.fieldCateId}" required=""/></td>
                                                        <td><input title="Input what you want to update" type="text" name="userId" value="${field.user.userID}" required=""/></td>
                                                        <td><input title="Input what you want to update" type="text" name="locationId" value="${field.location.locationId}" required=""/></td>
                                                        <td><input title="Input what you want to update" type="text" name="cityId" value="${field.city.cityId}" required=""/></td>
                                                        <td><input title="Input what you want to update" type="text" name="status" value="${field.status}" required=""/></td>
                                                        <td>
                                                            <div class="d-flex">
                                                                <input style="color: white" class="btn btn-primary btn-sm" title="Click here to update field" type="submit" name="action" value="UpdateField"/>
                                                                <input type="hidden" name="fieldId" value="${field.fieldId}"/>
                                                                <c:url var="delete" value="MainController">
                                                                    <c:param name="action" value="DeleteField"></c:param>
                                                                    <c:param name="fieldId" value="${field.fieldId}"></c:param>
                                                                </c:url>
                                                                <a title="Click here to delete field" href="${delete}" class="btn btn-danger shadow btn-sm sharp ml-1"><i class="fa fa-trash"></i></a>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </form>
                                            </c:forEach>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <p style="color: red">${requestScope.ERROR_MESSAGE} </p>
                                        <p style="color: green">${requestScope.UPDATE_SUCCESS} </p>
                                        <p style="color: red">${requestScope.UPDATE_UNSUCCESS} </p>
                                        <p style="color: green">${requestScope.DELETE_SUCCESS} </p>
                                        <p style="color: red">${requestScope.DELETE_UNSUCCESS} </p>
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
