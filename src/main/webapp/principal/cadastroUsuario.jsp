<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Maxima
  Date: 08/12/2023
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                                                <form class="form-material" action="<%= request.getContextPath() %>/servletUsuarioController" method="post" id="formUser">

                                                                    <input type="hidden" name="acao" id="acao" value="">

                                                                    <div class="form-group form-default form-static-label">
                                                                        <input type="text" name="id" id="id" class="form-control"  readonly="readonly" value="${modelLogin.id}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">ID:</label>
                                                                    </div>

                                                                    <div class="form-group form-default form-static-label">
                                                                        <input type="email" name="email" id="email" class="form-control" required="required" value="${modelLogin.email}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">Email:</label>
                                                                    </div>

                                                                    <div class="form-group form-default form-static-label">
                                                                        <input type="text" name="login" id="nome" class="form-control" required="required" value="${modelLogin.login}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">Login:</label>
                                                                    </div>

                                                                    <div class="form-group form-default form-static-label">
                                                                        <input type="password" name="senha" id="senha" class="form-control" required="required" value="${modelLogin.senha}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">Senha:</label>
                                                                    </div>

                                                                    <div class="form-group form-default form-static-label">
                                                                        <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLogin.nome}">
                                                                        <span class="form-bar"></span>
                                                                        <label class="float-label">Nome completo:</label>
                                                                    </div>
                                                                    <button class="btn btn-success waves-effect waves-light" >Salvar</button>
                                                                    <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
                                                                    <button type="button" class="btn btn-danger waves-effect waves-light" onclick="deletaComAjax();">Deletar</button>
                                                                    <!-- Butão tipo modal -->
                                                                    <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal"> Botão modal
                                                                    </button>
                                                                </form>
                                                                <span id="msg">${msg}</span>
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

<!-- Estrutura do modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Pesquisa de usuário</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                Pesquisa por nome do usuário
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Exemplo: Maria" aria-label="pesquisaNome" id="nomeBusca" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-success" type="button" onclick="buscarUsuario()">Pesquisar</button>
                    </div>
                </div>

                <div style="height: 200px; overflow: scroll">
                    <table class="table table-dark" id="resultadoUsuario">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Email</th>
                            <th scope="col">Ver</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <span id="total"></span>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="scriptsDaPagina.jsp"></jsp:include>
<script type="text/javascript">

    function limparForm(){
        let elemento = document.getElementById("formUser").elements
        for (p = 0; p < elemento.length; p ++){
            elemento[p].value = '';
        }
    }

    function deletaComAjax(){
        if (confirm("Tem certeza que vai excluir?")){
            let urlAction = document.getElementById("formUser").action;
            let idUser = document.getElementById("id").value;
            $.ajax({

                method: 'get',
                url: urlAction,
                data: "id=" + idUser + '&acao=deletarAjax',
                success: function (response){
                    limparForm();
                    document.getElementById("msg").textContent = response;
                }

            }).fail(function (xhr, status, errorThrown){

                alert('erro ao deletar o usuário ' +xhr.responseText )

            });
        }
    }

    /*function deletar(){
        if (confirm("Tem certeza que vai excluir?")){
            document.getElementById("formUser").method = 'get';
            document.getElementById("acao").value = "deletar";
            document.getElementById("formUser").submit();
        }
    }
    */

    function buscarUsuario(){
        let nomeUsuario = document.getElementById('nomeBusca').value;
        let urlAction = document.getElementById("formUser").action;
        if (nomeUsuario != null && nomeUsuario != '' && nomeUsuario.trim() != ''){
            $.ajax({
                method: 'get',
                url: urlAction,
                data: 'nomeUsuario=' + nomeUsuario + '&acao=buscaUsuarioAjax',
                success: function (response){
                    let json = JSON.parse(response)
                    $('#resultadoUsuario > tbody > tr').remove();
                    for (let pos = 0; pos < json.length; pos ++){
                        $('#resultadoUsuario > tbody').append('<tr> <td> '+json[pos].id+' </td> <td> '+json[pos].nome+' </td> <td> <button onclick="verEditar('+ json[pos].id +')" type="button" class="btn btn-outline-info btn-sm">Abrir</button> </td> </tr>')
                    }
                    document.getElementById('total').textContent = 'Total de resultado: ' + json.length();
                }

            }).fail(function (xhr, status, errorThrown){
                alert('Ocorreu um erro inesperado.')
            })

        }
    }

    function verEditar(id){
        let urlAction = document.getElementById('formUser').action;
        window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
    }

</script>
</body>

</html>

