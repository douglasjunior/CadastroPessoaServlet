package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramMobile = req.getParameter("mobile");
        String paramUsuario = req.getParameter("usuario");
        String paramSenha = req.getParameter("senha");
        String paramSalvarDados = req.getParameter("salvarDados");
        if (USUARIO.equals(paramUsuario) && SENHA.equals(paramSenha)) {
            if ("on".equals(paramSalvarDados)) {
                Cookie cookieUsuario = new Cookie("usuario", paramUsuario);
                cookieUsuario.setMaxAge(86400);
                Cookie cookieSenha = new Cookie("senha", paramSenha);
                cookieSenha.setMaxAge(86400);
                resp.addCookie(cookieUsuario);
                resp.addCookie(cookieSenha);
            }
            HttpSession sessao = req.getSession();
            sessao.setAttribute("usuario_logado", true);
            if (!"true".equals(paramMobile)) {
                // faz o redrect somente se não for requisição Android
                resp.sendRedirect("pesquisaPessoa.jsp");
            }
        } else {
            if (!"true".equals(paramMobile)) {
                // retorna erro para a Web
                req.setAttribute("mensagem_erro", "Usuário ou senha incorretos.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                // retorna erro "401 UNAUTHRORIZED" para o Android
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário ou senha incorretos.");
            }
        }
    }

    private static final String USUARIO = "admin";
    private static final String SENHA = "12345";

}
