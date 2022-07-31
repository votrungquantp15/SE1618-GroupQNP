<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="data:image/png;base64,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">
    <title>Home</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="vendors/linericon/style.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
    <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
    <!-- main css -->
    <link rel="stylesheet" href="css/homestyle.css">
    <link rel="stylesheet" href="css/responsive.css">
</head>
<body>
    <!--================Header Area =================-->
    <header class="header_area">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <c:choose>
                    <c:when test="${sessionScope.LOGIN_USER == null}">
                        <a class="navbar-brand logo_h" href="HomeShowFieldController?index=1"><img src="https://logopond.com/logos/18c31fb8cfe3ce15b964939a13c369a5.png" alt=""></a>
                        </c:when>
                        <c:otherwise>
                        <a class="navbar-brand logo_h" href="MainController?action=Print&index=1"><img src="https://logopond.com/logos/18c31fb8cfe3ce15b964939a13c369a5.png" alt=""></a>
                        </c:otherwise>
                    </c:choose>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto">
                        <c:choose>
                            <c:when test="${sessionScope.LOGIN_USER == null}">
                                <li class="nav-item active"><a class="nav-link" href="HomeShowFieldController?index=1">Trang chủ</a></li>
                                </c:when>
                                <c:otherwise>
                                <li class="nav-item active"><a class="nav-link" href="MainController?action=Print&index=1">Trang chủ</a></li>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${sessionScope.LOGIN_USER == null}">
                                <li class="nav-item"><a class="nav-link" href="MainController?action=LoginPage">Giỏ sân đặt</a></li>
                                </c:when>
                                <c:otherwise>
                                <li class="nav-item"><a class="nav-link" href="MainController?action=ViewCart">Giỏ sân đặt(<c:if test="${CART == null or CART.getCart().size() == 0}">0</c:if><c:if test="${CART != null or CART.getCart().size() > 0}">${CART.getCart().size()}</c:if>)</a></li>
                                </c:otherwise>
                            </c:choose>
                        <li class="nav-item submenu dropdown">
                            <c:choose>
                                <c:when test="${sessionScope.LOGIN_USER == null}">
                                    <a href="login.jsp" class="nav-link">Đăng nhập</a>
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
            </div>
        </nav>
    </header>
    <!--================Header Area =================-->

    <!--================Breadcrumb Area =================-->
    <section class="breadcrumb_area">
        <div class="overlay bg-parallax" data-stellar-ratio="0.8" data-stellar-vertical-offset="0" data-background=""></div>
        <div class="container">
            <div class="page-cover breadcrumb-text text-center">
                <h2 class="page-cover-tittle">CHÀO MỪNG BẠN ĐẾN VỚI FBS</h2>
                <h3 style="color: wheat;">Khám phá sân bóng yêu thích của bạn với chúng tôi!</h3>
            </div>
        </div>
    </section>
    <!--================Breadcrumb Area =================-->

    <!--================ Accomodation Area  =================-->

    <section class="hotel_booking_area">

        <div class="hotel_booking_table">
            <div class="container">
                <div class="row">
                <div class="col-md-3">
                    <br>
                    <h2>TÌM SÂN BÓNG</h2>
                </div>

                <div class="col-md-9">
                    <div class="boking_table">
                        <form action="MainController">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="book_tabel_item">
                                        <div class="form-group">
                                            <div class='input-group'>
                                                <input name="fieldName" value="${param.fieldName}" style="color: white;" type='text' class="form-control" placeholder="Nhập tên cần tìm"/>
                                            </div>
                                        </div>
                                        <div class="input-group">
                                            <select name ="districtId" class="wide selectpicker mh" data-live-search="true">
                                                <c:choose>
                                                    <c:when test="${requestScope.DISTRICT == null}">
                                                        <option selected value="">Chọn quận cần tìm</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option selected value="">${requestScope.DISTRICT.districtName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:forEach var="district" items="${requestScope.LIST_DISTRICT}">
                                                    <option value="${district.districtId}">${district.districtName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="book_tabel_item">
                                        <!-- <a class="book_now_btn button_hover" href="#">Search</a> -->
                                        <div class="form-field w-100 justify-content-center d-flex">
                                            <br>
                                            <input type="hidden" name="index" value="1"/>
                                            <input type="hidden" name="action" value="SearchFieldByUser"/>
                                            
                                        </div>
                                        <input type="submit" value="Tìm kiếm" class="align-self-stretch form-control btn btn-primary">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<!--================Booking Tabel Area  =================-->
<!--================ Accomodation Area  =================-->
<section class="accomodation_area section_gap">
    <div class="container">
        <div class="section_title text-center">
            <h2 class="title_color">Sân bóng đá</h2>
            <p>"Chọn đúng thời gian, sự bền bỉ và mười năm nỗ lực rồi cuối cùng sẽ khiến bạn có vẻ như thành công chỉ trong một đêm"</p>
        </div>
        <div class="row accomodation_two">
            <h4 style="color: red"> ${requestScope.FIELD_NOT_FOUND} </h4>
            <c:forEach var="field" items="${requestScope.FIELD}">
                <div class="col-lg-4 col-sm-6">
                    <div class="accomodation_item text-center">
                        <div class="hotel_img">
                            <img class="img_field" src="${field.image}" alt="">
                            <form action="MainController" method="POST">
                                <input name="fieldID" value="${field.fieldId}" type="hidden">

                                <c:choose>
                                    <c:when test="${sessionScope.LOGIN_USER == null}">
                                        <button type="submit" name="action" value="LoginPage" class="btn theme_btn button_hover rounded">Đặt ngay</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" name="action" value="Booking" class="btn theme_btn button_hover rounded">Đặt ngay</button>
                                    </c:otherwise>
                                </c:choose>
                            </form>

                        </div>
                        <a href="MainController?action=UserPrintFieldDetail&fieldId=${field.fieldId}"><h4 class="sec_h4">${field.fieldName}</h4></a>
                        <h6>${field.district.districtName}</h6>
                        <h5>${field.price}00<small> vnd/h</small></h5>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:choose>
            <c:when test="${(sessionScope.LOGIN_USER == null)}">
                <ul class="pagination justify-content-center mt-2">
                    <c:choose>
                        <c:when test="${sessionScope.ACTION_FIELD == 'Print'}">
                            <c:forEach var="i" begin="1" end="${END_PAGE}">
                                <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                    <a href="HomeShowFieldController?index=${i}" class="page-link">${i}</a>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:when test="${sessionScope.ACTION_FIELD == 'Search'}">
                            <c:forEach var="i" begin="1" end="${END_PAGE}">
                                <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                    <a href="MainController?action=SearchFieldByUser&index=${i}&fieldName=${requestScope.FIELD_NAME}&districtId=${requestScope.DISTRICT_ID}" class="page-link">${i}</a>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                </ul>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${(requestScope.FIELD != null)}">
                        <ul class="pagination justify-content-center mt-2">
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
                                            <a href="MainController?action=SearchFieldByUser&index=${i}&fieldName=${requestScope.FIELD_NAME}&districtId=${requestScope.DISTRICT_ID}" class="page-link">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </ul>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>
</section>
<!--================ Accomodation Area  =================-->
<!--================ start footer Area  =================-->	
<footer class="footer-area section_gap">
    <div class="container">
        <div class="row">
            <div class="col-lg-3  col-md-6 col-sm-6">
                <div class="single-footer-widget">
                    <h6 class="footer_title">Về chúng tôi</h6>
                    <p>Chúng tôi mong muốn mang đến cho bạn một trải nghiệm tuyệt vời nhất bằng tất cả khả năng.</p>
                </div>
            </div>
            <div class="col-lg-3  col-md-6 col-sm-6">
                <div class="single-footer-widget">
                    <h6 class="footer_title">Chính sách</h6>
                    <p><a href="policy.jsp">Nhấp chuột để xem</a></p>
                </div>
            </div>
            <div class="col-lg-3  col-md-6 col-sm-6">
                <div class="single-footer-widget">
                    <h6 class="footer_title">Điều khoản</h6>
                    <p><a href="term.jsp">Nhấp chuột để xem</a></p>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <ul>
                    <li><span class="icon fa fa-map-marker"></span><span class="text"> Lô E2a-7, Đường D1, Khu Công nghệ cao, P.Long Thạnh Mỹ, Tp. Thủ Đức, TP.HCM.</span></li>
                    <li><a href="#"><span class="icon fa fa-phone"></span><span class="text"> +84 938 736 555</span></a></li>
                    <li><a href="#"><span class="icon fa fa-paper-plane"></span><span class="text"> DN1@gmail.com</span></a></li>
                </ul>
            </div>				
        </div>
        <div class="border_line"></div>
        <div class="row footer-bottom d-flex justify-content-between align-items-center">
            <p class="col-lg-8 col-sm-12 footer-text m-0"></p>
            <div class="col-lg-4 col-sm-12 footer-social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-instagram"></i></a>
            </div>
        </div>
    </div>
</footer>
<!--================ End footer Area  =================-->


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
<script src="js/jquery.ajaxchimp.min.js"></script>
<script src="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="vendors/nice-select/js/jquery.nice-select.js"></script>
<script src="js/mail-script.js"></script>
<script src="js/stellar.js"></script>
<script src="vendors/lightbox/simpleLightbox.min.js"></script>
<script src="js/custom.js"></script>
</body>
</html>