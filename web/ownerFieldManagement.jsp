<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <meta name="robots" content="">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="Zenix - Crypto Admin Dashboard">
    <meta property="og:title" content="Zenix - Crypto Admin Dashboard">
    <meta property="og:description" content="Zenix - Crypto Admin Dashboard">
    <meta property="og:image" content="https://zenix.dexignzone.com/xhtml/social-image.png">
    <meta name="format-detection" content="telephone=no">
    <title>Owner Field Management</title>
    <!-- Favicon icon -->
    <link rel="icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAhFBMVEUAvkD///8AvTsAuzFNymyz578AuioAvDWe4K5u0YUAwEQAui0AvTzo9utazXWO3KGW26Ot4re758Mww1TO8Nfl9+lQzG+Z3qnE7M75/vvz/PZr0oRCyGSA2JZfz3t31Y45x2Eawk7X8t6/68qF2Jnb8+Jm0YCu5bzR8Nmm47U+x2Eawkz3AKnrAAAGvUlEQVR4nO2d6XqqMBCGQ4BETVwqIIoLWq0t9f7v74ArCYi2hxGaZ95/FVLzOdkmgRliqUR+2PUO5G9y8LqhH2mKiPKXvxKC06Yr+h9QLsTKv6tw67G/rO4CZd62VGHgMNl05WpCMicoKkyMsN8Fyj51hTFrulI1w2JV4Vw0XaPaEd28wqV5AlOJy5tC17QmeoK5F4WBmQJTicFZ4cKkUTQPdU4Kx6aaMDXi+KjQM2WiLyK9TOHUXBOmRpymCvem9sIMurfIjDddC1D4jPgmTvY3hE9Cw20Ykq7J3TDtiF2yNneuyJBrsmm6DsCYro+QXtMVQBAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQZC2ICnl3LZtzil92Tshkj/D6d7KW6isrrPkgh0GcZgkb0kyjCc9Zr/k1R65SDqPGZ4k3r8hHA3jBRH8rkguvNGXGr4jcAf2/QK1QbvWE/SPb2z2Ht32Ed6JwsHFLigrEHWk3Q6FxzfD5UOFKf538c1AKXazuwVCAWzGuhVa1qcebMTelNrvwscBtjvWr9AKiCKRLR8VWINKBFBoBfm+JXaPC3iQEiEUWu6tL/L5E/dHkEMqiMJbRIfs5fkn+AR8cRlG4fRSY+Y/vjkDMEICjELrvL6hk+KlKAiKc8cUzog/mPE1hVH/ih5szLqEPBCaCaNwwVnKYT7VCmzAjKgpTIalLHlR4TcTV2T3Q61w57jMk2pcNWvLLmHVKNNGoBHYC/aawom4v/LWFDq5IV5ytlUuuscJg6oy3vPROuz9/WuQCgcVM1OFwhSmdK5Tv+IdpcRcsRNTG+qrbPh7hfa4qFD9TIsKRBfXC9H7tgPWEWtTyMP8xVMrFaqZtIgrrB8F07fdfm0zAehGASk8Dk36UPqp9zWeSYN29mtTqNrr1OZsdfix4iZiANalkCoTg3+ylv1pqUwnLzCaxv8olFeoTZQJ8Rx6hBf9imi7G2zYK1rnLxRquxgr78ZeNVZ4XoPJhVVO/+tzuPcoe0Vc1Wdm/POtT69Lb3G4RHE9l2P2ngzgN9w0hW+jIqH3M4W5IVMdYEtxv4GHn2dW3hP6E4X5mKL6wrSU95LNqxYrdHvK8os/3KTJSOyGffyzwsf7pekQsiPqBqi2Ir/DB2nWA/5ZKx2rVhS6J1hK/y8ptKydsjpjncclXug91aJQW4Da6/cnysRQ7hOIQi24KGWTx001gjrAgFF49i1u3yJ68bZy9i/4Vk0o1MbS+cK5Moh1I+nfI6lgZDV0/f69Q5oxkBF/r3DB6Q3OPNVEnZJuJSm3hSDearQtOavpA401msLtWxH37ClU+4dU3d2O7q5TZCaU0a52Xgq2U6OvvJld5DxXVSvUd5YW+gyn/U3ZQGuvQPvetfn4ujOYG/2zxsnstf7/uKMqrPTcfk+NCmPlctYRJU/bIz9MdokfFMbXc2juG6vWK1RtmHAivTjZBtcRqBBEXdtsnLRdoXbMlNpQrpVPrKE2+mhL1pb3QylWqpysTeou/kqoRdSh5jWzxQ8UfueGXcHoSBVzbHOFvbZlzp+nmmP1DuQH/17hdHxjW5jcrOwfUUf/9GvN7GzrJ/1NJtpxFdTp0+8VVnMKuM0+CheCZLhbjpKvwir1AOQiQik8Df108HSBLdRmDZDCi0PLnvLwM8BiAwMp9K4rvfvPeykUlgMtV3g7C6X3tr1VCsdSLVc4z7l6/BmJHcBsFQAKo4Xiy3Kv/6gEaGKq2hXOOvouvWRJZQn/AJrooGaFX0tashlhe/f3hafQqeHoPvfkTz+qWt/LXr+CwHeHK87KzSEF2RWXPekPMuwJ8AM2yvJUfp1kVWSHnhVluZDO0P06P/EVfaQ/iCOFYYk4jr4+Sxem9sMfBEEQBEEQBFGRJYdNTQC2QpXO2G0D42GTTyq8hK8X7SY2B1jORlT4MlAhKkSFzYMK/75CsBlfbiZOK/gG2/mWtB2YneAXQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEgQUsXk9L6JFN01UAZkPWZj9SLL9J1+yIE7RLQsNChmjwkIC9ndEOhE9mhttwRsAiR7cCurdILoeGgbBpqhAwUVvjZCkViR723SiysKdZYpCFqT3xGKYwU1gIS2wKxwDSx+QugLEWm4QdA/ee0tfEJk774hQ0/JygZ26eRHF+4/WSgmhpWkO9poe+Jllym8hVBwZlrqUrtIIFdObylyGZcwvDn0+U5W6MsCNlXj7mspoKbDqnglP5V22ZJfwQdK4G7deTnc38MHa8v7k91fOcOPT1gNL/AJgKdV+s8r1aAAAAAElFTkSuQmCC">

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

    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="sk-three-bounce">
            <div class="sk-child sk-bounce1"></div>
            <div class="sk-child sk-bounce2"></div>
            <div class="sk-child sk-bounce3"></div>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->


    <!--**********************************
        Main wrapper start
    ***********************************-->
    <div id="main-wrapper">

        <jsp:include page="navbarFieldOwner.jsp"></jsp:include>
            <div class="content-body">
                <div class="col-12">
                    <div class="card">

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Fields Management</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="form col-12">
                                                <form class="col-12 form-inline mb-3" action="MainController">
                                                    <div class="input-group col-sm-10">
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-primary disabled" type="button">Status</button>
                                                            <select name="status">
                                                                <option value="" <c:if test="${param.status == null}">selected</c:if>>Show all status</option>
                                                            <option value="In-Active" <c:if test="${param.status eq 'In-Active'}">selected</c:if>>In-Active</option>
                                                            <option value="Request" <c:if test="${param.status eq 'Request'}">selected</c:if>>Request</option>
                                                            <option value="Active" <c:if test="${param.status eq 'Active'}">selected</c:if>>Active</option>
                                                            </select>
                                                        </div>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-primary disabled" type="button">Search By</button>
                                                            <select name ="searchBy">
                                                                <option value="Name" <c:if test="${param.searchBy eq 'Name'}">selected</c:if>>Name</option>
                                                            <option value="Category" <c:if test="${param.searchBy eq 'Category'}">selected</c:if>>Category</option>
                                                            <option value="District" <c:if test="${param.searchBy eq 'District'}">selected</c:if>>District</option>
                                                            </select>
                                                        </div>
                                                        <input class="col-sm-4" type="text" class="form-control" name="searchByAdmin" value="${param.searchByAdmin}" placeholder="Search here">
                                                    <div class="input-group-append">
                                                        <input type="hidden" name="index" value="1"/>
                                                        <button class="btn btn-primary btn-sm-3" type="submit" name="action" value="SearchFieldByAdmin">Search</button>
                                                    </div>
                                                </div>
                                                <button class="btn btn-primary col-sm" type="button" data-toggle="modal" data-target="#createNewField">Create new field</button>
                                            </form>
                                            <p style="color: red"> ${requestScope.SEARCH_FIELD_ERROR} </p>
                                            <p style="color: green"> ${requestScope.CREATE_SUCCESS} </p>
                                            <p style="color: red"> ${requestScope.CREATE_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_CATE_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_USER_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_LOCATION_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_DISTRICT_ERROR} </p>
                                            <p style="color: red"> ${requestScope.CREATE_UNSUCCESS} </p>
                                        </div>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-responsive-md">
                                            <thead>
                                                <tr>
                                                    <th style="width:80px;"><strong>#</strong></th>
                                                    <th><strong>Field ID</strong></th>
                                                    <th><strong>Field Name</strong></th>
                                                    <th><strong>Category</strong></th>
                                                    <th><strong>Price</strong></th>
                                                    <th><strong>Field Owner</strong></th>
                                                    <th><strong>District Name</strong></th>
                                                    <th><strong>Status</strong></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <c:forEach var="field" items="${requestScope.LIST_FIELD}" varStatus="counter">
                                                    <tr>
                                                        <td><strong>${counter.count}</strong></td>
                                                        <td>${field.fieldId}</td>
                                                        <td><a class="text-primary" title="Click to view detail" href="MainController?action=PrintDetail&fieldId=${field.fieldId}">${field.fieldName}</a></td>
                                                        <td>${field.fieldCate.fieldCateName}</td>
                                                        <td>${field.price}</td>
                                                        <td>${field.user.fullName}</td>
                                                        <td>${field.district.districtName}</td>
                                                        <td>${field.status}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tr>
                                        </table>
                                    </div>
                                        
                                    <c:choose>
                                        <c:when test="${requestScope.LIST_FIELD != null}">
                                            <ul class="pagination">
                                                <c:choose>
                                                    <c:when test="${sessionScope.ACTION_FIELD == 'Print'}">
                                                        <c:forEach var="i" begin="1" end="${END_PAGE}">
                                                            <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                                                <a href="MainController?action=Print&index=${i}" class="page-link">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:when test="${sessionScope.ACTION_FIELD == 'Search'}">
                                                        <c:forEach var="i" begin="1" end="${END_PAGE}">
                                                            <li class="page-item <c:if test="${param.index eq i}"> active </c:if>">
                                                                <a href="MainController?action=SearchFieldByAdmin&index=${i}&searchBy=${requestScope.SEARCH_BY}&searchByAdmin=${requestScope.SEARCH}&status=${requestScope.STATUS}" class="page-link">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise></c:otherwise>
                                                </c:choose>
                                            </ul>
                                        </c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                        
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="MainController" method="POST" accept-charset="utf-8"> 
                <div class="modal fade" id="createNewField" tabindex="-1" aria-labelledby="createNewField" aria-hidden="true">
                    <div class="modal-dialog modal-xl modal-dialog-scrollable">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Create new field</h5>
                                <button type="button" class="close" aria-label="Close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body" style="margin-top: -20px">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="product-detail-content">
                                            <div class="new-arrival-content pr row">
                                                <div class="col-12 col-sm-12">
                                                    <div class="card-body">
                                                        <div class="table row">
                                                            <table class="col-12">
                                                                <tr>
                                                                    <th>Field Name:</th>
                                                                    <th class="col-10"><input class="col-12" title="Input information here" type="text" name="fieldName" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Description:</th>
                                                                    <th><textarea title="Input information here" class="col-12" cols="500" rows="3" name="description"></textarea></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Image:</th>
                                                                    <th><textarea title="Input information here" class="col-12" rows="6" name="image"></textarea></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Category:</th>
                                                                    <th>
                                                                        <select name ="categoryFieldId">
                                                                            <c:forEach var="category" items="${requestScope.LIST_CATEGORY}">
                                                                                <option value="${category.fieldCateId}">${category.fieldCateName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Price:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="price" required=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Field Owner:</th>
                                                                    <th><input class="col-12" title="Input information here" type="text" name="userName" value="${requestScope.USER_NAME}" readonly=""></th>
                                                                </tr>
                                                                <tr>
                                                                    <th>Address:</th>
                                                                    <th>
                                                                        <select name ="locationId">
                                                                            <c:forEach var="location" items="${requestScope.LIST_LOCATION}">
                                                                                <option value="${location.locationId}">${location.locationName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </th>
                                                                </tr>
                                                                <tr>
                                                                    <th>District:</th>
                                                                    <th>
                                                                        <select name ="districtId">
                                                                            <c:forEach var="district" items="${requestScope.LIST_DISTRICT}">
                                                                                <option value="${district.districtId}">${district.districtName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </th>
                                                                </tr>
                                                                <tr>
                                                                    <th></th>
                                                                    <th class="d-flex justify-content-end"><input class="btn btn-secondary" type="reset" value="Reset"/></th>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input type="hidden" name="action" value="CreateField"/>
                                <input type="hidden" name="index" value="1"/>
                                <input class="btn btn-primary" type="submit" value="Accept"/>
                            </div>

                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- Required vendors -->
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
