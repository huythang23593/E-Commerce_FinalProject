<%-- 
    Document   : home
    Created on : Jun 20, 2019, 7:47:58 PM
    Author     : AnhLe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
<!--        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
        <meta name="author" content="GeeksLabs">
        <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">-->
        <link rel="shortcut icon" href="img/favicon.png">

        <title>Admin Dashboard</title>
        <jsp:include page="../include/management/admin-css.jsp"/>
        
    </head>
    <body>
 
        <jsp:include page="../include/management/admin-header-css.jsp"/>
        <jsp:include page="../include/management/admin-aside.jsp"/>
        <jsp:include page="../include/management/admin-index.jsp"/>
        <jsp:include page="../include/management/admin-javascript.jsp"/>
    </body>
</html>
