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
    <title>Field Detail Management</title>
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
                            <div class="col-sm-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Fields Management</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                        <c:if test="${requestScope.FIELD_DETAIL != null}">
                                            <c:if test="${not empty requestScope.FIELD_DETAIL}">
                                                <div class="col-sm-12">
                                                    <div class="product-detail-content">
                                                        <div class="new-arrival-content pr row">
                                                            <div class="col-12 col-sm-12">
                                                                <div class="card-header">
                                                                    <h3><strong>Field</strong></h3>
                                                                </div>
                                                                <div class="card-body">
                                                                    <p style="color: red">${requestScope.ERROR_MESSAGE} </p>
                                                                    <p style="color: green">${requestScope.UPDATE_SUCCESS} </p>
                                                                    <p style="color: red">${requestScope.UPDATE_UNSUCCESS} </p>
                                                                    <p style="color: green">${requestScope.DELETE_SUCCESS} </p>
                                                                    <p style="color: red">${requestScope.DELETE_UNSUCCESS} </p>
                                                                    <div class="table row">
                                                                        <table class="col-12">
                                                                            <form action="MainController" method="POST" accept-charset="utf-8"> 
                                                                                <tr>
                                                                                    <th>Field Id:</th>
                                                                                    <th style="color: black">${requestScope.FIELD_DETAIL.fieldId}</th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>Field Name:</th>
                                                                                    <th class="col-10"><input class="col-12" title="Input what you want to update" type="text" name="fieldName" value="${requestScope.FIELD_DETAIL.fieldName}" required=""></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>Description:</th>
                                                                                    <th><textarea title="Input what you want to update" class="col-12" cols="500" rows="3" name="description">${requestScope.FIELD_DETAIL.description}</textarea></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>Image:</th>
                                                                                    <th><textarea title="Input what you want to update" class="col-12" rows="6" name="image">${requestScope.FIELD_DETAIL.image}</textarea></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>Field Category:</th>
                                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="categoryFieldId" value="${requestScope.FIELD_DETAIL.fieldCate.fieldCateId}" required=""></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>Field Price:</th>
                                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="price" value="${requestScope.FIELD_DETAIL.price}" required=""></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>Field Owner:</th>
                                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="userId" value="${requestScope.FIELD_DETAIL.user.userID}" required=""></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>Address:</th>
                                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="locationId" value="${requestScope.FIELD_DETAIL.location.locationId}" required=""></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>City:</th>
                                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="cityId" value="${requestScope.FIELD_DETAIL.city.cityId}" required=""></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>Status:</th>
                                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="status" value="${requestScope.FIELD_DETAIL.status}" required=""></th>
                                                                                </tr>
                                                                                <tr>
                                                                                    <th>
                                                                                        <div class="d-flex">
                                                                                            <input style="color: white" class="btn btn-primary btn-sm" title="Click here to update field" type="submit" name="action" value="UpdateField"/>
                                                                                            <input type="hidden" name="fieldId" value="${requestScope.FIELD_DETAIL.fieldId}"/>
                                                                                            <c:url var="delete" value="MainController">
                                                                                                <c:param name="action" value="DeleteField"></c:param>
                                                                                                <c:param name="fieldId" value="${requestScope.FIELD_DETAIL.fieldId}"></c:param>
                                                                                            </c:url>
                                                                                            <a title="Click here to delete field" href="#" class="btn btn-danger shadow ml-1" data-toggle="modal" data-target="#deleteConfirm"><i class="fa fa-trash"></i></a>
                                                                                        </div>
                                                                                        <div class="d-flex">
                                                                                            <form>
                                                                                                <input class="btn btn-primary btn-sm mt-2" title="Click here to back previous page" type="button" value="Back" onclick="history.go(-1)">
                                                                                            </form>
                                                                                        </div>
                                                                                    </th>
                                                                                    <th></th>
                                                                                </tr>
                                                                        </table>
                                                                        <div class="modal fade" id="deleteConfirm" tabindex="-1" aria-labelledby="deleteConfirm" aria-hidden="true">
                                                                            <div class="modal-dialog">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header">
                                                                                        <h5 class="modal-title" id="exampleModalLabel">Delete Confirm</h5>
                                                                                        <button type="button" class="close" aria-label="Close" data-dismiss="modal">&times;</button>
                                                                                    </div>
                                                                                    <div class="modal-body">
                                                                                        Do you really want to delete?
                                                                                    </div>
                                                                                    <div class="modal-footer">
                                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                        <a class="btn btn-primary" href="${delete}">Accept</a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:if>
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
