<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="data:image/png;base64,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">
        <title>Edit Cart Item Page</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <!-- main css -->
        <link rel="stylesheet" href="css/homestyles.css">
        <link rel="stylesheet" href="css/homestyle.css">
        <link rel="stylesheet" href="css/addToCart.css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="vendor/star-rating/star-rating-svg.css">
        <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="css/responsive.css">

    </head>
    <body>
        <!--================Header Area =================-->
        <header class="">

            <nav class="navbar navbar-expand-lg navbar-light">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <a class="navbar-brand logo_h" href="MainController?action=Print"><img src="https://logopond.com/logos/18c31fb8cfe3ce15b964939a13c369a5.png" alt=""></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                        <ul class="nav navbar-nav menu_nav ml-auto">
                            <li class="nav-item active"><a class="nav-link" href="MainController?action=Print&index=1">Trang chủ</a></li>
                            <li class="nav-item"><a class="nav-link" href="MainController?action=ViewCart">Giỏ sân đặt(<c:if test="${CART == null or CART.getCart().size() == 0}">0</c:if><c:if test="${CART != null or CART.getCart().size() > 0}">${CART.getCart().size()}</c:if>)</a></li>
                                <li class="nav-item submenu dropdown">
                                <c:choose>
                                    <c:when test="${sessionScope.LOGIN_USER == null}">
                                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Đăng nhập</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.LOGIN_USER.fullName}</a>
                                        <ul class="dropdown-menu">
                                            <li class="nav-item"><a href="MainController?action=ProfileUser&id=${sessionScope.LOGIN_USER.userID}" class="nav-link">Hồ sơ</a></li>
                                            <li class="nav-item"><a href="MainController?action=SearchBooking&userID=${sessionScope.LOGIN_USER.userID}&search=&status=" class="nav-link">Lịch sử đặt</a></li>
                                            <li class="nav-item"><a href="MainController?action=Logout" class="nav-link">Log out</a></li>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                            </li> 
                        </ul>
                    </div> 
                </div>
            </nav>

        </header>

        <div>        
            <div style="margin-top: 20px">               
                <div class="container-fluid">
                    <!-- Add Project -->
                    <div class="row">                        
                        <form class="col-lg-12" action="MainController" method="POST">
                            <div class="card">
                                <h2 style="margin-left: 600px; margin-top:10px" >Thông tin Sân đặt</h2>
                                <div class="card-body">
                                    <c:if test="${FIELD == null}">
                                        <img style="margin-left: 420px; width: 500px; height: 500px" src="https://static.vecteezy.com/system/resources/previews/005/006/031/original/no-result-data-document-or-file-not-found-concept-illustration-flat-design-eps10-modern-graphic-element-for-landing-page-empty-state-ui-infographic-icon-etc-vector.jpg"> 
                                    </c:if>
                                    <c:if test="${FIELD != null}">
                                        <div class="row">
                                            <div class="col-xl-3 col-lg-6  col-md-6 col-xxl-5 ">
                                                <!-- Tab panes -->
                                                <div class="tab-content">
                                                    <div role="tabpanel" class="tab-pane fade show active" id="first">
                                                        <img class="img-fluid" src="${FIELD.image}" alt="">
                                                    </div>
                                                </div>
                                            </div>

                                            <!--Tab slider End-->
                                            <div class="col-xl-9 col-lg-6  col-md-6 col-xxl-7 col-sm-12">
                                                <div class="product-detail-content">
                                                    <!--Product details-->
                                                    <div class="new-arrival-content pr">
                                                        <h4>${FIELD.fieldName}</h4>
                                                        <div class="d-table mb-2">
                                                            <p class="price float-left d-block">${FIELD.price}</p>
                                                        </div>

                                                        <p>Field code: <span class="item">${FIELD.fieldId}</span> </p>
                                                        <p>Owner field: <span class="item">${FIELD.user.fullName}</span></p>
                                                        <p class="text-content">${FIELD.description}</p>
                                                        <div class="filtaring-area my-3">
                                                            <div class="size-filter">
                                                                <c:if test="${LIST_SLOT_DETAIL != null}">
                                                                    <c:if test="${not empty LIST_SLOT_DETAIL}">
                                                                        <h3 class="m-b-15">Select time</h3>
                                                                        <div class="btn-group" data-toggle="buttons">


                                                                            <c:forEach var="list" items="${LIST_SLOT_DETAIL}">
                                                                                <c:if test="${param.slotDetailID ne list.slotDetailID}">
                                                                                    <label class="btn btn-outline-primary light btn-sm">
                                                                                        <input type="radio" class="position-absolute invisible" name="slotDetailID" value="${list.slotDetailID}"> ${list.slot.timeStart} - ${list.slot.timeEnd}
                                                                                    </label>
                                                                                </c:if>

                                                                                <c:if test="${param.slotDetailID eq list.slotDetailID}">
                                                                                    <label class="border-dark btn light btn-sm disabled bg-success btn-outline-light">
                                                                                        <input type="radio" class="position-absolute" name="slotDetailID" value="${list.slotDetailID}"> ${list.slot.timeStart} - ${list.slot.timeEnd}
                                                                                    </label>
                                                                                </c:if>    

                                                                            </c:forEach>
                                                                        </c:if> 
                                                                    </c:if> 
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!--Quantity start-->
                                                        <div class="col-2 px-0">
                                                            <h3>Date:</h3>
                                                            <input type="date" id="datePicker" name="playDate" value="${param.playDate}" required="">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <input type="hidden" name="fieldID" value="${FIELD.fieldId}">
                                    <input type="hidden" name="bookingDetailID" value="${param.bookingDetailID}">
                                    <div class="shopping-cart mt-3 float-right">
                                        <button class="btn btn-primary btn-lg" type="submit" name="action" value="EditCartItem"><i class="fa fa-shopping-basket mr-2"></i>Edit</button>
                                        <a href="MainController?action=ViewCart" class="btn btn-primary btn-lg" >Back</button></a>
                                    </div>


                                </div>
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            document.getElementById("datePicker").min = getDateMin();
            document.getElementById("datePicker").max = getDateMax();
            function getDateMin() {
                var now = new Date();
                now.setDate(new Date().getDate() + 1);
                var day = ("0" + now.getDate()).slice(-2);
                var month = ("0" + (now.getMonth() + 1)).slice(-2);
                var today = now.getFullYear() + "-" + (month) + "-" + (day);
                return today;
            }
            function getDateMax() {
                var now = new Date();
                now.setDate(new Date().getDate() + 7);
                var day = ("0" + now.getDate()).slice(-2);
                var month = ("0" + (now.getMonth() + 1)).slice(-2);
                var today = now.getFullYear() + "-" + (month) + "-" + (day);
                return today;
            }
        });
    </script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>
    <script src="vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
    <!-- Rating -->
    <script src="js/deznav-init.js"></script>
    <script src="js/demo.js"></script>
</body>
</html>