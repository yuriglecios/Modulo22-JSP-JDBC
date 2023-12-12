package servlets;

import dao.LoginDAORepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {

    private LoginDAORepository loginDAORepository = new LoginDAORepository();


    public ServletLogin(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("Login");
        String senha = req.getParameter("Senha");
        String url = req.getParameter("url");

        try {

            if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()){

                ModelLogin usuarioLogin = new ModelLogin();
                usuarioLogin.setLogin(login);
                usuarioLogin.setSenha(senha);

                if (loginDAORepository.validaLogin(usuarioLogin)){

                    req.getSession().setAttribute("usuario", usuarioLogin.getLogin());

                    if (url == null || url.equals("null")){

                        url = "principal/principal.jsp";

                    }

                    RequestDispatcher redirecionar = req.getRequestDispatcher(url);
                    redirecionar.forward(req,resp);

                } else {

                    RequestDispatcher redirecionar = req.getRequestDispatcher("/index.jsp");
                    req.setAttribute("mensagem","Usuário ou senha errado!");
                    redirecionar.forward(req,resp);

                }
            } else {

                RequestDispatcher redirecionar = req.getRequestDispatcher("index.jsp");
                req.setAttribute("mensagem","Faça login primeiramente");
                redirecionar.forward(req,resp);

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
