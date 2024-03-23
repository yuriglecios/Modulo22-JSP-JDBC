package servlets;

import connections.SingleConnection;
import dao.DaoUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

public class ServletGenericUtil extends HttpServlet implements Serializable {

    private static final long serialVersionUID = -70866098017546464L;

    private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();

    public Long getUsuarioLogado(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        return daoUsuarioRepository.consultaUsuario(usuario).getId();
    }

}
