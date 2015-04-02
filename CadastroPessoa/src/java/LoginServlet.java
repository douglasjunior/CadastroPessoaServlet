
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuarioSalvo = "";
        String senhaSalva = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("usuario".equals(cookie.getName())) {
                    usuarioSalvo = cookie.getValue();
                }
                if ("senha".equals(cookie.getName())) {
                    senhaSalva = cookie.getValue();
                }
                System.out.println(cookie.getName() + " / " + cookie.getValue());
            }
        }
        imprimirFormulario(resp, null, usuarioSalvo, senhaSalva);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramUsuario = req.getParameter("usuario");
        String paramSenha = req.getParameter("senha");
        String paramSalvarDados = req.getParameter("salvarDados");
        System.out.println("Salvar dados? " + paramSalvarDados);
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
            
            resp.sendRedirect("PesquisaPessoaServlet");
        } else {
            imprimirFormulario(resp, "Usuário ou senha incorretos.", "", "");
        }
    }

    private static final String USUARIO = "admin";
    private static final String SENHA = "12345";

    private void imprimirFormulario(HttpServletResponse resp, String mensagem, String usuarioSalvo, String senhaSalva) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Login</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h2>Informe os dados de Login</h2>");
        if (mensagem != null) {
            out.print("<p style=\"color:red\">");
            out.print(mensagem);
            out.print("</p>");
        }
        out.print("<form method=\"POST\">");
        out.print("Usuário: <input type=\"text\" name=\"usuario\" value=\"" + usuarioSalvo + "\" /><br /><br />");
        out.print("Senha: <input type=\"password\" name=\"senha\" value=\"" + senhaSalva + "\" /><br /><br />");
        out.print("Salvar dados: <input type=\"checkbox\" name=\"salvarDados\""
                + (usuarioSalvo != null && !usuarioSalvo.isEmpty() ? "checked" : "") + " /><br /><br />");
        out.print("<input type=\"submit\" value=\"Entrar\" />");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");
    }

}
