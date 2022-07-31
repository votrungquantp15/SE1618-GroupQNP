<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="data:image/png;base64,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">
    <title>Home Detail</title>
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
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
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
                                <li class="nav-item"><a class="nav-link" href="MainController?action=LoginPage">Giỏ hàng</a></li>
                                </c:when>
                                <c:otherwise>
                                <li class="nav-item"><a class="nav-link" href="MainController?action=ViewCart">Giỏ sân đặt(<c:if test="${CART == null or CART.getCart().size() == 0}">0</c:if><c:if test="${CART != null or CART.getCart().size() > 0}">${CART.getCart().size()}</c:if>)</a></li>
                                </c:otherwise>
                            </c:choose>
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
                                        <li class="nav-item"><a href="MainController?action=Logout" class="nav-link">Đăng xuất</a></li>
                                    </ul>
                                </c:otherwise>
                            </c:choose>
                        </li> 
                    </ul>
                </div> 
            </nav>
        </div>
    </header>
    <!--================Header Area =================-->

    <!--================Breadcrumb Area =================-->
    <section class="breadcrumb_area">
        <div class="overlay bg-parallax" data-stellar-ratio="0.8" data-stellar-vertical-offset="0" data-background=""></div>
        <div class="container">
            <div class="page-cover breadcrumb-text text-center">
                <h2 class="page-cover-tittle">CHÀO MỪNG BẠN ĐẾN VỚI FBS</h2>
                <h3 style="color: wheat;">Khám phá sân bóng yêu thích của bạn với chúng tôi!</h3>
                <ol class="breadcrumb">
                    <c:choose>
                        <c:when test="${sessionScope.LOGIN_USER == null}">
                            <li><a href="HomeShowFieldController?index=1">Trang chủ</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="MainController?action=Print&index=1">Trang chủ</a></li>
                            </c:otherwise>
                        </c:choose>

                    <li class="active">Chi tiết</li>
                </ol> 
            </div>
        </div>
    </section>

    <!--================ Accomodation Area  =================-->

    <div class="content-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <c:if test="${requestScope.FIELD_DETAIL == null}">
                                    <strong>Không có kết quả</strong>
                                </c:if>
                                <c:if test="${requestScope.FIELD_DETAIL != null}">
                                    <c:if test="${not empty requestScope.FIELD_DETAIL}">
                                        <div class="col-xl-6 col-lg-6  col-md-6 col-xxl-5 ">
                                            <img class="img-fluid" src="${requestScope.FIELD_DETAIL.image}" alt="">
                                        </div>

                                        <div class="col-xl-6 col-lg-6  col-md-6 col-xxl-7 col-sm-12">
                                            <div class="product-detail-content">
                                                <!--Product details-->
                                                <div style="color: black" class="new-arrival-content pr row">
                                                    <div class="col-12 col-md-12">
                                                        <div class="card-header">
                                                            <h3><strong>Sân bóng</strong></h3>
                                                        </div>
                                                        <div class="table">
                                                            <table>
                                                                <tr>
                                                                    <th>Tên sân:</th>
                                                                    <th class="col-10"><p>${requestScope.FIELD_DETAIL.fieldName}</p></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Mô tả:</th>
                                                                    <th><p>${requestScope.FIELD_DETAIL.description}</p></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Loại sân:</th>
                                                                    <th><p>${requestScope.FIELD_DETAIL.fieldCate.fieldCateName}</p></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Chủ sân:</th>
                                                                    <th><p>${requestScope.FIELD_DETAIL.user.fullName}</p></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Địa chỉ:</th>
                                                                    <th><p>${requestScope.FIELD_DETAIL.location.locationName}</p></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Dịch vụ:</th>
                                                                    <th><p><a href="MainController?action=ViewFoodOfField&fieldId=${requestScope.FIELD_DETAIL.fieldId}&index=1">Nhấp chuột để xem</a></p></th>
                                                                </tr>
                                                            </table>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xl-6 col-lg-6  col-md-6 mt-10 border">
                                            <h2 style="color: black">Đánh giá</h2>
                                            <c:if test="${not empty requestScope.LIST_FEEDBACK}">
                                                <c:forEach var="feedback" items="${requestScope.LIST_FEEDBACK}" varStatus="counter">
                                                    <div class="card-body border border-dark mb-1">
                                                        <div class="row">
                                                            <h4 style="font-weight: bold; color: black" class="col-12">${feedback.user.fullName}</h4>
                                                        </div>
                                                        <p>${feedback.content}</p>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                            <form action="MainController" method="POST">
                                                <div class="card border border-dark mb-2 mt-1">
                                                    <h4 class="card-header" style="color: black">Nội dung:</h4>
                                                    <textarea class="form-control" id="exampleFormControlTextarea4" name="content" rows="3" placeholder="Nhập nội dung đánh giá ở đây..." required=""></textarea>
                                                    <p style="color: red">${requestScope.CREATE_CONTENT_ERROR}</p>
                                                    <div class="d-flex justify-content-end">
                                                        <c:choose>
                                                            <c:when test="${sessionScope.LOGIN_USER == null}">
                                                                <button type="submit" name="action" value="LoginPage" class="btn btn-info mr-2">Gửi feedback</button>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="hidden" name="fieldId" value="${requestScope.FIELD_DETAIL.fieldId}"/>
                                                                <input type="hidden" name="action" value="CreateFeedback"/>
                                                                <button type="submit" class="btn btn-info mr-2">Gửi feedback</button>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <div class="ml-2">
                                                        <p style="color: green">${requestScope.CREATE_SUCCESS} </p>
                                                        <p style="color: red">${requestScope.CREATE_UNSUCCESS} </p>
                                                    </div>
                                                </div>
                                            </form>
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

    <!--================ Accomodation Area  =================-->
    <!--================ start footer Area  =================-->	
    <footer class="footer-area section_gap">
        <div class="container">
            <div class="row">
                <div class="col-lg-3  col-md-6 col-sm-6">
                    <div class="single-footer-widget">
                        <h6 class="footer_title">About Agency</h6>
                        <p>The world has become so fast paced that people don't want to stand by reading a page of information, they would much rather look at a presentation and understand the message. It has come to a point </p>
                    </div>
                </div>					
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <ul>
                        <li><span class="icon fa fa-map-marker"></span><span class="text"> 203 Fake St. Mountain View, San Francisco, California, USA</span></li>
                        <li><a href="#"><span class="icon fa fa-phone"></span><span class="text"> +2 392 3929 210</span></a></li>
                        <li><a href="#"><span class="icon fa fa-paper-plane"></span><span class="text"> info@yourdomain.com</span></a></li>
                    </ul>
                </div>	
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="single-footer-widget">
                        <h6 class="footer_title">Newsletter</h6>
                        <p>For business professionals caught between high OEM price and mediocre print and graphic output, </p>		
                        <div id="mc_embed_signup">
                            <form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01" method="get" class="subscribe_form relative">
                                <div class="input-group d-flex flex-row">
                                    <input name="EMAIL" placeholder="Email Address" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email Address '" required="" type="email">
                                    <button class="btn sub-btn"><span class="lnr lnr-location"></span></button>		
                                </div>									
                                <div class="mt-10 info"></div>
                            </form>
                        </div>
                    </div>
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