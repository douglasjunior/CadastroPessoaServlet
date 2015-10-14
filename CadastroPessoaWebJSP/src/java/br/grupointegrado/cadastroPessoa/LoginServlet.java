package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    private static final String USUARIO = "douglas";
    private static final String SENHA = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        preencherFormulario(req, resp, null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String senha = req.getParameter("senha");
        String salvarLogin = req.getParameter("salvarLogin");

        // verifica se o usuário e senha estão corretos
        if (USUARIO.equals(usuario) && SENHA.equals(senha)) {
            // verifica se o usuário marcou o checkbox
            if ("on".equals(salvarLogin)) {
                Cookie cUsuario = new Cookie("usuario", usuario);
                cUsuario.setMaxAge(Integer.MAX_VALUE);
                Cookie cSenha = new Cookie("senha", senha);
                cSenha.setMaxAge(Integer.MAX_VALUE);
                resp.addCookie(cUsuario);
                resp.addCookie(cSenha);
            }
            
            HttpSession sessao = req.getSession();
            sessao.setAttribute("usuario_logado", usuario);
            
            resp.sendRedirect("ConsultaServlet");
        } else {
            preencherFormulario(req, resp, "Usuário ou senha incorretos.");
        }
    }

    private void preencherFormulario(HttpServletRequest req, HttpServletResponse resp, String mensagemErro) throws IOException, ServletException {
        String usuario = "";
        String senha = "";

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("usuario")) {
                    usuario = c.getValue();
                }
                if (c.getName().equals("senha")) {
                    senha = c.getValue();
                }
            }
        }

        req.setAttribute("usuario", usuario);
        req.setAttribute("senha", senha);
        req.setAttribute("mensagem_erro", mensagemErro);
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

}
