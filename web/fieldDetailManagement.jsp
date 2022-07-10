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
                                                                <div class="row">
                                                                    <div class="card-body col-6 col-sm-5">
                                                                        <img src="${requestScope.FIELD_DETAIL.image}" alt="Field image" width="100%" height="50%">
                                                                    </div>
                                                                    <div class="card-body col-6 col-sm-7">
                                                                        <p style="color: red">${requestScope.ERROR_MESSAGE} </p>
                                                                        <p style="color: green">${requestScope.UPDATE_SUCCESS} </p>
                                                                        <p style="color: red">${requestScope.UPDATE_UNSUCCESS} </p>
                                                                        <p style="color: red">${requestScope.UPDATE_ERROR} </p>
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
                                                                                        <th style="color: black">Field Owner:</th>
                                                                                        <th>${requestScope.FIELD_DETAIL.user.fullName}</th>
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
                    <div class="modal-dialog modal-dialog-scrollable">
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
                                                                    <th style="position: relative; top: 15px;">Status:</th>
                                                                    <th>
                                                                        <select class="form-control" name ="status">
                                                                            <option value="Active">Active</option>
                                                                            <option value="Request">Request</option>
                                                                            <option value="In-Active">In-Active</option>
                                                                        </select>
                                                                    </th>
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
                                <input style="color: white" class="btn btn-primary" title="Click here to update field" type="submit" name="action" value="UpdateField"/>
                                <input type="hidden" name="fieldId" value="${requestScope.FIELD_DETAIL.fieldId}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>
        $(document).ready(function(){
        if (${requestScope.UPDATE_MODAL} === "1"){
        $('#updateField').modal('show');
        }
        }
    </script>
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
