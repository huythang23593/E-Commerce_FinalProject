<%-- 
    Document   : product-detail
    Created on : Jun 24, 2021, 3:56:09 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Single Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <jsp:include page="include/menu-page.jsp" />
        <section class="banner-area relative">
            <div class="container">
                <div class="breadcrumb-banner d-flex flex-wrap align-items-center">
                    <div class="col-first">
                        <h1>Single Product Page</h1>
                        <nav class="d-flex align-items-center justify-content-start">
                            <a href="home">Home<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                            <a href="product-detail">Single Product Page</a>
                        </nav>
                    </div>
                </div>
            </div>
        </section>

        <div class="container">
            <div class="product-quick-view">
                <div class="row align-items-center">
                    <div class="col-lg-6">
                        <div >
                            <img  src = "<c:url value="/resources/img/${detail.products.image.name}"/>" style="border:1px solid black" >
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="quick-view-content">
                            <div class="top">
                                <h3 class="head">${detail.products.name}/${detail.color.color}/${detail.size.size}</h3>
                                <div class="price d-flex align-items-center"><span class="lnr lnr-tag"></span> <span class="ml-10">${detail.products.price}</span></div>
                                <div class="category">Category: <span>${detail.products.category.name}</span></div>
                                <div class="category">Quantity <span>${detail.quantity}</span></div>
                            </div>
                            <c:choose>
                                <c:when test="${detail.quantity >= 1}">
                                    <div class="available">Availibility: <span>${detail.products.status}</span></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="available">Availibility: <span>OUT_STOCK</span></div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <!--                            <div class="middle">
                                                        <p class="content">${detail.products.description}</p>
                                                    </div>                  -->
                        <!--                             <form action="{pageContext.request.contextPath}/order/{product.productDetail[0].id}" method="POST">-->

                        <form action="${pageContext.request.contextPath}/updateProduct" method="GET">
                            <div class="row">
                                <div class="category"><span>&nbsp;&nbsp; Mã Sản Phẩm: &nbsp;&nbsp;</span> </div>
                                <input type="number" name="detailId" readonly="true" value="${detail.id}"   />
                                <input readonly name="productId" value="${detail.products.id}"  hidden="" />
                                <br>
                                <div class="row">                                    
                                    <div class="category"><span>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;Color: &nbsp;&nbsp;</span> </div>
                                    <div class="form-group">
                                        <select name="colorId" class="form-check"  >

                                            <c:forEach items="${distinctColor}" var="c">
                                                <c:if test="${detail.color.id == c.id}">
                                                    <option value="${c.id}" selected>
                                                        ${c.color}
                                                    </option>
                                                </c:if>
                                                <c:if test="${detail.color.id != c.id}">
                                                    <option value="${c.id}">
                                                        ${c.color}
                                                    </option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label>Size : &nbsp;&nbsp;</label> 
                                    <div class="form-group">
                                        <select name="sizeId" class="form-check" >

                                            <c:forEach items="${distinctSize}" var="s" >
                                                <c:if test="${detail.size.id == s.id}">
                                                    <option value="${s.id}" selected>
                                                        ${s.size}
                                                    </option>
                                                </c:if>
                                                <c:if test="${detail.size.id != s.id}">
                                                    <option value="${s.id}" >
                                                        ${s.size}
                                                    </option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div>
                                <div class="quantity-container d-flex align-items-center mt-15">
                                    Quantity:
                                    <input type="text" name="quantity" id="quantity" class="quantity-amount ml-15" value="1" />
                                    <div class="arrow-btn d-inline-flex flex-column">
                                        <button class="increase arrow" type="button" title="Increase Quantity"><span class="lnr lnr-chevron-up"></span></button>
                                        <button class="decrease arrow" type="button" title="Decrease Quantity"><span class="lnr lnr-chevron-down"></span></button>
                                    </div>

                                </div>


                                <div class="d-flex mt-20">
                                    <button class="btn btn-info" type="submit" >Update</button>
                                </div>



                            </div>
                            <c:choose>
                                <c:when test="${detail.quantity >= 1}">
                                    <input type="hidden" id="id" value="${detail.id}">
                                    <div class="d-flex mt-20">
                                        <a href="#" onclick="MyFunction();" class="view-btn color-2"><span>Add To Cart</span></a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="available">Availibility: <span>OUT_STOCK</span></div>
                                </c:otherwise>
                            </c:choose>
                        </form>

                    </div>
                </div>    
            </div>
            <h3 style="color: red" >${message}</h3>
        </div>
    </div>

</div>



<jsp:include page="include/footer-page.jsp" />
<jsp:include page="include/js-page.jsp" />
</body>
<script type="text/javascript">
function MyFunction() {
var quantity = document.getElementById("quantity").value;
var id = document.getElementById("id").value;

window.location.href = "${contextPath}/order/" + id + "/" + quantity;

}
</script>
</html>
