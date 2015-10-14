package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConsultaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter saida = resp.getWriter();
        saida.println("<html>");
        saida.println("<head>");
        saida.println("<title>Consulta de Pessoas</title>");
        saida.println("</head>");
        saida.println("<body>");

        saida.println("<h1>Consulta de Pessoas</h1>");
        saida.println("<br />");
        saida.println("<p>Insira o nome da pessoa que deseja consultar.</p>");

        saida.println("<form>");
        saida.println("<label>Nome: </label>");
        saida.println("<input type=\"text\" name=\"busca\" value=\"\" />");
        saida.println("<input type=\"submit\" value=\"Buscar\" />");
        saida.println("</form>");

        saida.println("<br /><br /><br />");

        saida.println("<table>");
        saida.println("<tr>");
        saida.println("<th>Nome</th>");
        saida.println("<th>Idade</th>");
        saida.println("<th>Time</th>");
        saida.println("<th>Opções</th>");
        saida.println("</tr>");

        String busca = req.getParameter("busca");

        if (busca != null && !busca.isEmpty()) {

            PessoaDao dao = new PessoaDao();
            for (Pessoa p : dao.getPessoasPorNome(busca)) {

                saida.println("<tr>");
                saida.println("<td>" + p.getNome() + "</td>");
                saida.println("<td>" + p.getIdade() + "</td>");
                saida.println("<td>" + p.getTime() + "</td>");
                saida.println("<td>");

                saida.println("<a href=\"ExcluirServlet?id=" + p.getId() + "\" >Excluir </a>");
                saida.println("<a href=\"AlterarServlet?id=" + p.getId() + "\" >Alterar </a>");

                saida.println("</td>");

                saida.println("</tr>");

            }

        }

        saida.println("</table>");

        saida.println("</body>");
        saida.println("</html>");
    }

}
