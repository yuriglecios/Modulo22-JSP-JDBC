package servlets;

import dao.DaoUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

@WebServlet(urlPatterns = "/servletUsuarioController")
public class servletUsuarioController extends HttpServlet {
    private static final long serialVersionUID = 4914146356979458301L;

    private final DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();

    public servletUsuarioController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            var msg = "Cadastro realizado!";
            //id, nome, email, login, senha
            String id = req.getParameter("id");
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String login = req.getParameter("login");
            String senha = req.getParameter("senha");

            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
            modelLogin.setNome(nome);
            modelLogin.setEmail(email);
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);

            if (daoUsuarioRepository.validaUsuario(modelLogin.getLogin()) && modelLogin.getId() == null){
                msg = "Cadastro já existe no banco de dados";
            }
            else {
                modelLogin = daoUsuarioRepository.registrarUsuario(modelLogin);
            }

            req.setAttribute("msg", msg);
            req.setAttribute("modelLogin", modelLogin); // para manter os dados em tela
            req.getRequestDispatcher("principal/cadastroUsuario.jsp").forward(req,resp);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = req.getRequestDispatcher("erro.jsp");
            req.setAttribute("msg",e.getMessage());
            dispatcher.forward(req,resp);
        }

    }
}
