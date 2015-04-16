package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterarPessoaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramCodigo = req.getParameter("codigo");
        if (paramCodigo != null && !paramCodigo.isEmpty()) {
            int codigo = Integer.parseInt(paramCodigo);
            Pessoa pessoa = Pessoas.getPessoa(codigo);
            if (pessoa != null) {
                req.setAttribute("pessoa", pessoa);
            } else {
                req.setAttribute("mensagem_erro", "Pessoa não encontrada.");
            }
        } else {
            req.setAttribute("mensagem_erro", "Código incorreto.");
        }
        req.getRequestDispatcher("alterarPessoa.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramCodigo = req.getParameter("codigo");
        String paramNome = req.getParameter("nome");
        String paramIdade = req.getParameter("idade");
        String paramTime = req.getParameter("time");
        System.out.println(paramCodigo + paramNome + paramIdade + paramTime);
        if (paramCodigo != null) {
            int codigo = Integer.parseInt(paramCodigo);
            Pessoa pessoa = Pessoas.getPessoa(codigo);
            pessoa.setNome(paramNome);
            pessoa.setIdade(paramIdade != null ? Integer.parseInt(paramIdade) : 0);
            pessoa.setTime(paramTime);
        }

        resp.sendRedirect("pesquisaPessoa.jsp");
    }

}
