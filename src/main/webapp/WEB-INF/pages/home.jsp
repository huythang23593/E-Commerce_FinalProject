<%-- 
    Document   : home1
    Created on : Jun 12, 2021, 7:58:27 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>


        <jsp:include page="include/menu-page.jsp" />
        <section class="banner-area relative" id="home">
            <div class="container-fluid">
                <div class="row fullscreen align-items-center justify-content-center">
                    <div class="col-lg-4 col-md-12 d-flex align-self-end img-right no-padding">
                        <img class="img-fluid" src="<c:url value="/resources/img/1200px-UEFA_Euro_2020_Logo.svg.png" />"   alt="">
                    </div>
                    <div class="banner-content col-lg-6 col-md-12">
                        <h1 class="title-top"><span>Discount up to</span> 50%Off</h1>
                        <h1 class="text-uppercase">
                            It’s Happening <br>
                            this Euro2020 season!
                        </h1>
                        <button class="primary-btn text-uppercase"><a href="${contextPath}/product/0">Shopping Now</a></button>
                    </div>							
                </div>
            </div>
        </section>

        <section class="category-area section-gap section-gap" id="catagory">
            <div class="container">
                <div class="row d-flex justify-content-center">
                    <div class="menu-content pb-40">
                        <div class="title text-center">
                            <h1 class="mb-10">Shop for Different Categories</h1>

                        </div>
                    </div>
                </div>					
                <div class="row">
                    <div class="col-lg-12 col-md-12 mb-10">
                        <div class="row category-bottom">
                            <div class="col-lg-4 col-md-4 mb-30">
                                <div class="content">
                                    <a href="${contextPath}/product/AdidasShose/0"> <!target="_blank" : new page product ==>
                                        <div class="content-overlay"></div>
                                        <img class="content-image img-fluid d-block mx-auto" src=<c:url value="/resources/img/adidas7.jfif"/> alt="" style="border:1px solid black">
                                        <div class="content-details fadeIn-bottom">
                                            <h3 class="content-title">Product of Adidas</h3>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 mb-30">
                                <div class="content">
                                    <a href="${contextPath}/product/NikeShoes/0">
                                        <div class="content-overlay"></div>
                                        <img class="content-image img-fluid d-block mx-auto" src=<c:url value="/resources/img/nike8.jfif"/>  alt="" style="border:1px solid black">
                                        <div class="content-details fadeIn-bottom">
                                            <h3 class="content-title">Product of Nike</h3>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 mb-30">
                                <div class="content">
                                    <a href="${contextPath}/product/Jerseys/0" >
                                        <div class="content-overlay"></div>
                                        <img class="content-image img-fluid d-block mx-auto" src=<c:url value="/resources/img/AoCategory.jpg"/> alt=""  style="border:1px solid black">
                                        <div class="content-details fadeIn-bottom">
                                            <h3 class="content-title">Product For Jerseys</h3>
                                        </div>
                                    </a>
                                </div>
                            </div>        

                        </div>	
                    </div>

                </div>
            </div>	
        </section>
        <!--                                        New Product-->
        <section class="men-product-area section-gap relative" id="New">
            <div class="overlay overlay-bg"></div>
            <div class="container">
                <div class="row d-flex justify-content-center">
                    <div class="menu-content pb-40">
                        <div class="title text-center">
                            <h1 class="text-white mb-10">New products </h1>
                            <p class="text-white">Who are in extremely love with eco friendly system.</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!--                    C:ForEarch-->
                    <c:forEach var="newP" items="${newP}" begin="0" end="3">
                        <div class="col-lg-3 col-md-6 single-product">
                            <div class="content">
                                <div class="content-overlay"></div>
                                <img  src = "<c:url value="resources/img/${newP.image.name}"/>" style="border:1px solid black" >
                                <div class="content-details fadeIn-bottom">
                                    <div class="bottom d-flex align-items-center justify-content-center">
                                        <a href="#"><span class="lnr lnr-heart"></span></a>
                                        <a href="${contextPath}/product-detail/${newP.productDetail[0].id}"><span class="lnr lnr-layers"></span></a>
                                        <a href="${contextPath}/addorder/${newP.productDetail[0].id}/0"><span class="lnr lnr-cart"></span></a>
                                        <a href="#" data-toggle="modal" data-target="#exampleModal"><span class="lnr lnr-frame-expand"></span></a>
                                    </div>
                                </div>
                            </div>
                            <div class="price">
                                <h5>${newP.name}</h5>

                                <h3>
                                    ${newP.productDetail[0].price}$
                                </h3>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </section>
        <!-- End New-product Area -->

        <!-- Start Favorite-product Area -->
        <section class="women-product-area section-gap" id="Favorite">
            <div class="container">
                <div class="countdown-content pb-40">
                    <div class="title text-center">
                        <h1 class="mb-10">Favorite products</h1>
                        <p>Who are in extremely love with eco friendly system.</p>
                    </div>
                </div>
                <div class="row">
                    <!--                    C:ForEarch-->
                    <c:forEach var="fav" items="${favorite}" >
                        <div class="col-lg-3 col-md-6 single-product">
                            <div class="content">
                                <div class="content-overlay"></div>
                                <img  src = "<c:url value="resources/img/${fav.image.name}"/>" style="border:1px solid black">
                                <div class="content-details fadeIn-bottom">
                                    <div class="bottom d-flex align-items-center justify-content-center">
                                        <a href="#"><span class="lnr lnr-heart"></span></a>
                                        <a href="${contextPath}/product-detail/${fav.productDetail[0].id}"><span class="lnr lnr-layers"></span></a>
                                        <a href="${contextPath}/addorder/${fav.productDetail[0].id}/0"><span class="lnr lnr-cart"></span></a>
                                        <a href="#" data-toggle="modal" data-target="#exampleModal"><span class="lnr lnr-frame-expand"></span></a>
                                    </div>
                                </div>
                            </div>
                            <div class="price">
                                <h5>${fav.name}</h5>
                                <h3>
                                    ${fav.productDetail[0].price}$
                                </h3>
                            </div>
                        </div>

                    </c:forEach>

                </div>
            </div>	
        </section>

        <jsp:include page="include/footer-page.jsp" />
        <jsp:include page="include/js-page.jsp" />
    </body>
</html>
