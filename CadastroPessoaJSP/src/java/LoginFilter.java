/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Douglas
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        boolean acessandoLogin = httpRequest.getServletPath().equals("/LoginServlet");
//
//        HttpSession sessao = httpRequest.getSession();
//        Boolean usuarioLogado = (Boolean) sessao.getAttribute("usuario_logado");
//        if (acessandoLogin || (usuarioLogado != null && usuarioLogado == true)) {
//            chain.doFilter(request, response);
//        } else {
//            httpResponse.sendRedirect("LoginServlet");
//        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
