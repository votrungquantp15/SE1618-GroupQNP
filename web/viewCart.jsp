<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="data:image/png;base64,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">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/homestyles.css">
        <link rel="stylesheet" href="css/homestyle.css">

        <link href="css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="css/responsive.css">
        <title>Giỏ sân đặt</title>

    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.role.roleId ne 'US'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <div class="jumbotron-fluid">
            <nav class="navbar navbar-expand-lg navbar-light fixed-top">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h ml-5" href="MainController?action=Print&index=1"><img src="https://logopond.com/logos/18c31fb8cfe3ce15b964939a13c369a5.png" height="85px"></a>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto pr-5">
                        <li class="nav-item active"><a class="nav-link" href="MainController?action=Print">Trang chủ</a></li>
                        <li class="nav-item"><a class="nav-link" href="MainController?action=ViewCart">Giỏ sân đặt(<c:if test="${CART == null or CART.getCart().size() == 0}">0</c:if><c:if test="${CART != null or CART.getCart().size() > 0}">${CART.getCart().size()}</c:if>)</a></li>
                            <li class="nav-item submenu dropdown">
                            <c:choose>
                                <c:when test="${sessionScope.LOGIN_USER == null}">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Login</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.LOGIN_USER.fullName}</a>
                                    <ul class="dropdown-menu">
                                        <li class="nav-item"><a href="MainController?action=ProfileUser&id=${sessionScope.LOGIN_USER.userID}" class="nav-link">Hồ sơ</a></li>
                                        <li class="nav-item"><a href="MainController?action=SearchBooking&userID=${sessionScope.LOGIN_USER.userID}&index=1&status=" class="nav-link">Lịch sử đặt</a></li>
                                        <li class="nav-item"><a href="MainController?action=Logout" class="nav-link">Đăng xuất</a></li>
                                    </ul>
                                </c:otherwise>
                            </c:choose>
                        </li> 
                    </ul>
                </div> 
            </nav>
        </div>
        <div class="deznav pt-4">
            <div class="deznav-scroll">
                <div class="main-profile">
                    <div class="image-bx">
                        <img src="https://i.pinimg.com/736x/89/90/48/899048ab0cc455154006fdb9676964b3.jpg" alt="">
                    </div>
                    <h5 class="name"><span class="font-w400">Hello, ${sessionScope.LOGIN_USER.fullName}</span></h5>
                    <p><strong>[Role: User]</strong></p>
                </div>
                <ul class="metismenu" id="menu">
                    <li class="nav-label first">Menu</li>
                    <li><a href="MainController?action=ProfileUser&id=${sessionScope.LOGIN_USER.userID}">Hồ sơ</a></li>
                    <li><a href="MainController?action=PrintFeedback&index=1">Phản hồi</a></li>
                    <li><a href="MainController?action=SearchBooking&userID=${sessionScope.LOGIN_USER.userID}&index=1&status=">Lịch sử đặt</a></li>
                    <li><a href="MainController?action=ViewCart">Giỏ sân đặt</a></li>
                </ul>
            </div>
        </div>

        <div id="main-wrapper">
            <div class="content-body">
                <div class="col-12">
                    <div class="card">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-header bg-warning">
                                        <h4 class="card-title"><strong>Sân đang chờ xác nhận</strong></h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <h4 class="text-danger text-center"><strong>${DELETE_ITEM_FAIL}</strong></h4>
                                            <h4 class="text-success text-center"><strong>${DELETE_ITEM_SUCCESS}</strong></h4>
                                            <h4 class="text-success text-center"><strong>${EDIT_SUCCESS}</strong></h4>
                                            <table class="table table-responsive-sm">
                                                <c:if test="${empty CART}">
                                                    <h2 class="text-center bold text-danger">Chưa có sân</h2> 
                                                </c:if>
                                                <c:if test="${sessionScope.CART != null}">
                                                    <c:if test="${not empty sessionScope.CART}">
                                                        <thead>
                                                            <tr>                                                
                                                                <th></th>
                                                                <th><strong>Field Name</strong></th>
                                                                <th><strong>Time</strong></th>
                                                                <th><strong>Price</strong></th>
                                                                <th><strong>Play Date</strong></th>
                                                                <th><strong>Actions</strong></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:set var="total" value="0"/>
                                                            <c:set var="counter" value="0"/>
                                                            <c:forEach var="cart" items="${sessionScope.CART.getCart().values()}">  
                                                                <c:set var="counter" value="${counter + 1}"/>
                                                                <c:set var="total" value="${total + cart.field.price}"/>
                                                                <c:url var="RemoveCartItem" value="MainController">                              
                                                                    <c:param name="bookingDetailID" value="${cart.bookingDetailID}"></c:param>
                                                                    <c:param name="action" value="RemoveCartItem"></c:param>
                                                                </c:url>

                                                                <c:url var="EditCartItemPage" value="MainController">                              
                                                                    <c:param name="fieldID" value="${cart.field.fieldId}"></c:param>
                                                                    <c:param name="slotDetailID" value="${cart.slotDetail.slotDetailID}"></c:param>
                                                                    <c:param name="bookingDetailID" value="${cart.bookingDetailID}"></c:param>            
                                                                    <c:param name="playDate" value="${cart.playDate}"></c:param>
                                                                    <c:param name="action" value="EditCartItemPage"></c:param>
                                                                </c:url>

                                                                <tr>
                                                                    <td><img src="${cart.field.image}" height="200px" width="280px"/></td>
                                                                    <td>${cart.field.fieldName}</td>
                                                                    <td>${cart.slotDetail.slot.timeStart} - ${cart.slotDetail.slot.timeEnd}</td>
                                                                    <td>${cart.field.price}$</td>
                                                                    <td>${cart.playDate}</td>
                                                                    <td>
                                                                        <a href="#modalDelete${counter}" data-toggle="modal" data-target="#modalDelete${counter}" type="button" class="btn btn-primary btn-sm"><i class="fa fa-trash mr-2"></i>Delete</a>
                                                                        <div class="modal fade" id="modalDelete${counter}">
                                                                            <div class="modal-dialog" role="document">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header bg-warning">
                                                                                        <h3 class="modal-title">Xóa ${cart.field.fieldName}</h3>
                                                                                        <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                                                                                        </button>
                                                                                    </div>
                                                                                    <div class="modal-body" style="background-color: #f7f3e6">
                                                                                        <h3>Quý khách thực sự muốn xóa<span class="text-danger"> ${cart.field.fieldName} </span>khỏi giỏ sân đặt?</h3>
                                                                                    </div>
                                                                                    <div class="modal-footer" style="background-color: #f7f3e6">
                                                                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                                                                        <a href="${RemoveCartItem}" type="button" class="btn btn-success">Confirm</a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                        <a href="${EditCartItemPage}" type="button" class="btn btn-primary btn-sm"><i class="fa fa-pencil mr-2"></i>Edit</a> 
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>

                                                        </c:if>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                            <form action="MainController" method="POST">
                                                <div class="float-right">

                                                    <c:if test="${CART != null}">
                                                        <c:if test="${not empty CART}">
                                                            <button class="btn btn-primary btn-lg" type="submit" name="action" value="CheckOut"><i class="fa fa-credit-card mr-2"></i>Check Out</button>
                                                        </c:if>
                                                    </c:if>
                                                    <a href="MainController?action=Print&index=1" class="btn btn-primary btn-lg"><i class="fa fa-home mr-2"></i>Home</a>
                                                </div>
                                            </form>
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
    </body>
</html>
