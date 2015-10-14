package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Setvlet que faz a Exclusão das pessoas
 *
 * @author dougl
 */
public class ExcluirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        saida.println("<html>");
        saida.println("<head>");
        saida.println("<title>Excluir pessoas</title>");
        saida.println("</head>");
        saida.println("<body>");

        int id = stringParaInteger(req.getParameter("id"));
                
        if (id <= 0) {
            saida.println("<p>O ID informado é inválido.</p>");
        } else {
            PessoaDao dao = new PessoaDao();
            Pessoa pessoa = dao.getPessoaPorID(id);
            
            if (pessoa == null) {
                saida.println("<p>Não foi encontrada uma pessoa com o ID informado.</p>");
            } else {
                dao.excluirPessoa(pessoa);    
                saida.println("<p>A pessoa " + pessoa.getNome() + " foi excluída com sucesso!</p>");
            }
        }

        saida.println("<br />");
        saida.println("<a href=\"ConsultaServlet\" >Voltar</a>");

        saida.println("</body>");
        saida.println("</html>");
    }

    private int stringParaInteger(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception ex) {
            return 0;
        }
    }
}
