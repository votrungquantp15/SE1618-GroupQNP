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
    <title>Owner Field Detail Management</title>
    <!-- Favicon icon -->
    <link rel="icon" href="data:image/png;base64,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">

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
                                                                <div class="row">
                                                                    <div class="card-body col-6 col-sm-5">
                                                                        <img src="${requestScope.FIELD_DETAIL.image}" alt="Field image" width="100%" height="50%">
                                                                    </div>
                                                                    <div class="card-body col-6 col-sm-7">
                                                                        <p style="color: red">${requestScope.ERROR_MESSAGE} </p>
                                                                        <p style="color: green">${requestScope.UPDATE_SUCCESS} </p>
                                                                        <p style="color: green">${requestScope.DELETE_SUCCESS} </p>
                                                                        <p style="color: red">${requestScope.DELETE_UNSUCCESS} </p>
                                                                        <div class="table row">
                                                                            <table class="col-12">
                                                                                <form action="MainController" method="POST" accept-charset="utf-8"> 
                                                                                    <tr>
                                                                                        <th style="color: black">Field Id:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.fieldId}</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th style="color: black">Field Name:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.fieldName}</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th style="color: black">Description:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.description}</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th style="color: black">Field Category:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.fieldCate.fieldCateName}</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th style="color: black">Field Price:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.price}</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th style="color: black">Address:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.location.locationName}</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th style="color: black">District:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.district.districtName}</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th style="color: black">Status:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.status}</th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th style="color: black">Food Service:</th>
                                                                                        <th><a href="MainController?action=ViewFoodOfField&fieldId=${requestScope.FIELD_DETAIL.fieldId}&index=1" class="btn btn-warning shadow btn-xs sharp"><i class="fa fa-clipboard"></i></a></th>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th></th>
                                                                                        <th>
                                                                                            <div class="d-flex justify-content-end">
                                                                                                <button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#updateField">Update field</button>

                                                                                                <c:url var="delete" value="MainController">
                                                                                                    <c:param name="action" value="DeleteField"></c:param>
                                                                                                    <c:param name="fieldId" value="${requestScope.FIELD_DETAIL.fieldId}"></c:param>
                                                                                                </c:url>
                                                                                                <a title="Click here to delete field" href="#" class="btn btn-danger shadow ml-1" data-toggle="modal" data-target="#deleteConfirm"><i class="fa fa-trash"></i></a>

                                                                                            </div>
                                                                                            <div class="d-flex justify-content-end">
                                                                                                <a class="btn btn-primary mt-2" href="MainController?action=Print&index=1">Back</a>
                                                                                            </div>
                                                                                        </th>
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
            <form action="MainController" method="POST" accept-charset="utf-8"> 
                <div class="modal fade" id="updateField" tabindex="-1" aria-labelledby="updateField" aria-hidden="true">
                    <div class="modal-dialog modal-xl modal-dialog-scrollable">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Update field</h5>
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
                                                                    <th class="col-10"><input class="col-12" title="Input what you want to update" type="text" name="fieldName" value="${requestScope.FIELD_DETAIL.fieldName}" required=""></th>
                                                                </tr>
                                                                <c:if test = "${not empty requestScope.NAME_ERROR}">
                                                                    <tr>
                                                                        <th></th>
                                                                        <th><p style="color: red"> ${requestScope.NAME_ERROR} </p></th>
                                                                    </tr>
                                                                </c:if>
                                                                <tr>
                                                                    <th>Description:</th>
                                                                    <th><textarea title="Input what you want to update" class="col-12" cols="500" rows="3" name="description">${requestScope.FIELD_DETAIL.description}</textarea></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Image:</th>
                                                                    <th><textarea title="Input what you want to update" class="col-12" rows="6" name="image">${requestScope.FIELD_DETAIL.image}</textarea></th>
                                                                </tr>
                                                                <c:if test = "${not empty requestScope.IMAGE_ERROR}">
                                                                    <tr>
                                                                        <th></th>
                                                                        <th><p style="color: red">${requestScope.IMAGE_ERROR}</p></th>
                                                                    </tr>
                                                                </c:if>
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
                                                                    <th><input class="col-12" title="Input what you want to update" type="text" name="price" value="${requestScope.FIELD_DETAIL.price}" required=""></th>
                                                                </tr>
                                                                <c:if test = "${not empty requestScope.PRICE_ERROR}">
                                                                    <tr>
                                                                        <th></th>
                                                                        <th><p style="color: red">${requestScope.PRICE_ERROR}</p></th>
                                                                    </tr>
                                                                </c:if>
                                                                <input type="hidden" name="userId" value="${requestScope.USER_ID}">
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
                                                            </table>
                                                            <p style="color: red">${requestScope.UPDATE_UNSUCCESS} </p>
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
                                <input style="color: white" class="btn btn-primary" title="Click here to update field" type="submit" name="action" value="UpdateField"/>
                                <input type="hidden" name="fieldId" value="${requestScope.FIELD_DETAIL.fieldId}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
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
    <c:if test = "${requestScope.SHOW_MODAL == '1'}">
        <script type="text/javascript">
            $(document).ready(() => {
                $('#updateField').modal('show');
            });
        </script>
    </c:if>
</body>
</html>
