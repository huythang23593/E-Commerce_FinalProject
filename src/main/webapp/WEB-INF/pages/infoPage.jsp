<%-- 
    Document   : infoPage
    Created on : Jul 14, 2021, 6:25:45 PM
    Author     : Admin
--%>

<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <jsp:include page="include/menu-page.jsp" />

        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12" style="text-align: center; margin-top: 130px;">
                    <h2 style="color: #fe4c50;">${headerName}</h2>                 
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12" >
                    <mvc:form action="${pageContext.request.contextPath}/infomation" method="POST">

                        <div class="form-group">
                            <label for="idName">Name: </label>
                            <input type="text" class="form-control" id="idname" name="fullname" required="true" placeholder="Enter name" />
                            <div class="form-group">
                                <label for="idEmail">Email: </label>
                                <input type="email" class="form-control" id="idEmail" name="email" required="true" placeholder="Enter email" />
                            </div>

                            <div class="form-group">
                                <label for="idPassword">Address </label>
                                <input type="text" class="form-control" id="idPassword" name="address" required="true" placeholder="Enter address" />
                            </div>
                            <div class="form-group">
                                <label for="idDescription:">Phone: </label>
                                <input type="number" class="form-control" id="idDescription" name="phoneNumber" required="true" placeholder="Enter phone" />
                            </div>
                            <div class="form-group">
                                <label for="idBirthday">Birthday (MM/dd/yyyy):</label>
                                <input type="date" class="form-control" id="idBirthday" name="birthDate" required="true" placeholder="Enter birthday" "/>
                            </div>
                            <div class="form-group">
                                <label>Gender: </label>
                                <input type="radio" name="gender" value="Male" checked> Male &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="gender" value="FeMale"> FeMale                     
                            </div>
                            <h3 style="color: #f9002f">${message} </h3>
                            <div style="margin-bottom: 35px">
                                <h4>Customer's Credit Card: </h4>
                            </div>
                            <div class="row">
                                <div class="form-group col-6">
                                    <label>Cardholder Name <font color = "#ff0000">(*)</font> </label>
                                    <input class="form-control" type="text" name="name" required >
                                </div>
                                <div class="form-group col-6">
                                    <label>Card Number <font color = "#ff0000">(*)</font></label>
                                    <input class="form-control" type="text" name="cardNumber" maxlength="19" minlength="12" required >
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-6">
                                    <label>Expiration Date <font color = "#ff0000">(*)</font> </label>
                                    <input type="date" class="form-control" name="expDate" required >
                                </div>
                                <div class="form-group col-6">
                                    <label>CVV <font color = "#ff0000">(*)</font> </label>
                                    <input class="form-control" type="password" name="cvcCode" maxlength="3" minlength="3" required >
                                </div>
                            </div>


                            <button type="submit" class="btn btn-success" style="align-content: center">Order confirmation</button>
                        </mvc:form>
                    </div>
                </div>
            </div>

            <jsp:include page="include/footer-page.jsp" />
            <jsp:include page="include/js-page.jsp" />
    </body>
</html>
