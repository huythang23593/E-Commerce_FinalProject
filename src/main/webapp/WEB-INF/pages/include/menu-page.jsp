<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" 
           prefix="sec" %>
<body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <header class="default-header">

        <!--    <div class="menutop-wrap">
                <div class="menu-top container">
                    <div class="d-flex justify-content-between align-items-center">
                        <ul class="list">
                            <li><a href="tel:+12312-3-1209">+12312-3-1209</a></li>
                            <li><a href="mailto:support@colorlib.com">support@colorlib.com</a></li>								
                        </ul>
                        <ul class="list">
                            <li><a href="login">login</a></li>
                        </ul>
                    </div>
                </div>		
        onclick="location.href = '<c:url value="/home"/>'"
            </div>-->


        <nav class="navbar navbar-expand-lg  navbar-light  ">
            <div class="container">
                <a class="navbar-brand" href="${contextPath}/home" >
                    <img src="<c:url value="/resources/img/logo1.png"/>" height="60" width="60" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end align-items-center" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li><a href="${contextPath}/home" > Home </a></li>
                        <li><a href="${contextPath}/product/0"> Shop </a></li>
                        <li><a href="#New">New</a></li>
                        <li><a href="#Favorite">Favorite</a></li>
                        <!-- Dropdown -->
                    </ul>       

                    <div>
                        <form action="${pageContext.request.contextPath}/search/0" method="POST"
                              class="form-inline float-right"  >
                            <div class="form-group">
                                <input name="strSearch" type="text" class="form-control" />
                                <input type="submit" value="search" class="btn btn-secondary"/>
                            </div>
                        </form>
                    </div>
                    &nbsp;&nbsp;
                    <div class="user-comment">
                        <a href="${contextPath}/login"  >
                            <div>
                                <img src="<c:url value="/resources/img/user.svg"/>" alt="https://www.flaticon.com/authors/freepik" width="30">
                            </div>
                        </a>
                    </div>
                    &nbsp;&nbsp;
                    <div class="cart">
                        <a href="${contextPath}/order"><div><img class="svg" src="<c:url value="/resources/img/cart.svg"/>" alt="https://www.flaticon.com/authors/freepik" width="30"></div>
                        </a>
                    </div>
                    <div class="logout">
                        <sec:authorize access="isAuthenticated()">
                            <a class="nav-link" data-toggle="modal" data-target="#exampleModal" href="#">
                                <i class="fa fa-fw fa-sign-out"></i>
                            </a>
                        </sec:authorize>
                    </div>
                </div>
            </div>						
        </nav>
    </header>
    <sec:authorize access="isAuthenticated()">
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <!--                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>-->
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="<c:url value="/logout" />">Logout</a>
                    </div>
                </div>
            </div>
        </div>  
    </sec:authorize>
</body>