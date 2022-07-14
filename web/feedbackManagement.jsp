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
                                        <h4 class="card-title">Feedback Management</h4>
                                    </div>
                                    <h4 style="color: red"> ${requestScope.SEARCH_FEEDBACK_ERROR} </h4>  
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="form col-12">
                                                <form class="col-12 form-inline mb-3" action="MainController">                                                   
                                                    <button class="btn btn-primary col-sm" type="button" data-toggle="modal" data-target="#createNewField">Create new field</button>
                                                </form>

                                            </div>
                                        </div>
                                    <form action="MainController">
                                            <input class="col-sm-4" type="text" class="form-control" name="searchFeedbackByFieldName" value="${param.searchFeedbackByFieldName}" placeholder="Search By Field Name">
                                            <input class="col-sm-4" type="text" class="form-control" name="searchFeedbackByUserName" value="${param.searchFeedbackByUserName}" placeholder="Search By User name">
                                        <!--<div class="input-group-append">-->
                                            <button class="btn btn-primary btn-sm-3" type="submit" name="action" value="SearchFeedbackByAdmin">Search</button>
                                        <!--</div>-->
                                    </form>


                                    <div class="table-responsive">
                                        <table class="table table-responsive-md">
                                            <thead>
                                                <tr>
                                                    <th style="width:80px;"><strong>#</strong></th>
                                                    <th><strong>Feedback ID</strong></th>
                                                    <th><strong>Title</strong></th>
                                                    <th><strong>Content</strong></th>
                                                    <th><strong>User Name</strong></th>
                                                    <th><strong>Field Name</strong></th>                                                   

                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!--<tr>-->
                                                    <c:forEach var="feedback" items="${requestScope.LIST_FEEDBACK}" varStatus="counter">
                                                    <tr>
                                                        <td><strong>${counter.count}</strong></td>
                                                        <td>${feedback.feedbackId}</td>
                                                        <td>${feedback.title}</td>                                        
                                                        <td>${feedback.content}</td>                                                        
                                                        <td>${feedback.user.fullName}</td>
                                                        <td><a class="text-primary" title="Click to view detail" href="MainController?action=PrintDetail&fieldId=${feedback.field.fieldId}" >${feedback.field.fieldName}</a></td>
                                                        
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
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
                                                                    <th><input class="col-12" title="Input information here" type="text" name="categoryFieldId" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Price:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="price" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Owner:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="userId" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Address:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="locationId" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>City:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="cityId" required=""></th>
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
