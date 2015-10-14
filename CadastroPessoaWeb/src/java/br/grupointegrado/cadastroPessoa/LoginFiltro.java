package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        System.out.println("LoginFiltro.doFilter() " + req.getServletPath());

        // verifica se a requisição não é para o Servlet de Login
        if (!req.getServletPath().equals("/LoginServlet")) {
            HttpSession sessao = req.getSession();
            String usuarioLogado = (String) sessao.getAttribute("usuario_logado");
            // verifica se o usuário está logado
            if (usuarioLogado == null) {
                resp.sendRedirect("LoginServlet");
                return;
            }
        }
        // se o usuário está logado ou acessando a página de Login, 
        // então encaminha a requisição para o próximo
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
