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
    <title>Slot Management</title>
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
                                        <h4 class="card-title">Slot Management
                                            <p style="color: #17e379"><strong>${DELETE_SUCCESS}</strong></p> 
                                        <p style="color: #ff2457"><strong>${DELETE_UNSUCCESS}</strong></p> 
                                        <p style="color: #17e379"><strong>${UPDATE_SUCCESS}</strong></p> 
                                        <p style="color: #ff2457"><strong>${UPDATE_UNSUCCESS}</strong></p> 
                                    </h4>
                                </div>
                                <div class="card-body">
                                    <form action="MainController" method="POST">
                                        <div class="form-group">
                                            <div class="row">

                                                <label class="col-sm-2 control-label">Search by Slot ID</label>
                                                <div class="col-sm-6">
                                                    <input class="form-control" type="text" name="search" value="${param.search}" placeholder="Search by slotID" />
                                                </div>

                                                <div class ="col-sm-2">
                                                    <button type="submit" name="action" class="btn btn-rounded btn-warning" value="SearchSlot"><i class="fa fa-search "></i></button>
                                                </div>
                                            </div>                              
                                        </div>                                                                                  
                                    </form>

                                    <div class="table-responsive">
                                        <table class="table table-responsive-md">
                                            <c:if test="${requestScope.LIST_SLOT == null}">
                                                <strong>No Result</strong>
                                            </c:if>
                                            <thead style="background-color: #fcd15b">
                                                <tr>
                                                    <th style="width:80px;"><strong>#</strong></th>
                                                    <th><strong>Slot ID</strong></th>
                                                    <th><strong>Time Start</strong></th>
                                                    <th><strong>Time End</strong></th>
                                                    <th><strong>Status</strong></th>
                                                    <th class="d-flex justify-content-center"><strong>Update</strong></th>
                                                    <th><strong>Delete</strong></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${requestScope.LIST_SLOT != null}">
                                                    <c:if test="${not empty requestScope.LIST_SLOT}">
                                                        <c:set var="counter" scope="page" value="0"/>
                                                        <c:forEach var="slot" items="${requestScope.LIST_SLOT}">
                                                            <c:set var="counter" scope="page" value="${counter + 1}"/>
                                                            <tr>
                                                                <td><strong>${counter}</strong></td>
                                                                <td>${slot.slotId}</td>
                                                                <td>${slot.timeStart}</td>
                                                                <td>${slot.timeEnd}</td>
                                                                <td>${slot.status}</td>
                                                                <td>
                                                                    <div class="d-flex justify-content-center">
                                                                        <button type="button" class="btn btn-primary shadow btn-xs sharp" data-toggle="modal" data-target="#basicModalUpdate${counter}" title="Update"><i class="fa fa-pencil"></i></button>
                                                                        <div class="modal fade" id="basicModalUpdate${counter}">
                                                                            <div class="modal-dialog" role="document">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header"  style="background-color: #fcd15b">
                                                                                        <h3 class="modal-title">Update Slot ${slotDetail.slot.slotID}</h3>
                                                                                        <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                                                                                        </button>
                                                                                    </div>
                                                                                    <div class="modal-body" style="background-color: #f7f3e6">                                                                                      
                                                                                        <form action="MainController" method="POST">
                                                                                            <input type="hidden" name="slotID" value="${slotDetail.slot.slotID}">
                                                                                            <input type="hidden" name="search" value="${param.search}">

                                                                                            <div class="form-row">

                                                                                                <h4>Slot ${slot.slotId}: ${slot.timeStart} - ${slot.timeEnd}</h4>

                                                                                                <input type="time" name="timeStart" value="">

                                                                                            </div>
                                                                                        </form>
                                                                                    </div>
                                                                                    <div class="modal-footer">                                                                                       
                                                                                        <div class="form-row d-inline-block">
                                                                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                                                                            <input type="submit" class="btn btn-primary" name="action" value="UpdateBooking">
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                </td>
                                                                <td>
                                                                    <div class="d-flex justify-content-center">
                                                                        <button type="button" class="btn btn-primary shadow btn-xs sharp" data-toggle="modal" data-target="#basicModalDelete${counter}" title="Delete"><i class="fa fa-trash"></i></button>
                                                                        <div class="modal fade" id="basicModalDelete${counter}">
                                                                            <div class="modal-dialog" role="document">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header"  style="background-color: #fcd15b">
                                                                                        <h3 class="modal-title">Delete Slot ${slotDetail.slot.slotId}</h3>
                                                                                        <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                                                                                        </button>
                                                                                    </div>
                                                                                    <div class="modal-footer" style="background-color: #f7f3e6">
                                                                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                                                                        <a href="" type="button" class="btn btn-primary">Confirm</a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                            </tbody>
                                        </table>
                                    </div>
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
