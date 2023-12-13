<%--
  Created by IntelliJ IDEA.
  User: Maxima
  Date: 29/11/2023
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="em">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Glecios-jsp</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <style type="text/css">

            form{
                position: absolute;
                top: 50%;
                left: 30%;
            }

            h1{
                position: absolute;
                top: 40%;
                left: 45%;
            }
            h3{
                position: absolute;
                left: 35%;
                color: red;
            }
        </style>
    </head>
    <body>
    <h1>Login</h1>
        <form action="ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
            <input type="hidden" value="<%=request.getParameter("url")%>" name="url">
            <div class="mb-3">
                <label class="form-label">Usuário</label>
                <input name="Login" class="form-control" type="text" required>
                <div class="invalid-feedback">
                    Por favor, preencha o campo!
                </div>
                <div class="valid-feedback">
                    Ótimo!
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label">Senha</label>
                <input name="Senha" class="form-control" type="password" required>
                <div class="invalid-feedback">
                    Por favor, preencha o campo!
                </div>
                <div class="valid-feedback">
                    Ótimo!
                </div>
            </div>
            <input type="submit" value="Entrar" class="btn btn-primary">
        </form>


    <h3>${mensagem}</h3>




    <script type="text/javascript">
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (() => {
            'use strict'

            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            const forms = document.querySelectorAll('.needs-validation')

            // Loop over them and prevent submission
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
        })()
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
