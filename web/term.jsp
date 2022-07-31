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

    <section class="accomodation_area section_gap">
        <div>
            <section class="site-section fight-card">
                <div class="container moreone">
                    <br/>
                    <br/>
                    <h2 class="section-header section-header-events">ĐIỀU KHOẢN SỬ DỤNG</h2>
                    <br>
                    Đây là các điều khoản và điều kiện (“Điều kiện”), áp dụng cho việc bạn (“Người dùng”) sử dụng Trang web và Ứng dụng. Bằng cách truy cập vào bất kỳ phần nào (bao gồm các miền phụ) của Trang web, Ứng dụng hoặc bất kỳ trang web nào khác mà chúng tôi sở hữu và / hoặc đã đăng ký trên Trang web, bạn đồng ý bị ràng buộc bởi Điều kiện và Chính sách quyền riêng tư của chúng tôi và được coi là đã chấp nhận và hiểu tất cả các điều kiện.
                    <br>
                    Trang web và Ứng dụng được cung cấp vì lợi ích của Người dùng từ 18 tuổi trở lên. Nếu bạn không trên 18 tuổi hoặc không đồng ý bị ràng buộc bởi các Điều kiện, bạn không được sử dụng Trang web và Ứng dụng và chúng tôi sẽ có quyền hạn chế hoặc ngăn chặn quyền truy cập của bạn vào Trang web / Ứng dụng.
                    <br>
                    Vui lòng đọc kỹ Điều kiện và nếu bạn không chấp nhận Điều kiện, không được sử dụng Trang web và Ứng dụng.
                    <br>
                    Tham chiếu đến "bạn", "của bạn" hoặc "người dùng" / "Người dùng" là tham chiếu đến bất kỳ người nào sử dụng Trang web / Ứng dụng hoặc Dịch vụ của FBS.
                    <br>
                    <br>
                    <h3>ĐỐI TƯỢNG PHỤC VỤ</h3><br>
                    FBS cung cấp quyền truy cập vào nội dung cao cấp do dịch vụ của chúng tôi tạo ra thông qua Trang web và Ứng dụng dành cho thiết bị di động. Quyền truy cập dựa trên một mô hình đăng ký. Nội dung cao cấp bao gồm các dự đoán về các sự kiện thể thao trong tương lai và lời khuyên (mẹo) về cách thức và địa điểm đặt cược để giành chiến thắng.<br>
                    Thuật toán độc đáo của chúng tôi tạo ra nội dung dựa trên thống kê và phân tích tin tức.<br>
                    FBS không lấy tiền bằng cách đánh bạc. FBS không chấp nhận bất kỳ hình thức cờ bạc, cá cược hay đặt cược nào.<br>
                    FBS chỉ là một dịch vụ tư vấn. Mọi thông tin do FBS công bố chỉ nhằm mục đích cung cấp thông tin và giải trí.<br>
                    FBS không chịu trách nhiệm về bất kỳ chiến thắng, thua lỗ hoặc thiệt hại nào cho người dùng, dù trực tiếp hay gián tiếp, do sử dụng thông tin này để đánh bạc hoặc cá cược. <br>
                    <br>                   
                    <h3>TRUY CẬP ĐỊNH KỲ</h3>
                    Đăng ký của bạn sẽ tự động được kéo dài vào ngày cuối cùng của mỗi giai đoạn đăng ký. Bạn có thể chấm dứt gói đăng ký của mình bất kỳ lúc nào có hiệu lực khi kết thúc thời hạn đăng ký - trong trường hợp này, gói đăng ký của bạn sẽ không được kéo dài thêm nữa, bạn sẽ mất quyền truy cập vào nội dung cao cấp sau khi kết thúc thời gian đăng ký và chúng tôi sẽ không tính phí cho thời gian đăng ký mới. Để chấm dứt đăng ký của bạn, vui lòng đăng nhập vào tài khoản Cửa hàng Google Play hoặc Apple App Store, chọn “Tài khoản”, nhấp vào “Đăng ký”, chọn liên kết Đăng ký và “Hủy đăng ký”. <br>
                    Trong trường hợp đến ngày gia hạn đăng ký của bạn, chúng tôi không thể tính phí bạn do không đủ số tiền trong tài khoản hoặc thẻ tín dụng của bạn, chúng tôi sẽ cố gắng tính phí lại cho bạn trong 3 ngày tiếp theo và nếu chúng tôi vẫn không thể tính phí cho bạn, việc gia hạn đăng ký của bạn sẽ bị hủy bỏ (bạn không có nghĩa vụ phải trả tiền cho việc kéo dài đó). <br>
                    Người dùng có 14 ngày theo lịch để yêu cầu hoàn lại toàn bộ tiền kể từ ngày họ mua đăng ký. Điều này chỉ áp dụng cho đăng ký lần đầu tiên. <br>
                    Bất kỳ phần nào chưa sử dụng trong thời gian dùng thử miễn phí, nếu được cung cấp, sẽ bị mất khi bạn mua đăng ký ấn phẩm đó, nếu có. <br>
                    <br>
                    <h3> NGHĨA VỤ CỦA NGƯỜI DÙNG </h3>
                    <br>
                    Người dùng phải đọc, đồng ý và chấp nhận tất cả các Điều kiện và Chính sách Bảo mật trước khi trở thành Người dùng của FBS. <br>
                    Người dùng sẽ không: <br>
                    <b> i. </b> sử dụng Trang web / Ứng dụng (hoặc bất kỳ phần nào của nó) cho bất kỳ mục đích bất hợp pháp nào và đồng ý sử dụng nó theo tất cả các luật có liên quan; <br>
                    <b> ii. </b> tải lên hoặc truyền qua Trang web (i) bất kỳ vi rút máy tính nào, vi rút macro, trojan, sâu hoặc bất kỳ thứ gì khác được thiết kế để can thiệp, làm gián đoạn hoặc phá vỡ quy trình hoạt động bình thường của một máy tính hoặc (ii) bất kỳ tài liệu nào có tính chất phỉ báng, xúc phạm, hoặc có tính chất khiêu dâm hoặc đe dọa, hoặc có thể gây khó chịu, bất tiện hoặc lo lắng không cần thiết; <br>
                    <b> iii. </b> sử dụng Trang web / Ứng dụng theo cách (i) có thể khiến Trang web / Ứng dụng bị gián đoạn, hư hỏng, hiển thị kém hiệu quả hơn hoặc ảnh hưởng đến hiệu quả hoặc chức năng của Trang web / Ứng dụng bị suy giảm theo bất kỳ cách nào hoặc (ii) vi phạm hoặc vi phạm quyền của bất kỳ cá nhân, công ty hoặc công ty nào (bao gồm nhưng không giới hạn ở quyền sở hữu trí tuệ, quyền bảo mật hoặc quyền riêng tư); <br>
                    <b> iv. </b> tạo hoặc xuất bản liên kết siêu văn bản tới bất kỳ phần nào của Trang web hoặc cố gắng truy cập trái phép bất kỳ phần nào hoặc thành phần nào của Trang web. <br>
                    <b> v. </b> sao chép, nhân bản, sao chép, bán, bán lại hoặc khai thác bất kỳ phần nào của Trang web hoặc Ứng dụng mà không có sự cho phép rõ ràng bằng văn bản của FBS. <br>
                    <br>
                    Người dùng phải cung cấp tên hợp pháp, địa chỉ email hợp lệ và bất kỳ thông tin nào khác cần thiết để hoàn tất giao dịch mua và có trách nhiệm bảo mật mật khẩu của họ. FBS không thể và sẽ không chịu trách nhiệm về bất kỳ tổn thất hoặc thiệt hại nào do bạn không duy trì tính bảo mật của tài khoản và mật khẩu của mình. <br>
                    Người dùng không được phép chia sẻ hoặc tái tạo thông tin đã mua với bất kỳ ai. <br>
                    <br>
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