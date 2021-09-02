<%-- 
    Document   : payment
    Created on : Jul 14, 2021, 5:10:54 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <jsp:include page="include/menu-page.jsp" />
        <div class="container">
            <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
            <div class="row" style="text-align: center; padding-top: 120px">
                <div class="col-lg-12">
                    <h2>Check out successfully</h2>
                </div>
                <div class="col-lg-12" style="padding: 40px">
                    <div class="col-lg-12 get_in_touch_col">
                        Your courses have been registered successfully. Thanks for purchasing!
                    </div>
                </div>

                <div class="col-lg-12">
                    <button class="btn btn-warning" onclick="
                            location.href = '${contextPath}/product/0'">Continue</button>
                    <button class="btn btn-danger" onclick="
                            location.href = '${contextPath}/home'">Go to Home</button>
                </div>
            </div>
        </div>
        <jsp:include page="include/footer-page.jsp" />
        <jsp:include page="include/js-page.jsp" />
    </body>
</html>
