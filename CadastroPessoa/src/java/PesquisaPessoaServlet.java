
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisaPessoaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramNome = (String) req.getAttribute("nome");
        String paramMobile = req.getParameter("mobile");

        System.out.println("Nome " + paramNome);
        System.out.println("Mobile? " + paramMobile);

        List<Pessoa> encontradas = Pessoas.getPessoa(paramNome);

        if ("true".equals(paramMobile)) {
            imprimirMobile(resp, encontradas);
        } else {
            imprimirFormularioHTML(resp, encontradas, paramNome);
        }
    }

    private void imprimirFormularioHTML(HttpServletResponse resp, List<Pessoa> encontradas, String paramNome) throws IOException {
        PrintWriter out = resp.getWriter();
        out.print("<h2>Pesquisa de Pessoas</h2>");
        out.print("<form method=\"GET\">");
        out.print("Nome: <input type=\"text\" name=\"nome\" value=\"" + (paramNome != null ? paramNome : "") + "\" /> ");
        out.print("<input type=\"submit\" value=\"Pesquisar\" /> <br /><br /> ");

        if (!encontradas.isEmpty()) {
            out.print("<table>");
            out.print("<tr>");
            out.print("<th>Código</th>");
            out.print("<th>Nome</th>");
            out.print("<th>Idade</th>");
            out.print("<th>Time</th>");
            out.print("<th>Opções</th>");
            out.print("</tr>");
            for (Pessoa pessoa : encontradas) {
                out.print("<tr>");
                out.print("<td>" + pessoa.getCodigo() + "</td>");
                out.print("<td>" + pessoa.getNome() + "</td>");
                out.print("<td>" + pessoa.getIdade() + "</td>");
                out.print("<td>" + pessoa.getTime() + "</td>");
                out.print("<td><a href=\"ExcluirPessoaServlet?codigo=" + pessoa.getCodigo() + "\" >Excluir </a>");
                out.print("<a href=\"AlterarPessoaServlet?codigo=" + pessoa.getCodigo() + "\" > Alterar</a></td>");
                out.print("</tr>");
            }
            out.print("</table>");
        }

        out.print("</form>");
    }

    private void imprimirMobile(HttpServletResponse resp, List<Pessoa> encontradas) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        for (Pessoa pessoa : encontradas) {
            out.print(pessoa.getCodigo() + "|");
            out.print(pessoa.getNome() + "|");
            out.print(pessoa.getIdade() + "|");
            out.println(pessoa.getTime());
        }
    }

}
