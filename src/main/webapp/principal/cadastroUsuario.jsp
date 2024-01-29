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
                                                    <div class="col-sm-12">
                                                        <!-- Basic Form Inputs card start -->
                                                        <div class="card">
                                                            <div class="card-block">

                                                                <h4 class="sub-title">Formulário de cadastro</h4>
                                                                <form class="form-material" action="<%= request.getContextPath() %>/servletUsuarioController" method="post">
                                                                    <div class="form-group form-default">
                                                                        <input type="text" name="id" id="id" class="form-control"  readonly="readonly" value="${modelLogin.id}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">ID:</label>
                                                                    </div>

                                                                    <div class="form-group form-default">
                                                                        <input type="email" name="email" id="email" class="form-control" required="required" value="${modelLogin.email}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">Email:</label>
                                                                    </div>

                                                                    <div class="form-group form-default">
                                                                        <input type="text" name="login" id="nome" class="form-control" required="required" value="${modelLogin.login}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">Login:</label>
                                                                    </div>

                                                                    <div class="form-group form-default">
                                                                        <input type="password" name="senha" id="senha" class="form-control" required="required" value="${modelLogin.senha}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">Senha:</label>
                                                                    </div>

                                                                    <div class="form-group form-default">
                                                                        <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLogin.nome}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">Nome completo:</label>
                                                                    </div>
                                                                    <button class="btn btn-success waves-effect waves-light">Salvar</button>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
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

