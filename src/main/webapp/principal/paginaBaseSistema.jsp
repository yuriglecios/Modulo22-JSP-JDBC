<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Maxima
  Date: 08/12/2023
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
<!-- Pre-loader start -->
<jsp:include page="theme-loader.jsp"></jsp:include>

<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">

        <jsp:include page="menuSuperior.jsp"></jsp:include>

            <div class="pcoded-main-container">
                <div class="pcoded-wrapper">

                    <jsp:include page="menuLateral.jsp"></jsp:include>

                        <div class="pcoded-content">

                            <jsp:include page="pageHeader.jsp"></jsp:include>

                                <div class="pcoded-inner-content">
                                    <!-- Main-body start -->
                                    <div class="main-body">
                                        <div class="page-wrapper">
                                            <!-- Page-body start -->
                                            <div class="page-body">
                                                <div class="row">

                                                </div>
                                            </div>
                                            <!-- Page-body end -->
                                        </div>
                                        <div id="styleSelector"> </div>
                                    </div>
                            </div>
                    </div>
                </div>
            </div>
    </div>
</div>

<jsp:include page="scriptsDaPagina.jsp"></jsp:include>

</body>

</html>

