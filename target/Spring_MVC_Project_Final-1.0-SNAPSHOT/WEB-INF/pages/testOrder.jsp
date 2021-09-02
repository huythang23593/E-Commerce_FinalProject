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


            <c:forEach var="order" items="${orders.orderDetail}">
                <div class="cart-single-item">
                    <div class="row align-items-center">
                        <div class="col-md-2 col-6">


                        </div>
                        <div class="col-md-2 col-6">
                            <div class="item">
                                <h4>${order.productOrderDetail.name}</h4>
                            </div>       
                        </div>    
                        <div class="col-md-1 col-6">
                            <div class="price">${order.productOrderDetail.price}$</div>
                        </div>


                        <div class="col-md-2 col-6">
                            <h4>Color:${order.color}</h4>    
                            <h4>Size:${order.size}</h4>  
                        </div>
                        <div class="col-md-2 col-6">
                            <form action="${pageContext.request.contextPath}/updateorder" method="post">
                                <input type="number" id="quantity" class="form-control" name="quantity" value="${order.quantity}" max="5" min="1"/>
                                <input name="productId" value="${order.productOrderDetail.id}" hidden/>
                                <button class="btn btn-info"
                                        type="submit" >Update</button>
                            </form>
                        </div>
                        <div class="col-md-1 col-6">
                            <div class="total">${order.quantity * order.productOrderDetail.price}$</div>
                        </div>
                        <div class="col-md-2 col-6">
                            <div class="">
                                <button class="btn btn-danger"
                                        onclick="location.href = '<c:url value="/removeorder/${order.productOrderDetail.id}" />'">Remove</button>
                            </div>
                        </div>

                    </c:forEach>
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
                </div>
                </div>
        </div>

                <jsp:include page="include/footer-page.jsp" />
                <jsp:include page="include/js-page.jsp" />
                </body>
                </html>