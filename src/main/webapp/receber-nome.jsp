<%--
  Created by IntelliJ IDEA.
  User: Maxima
  Date: 03/12/2023
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Receber-nome</title>
</head>
<body>

<%
    String nome = request.getParameter("nome");
    out.println(nome);

    String idade = request.getParameter("idade");
    out.println(idade);
%>

</body>
</html>
