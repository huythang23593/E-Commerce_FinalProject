<%-- 
    Document   : products
    Created on : Jun 28, 2021, 10:56:50 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="img/favicon.png">

        <title>Manage Products</title>
        <jsp:include page="../include/management/admin-css.jsp"/>
    </head>
    <body>
        <jsp:include page="../include/management/admin-header-css.jsp"/>
        <jsp:include page="../include/management/seller-aside.jsp"/>
        <section id="main-content">
            <section class="wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <section class="panel">
                            <header class="panel-heading">
                                Manage Products
                            </header>

                            <table class="table table-striped table-advance table-hover">
                                <tbody>
                                    <tr>
                                        <th><i class="icon_profile"></i> Full Name</th>
                                        <th><i class="icon_calendar"></i> Date</th>
                                        <th><i class="icon_mail_alt"></i> Email</th>
                                        <th><i class="icon_pin_alt"></i> City</th>
                                        <th><i class="icon_mobile"></i> Mobile</th>
                                        <th><i class="icon_cogs"></i> Action</th>
                                    </tr>
                                    <tr>
                                        <td>Angeline Mcclain</td>
                                        <td>2004-07-06</td>
                                        <td>dale@chief.info</td>
                                        <td>Rosser</td>
                                        <td>176-026-5992</td>
                                        <td>
                                            <div class="btn-group">
                                                <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                                                <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                                                <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                                            </div>
                                        </td>
                                    </tr>
                                    
                                </tbody>
                            </table>
                        </section>
                    </div>
                </div>
            </section>
        </section>
        <jsp:include page="../include/management/admin-javascript.jsp"/>
    </body>
</html>
