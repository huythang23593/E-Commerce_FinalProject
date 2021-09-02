<%-- 
    Document   : order
    Created on : Jun 26, 2021, 3:21:29 PM
    Author     : Admin
--%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <jsp:include page="include/menu-page.jsp" />

        <section class="banner-area organic-breadcrumb">
            <div class="container">
                <div class="breadcrumb-banner d-flex flex-wrap align-items-center">
                    <div class="col-first">
                        <h1>Shopping Cart</h1>
                        <nav class="d-flex align-items-center justify-content-start">
                            <a href="index.html">Home<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                            <a href="cart.html">Shopping Cart</a>
                        </nav>
                    </div>
                </div>
            </div>
        </section>

        <div class="container">
            <div class="cart-title">
                <div class="row">
                    <div class="col-md-2">
                        <h6 class="ml-15">Product</h6>
                    </div>
                    <div class="col-md-2">
                        <h6>Name</h6>
                    </div>
                    <div class="col-md-1">
                        <h6>Price</h6>
                    </div>
                    <div class="col-md-2">
                        <h6>Color-Size</h6>
                    </div>
                    <div class="col-md-2">
                        <h6>Quantity</h6>
                    </div>
                    <div class="col-md-1">
                        <h6>UnitPrice</h6>
                    </div>
                    <div class="col-md-2">
                        <h6>Action</h6>
                    </div>
                </div>
            </div>

            <c:if test="${sessionScope.myOrderDetailItems != null && fn:length(sessionScope.myOrderDetailItems) > 0}">
                <c:forEach var="order" items="${sessionScope.myOrderDetailItems}">
                    <div class="cart-single-item">
                        <div class="row align-items-center">
                            <div class="col-md-2 col-6">

                                <div class="product-item d-flex align-items-center">
                                    <img src="<c:url value="/resources/img/${order.value.productOrderDetail.image.name}"/>"  WIDTH="150" HEIGHT="150" style="border:1px solid black""/>
                                </div>
                            </div>
                            <div class="col-md-2 col-6">
                                <div class="item">
                                    <h4>${order.value.productOrderDetail.name}</h4>
                                </div>       
                            </div>    
                            <!--                            <div class="col-md-1 col-6">
                            <c:forEach var="p" items="{order.value.productOrderDetail.productDetail}">
                            <div class="price">{p.price}$</div>
                            </c:forEach>
                        </div>-->

                            <div class="col-md-1 col-6">
                                <div class="price">${order.value.unitPrice}$</div>
                            </div>


                            <div class="col-md-2 col-6">
                                <h4>Color:${order.value.color}</h4>    
                                <h4>Size:${order.value.size}</h4>  
                            </div>

                            <div class="col-md-2 col-6">
                                <form action="${pageContext.request.contextPath}/updateorder" method="post">
                                    <input type="number" id="quantity" class="form-control" name="quantity" value="${order.value.quantity}" max="99" min="0"/>
                                    <input name="productId" value="${order.value.productOrderDetail.id}" hidden/>
                                    <input name="color" value="${order.value.color}" hidden/>
                                    <input name="size" value="${order.value.size}" hidden/>
                                    <button class="btn btn-info"
                                            type="submit" >Update</button>
                                </form>
                            </div>
                            <div class="col-md-1 col-6">
                                <div class="total">${order.value.quantity * order.value.unitPrice}$</div>
                            </div>
                            <div class="col-md-2 col-6">
                                <div class="">
                                    <button class="btn btn-danger"
                                            onclick="location.href = '<c:url value="/removeorder/${order.value.productOrderDetail.id}/${order.value.color}/${order.value.size}" />'">Remove</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <div class="subtotal-area d-flex align-items-center justify-content-end">
                <div class="title text-uppercase">Total price:</div>
                <div class="subtotal">${sessionScope.myOrderDetailTotal}$</div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4"></div>
                <div class="col-md-2">
                    <a href="${contextPath}/product/0" class="btn btn-primary"><span>Continue</span></a>
                </div>
                <div class="col-md-2">

                    <a href="${contextPath}/checkout" class="btn btn-success"><span>Check Out</span></a>
                </div>

            </div>
            <h3 style="color: red">  ${message}</h3>
        </div>

        <jsp:include page="include/footer-page.jsp" />
        <jsp:include page="include/js-page.jsp" />
    </body>
</html>