<%-- 
    Document   : product
    Created on : Jun 24, 2021, 2:15:21 PM
    Author     : Admin
--%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <jsp:include page="include/menu-page.jsp" />

        <section class="banner-area relative">
            <div class="container">
                <div class="breadcrumb-banner d-flex flex-wrap align-items-center">
                    <div class="col-first">
                        <h1>Shop Category page</h1>
                        <nav class="d-flex align-items-center justify-content-start">
                            <a href="home">Home<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                            <a href="category.html">Product Category</a>
                        </nav>
                    </div>
                </div>
            </div>
        </section>

        <div class="container ">
            <div class="row">
                <div class="col-xl-9 col-lg-8 col-md-7">
                    <div class="filter-bar d-flex flex-wrap align-items-center">
                        <div class="sidebar">
                            <div class="sidebar_section">
                                <div class="sidebar_title" >
                                    <h4>Product Category:</h4>
                                </div>
                                <ul class="sidebar_categories" style="margin-left: 10px" >
                                    <!--<li class="active"><a href="${contextPath}/courses"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>All courses</a></li>-->
                                    <li>
                                        <i class="fa fa-th"></i><a href="${contextPath}/product/0" class="list-btn" style="color: black"> All Product</a>
                                        <i class="fa fa-long-arrow-right"></i><a href="${contextPath}/product/AdidasShose/0" class="list-btn" style="color: black"> Adidas.</a>
                                        <i class="fa fa-long-arrow-right"></i><a href="${contextPath}/product/NikeShoes/0" class="list-btn" style="color: black"> Nike.</a>
                                        <i class="fa fa-long-arrow-right"></i><a href="${contextPath}/product/Jerseys/0" class="list-btn" style="color: black"> Jerseys.</a>
                                    </li>
                                </ul>
                            </div>

                        </div>

                    </div>
                    <h3 style="color: red">  ${message}</h3>
                    <section class="lattest-product-area pb-40 category-list">
                        <div class="row">
                            <c:forEach var="p" items="${products}">
                                <div class="col-xl-4 col-lg-6 col-md-12 col-sm-6 single-product">

                                    <div class="content">
                                        <div class="content-overlay"></div>
                                        <img  src = "<c:url value="/resources/img/${p.image.name}"/>" style="border:1px solid black" >
                                        <div class="content-details fadeIn-bottom">
                                            <div class="bottom d-flex align-items-center justify-content-center">
                                                <a href="#"><span class="lnr lnr-heart"></span></a>
                                                <a href="${contextPath}/product-detail/${p.productDetail[0].id}"><span class="lnr lnr-layers"></span></a>
                                                <a href="${contextPath}/addorder/${p.productDetail[0].id}/0"><span class="lnr lnr-cart"></span></a>
                                                <a href="#" data-toggle="modal" data-target="#exampleModal"><span class="lnr lnr-frame-expand"></span></a>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="price">
                                        <h5>${p.name}</h5>
                                        <h3>
                                            ${p.productDetail[0].price}$
                                        </h3>
                                    </div>
                                </div>

                            </c:forEach>
                        </div>

                        <c:choose>
                            <c:when test="${products == null || fn:length(products) <= 0}">
                                <h2 style="color: red">No Value !!!</h2>
                            </c:when>
                            <c:otherwise>
                                <div class="pagination">
                                    <a href="#" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>

                                    <c:forEach var="c" begin="1" end="${count}">
                                        <c:choose>
                                            <c:when test="${c==1}">
                                                <a href="${contextPath}/product/${c-1}" class="active">${c}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${contextPath}/product/${c-1}" class="active">${c}</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <a href="#" class="dot-dot"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>
                                    <a href="#" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                </div>
                            </c:otherwise>
                        </c:choose>

<!--                        <c:if test="${products == null || fn:length(products) <= 0}">
                            <h2 style="color: red">No Value !!!</h2>
                        </c:if>

                        <c:if test="${products != null || fn:length(products) > 0}">
                            <div class="pagination">
                                <a href="#" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>

                                <c:forEach var="c" begin="1" end="${count}">
                                    <c:choose>
                                        <c:when test="${c==1}">
                                            <a href="${contextPath}/product/${c-1}" class="active">${c}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${contextPath}/product/${c-1}" class="active">${c}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <a href="#" class="dot-dot"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></a>
                                <a href="#" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                            </div>
                        </c:if>
-->

                    </section>

                </div>
                <div class="col-xl-3 col-lg-4 col-md-5">
                    <div class="sidebar-categories">
                        <div class="head">Browse Categories</div>
                        <ul class="main-categories">
                            <div class="head">Price</div>    

                            <li class="main-nav-list child"><a href="${contextPath}/search-price/${1}/${25}">1$ To 25$<span class="number"></span></a></li>
                            <li class="main-nav-list child"><a href="${contextPath}/search-price/${26}/${50}">26$ To 50$<span class="number"></span></a></li>
                            <li class="main-nav-list child"><a href="${contextPath}/search-price/${51}/${99}">51$ To 99$<span class="number"></span></a></li>
                            <li class="main-nav-list child"><a href="${contextPath}/search-price/${100}/${150}">100$ To 150$<span class="number"></span></a></li>



                        </ul>
                    </div>
                    <div class="sidebar-filter mt-50">
                        <div class="top-filter-head">Product Filters</div>
                        <div class="common-filter">
                            <div class="head">Active Filters</div>
                            <ul>
                                <li class="filter-list"><i class="fa fa-window-close" aria-hidden="true"></i>Gionee (29)</li>
                                <li class="filter-list"><i class="fa fa-window-close" aria-hidden="true"></i>Black with red (09)</li>
                            </ul>
                        </div>
                        <div class="common-filter">
                            <div class="head">Brands</div>
                            <form action="#">
                                <ul>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="apple" name="brand"><label for="apple">Adidas<span>(29)</span></label></li>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="asus" name="brand"><label for="asus">Nike<span>(29)</span></label></li>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="gionee" name="brand"><label for="gionee">Kappa<span>(19)</span></label></li>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="micromax" name="brand"><label for="micromax">Under Armour<span>(19)</span></label></li>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="samsung" name="brand"><label for="samsung">Puma<span>(19)</span></label></li>
                                </ul>
                            </form>
                        </div>
                        <div class="common-filter">
                            <div class="head">Color</div>
                            <form action="#">
                                <ul>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="black" name="color"><label for="black">Black<span>(29)</span></label></li>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="balckleather" name="color"><label for="balckleather">Black Leather<span>(29)</span></label></li>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="blackred" name="color"><label for="blackred">Black with red<span>(19)</span></label></li>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="gold" name="color"><label for="gold">Gold<span>(19)</span></label></li>
                                    <li class="filter-list"><input class="pixel-radio" type="radio" id="spacegrey" name="color"><label for="spacegrey">Spacegrey<span>(19)</span></label></li>
                                </ul>
                            </form>
                        </div>
                        <div class="common-filter">
                            <div class="head">Price</div>
                            <div class="price-range-area">
                                <div id="price-range"></div>
                                <div class="value-wrapper d-flex">
                                    <div class="price">Price:</div>
                                    <span>$</span><div id="lower-value"></div> <div class="to">to</div> 
                                    <span>$</span><div id="upper-value"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>







        <jsp:include page="include/footer-page.jsp" />
        <jsp:include page="include/js-page.jsp" />
    </body>
</html>
