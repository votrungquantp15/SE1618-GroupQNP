<%-- 
    Document   : create
    Created on : May 26, 2022, 1:16:28 PM
    Author     : ROG STRIX
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />

        <link rel="stylesheet"
              href="styles/profileUser.css"
              />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
            integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="styles/login.css" />

        <title>Dang ki</title>
    </head>
    <body>
        <section class="Form my-5">
            <div class="container">
                <div class="row no-gutters">
                    <div src="" alt="" class="col-lg-6">
                        <img
                            src="https://cdn.pixabay.com/photo/2015/01/26/22/40/child-613199_1280.jpg"
                            alt=""
                            class="img-fluid"
                            />
                    </div>
                    
                    <div class="col-lg-6 px-5 ">
                        <h4 class="my-4">Đăng kí tài khoản</h4>
                        <div  style="color: red">${requestScope.CUSTOMER_ERROR.messageError}</div>
                        <form action="MainController" method="POST">
                            <div class="form-row">
                                <div class="col-lg-6">
                                    <label class="labels">Email</label> <label class="labels" style="color: red">${requestScope.CUSTOMER_ERROR.emailError}</label>
                                    <input
                                        type="email"
                                        name="email"
                                        placeholder="Email"
                                        required=""
                                        class="form-control p-4"
                                        />
                                </div>
                                <div class="col-lg-6">
                                    <label class="labels">Tên người dùng</label>
                                    <input
                                        type="input"
                                        name="fullName"
                                        placeholder="Tên"
                                        required=""
                                        class="form-control p-4"
                                        />
                                </div>
                            </div>
                            <div class="form-row py-2">
                                <div class="col-lg-6">
                                    <label class="labels">Mật khẩu</label>
                                    <input
                                        type="password"
                                        name="password"
                                        placeholder="Mật khẩu"
                                        required=""
                                        class="form-control p-4"
                                        />
                                </div>
                                <div class="col-lg-6">
                                    <label class="labels">Nhập lại mật khẩu</label> <label class="labels" style="color: red">${requestScope.CUSTOMER_ERROR.confirmError}</label>
                                    <input
                                        type="password"
                                        name="confirm"
                                        placeholder="Mật khẩu"
                                        required=""
                                        class="form-control p-4"
                                        />
                                </div>
                                
                            </div>
                            <div class="form-row py-2">
                                <div class="col-lg-6">
                                    <label class="labels">Tài khoản</label> <label class="labels" style="color: red">${requestScope.CUSTOMER_ERROR.accNameError}</label>
                                    <input
                                        type="input"
                                        name="accName"
                                        placeholder="Tài khoản"
                                        required=""
                                        class="form-control p-4"
                                        />
                                </div>                                                      
                                <div class="col-lg-6"> 
                                    <label class="labels">Số điện thoại</label> <label class="labels" style="color: red">${requestScope.CUSTOMER_ERROR.phoneError}</label>
                                    <input
                                        type="input"
                                        name="phone"
                                        placeholder="Số điện thoại"
                                        required=""
                                        class="form-control p-4"
                                        />
                                </div>
                            </div>
                            <div class="form-row py-2">
                                <div class="col-lg-6">
                                    <label class="labels">Ngày sinh</label>
                                    <input
                                        type="date"
                                        name="birthDay"
                                        placeholder="Password"
                                        required=""
                                        class="form-control p-4"
                                        />
                                </div>
                                <div class="col-lg-6">
                                    <label class="labels">Thành phố</label>

                                    <select class="form-control " name="cityId">
                                                                             
                                        <option value="">Show all</option>
                                        <c:forEach var="cityName" items="${requestScope.CITY_NAME}">
                                            <option value="${cityName.cityId}">${cityName.cityName}</option>
                                        </c:forEach>
                                        

                                    </select>

                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-lg-2"></div>
                                <div class="col-lg-8 my-3">
                                    <button class="btn1" type="submit" name="action" value="CreateAccountForUser">Đăng kí </button>
                                </div>
                                <div class="col-lg-2"></div>
                                <div class="col-lg-2"></div>
                                <div class="col-lg-8 ">
                                    <button class="btn1" onclick="location.href = 'login.jsp';" >Trở lại</button>
                                </div>
                            </div>                           
                        </form>
                    </div>
                </div>
            </div>
        </section>


        <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
            integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
            crossorigin="anonymous"
        ></script>
        <script src="js/plugins-init/select2-init.js"></script>
    </body>
</html>


<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            User ID<input type="text" name="userID"/></br>
            Full Name<input type="text" name="fullName"/></br>
            Password<input type="password" name="password"/></br>
            Confirm password<input type="password" name="confirm"/></br>
            NickName<input type="text" name="accName"/></br>
            Address<input type="text" name="address"/></br>           
            BirthDay<input type="date" name="birthDay" min="1995-01-01" max="2022-05-27"/></br>
            Phone<input type="number" name="phone"/></br>
            Email<input type="email" name="email"/></br>
            Role<input type="text" name="roleID"/></br>
            status<input type="number" name="status" min="0" max="1"/></br>
            <input type="submit" name="action" value="CreateAccountForUser"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>-->
<!--dddd-->