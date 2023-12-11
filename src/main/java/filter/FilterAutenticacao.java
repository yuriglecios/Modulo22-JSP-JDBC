package filter;

import connections.SingleConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {

    private static Connection connection;

    public FilterAutenticacao() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        connection = SingleConnection.getConnection();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {

            HttpServletRequest httpReq = (HttpServletRequest) request;
            HttpSession session = httpReq.getSession();
            String usuario = (String) session.getAttribute("usuario");
            String urlParaAutenticar = httpReq.getServletPath(); // url que está sendo acessada

            if (usuario == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin") ){

                RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
                request.setAttribute("mensagem", "Relize o login primeiro");
                redirecionar.forward(request,response);
                return;

            }else {

                chain.doFilter(request,response);

            }

            connection.commit();

        } catch (Exception e) {

            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }



    }

    @Override
    public void destroy() {

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
