<%-- 
    Document   : updateSuccess
    Created on : Jul 10, 2021, 3:28:58 PM
    Author     : Admin
--%>

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
    <div class="container" style="margin-top: 150px;">
        <div class="row">
            <div class="col-md-12" style="text-align: center;">
                <h2 style=" margin-bottom: 50px;">Successful !!!</h2>
                
                <button class="btn btn-danger" onclick="location.href = '<c:url value="/user/user-page/"/>'">Back to user page</button>
            </div>
        </div>

    </div>
    <jsp:include page="include/footer-page.jsp" />
    <jsp:include page="include/js-page.jsp" />
</body>
</html>
