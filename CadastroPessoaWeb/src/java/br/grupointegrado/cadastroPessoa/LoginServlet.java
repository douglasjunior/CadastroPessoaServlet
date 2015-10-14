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

    private void preencherFormulario(HttpServletRequest req, HttpServletResponse resp, String mensagemErro) throws IOException {
        PrintWriter saida = resp.getWriter();
        saida.println("<html>");
        saida.println("<head>");
        saida.println("<title>Efetue Login</title>");
        saida.println("</head>");
        saida.println("<body>");

        if (mensagemErro != null) {
            saida.println("<p style=\"color : red\">" + mensagemErro + "</p>");
        }

        String usuario = "";
        String senha = "";
        String checkbox = "";

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("usuario")) {
                    usuario = c.getValue();
                    checkbox = " checked=\"checked\" ";
                }
                if (c.getName().equals("senha")) {
                    senha = c.getValue();
                }
            }
        }

        saida.println("<form method=\"POST\">");

        saida.println("<label for=\"usuario\">Login: </label>");
        saida.println("<input type=\"text\" name=\"usuario\" id=\"usuario\" value=\"" + usuario + "\" />");
        saida.println("<br />");
        saida.println("<label for=\"senha\">Senha: </label>");
        saida.println("<input type=\"password\" name=\"senha\" id=\"senha\" value=\"" + senha + "\" />");
        saida.println("<br />");
        saida.println("<input type=\"checkbox\" name=\"salvarLogin\" id=\"salvarLogin\"  " + checkbox + "  />");
        saida.println("<label for=\"salvarLogin\">Salvar usuário e senha.</label>");
        saida.println("<br />");
        saida.println("<input type=\"submit\" value=\"Entrar\" />");
        saida.println("</form>");

        saida.println("</body>");
        saida.println("</html>");
    }

}
