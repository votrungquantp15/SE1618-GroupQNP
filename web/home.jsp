<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Arizonia&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/homestyles.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="home.jsp">FBS<span>Booking football field</span></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a href="home.jsp" class="nav-link">Home</a></li>
                        <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
                    </ul>
                    <ul class="navbar-nav">
                        <c:choose>
                            <c:when test="${sessionScope.LOGIN_USER == null}">
                                <li class="nav-item active"><a href="login.jsp" class="nav-link">Login</a></li>
                                </c:when>
                                <c:otherwise>
                                <li class="nav-item dropdown ">
                                    <a href="#" data-toggle="dropdown">
                                        <div class="header-info">
                                            <span>${sessionScope.LOGIN_USER.fullName}</span>
                                        </div>
                                    </a>
                                    <div class="dropdown-menu">
                                        <a href="MainController?action=ProfileUser&id=${sessionScope.LOGIN_USER.userID}" class="dropdown-item ai-icon">
                                            <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary" width="18" height="18" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                                            <span class="ml-2">Profile</span>
                                        </a>
                                        <a href="MainController?action=SearchBooking&userID=${sessionScope.LOGIN_USER.userID}&search=&status=" class="dropdown-item ai-icon">
                                            <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary" width="18" height="18" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                                            <span class="ml-2">Booking History</span>
                                        </a>
                                        <a href="page-login.html" class="dropdown-item ai-icon">
                                            <a href="MainController?action=Logout" class="dropdown-item ai-icon">
                                                <svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger" width="18" height="18" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
                                                <span class="ml-2">Logout</span>
                                            </a>
                                    </div>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- END nav -->

        <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('https://wallpaper.dog/large/17048551.jpg');">
            <div class="overlay"></div>
            <div class="container">
                <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-center">
                    <div class="col-md-7 ftco-animate">
                        <span class="subheading" style="font-family: Arial, Helvetica, sans-serif;">Welcome to FBS</span>
                        <h1 class="mb-4">Discover Your Favorite Football Field with Us</h1>
                    </div>
                    <div class="col-md-9 ftco-animate pb-5 text-center">
                        <h1 class="mb-0 bread">Home</h1>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-section ftco-no-pb">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="search-wrap-1 ftco-animate">
                            <form action="#" class="search-property-1">
                                <div class="row no-gutters">
                                    <div class="col-lg d-flex">
                                        <div class="form-group p-4 border-0">
                                            <label for="#">Name</label>
                                            <div class="form-field">
                                                <div class="icon"><span class="fa fa-search"></span></div>
                                                <input type="text" class="form-control" name="name" value="${param.name}" placeholder="Nhập tên sân">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg d-flex">
                                        <div class="form-group p-4">
                                            <label for="#">City</label>
                                            <div class="form-field">
                                                <div class="icon"><span class="fa fa-search"></span></div>
                                                <input type="text" class="form-control" placeholder="Search city">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg d-flex">
                                        <div class="form-group p-4">
                                            <label for="#">Price Limit</label>
                                            <div class="form-field">
                                                <div class="select-wrap">
                                                    <div class="icon"><span class="fa fa-chevron-down"></span></div>
                                                    <select name="" id="" class="form-control">
                                                        <option value="">$5,000</option>
                                                        <option value="">$10,000</option>
                                                        <option value="">$50,000</option>
                                                        <option value="">$100,000</option>
                                                        <option value="">$200,000</option>
                                                        <option value="">$300,000</option>
                                                        <option value="">$400,000</option>
                                                        <option value="">$500,000</option>
                                                        <option value="">$600,000</option>
                                                        <option value="">$700,000</option>
                                                        <option value="">$800,000</option>
                                                        <option value="">$900,000</option>
                                                        <option value="">$1,000,000</option>
                                                        <option value="">$2,000,000</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg d-flex">
                                        <div class="form-group d-flex w-100 border-0">
                                            <div class="form-field w-100 justify-content-center d-flex">
                                                <input type="hidden" name="action" value="SearchFieldByName"/>
                                                <input type="submit" value="Search" class="align-self-stretch form-control btn btn-primary">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <section class="ftco-section" style="background-color: ghostwhite">
            <div class="container">
                <div class="row">
                    <c:forEach var="field" items="${requestScope.FIELD}">
                        <div class="col-md-4 ftco-animate">
                            <div class="project-wrap hotel">
                                <a href="#" class="img" style="background-image: url(${field.image});">
                                    <span class="price">${field.price}00vnd/h</span>
                                </a>
                                <div class="card-body text p-3">
                                    <p class="star mb-2">
                                        <span class="fa fa-star"></span>
                                        <span class="fa fa-star"></span>
                                        <span class="fa fa-star"></span>
                                        <span class="fa fa-star"></span>
                                        <span class="fa fa-star"></span>
                                    </p>
                                    <span class="days">${field.city.cityName}</span>
                                    <h3><a href="#">${field.fieldName}</a></h3>
                                    <p class="location"><span class="fa fa-map-marker"></span> ${field.location.locationName}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="row mt-5">
                    <div class="col text-center">
                        <div class="block-27">
                            <ul>
                                <li><a href="#">&lt;</a></li>
                                <li class="active"><span>1</span></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">&gt;</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <footer class="ftco-footer bg-bottom ftco-no-pt" style="background-image: url(homeimages/bg_3.jpg);">
            <div class="container">
                <div class="row mb-5">
                    <div class="col-md pt-5">
                        <div class="ftco-footer-widget pt-md-5 mb-4">
                            <h2 class="ftco-heading-2">About</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                            <ul class="ftco-footer-social list-unstyled float-md-left float-lft">
                                <li class="ftco-animate"><a href="#"><span class="fa fa-twitter"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="fa fa-facebook"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="fa fa-instagram"></span></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md pt-5 border-left">
                        <div class="ftco-footer-widget pt-md-5 mb-4 ml-md-5">
                            <h2 class="ftco-heading-2">Infromation</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Online Enquiry</a></li>
                                <li><a href="#" class="py-2 d-block">General Enquiries</a></li>
                                <li><a href="#" class="py-2 d-block">Booking Conditions</a></li>
                                <li><a href="#" class="py-2 d-block">Privacy and Policy</a></li>
                                <li><a href="#" class="py-2 d-block">Refund Policy</a></li>
                                <li><a href="#" class="py-2 d-block">Call Us</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md pt-5 border-left">
                        <div class="ftco-footer-widget pt-md-5 mb-4">
                            <h2 class="ftco-heading-2">Experience</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Adventure</a></li>
                                <li><a href="#" class="py-2 d-block">Hotel and Restaurant</a></li>
                                <li><a href="#" class="py-2 d-block">Beach</a></li>
                                <li><a href="#" class="py-2 d-block">Nature</a></li>
                                <li><a href="#" class="py-2 d-block">Camping</a></li>
                                <li><a href="#" class="py-2 d-block">Party</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md pt-5 border-left">
                        <div class="ftco-footer-widget pt-md-5 mb-4">
                            <h2 class="ftco-heading-2">Have a Questions?</h2>
                            <div class="block-23 mb-3">
                                <ul>
                                    <li><span class="icon fa fa-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
                                    <li><a href="#"><span class="icon fa fa-phone"></span><span class="text">+2 392 3929 210</span></a></li>
                                    <li><a href="#"><span class="icon fa fa-paper-plane"></span><span class="text">info@yourdomain.com</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>

        <script src="homejs/jquery.min.js"></script>
        <script src="homejs/jquery-migrate-3.0.1.min.js"></script>
        <script src="homejs/popper.min.js"></script>
        <script src="homejs/bootstrap.min.js"></script>
        <script src="homejs/jquery.easing.1.3.js"></script>
        <script src="homejs/jquery.waypoints.min.js"></script>
        <script src="homejs/jquery.stellar.min.js"></script>
        <script src="homejs/owl.carousel.min.js"></script>
        <script src="homejs/jquery.magnific-popup.min.js"></script>
        <script src="homejs/jquery.animateNumber.min.js"></script>
        <script src="homejs/bootstrap-datepicker.js"></script>
        <script src="homejs/scrollax.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="homejs/google-map.js"></script>
        <script src="homejs/main.js"></script>

    </body>
</html>