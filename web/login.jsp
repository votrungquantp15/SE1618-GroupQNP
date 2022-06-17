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


        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
            integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="styles/login.css" />

        <title>Login Page</title>
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
                    <div class="col-lg-6 px-5 pt-5">
                        <h4 class="my-4">Sign into your account</h4>
                        <div  style="color: green">${requestScope.CUSTOMER_ERROR.messageError}</div>
                        <form action="MainController" method="POST">
                            <div class="form-row">
                                <div class="col-lg-8">
                                    <input
                                        type="email"
                                        name="email"
                                        placeholder="Email"
                                        required=""
                                        class="form-control my-3 p-4"
                                        />
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-lg-8">
                                    <input
                                        type="password"
                                        name="password"
                                        placeholder="Password"
                                        required=""
                                        class="form-control my-3 p-4"
                                        />
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-lg-8 my-3">
                                    <button class="btn1" type="submit" name="action" value="Login">Login</button>
                                </div>
                            </div>
                            <p style="color: red">${requestScope.ERROR_MESSAGE} </p>
                            <a href="#">Forget Password</a>
                            <p>Don't have an account? <a href="MainController?action=CreateAccountForUser">Create User</a></p>
                        </form>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/sg-booking-management/login-google&response_type=code
                           &client_id=156929057174-2giaf3dbm4itufvgf7ks8hb820l765s1.apps.googleusercontent.com&approval_prompt=force">Login With Google</a> 
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
    </body>
</html>
