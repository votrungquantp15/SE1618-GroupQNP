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
        <title>Account Editor</title>
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


        <jsp:include page="navbarAdmin.jsp"></jsp:include>

            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">Account Editor</h1>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-sm" border="1">
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>Full Name</th>
                                <th>Address</th>
                                <th>Birthday</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Account Name</th>
                                <th>Password</th>
                                <th>Role ID</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${requestScope.VIEW_ACCOUNT}">
                        <form action="MainController">
                            <tr>

                                <td>${user.userID}</td>                             
                                <td><input title="Input what you want to update" type="text" name="fullName" value="${user.fullName}" required=""/></td>
                                <td><input title="Input what you want to update" type="text" name="address" value="${user.address}" required=""/></td>
                                <td><input title="Input what you want to update" type="date" name="birthday" value="${user.birth}" required=""/></td>
                                <td><input title="Input what you want to update" type="text" name="phone" value="${user.phone}" required=""/></td>
                                <td><input title="Input what you want to update" type="email" name="email" value="${user.email}" required=""/></td>
                                <td><input title="Input what you want to update" type="text" name="accName" value="${user.accName}" required=""/></td>
                                <td><input title="Input what you want to update" type="text" name="password" value="${user.password}" required=""/></td>
                                <td><input title="Input what you want to update" type="text" name="roleId" value="${user.role.roleId}" required=""/></td>
                                <td><input title="Input what you want to update" type="text" name="status" value="${user.status}" /></td>
                                <td>
                                    <input title="Click here to update user" type="submit" name="action" value="UpdateAccountByAdmin"/>
                                    <input type="hidden" name="userID" value="${user.userID}"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
                <p style="color: green">${requestScope.UPDATE_SUCCESS} </p>
                <p style="color: red">${requestScope.UPDATE_FAILED} </p>
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
        <script src="js/styleSwitcher.js"></script>
    </body>
</html>
