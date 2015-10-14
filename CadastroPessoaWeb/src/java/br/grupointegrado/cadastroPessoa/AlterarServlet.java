package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AlterarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        saida.println("<html>");
        saida.println("<head>");
        saida.println("<title>Alterar Pessoa</title>");
        saida.println("<style type=\"text/css\" >");
        saida.println("#principal { text-align : center }");
        saida.println("</style>");
        saida.println("</head>");
        saida.println("<body>");
        saida.println("<div id=\"principal\" >");

        saida.println("<p>Alteração dos dados da Pessoa</p>");

        int id = stringParaInteger(req.getParameter("id"));
        if (id <= 0) {
            saida.println("<p>O ID informado é inválido.</p>");
        } else {
            PessoaDao dao = new PessoaDao();
            Pessoa pessoa = dao.getPessoaPorID(id);

            if (pessoa == null) {
                saida.println("<p>Não foi encontrada uma pessoa com o ID informado.</p>");
            } else {
                saida.println("<form method=\"POST\" >");
                // criar um campo oculto para armazenar o ID
                saida.println("<input type=\"hidden\" name=\"id\" value=\"" + pessoa.getId() + "\" />");
                saida.println("<label>Nome: </label>");
                saida.println("<input type=\"text\" name=\"nome\" value=\"" + pessoa.getNome() + "\" />");
                saida.println("<br />");
                saida.println("<label>Idade: </label>");
                saida.println("<input type=\"text\" name=\"idade\" value=\"" + pessoa.getIdade() + "\" />");
                saida.println("<br />");
                saida.println("<label>Time: </label>");
                saida.println("<input type=\"text\" name=\"time\" value=\"" + pessoa.getTime() + "\" />");
                saida.println("<br /><br />");
                saida.println("<input type=\"submit\" value=\"Salvar\" />");

                saida.println("</form>");
            }
        }

        saida.println("<br />");
        saida.println("<a href=\"ConsultaServlet\" >Voltar</a>");

        saida.println("</div>");
        saida.println("</body>");
        saida.println("</html>");
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
        HttpSession sessao = req.getSession();
        String usuarioLogado = (String) sessao.getAttribute("usuario_logado");

        if (usuarioLogado == null) {
            resp.sendRedirect("LoginServlet");
            return;
        }

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
