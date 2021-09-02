<%-- 
    Document   : infomation
    Created on : Jul 8, 2021, 10:30:35 PM
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
                    <mvc:form action="${pageContext.request.contextPath}/result" method="POST" modelAttribute="account">
                        <c:if test="${action == 'update-account'}">
                            <input hidden name="id" value="${account.id}"/>
                        </c:if>
                        <div class="form-group">
                            <label for="idName">Name: </label>
                            <input type="text" class="form-control" id="idname" name="fullname" required="true" placeholder="Enter name" value="${account.fullname}"/>

                            <input type="text" class="form-control" id="idname" name="password" required="true" placeholder="Enter name" value="${account.password}" hidden="true"/>

                            <div class="form-group">
                                <label for="idEmail">Email: </label>
                                <input type="email" class="form-control" id="idEmail" name="email" required="true" placeholder="Enter email" value="${account.email}"/>
                            </div>

                            <div class="form-group">
                                <label for="idPassword">Address </label>
                                <input type="text" class="form-control" id="idPassword" name="address" required="true" placeholder="Enter address" value="${account.address}"/>
                            </div>
                            <div class="form-group">
                                <label for="idDescription:">Phone: </label>
                                <input type="text" class="form-control" id="idPassword" name="phoneNumber" required="true" placeholder="Enter phone" value="${account.phoneNumber}"/>
                            </div>
                            <div class="form-group">
                                <label for="idBirthday">Birthday (mm/dd/yyyy):</label>
                                <input type="date" class="form-control" id="idBirthday" name="birthDate" required="true" placeholder="Enter birthday" value="${account.birthDate}"/>
                            </div>
                            <div class="form-group">
                                <label>Gender: </label>
                                <c:forEach var="genderItem" items="${gender}">
                                    <c:if test="${account.gender == genderItem}">
                                        <input type="radio" name="gender" value="${genderItem}" checked/> &nbsp; ${genderItem}
                                    </c:if>
                                    <c:if test="${account.gender != genderItem}">
                                        <div style="margin: 10px;display: inline-block">
                                            <input type="radio" name="gender" value="${genderItem}"/> &nbsp; ${genderItem}
                                        </div>
                                    </c:if> 
                                </c:forEach>                       
                            </div>
                        </div>
                    </div>
                    <c:if test="${action == 'infomation'}">
                        <button type="submit" 
                                class="btn btn-success">Confirm</button>
                    </c:if>
                    <c:if test="${action == 'update-account'}">
                        <button type="submit" 
                                class="btn btn-success">Update</button>
                    </c:if>
                </mvc:form>

                <jsp:include page="include/footer-page.jsp" />
                <jsp:include page="include/js-page.jsp" />
                </body>
                </html>
