<%-- 
    Document   : user-page
    Created on : Jul 8, 2021, 5:09:07 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <jsp:include page="include/menu-page.jsp" />

        <section class="banner-area relative">
            <div class="container">
                <div class="breadcrumb-banner d-flex flex-wrap align-items-center">
                    <div class="col-first">
                        <h1>User Page</h1>
                        <nav class="d-flex align-items-center justify-content-start">

                        </nav>
                    </div>
                </div>
            </div>
        </section>

        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

        <div class="container" style="margin-top: 150px;">
            <div class="row">

                <div class="col-md-12" style="text-align: center; margin-bottom: 20px;">
                    <h2>Welcome to T&T Shop </h2>
                    <h5><span style="color: #fe4c50;">Hello User : ${users.fullname}</span></h5>
                </div>

                <div class="col-md-12" style="text-align: center; margin-bottom: 20px;">
                    <button class="btn btn-danger" style="margin-top: 30px;"
                            onclick="location.href = '${contextPath}/updateAccount/${users.id}'">
                        <i class="fa fa-user" aria-hidden="true"></i>  Change personal information
                    </button>
                </div>


                <div class="col-md-12" style="text-align: center; margin-bottom: 20px;"> 
                    <button class="btn btn-danger" style="margin-top: 30px;" 
                            onclick="location.href = '${contextPath}/home'">
                        <i class="fa fa-user-secret" aria-hidden="true"></i>   Go to Home Page
                    </button>        
                </div>

                <div class="col-md-12" style="text-align: center; margin-bottom: 20px;">
                    <h2>Recently Added:</h2>
                </div>

                <!--         Check Out Giở Hàng                       --------------------------->


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
                                <h6>Color-Size</h6>
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
                                        <img src="<c:url value="/resources/img/${item.value.productOrderDetail.image.name}"/>"  WIDTH="150" HEIGHT="150" style="border:1px solid black""/>
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
                                    <h4>Color:${item.value.color}</h4>    
                                    <h4>Size:${item.value.size}</h4>  
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

                    <h3 style="color: #f9002f">${message} </h3>


                    <form action="<c:url value="/result-page" />" method="POST" >
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
                                <input class="form-control" type="text" name="cvcCode" maxlength="3" minlength="3" required >
                            </div>
                        </div>
                        <div >
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary" 
                                        style=" width: 150px">
                                    <i class="fas fa-credit-card"></i>&ensp;Payment
                                </button> 
                            </div>
                        </div>

                    </form>
                </div>


            </div>
        </div>


        <jsp:include page="include/footer-page.jsp" />
        <jsp:include page="include/js-page.jsp" />
    </body>
</html>
