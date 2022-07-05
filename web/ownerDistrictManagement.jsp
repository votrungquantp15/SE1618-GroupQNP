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
    <title>Owner District Management</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Datatable -->
    <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <!-- Custom Stylesheet -->
    <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body>
    <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.role.roleId ne 'MA'}">
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

        <jsp:include page="navbarFieldOwner.jsp"></jsp:include>
            <div class="content-body">
                <div class="col-12">
                    <div class="card">

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Districts Management</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="form col-12">
                                                <form class="col-12 form-inline mb-3" action="MainController">
                                                    <div class="input-group col-sm-10">
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-primary disabled" type="button">Status</button>
                                                            <select name="status">
                                                                <option value="" <c:if test="${param.status == null}">selected</c:if>>Show all status</option>
                                                            <option value="0" <c:if test="${param.status eq '0'}">selected</c:if>>In-Active</option>
                                                            <option value="1" <c:if test="${param.status eq '1'}">selected</c:if>>Active</option>
                                                            </select>
                                                        </div>
                                                        <input class="col-sm-4" type="text" class="form-control" name="searchByAdmin" value="${param.searchByAdmin}" placeholder="Search here">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-primary btn-sm-3" type="submit" name="action" value="SearchDistrictByAdmin">Search</button>
                                                    </div>
                                                </div>
                                                <button class="btn btn-primary col-sm" type="button" data-toggle="modal" data-target="#createNewDistrict">Create new district</button>
                                            </form>
                                            <p style="color: red"> ${requestScope.SEARCH_DISTRICT_ERROR} </p>
                                            <p style="color: green"> ${requestScope.CREATE_SUCCESS} </p>
                                            <p style="color: red"> ${requestScope.CREATE_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_UNSUCCESS} </p>
                                            <p style="color: green">${requestScope.UPDATE_SUCCESS} </p>
                                            <p style="color: red">${requestScope.UPDATE_UNSUCCESS} </p>
                                            <p style="color: red">${requestScope.UPDATE_ERROR} </p>
                                            <p style="color: green">${requestScope.DELETE_SUCCESS} </p>
                                            <p style="color: red">${requestScope.DELETE_UNSUCCESS} </p>
                                        </div>
                                    </div>
                                    <form action="MainController" method="POST" accept-charset="utf-8">
                                        <div class="table-responsive">
                                            <table class="table table-responsive-md">
                                                <thead>
                                                    <tr>
                                                        <th style="width:80px;"><strong>#</strong></th>
                                                        <th><strong>District ID</strong></th>
                                                        <th><strong>District Name</strong></th>
                                                        <th><strong>Status</strong></th>
                                                        <th><strong>Action</strong></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <c:forEach var="district" items="${requestScope.LIST_DISTRICT}">
                                                            <c:set var="counter" scope="page" value="${counter + 1}"/>
                                                        <tr>
                                                            <td><strong>${counter}</strong></td>
                                                            <td>${district.districtId}</td>
                                                            <td>${district.districtName}</td>
                                                            <td>${district.status}</td>
                                                            <td>
                                                                <a href="#" class="btn btn-warning shadow btn-xs sharp" data-toggle="modal" data-target="#updateDistrict${counter}"><i class="fa fa-pencil"></i></a>

                                                                <c:url var="delete" value="MainController">
                                                                    <c:param name="action" value="DeleteDistrict"></c:param>
                                                                    <c:param name="districtId" value="${district.districtId}"></c:param>
                                                                </c:url>
                                                                <a title="Click here to delete district" href="#" class="btn btn-danger btn-xs shadow sharp ml-1" data-toggle="modal" data-target="#deleteConfirm${counter}"><i class="fa fa-trash"></i></a>
                                                                <div class="modal fade" id="deleteConfirm${counter}" tabindex="-1" aria-labelledby="deleteConfirm" aria-hidden="true">
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
                                                            </td>
                                                            <td> 
                                                                <form action="MainController" method="POST" accept-charset="utf-8">
                                                                    <div class="modal fade" id="updateDistrict${counter}" tabindex="-1" aria-labelledby="updateDistrict" aria-hidden="true">
                                                                        <div class="modal-dialog modal-xl modal-dialog-scrollable">
                                                                            <div class="modal-content">

                                                                                <div class="modal-header">
                                                                                    <h5 class="modal-title" id="exampleModalLabel">Update district</h5>
                                                                                    <button type="button" class="close" aria-label="Close" data-dismiss="modal">&times;</button>
                                                                                </div>
                                                                                <div class="modal-body" style="margin-top: -20px">
                                                                                    <div class="row">
                                                                                        <div class="col-sm-12">
                                                                                            <div class="product-detail-content">
                                                                                                <div class="new-arrival-content pr row">
                                                                                                    <div class="col-12 col-sm-12">
                                                                                                        <div class="card-body">
                                                                                                            <div class="table row">
                                                                                                                <table class="col-12">
                                                                                                                    <tr>
                                                                                                                        <th>District Name:</th>
                                                                                                                        <th class="col-10"><input class="col-12" title="Input what you want to update" type="text" name="districtName" value="${district.districtName}" required=""></th>
                                                                                                                    </tr>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                    <input style="color: white" class="btn btn-primary" title="Click here to update district" type="submit" name="action" value="UpdateDistrict"/>
                                                                                    <input type="hidden" name="id_district" value="${district.districtId}"/>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </form>   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="MainController" method="POST" accept-charset="utf-8"> 
                <div class="modal fade" id="createNewDistrict" tabindex="-1" aria-labelledby="createNewDistrict" aria-hidden="true">
                    <div class="modal-dialog modal-xl modal-dialog-scrollable">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Create new district</h5>
                                <button type="button" class="close" aria-label="Close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body" style="margin-top: -20px">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="product-detail-content">
                                            <div class="new-arrival-content pr row">
                                                <div class="col-12 col-sm-12">
                                                    <div class="card-body">
                                                        <div class="table row">
                                                            <table class="col-12">
                                                                <tr>
                                                                    <th>District Name:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="districtName" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th></th>
                                                                    <th class="d-flex justify-content-end"><input class="btn btn-secondary" type="reset" value="Reset"/></th>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input type="hidden" name="action" value="CreateDistrict"/>
                                <input class="btn btn-primary" type="submit" value="Accept"/>
                            </div>

                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
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
