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
        <title>Field Detail Management</title>
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
    </head>
    <body>


        <jsp:include page="navbarAdmin.jsp"></jsp:include>
        <jsp:include page="layoutAdmin.jsp"></jsp:include>

            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">Field Detail</h1>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-sm" border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Field ID</th>
                                <th>Field Name</th>
                                <th>Description</th>
                                <th>Image</th>
                                <th>CategoryFieldID</th>
                                <th>UserID</th>
                                <th>LocationID</th>
                                <th>CityId</th>
                                <th>Status</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="field" items="${requestScope.FIELD_DETAIL}" varStatus="counter">
                        <form action="MainController">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${field.fieldId}</td>
                                <td><input type="text" name="fieldName" value="${field.fieldName}" required="/>"</td>
                                <td><input type="text" name="description" value="${field.description}" required=""/></td>
                                <td><input type="text" name="image" value="${field.image}" required=""/></td>
                                <td><input type="text" name="categoryFieldId" value="${field.fieldCate.fieldCateId}" required=""/></td>
                                <td><input type="text" name="userId" value="${field.user.userID}" required=""/></td>
                                <td><input type="text" name="locationId" value="${field.location.locationId}" required=""/></td>
                                <td><input type="text" name="cityId" value="${field.city.cityId}" required=""/></td>
                                <td><input type="text" name="status" value="${field.status}" required=""/></td>
                                <td>
                                    <c:url var="delete" value="MainController">
                                        <c:param name="action" value="DeleteField"></c:param>
                                        <c:param name="fieldId" value="${field.fieldId}"></c:param>
                                    </c:url>
                                    <a href="${delete}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" name="action" value="UpdateField"/>
                                    <input type="hidden" name="fieldId" value="${field.fieldId}"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
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
    </body>
</html>
