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
        String mensagemErro = null;
        int id = stringParaInteger(req.getParameter("id"));
        if (id <= 0) {
            mensagemErro = "O ID informado é inválido.";
        } else {
            PessoaDao dao = new PessoaDao();
            Pessoa pessoa = dao.getPessoaPorID(id);

            if (pessoa == null) {
                mensagemErro = "Não foi encontrada uma pessoa com o ID informado.";
            } else {
                dao.excluirPessoa(pessoa);
                mensagemErro = "A pessoa " + pessoa.getNome() + " foi excluída com sucesso!";
            }
        }
        req.setAttribute("mensagem_erro", mensagemErro);
        req.getRequestDispatcher("/WEB-INF/excluir.jsp").forward(req, resp);
    }

    private int stringParaInteger(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception ex) {
            return 0;
        }
    }
}
