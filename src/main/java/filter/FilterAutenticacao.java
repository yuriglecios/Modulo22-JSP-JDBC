package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {
    public FilterAutenticacao() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

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

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
