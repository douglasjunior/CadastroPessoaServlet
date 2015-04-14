
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterarPessoaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String paramCodigo = req.getParameter("codigo");
        if (paramCodigo != null) {
            int codigo = Integer.parseInt(paramCodigo);
            Pessoa pessoa = Pessoas.getPessoa(codigo);
            if (pessoa != null) {
                out.print("<h2>Alterar Pessoas</h2><br />");

                out.print("<form method=\"POST\">");
                out.print("Código: <input type=\"text\" name=\"codigo\" value=\"" + pessoa.getCodigo() + "\" disabled=\"disabled\" /><br /> ");
                out.print("Nome: <input type=\"text\" name=\"nome\" value=\"" + pessoa.getNome() + "\" /><br /> ");
                out.print("Idade: <input type=\"text\" name=\"idade\" value=\"" + pessoa.getIdade() + "\" /><br /> ");
                out.print("Time: <input type=\"text\" name=\"time\" value=\"" + pessoa.getTime() + "\" /><br /> ");

                out.print("<input type=\"submit\" value=\"Salvar\" /> <br /><br /> ");
                out.print("</form>");
            } else {
                out.print("<h2>Pessoa não encontrada.</h2>");
            }
        } else {
            out.print("<h2>Código incorreto</h2>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramCodigo = req.getParameter("codigo");
        String paramNome = req.getParameter("nome");
        String paramIdade = req.getParameter("idade");
        String paramTime = req.getParameter("time");

        if (paramCodigo != null) {
            int codigo = Integer.parseInt(paramCodigo);
            Pessoa pessoa = Pessoas.getPessoa(codigo);
            pessoa.setNome(paramNome);
            pessoa.setIdade(paramIdade != null ? Integer.parseInt(paramIdade) : 0);
            pessoa.setTime(paramTime);
        }

        resp.sendRedirect("PesquisaPessoaServlet");
    }

}
