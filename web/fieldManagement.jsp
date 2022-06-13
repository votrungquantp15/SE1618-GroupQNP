/<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Field Management</title>
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
                                        <h4 class="card-title">Fields Management</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="form col-12">
                                                <form class="col-12 form-inline mb-3" action="MainController">
                                                    <div class="input-group col-sm-10">
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-secondary disabled" type="button">Status</button>
                                                            <select name="status">
                                                                <option value="" <c:if test="${param.status == null}">selected</c:if>>Show all status</option>
                                                            <option value="0" <c:if test="${param.status eq '0'}">selected</c:if>>In-Active</option>
                                                            <option value="1" <c:if test="${param.status eq '1'}">selected</c:if>>Active</option>
                                                            </select>
                                                        </div>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-secondary disabled" type="button">Search By</button>
                                                            <select name ="searchBy">
                                                                <option value="Name" <c:if test="${param.searchBy eq 'Name'}">selected</c:if>>Name</option>
                                                            <option value="Category" <c:if test="${param.searchBy eq 'Category'}">selected</c:if>>Category</option>
                                                            <option value="Field Owner" <c:if test="${param.searchBy eq 'Field Owner'}">selected</c:if>>Field Owner</option>
                                                            <option value="City" <c:if test="${param.searchBy eq 'City'}">selected</c:if>>City</option>
                                                            </select>
                                                        </div>
                                                        <input class="col-sm-4" type="text" class="form-control" name="searchByAdmin" value="${param.searchByAdmin}" placeholder="Search by name">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-secondary btn-sm-3" type="submit" name="action" value="SearchFieldByAdmin">Search</button>
                                                    </div>
                                                </div>
                                                <button class="btn btn-secondary col-sm" type="button" data-toggle="modal" data-target="#createNewField">Create new field</button>
                                            </form>
                                            <p style="color: red"> ${requestScope.SEARCH_FIELD_ERROR} </p>
                                            <p style="color: green"> ${requestScope.CREATE_SUCCESS} </p>
                                            <p style="color: red"> ${requestScope.CREATE_CATE_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_USER_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_LOCATION_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_CITY_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_UNSUCCESS} </p>
                                        </div>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-responsive-md">
                                            <thead>
                                                <tr>
                                                    <th style="width:80px;"><strong>#</strong></th>
                                                    <th><strong>Field ID</strong></th>
                                                    <th><strong>Field Name</strong></th>
                                                    <th><strong>Category</strong></th>
                                                    <th><strong>Price</strong></th>
                                                    <th><strong>Field Owner</strong></th>
                                                    <th><strong>City Name</strong></th>
                                                    <th><strong>Status</strong></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <c:forEach var="field" items="${requestScope.LIST_FIELD}" varStatus="counter">
                                                    <tr>
                                                        <td><strong>${counter.count}</strong></td>
                                                        <td>${field.fieldId}</td>
                                                        <td><a title="Click to view detail" href="MainController?action=PrintDetail&fieldId=${field.fieldId}">${field.fieldName}</a></td>
                                                        <td>${field.fieldCate.fieldCateName}</td>
                                                        <td>${field.price}</td>
                                                        <td>${field.user.fullName}</td>
                                                        <td>${field.city.cityName}</td>
                                                        <td>${field.status}</td>
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
            </div>
            <form action="MainController" method="POST" accept-charset="utf-8"> 
                <div class="modal fade" id="createNewField" tabindex="-1" aria-labelledby="createNewField" aria-hidden="true">
                    <div class="modal-dialog modal-xl modal-dialog-scrollable">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Create new field</h5>
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
                                                                    <th>Field Name:</th>
                                                                    <th class="col-10"><input class="col-12" title="Input what you want to update" type="text" name="fieldName" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Description:</th>
                                                                    <th><textarea title="Input what you want to update" class="col-12" cols="500" rows="3" name="description"></textarea></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Image:</th>
                                                                    <th><textarea title="Input what you want to update" class="col-12" rows="6" name="image"></textarea></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Category:</th>
                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="categoryFieldId" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Price:</th>
                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="price" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Owner:</th>
                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="userId" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Address:</th>
                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="locationId" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>City:</th>
                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="cityId" required=""></th>
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
                                <input type="hidden" name="action" value="CreateField"/>
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
    <script src="js/styleSwitcher.js"></script>
</body>
</html>
