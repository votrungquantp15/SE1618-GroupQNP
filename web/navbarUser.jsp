<%-- 
    Document   : navbarAdmin
    Created on : 01-Jun-2022, 13:24:48
    Author     : votru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="nav-header">
    <a href="MainController?action=Print&index=1" class="brand-logo">
        <img src="https://logopond.com/logos/bad1f126aa10bfff3580048444bb8b85.png" alt="" style="height: 95%; width: 60%;" class="rounded mx-auto d-block"/>
    </a>
</div>
<!--**********************************
    Nav header end
***********************************-->

<!--**********************************
Chat box start
***********************************-->
<!--**********************************
Chat box End
***********************************-->



<!--**********************************
    Header start
***********************************-->
<div class="header">
    <div class="header-content">
        <nav class="navbar navbar-expand">
            <div class="collapse navbar-collapse justify-content-between">
                <div class="header-left">
                    <div class="input-group search-area right d-lg-inline-flex d-none">
                        </span>
                    </div>
                </div>
            </div>
            <ul class="navbar-nav header-right main-notification">
                <li class="nav-item dropdown notification_dropdown">
                    <a class="nav-link bell dz-fullscreen" href="#">
                        <svg id="icon-full" viewbox="0 0 24 24" width="20" height="20" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="css-i6dzq1"><path d="M8 3H5a2 2 0 0 0-2 2v3m18 0V5a2 2 0 0 0-2-2h-3m0 18h3a2 2 0 0 0 2-2v-3M3 16v3a2 2 0 0 0 2 2h3" style="stroke-dasharray: 37, 57; stroke-dashoffset: 0;"></path></svg>
                        <svg id="icon-minimize" width="20" height="20" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-minimize"><path d="M8 3v3a2 2 0 0 1-2 2H3m18 0h-3a2 2 0 0 1-2-2V3m0 18v-3a2 2 0 0 1 2-2h3M3 16h3a2 2 0 0 1 2 2v3" style="stroke-dasharray: 37, 57; stroke-dashoffset: 0;"></path></svg>
                    </a>
                </li>				
                <li class="nav-item dropdown header-profile">
                    <a class="nav-link" href="#" role="button" data-toggle="dropdown">
                        <img src="https://i.pinimg.com/736x/89/90/48/899048ab0cc455154006fdb9676964b3.jpg" width="20" alt="">
                        <div class="header-info">
                            <span>${sessionScope.LOGIN_USER.fullName}</span>
                        </div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a href="MainController?action=ProfileUser&id=${sessionScope.LOGIN_USER.userID}" class="dropdown-item ai-icon">
                            <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary" width="18" height="18" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                            <span class="ml-2">Hồ sơ</span>
                        </a>
                        <a href="MainController?action=SearchBooking&userID=${sessionScope.LOGIN_USER.userID}&index=1&status=" class="dropdown-item ai-icon">
                            <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary" width="18" height="18" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                            <span class="ml-2">Lịch sử đặt</span>
                        </a>
                        <a href="MainController?action=ViewCart" class="dropdown-item ai-icon">
                            <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary" width="18" height="18" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                            <span class="ml-2">Giỏ hàng</span>
                        </a>
                        <a href="MainController?action=Logout" class="dropdown-item ai-icon">
                            <svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger" width="18" height="18" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
                            <span class="ml-2">Đăng xuất</span>
                        </a>
                    </div>
                </li>
            </ul>
        </nav>
    </div>
</div>

<!--**********************************
    Header end ti-comment-alt
***********************************-->

<!--**********************************
    Sidebar start
***********************************-->
<div class="deznav">
    <div class="deznav-scroll">
        <div class="main-profile">
            <div class="image-bx">
                <img src="https://i.pinimg.com/736x/89/90/48/899048ab0cc455154006fdb9676964b3.jpg" alt="">
                <a href="javascript:void(0);"><i class="fa fa-cog" aria-hidden="true"></i></a>
            </div>
            <h5 class="name"><span class="font-w400">Hello, ${sessionScope.LOGIN_USER.fullName}</span></h5>
            <p><strong>[Role: User]</strong></p>
        </div>
        <ul class="metismenu" id="menu">
            <li class="nav-label first">Menu</li>
            <li><a href="MainController?action=ProfileUser&id=${sessionScope.LOGIN_USER.userID}">Hồ sơ</a></li>
            <li><a href="MainController?action=PrintFeedback&index=1">Phản hồi</a></li>
            <li class="active"><a href="MainController?action=SearchBooking&userID=${sessionScope.LOGIN_USER.userID}&index=1&status=">Lịch sử đặt</a></li>
            <li class="active"><a href="MainController?action=ViewCart">Giỏ hàng</a></li>
        </ul>
    </div>
</div>