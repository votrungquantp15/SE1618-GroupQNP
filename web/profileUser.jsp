<%-- 
    Document   : profileUser
    Created on : 01-Jun-2022, 11:46:43
    Author     : votru
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>profile User</title>
        <link rel="stylesheet"
              href="styles/profileUser.css"
              />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
    </head>
    <body>
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-1">

                </div>
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">Edogaru</span><span class="text-black-50">edogaru@mail.com.my</span><span> </span></div>
                </div>
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Profile Settings</h4>
                        </div>
                        <p style="color: red">${requestScope.UPDATE_PROFILE_FAIL} </p>   
                        <p style="color: green">${requestScope.UPDATE_PROFILE_SUCCESS} </p>

                        <form action="MainController" method="POST">
                            <div class="row mt-2">
                                <div class="col-md-6"><label class="labels">Name</label><input type="text" name="name" required="" class="form-control" placeholder="Full name" value="${requestScope.PROFILE_USER.fullName}"></div>
                                <div class="col-md-6"><label class="labels">Birth Day</label><input type="date" name="birth" class="form-control" value="${requestScope.PROFILE_USER.birth}" placeholder="birthDay"></div>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6"><label class="labels">UserID</label><input type="text" class="form-control" value="${requestScope.PROFILE_USER.userID}" disabled=""></div>
                                <input type="hidden" name="userID" value="${requestScope.PROFILE_USER.userID}" />
                                <div class="col-md-6"><label class="labels">Role Name</label><input type="text" name="role" class="form-control" value="${requestScope.PROFILE_USER.role.roleName}"disabled=""></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Account Name</label><input type="text" class="form-control" disabled="" placeholder="enter address line 2" value="${requestScope.PROFILE_USER.accName}"></div>
                                <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" required="" name="phone" class="form-control" placeholder="enter phone number" value="${requestScope.PROFILE_USER.phone}" ></div>
                                <div class="col-md-12"><label class="labels">Email</label><input type="email" required="" class="form-control" name="email" placeholder="Email" value="${requestScope.PROFILE_USER.email}" ></div>
                                <div class="col-md-12"><label class="labels">Address</label><input type="text" required=""  class="form-control" name="address" placeholder="enter address line 1" value="${requestScope.PROFILE_USER.address}"></div>
                                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit" name="action" value="UpdateProfileUser" >Save Profile</button></div>
                            </div>
                        </form>
                    </div>


                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
