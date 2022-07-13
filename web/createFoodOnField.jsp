<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta
            name="author"
            content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
            />
        <meta name="generator" content="Hugo 0.88.1" />
        <title>Create Food</title>
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
            integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
            crossorigin="anonymous"
            />
        <link
            rel="canonical"
            href="https://getbootstrap.com/docs/4.6/examples/dashboard/"
            />
        <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
            crossorigin="anonymous"
        ></script>
        <link href="./styles/adminDashboard.css" />
        <!-- Bootstrap core CSS -->

        <!-- Favicons -->
        <meta name="theme-color" content="#563d7c" />

        <!-- Custom styles for this template -->
        <link href="dashboard.css" rel="stylesheet" />
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
        <!-- Datatable -->
        <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
        <!-- Custom Stylesheet -->
        <link href="vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.role.roleId ne 'MA'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>

        <jsp:include page="navbarFieldOwner.jsp"></jsp:include>

            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>

            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">

                <form action="MainController" method="POST">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Create Food</h4>
                        </div>
                        <div class="card-body">
                            <div class="basic-form">

                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label>Field ID</label><label class="ml-1" style="color:red">(*)</label>
                                        <input type="text" class="form-control" name="fieldId" value = "${sessionScope.FIELD_ID}" readonly=""/>
                                </div>

                                <div class="form-group col-md-6">
                                    <label>Food Name</label><label class="ml-1" style="color:red">(*)</label>
                                    <input type="text" class="form-control" name="foodName" value = "${param.foodName}" required=""/>
                                    <p style="color: red">${requestScope.FOOD_ERROR.foodNameError}</p>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>Category</label><label class="ml-1" style="color:red">(*)</label>
                                    <select class="form-control " name="categoryFoodId">

                                        <option value="">Show all</option>
                                        <c:forEach var="foodCateName" items="${requestScope.FOOD_CATEGORY_NAME}">
                                            <option value="${foodCateName.foodCateId}">${foodCateName.foodCateName}</option>
                                        </c:forEach>


                                    </select>
                                    <p style="color: red">${requestScope.FOOD_ERROR.categoryFoodIdError}</p>
                                </div>

                                <div class="form-group col-md-6">
                                    <label>Image</label><label class="ml-1" style="color:red">(*)</label>
                                    <input type="text" class="form-control" name="image" value = "${param.image}" required=""/>
                                    <p style="color: red">${requestScope.FOOD_ERROR.foodImageError}</p>
                                </div>

                            </div>                                       
                            <button type="submit" name="action" class="btn btn-primary" value="CreateFoodOnField"/>Create</button>
                            <a class="btn btn-warning mt ml-1" href="MainController?action=ViewFoodOfField&fieldId=${sessionScope.FIELD_ID}&index=1">Back</a>
                            <p style="color: green">${requestScope.CREATE_SUCCESS} </p>
                            <p style="color: red">${requestScope.CREATE_FAIL} </p>
                            
                        </div>
                    </div>
                </div>                                
            </form>



        </div>
    </main>
    <script
        src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"
    ></script>
    <script>
        window.jQuery ||
                document.write(
                        '<script src="/docs/4.6/assets/js/vendor/jquery.slim.min.js"><\/script>'
                        );
    </script>
    <script
        src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"
    ></script>

    <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
    <script src="dashboard.js"></script>
    <script data-cfasync="false" src="../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="vendor/global/global.min.js"></script>
    <script src="vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
    <!-- Datatable -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="js/plugins-init/datatables.init.js"></script>
    <script src="js/custom.min.js"></script>
    <script src="js/deznav-init.js"></script>
    <script src="js/demo.js"></script>
</body>
</html>
