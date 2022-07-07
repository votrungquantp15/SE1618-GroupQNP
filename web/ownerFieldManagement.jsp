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
    <title>Owner Field Management</title>
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
                                        <h4 class="card-title">Fields Management</h4>
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
                                                            <option value="In-Active" <c:if test="${param.status eq 'In-Active'}">selected</c:if>>In-Active</option>
                                                            <option value="Request" <c:if test="${param.status eq 'Request'}">selected</c:if>>Request</option>
                                                            <option value="Active" <c:if test="${param.status eq 'Active'}">selected</c:if>>Active</option>
                                                            </select>
                                                        </div>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-primary disabled" type="button">Search By</button>
                                                            <select name ="searchBy">
                                                                <option value="Name" <c:if test="${param.searchBy eq 'Name'}">selected</c:if>>Name</option>
                                                            <option value="Category" <c:if test="${param.searchBy eq 'Category'}">selected</c:if>>Category</option>
                                                            <option value="District" <c:if test="${param.searchBy eq 'District'}">selected</c:if>>District</option>
                                                            </select>
                                                        </div>
                                                        <input class="col-sm-4" type="text" class="form-control" name="searchByAdmin" value="${param.searchByAdmin}" placeholder="Search here">
                                                    <div class="input-group-append">
                                                        <input type="hidden" name="index" value="1"/>
                                                        <button class="btn btn-primary btn-sm-3" type="submit" name="action" value="SearchFieldByAdmin">Search</button>
                                                    </div>
                                                </div>
                                                <button class="btn btn-primary col-sm" type="button" data-toggle="modal" data-target="#createNewField">Create new field</button>
                                            </form>
                                            <p style="color: red"> ${requestScope.SEARCH_FIELD_ERROR} </p>
                                            <p style="color: green"> ${requestScope.CREATE_SUCCESS} </p>
                                            <p style="color: red"> ${requestScope.CREATE_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_CATE_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_USER_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_LOCATION_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_DISTRICT_ERROR} </p>
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
                                                    <th><strong>District Name</strong></th>
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
                                                        <td><a class="text-primary" title="Click to view detail" href="MainController?action=PrintDetail&fieldId=${field.fieldId}">${field.fieldName}</a></td>
                                                        <td>${field.fieldCate.fieldCateName}</td>
                                                        <td>${field.price}</td>
                                                        <td>${field.user.fullName}</td>
                                                        <td>${field.district.districtName}</td>
                                                        <td>${field.status}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tr>
                                        </table>
                                    </div>
                                        
                                    <c:choose>
                                        <c:when test="${requestScope.LIST_FIELD != null}">
                                            <ul class="pagination">
                                                <c:choose>
                                                    <c:when test="${sessionScope.ACTION_FIELD == 'Print'}">
                                                        <c:forEach var="i" begin="1" end="${END_PAGE}">
                                                            <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                                                <a href="MainController?action=Print&index=${i}" class="page-link">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${sessionScope.ACTION_FIELD == 'Search'}">
                                                        <c:forEach var="i" begin="1" end="${END_PAGE}">
                                                            <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                                                <a href="MainController?action=SearchFieldByAdmin&index=${i}&searchBy=${requestScope.SEARCH_BY}&searchByAdmin=${requestScope.SEARCH}&status=${requestScope.STATUS}" class="page-link">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise></c:otherwise>
                                                </c:choose>
                                            </ul>
                                        </c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                        
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
                                                                    <th class="col-10"><input class="col-12" title="Input information here" type="text" name="fieldName" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Description:</th>
                                                                    <th><textarea title="Input information here" class="col-12" cols="500" rows="3" name="description"></textarea></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Image:</th>
                                                                    <th><textarea title="Input information here" class="col-12" rows="6" name="image"></textarea></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Category:</th>
                                                                    <th>
                                                                        <select name ="categoryFieldId">
                                                                            <c:forEach var="category" items="${requestScope.LIST_CATEGORY}">
                                                                                <option value="${category.fieldCateId}">${category.fieldCateName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Price:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="price" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Owner:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="userName" value="${requestScope.USER_NAME}" readonly=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Address:</th>
                                                                    <th>
                                                                        <select name ="locationId">
                                                                            <c:forEach var="location" items="${requestScope.LIST_LOCATION}">
                                                                                <option value="${location.locationId}">${location.locationName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </th>
                                                                </tr>
                                                                <tr>
                                                                    <th>District:</th>
                                                                    <th>
                                                                        <select name ="districtId">
                                                                            <c:forEach var="district" items="${requestScope.LIST_DISTRICT}">
                                                                                <option value="${district.districtId}">${district.districtName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </th>
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
</body>
</html>
