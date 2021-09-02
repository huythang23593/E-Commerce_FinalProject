<%-- 
    Document   : checkout
    Created on : Jun 26, 2021, 4:26:24 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <section class="banner-area relative">
            <div class="container">
                <div class="breadcrumb-banner d-flex flex-wrap align-items-center">
                    <div class="col-first">
                        <h1>Check Out Page</h1>
                        <nav class="d-flex align-items-center justify-content-start">

                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <br><br><br><br><br>
        <div class="container">
            <div class="cart-title">
                <div class="row">
                    <div class="col-md-2">
                        <h6 class="ml-15">Product</h6>
                    </div>
                    <div class="col-md-2">
                        <h6>Name</h6>
                    </div>
                    <div class="col-md-2">
                        <h6>Price</h6>
                    </div>
                    <div class="col-md-2">
                        <h6>Quantity</h6>
                    </div>
                    <div class="col-md-2">
                        <h6>UnitPrice</h6>
                    </div>
                   
                </div>
            </div>

            <c:forEach items="${sessionScope.myOrderDetailItems}" var="item">
                <div class="cart-single-item">
                    <div class="row align-items-center">
                        <div class="col-md-2 col-6">
                            <div class="product-item d-flex align-items-center">
                                <img src="<c:url value="/resources/img/${item.value.productOrderDetail.image[0].name}"/>"  WIDTH="150" HEIGHT="150" style="border:1px solid black""/>
                            </div>
                        </div>
                        <div class="col-md-2 col-6">
                            <div class="item">
                                <h4>${item.value.productOrderDetail.name}</h4>
                            </div>       
                        </div>    
                        <div class="col-md-2 col-6">
                             <div class="price">${item.value.unitPrice}$</div>
                        </div>
                        <div class="col-md-2 col-6">
                            <form action="${pageContext.request.contextPath}/updateorder" method="post">
                                <input type="number" readonly="true" id="quantity" class="form-control" name="quantity" value="${item.value.quantity}" max="5" min="1"/>
                                <input name="productId" value="${item.value.productOrderDetail.id}" hidden/>

                            </form>
                        </div>
                        <div class="col-md-2 col-6">
                            <div class="total">${item.value.quantity * item.value.unitPrice}$</div>
                        </div>
                    </div>
                </div>
            </c:forEach>


            <div class="subtotal-area d-flex align-items-center justify-content-end">
                <div class="title text-uppercase">Total price:</div>
                <div class="subtotal">${sessionScope.myOrderDetailTotal}$</div>
            </div>
        </div>          
        <div class="subtotal-area d-flex align-items-center justify-content-center">
            <button class="btn btn-success"
                    onclick="location.href = '<c:url value="/removesession" />'">Confirm</button>
        </div>
        
        <jsp:include page="include/footer-page.jsp" />
        <jsp:include page="include/js-page.jsp" />
    </body>
</html>
