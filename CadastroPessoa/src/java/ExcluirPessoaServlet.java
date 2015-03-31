
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcluirPessoaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramCodigo = req.getParameter("codigo");
        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Excluir Pessoas</title>");
        out.print("</head>");
        out.print("<body>");
        if (paramCodigo != null) {
            int codigo = Integer.parseInt(paramCodigo);
            Pessoa pessoa = Pessoas.getPessoa(codigo);
            Pessoas.remover(pessoa);
            out.print("<h2>Pessoa " + pessoa.getNome() + " excluída com sucesso!</h2>");
        } else {
            out.print("<h2>Código incorreto</h2>");
        }
        out.print("</body>");
        out.print("</html>");
    }

}
