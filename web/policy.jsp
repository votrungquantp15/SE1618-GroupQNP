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
                                <li class="nav-item"><a class="nav-link" href="MainController?action=ViewCart">Giỏ hàng</a></li>
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
            </nav>
    </header>

    <section class="accomodation_area section_gap">
        <div>
            <section class="site-section fight-card">
                <div class="container moreone">
                    <br/>
                    <br/>
                    <h2 class="section-header section-header-events">Chính sách</h2>
                    <br>
                    Chính sách bảo mật này đã được biên soạn để phục vụ cho những người quan tâm đến cách thức mà thông tin của họ được sử dụng tại trang web của chúng tôi. Vui lòng đọc kỹ chính sách bảo mật của chúng tôi để hiểu rõ về cách chúng tôi thu thập, sử dụng, bảo vệ hoặc xử lý thông tin cá nhân của bạn.<br>
                    <br>
                    <h3>Chúng tôi thu thập thông tin cá nhân nào từ những người truy cập trang web hoặc ứng dụng của chúng tôi?</h3>
                    Khi đặt sân trên ứng dụng của chúng tôi, bạn có thể được yêu cầu nhập tên, địa chỉ email hoặc các chi tiết khác để giúp bạn trải nghiệm dịch vụ một cách tốt hơn.<br>
                    <br>
                    <h3>Chúng tôi thu thập thông tin của bạn khi nào?</h3>
                    Chúng tôi thu thập thông tin từ bạn khi bạn đặt hàng hoặc nhập thông tin trên ứng dụng của chúng tôi.<br>
                    <br>
                    <h3>Chúng tôi sử dụng thông tin cá nhân của bạn như thế nào?</h3>
                    Chúng tôi có thể sử dụng thông tin chúng tôi thu thập từ bạn khi bạn mua hàng, trả lời khảo sát hoặc giao tiếp tiếp thị, duyệt trang web hoặc ứng dụng hoặc sử dụng một số tính năng của trang web / ứng dụng khác vì mục đích:<br><br>
                    <b>•</b> Cải thiện trang web và ứng dụng của chúng tôi nhằm phục vụ bạn tốt hơn.<br>
                    <b>•</b> Cho phép chúng tôi phục vụ bạn tốt hơn trong việc đáp ứng các yêu cầu dịch vụ khách hàng của bạn.<br>
                    <b>•</b> Nhanh chóng xử lý các giao dịch của bạn.<br>
                    <br>
                    <h3>Chúng tôi bảo vệ thông tin cá nhân của bạn như thế nào?</h3>
                    Trang web của chúng tôi được quét thường xuyên để tìm các lỗ hổng bảo mật và các lỗ hổng bảo mật đã biết để giúp bạn truy cập trang web của chúng tôi an toàn nhất có thể.<br>
                    Chúng tôi liên tục sử dụng Malware Scanning.<br>
                    Thông tin cá nhân của bạn được chứa trong các mạng bảo mật và chỉ một số hạn chế người có quyền truy cập đặc biệt vào các hệ thống đó mới có thể truy cập được và được yêu cầu giữ bí mật thông tin. Ngoài ra, tất cả thông tin nhạy cảm / thẻ tín dụng mà bạn cung cấp đều được mã hóa thông qua công nghệ Lớp cổng bảo mật (SSL). <br>
                    Chúng tôi thực hiện nhiều biện pháp bảo mật khi người dùng đặt hàng, nhập, gửi hoặc truy cập thông tin của họ để duy trì sự an toàn cho thông tin cá nhân của bạn.<br>
                    Tất cả các giao dịch được xử lý thông qua nhà cung cấp cổng và không được lưu trữ hoặc xử lý trên máy chủ của chúng tôi. <br>
                    <br>
                    <h3>Thay đổi</h3>
                    Chúng tôi có thể cập nhật chính sách bảo mật này theo thời gian để phản ánh, ví dụ: những thay đổi đối với hoạt động của chúng tôi hoặc vì các lý do hoạt động, pháp lý hoặc quy định khác.<br>
                    Bạn sẽ được thông báo về bất kỳ thay đổi Chính sách Quyền riêng tư nào trên trang 'Chính sách'.<br>                   
                    <br>                   
                    FBS<br>
                    Chỉnh sửa lần cuối vào ngày 10 tháng 7 năm 2022
                    <br/>
                    <br/>
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