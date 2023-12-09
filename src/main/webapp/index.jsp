<%--
  Created by IntelliJ IDEA.
  User: Maxima
  Date: 29/11/2023
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Glecios-jsp</title>
</head>
<body>

<form action="ServletLogin" method="post">
    <input type="hidden" value="<%=request.getParameter("url")%>" name="url">
    <h1>Login</h1>

    <table>
        <tr>
            <td><label>Usuário</label></td>
            <td><input name="Login" type="text"></td>
        </tr>
        <tr>
            <td><label>Senha</label></td>
            <td><input name="Senha" type="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Entrar"></td>
        </tr>
    </table>
</form>

<h3>${mensagem}</h3>

</body>
</html>
