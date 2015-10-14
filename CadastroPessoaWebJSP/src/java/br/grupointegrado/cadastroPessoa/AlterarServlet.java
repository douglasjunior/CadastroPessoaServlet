package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterarServlet extends HttpServlet {

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
                req.setAttribute("pessoa", pessoa);
            }
        }
        req.setAttribute("mensagem_erro", mensagemErro);
        req.getRequestDispatcher("/WEB-INF/alterar.jsp").forward(req, resp);
    }

    /**
     * Recebe a requisição POST do formulário e altera os dados da pessoa.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupera o ID vindo do campo oculto
        int id = stringParaInteger(req.getParameter("id"));

        PessoaDao dao = new PessoaDao();
        Pessoa pessoa = dao.getPessoaPorID(id);

        // recupera os parâmetros vindos do formulário
        String nome = req.getParameter("nome");
        int idade = stringParaInteger(req.getParameter("idade"));
        String time = req.getParameter("time");

        // seta os novos valores na pessoa
        pessoa.setNome(nome);
        pessoa.setIdade(idade);
        pessoa.setTime(time);

        // redireciona de volta para a página de consulta, passando o nome como busca
        resp.sendRedirect("ConsultaServlet?busca=" + pessoa.getNome());
    }

    private int stringParaInteger(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception ex) {
            return 0;
        }
    }
}
