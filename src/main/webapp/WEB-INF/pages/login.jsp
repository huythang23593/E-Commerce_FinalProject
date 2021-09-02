<%-- 
    Document   : login
    Created on : Jun 20, 2019, 8:17:26 PM
    Author     : AnhLe
--%>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <jsp:include page="include/menu-page.jsp" />

        <!-- Start Banner Area -->
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
        <!-- End Banner Area -->

        <!-- Start My Account -->

        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="login-form">
                        <h3 class="billing-title text-center">Login</h3>
                        <p class="text-center mt-80 mb-40">Welcome back! Sign in to your account </p>
                        <c:if test="${message != null && message != ''}">
                            <p style="color: red">${message}</p>
                        </c:if>
                        <form action="<c:url value="j_spring_security_check"/>" method="post">
                            <input type="email" placeholder="Email*" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email*'" required class="common-input mt-20" name="username">
                            <input type="password" placeholder="Password*" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password*'" required class="common-input mt-20" name="password">
                            <button type="submit" class="view-btn color-2 mt-20 w-100"><span>Login</span></button>
                            <div class="mt-20 d-flex align-items-center justify-content-between">
                                <div class="d-flex align-items-center"><input type="checkbox" class="pixel-checkbox" id="login-1"><label for="login-1">Remember me</label></div>
                                <a href="#">Lost your password?</a>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="register-form">
                        <h3 class="billing-title text-center">Register</h3>
                        <p class="text-center mt-40 mb-30">Create your very own account </p>
                        <mvc:form action="${pageContext.request.contextPath}/result" method="POST" modelAttribute="account">
                            <input name="fullname" type="text" placeholder="Full name*" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Full name*'" required class="common-input mt-20">
                            <input name="email" type="email" placeholder="Email *" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email *'" required class="common-input mt-20">
                            <input name="phoneNumber" type="text" placeholder="Phone number*" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Phone number*'" required class="common-input mt-20">
                            <input name="address" type="text" placeholder="Adress*" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Adress*'" required class="common-input mt-20">
                            <input name="password" type="password" placeholder="Password*" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password*'" required class="common-input mt-20">
                            <div style="margin: 10px;display: inline-block;color: white" >
                                <p>Gender:</p><input type="radio" name="gender" value="Male" checked> Male &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="gender" value="FeMale">FeMale
                            </div>
                            <button class="view-btn color-2 mt-20 w-100"><span>Register</span></button>


                        </mvc:form>>
                    </div>
                </div>
            </div>
        </div>
        <!-- End My Account -->

        <!-- Start Most Search Product Area -->
        <section class="section-half">
            <div class="container">
                <div class="organic-section-title text-center">
                    <h3>Most Searched Products</h3>
                </div>
                <div class="row mt-30">

                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single-search-product d-flex">
                            <a href="#"><img src="img/r5.jpg" alt=""></a>
                            <div class="desc">
                                <a href="#" class="title">Pixelstore Bell Pepper</a>
                                <div class="price"><span class="lnr lnr-tag"></span> $120.00</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single-search-product d-flex">
                            <a href="#"><img src="img/r6.jpg" alt=""></a>
                            <div class="desc">
                                <a href="#" class="title">Pixelstore fresh Blackberry</a>
                                <div class="price"><span class="lnr lnr-tag"></span> $120.00</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single-search-product d-flex">
                            <a href="#"><img src="img/r7.jpg" alt=""></a>
                            <div class="desc">
                                <a href="#" class="title">Pixelstore fresh Brocoli</a>
                                <div class="price"><span class="lnr lnr-tag"></span> $120.00</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single-search-product d-flex">
                            <a href="#"><img src="img/r8.jpg" alt=""></a>
                            <div class="desc">
                                <a href="#" class="title">Pixelstore fresh Carrot</a>
                                <div class="price"><span class="lnr lnr-tag"></span> $120.00</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single-search-product d-flex">
                            <a href="#"><img src="img/r9.jpg" alt=""></a>
                            <div class="desc">
                                <a href="#" class="title">Pixelstore fresh Strawberry</a>
                                <div class="price"><span class="lnr lnr-tag"></span> $240.00</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single-search-product d-flex">
                            <a href="#"><img src="img/r10.jpg" alt=""></a>
                            <div class="desc">
                                <a href="#" class="title">Prixma MG2 Light Inkjet</a>
                                <div class="price"><span class="lnr lnr-tag"></span> $240.00</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single-search-product d-flex">
                            <a href="#"><img src="img/r11.jpg" alt=""></a>
                            <div class="desc">
                                <a href="#" class="title">Pixelstore fresh Cherry</a>
                                <div class="price"><span class="lnr lnr-tag"></span> $240.00 <del>$340.00</del></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="single-search-product d-flex">
                            <a href="#"><img src="img/r12.jpg" alt=""></a>
                            <div class="desc">
                                <a href="#" class="title">Pixelstore fresh Beans</a>
                                <div class="price"><span class="lnr lnr-tag"></span> $240.00 <del>$340.00</del></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Most Search Product Area -->
        <jsp:include page="include/footer-page.jsp" />
        <jsp:include page="include/js-page.jsp" />


</html>
