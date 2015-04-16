package br.grupointegrado.cadastroPessoa;

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
        if (paramCodigo != null) {
            int codigo = Integer.parseInt(paramCodigo);
            Pessoa pessoa = Pessoas.getPessoa(codigo);
            if (pessoa != null) {
                Pessoas.remover(pessoa);
                req.setAttribute("mensagem_erro", "Pessoa " + pessoa.getNome() + " excluída com sucesso!");
            } else {
                req.setAttribute("mensagem_erro", "Pessoa não encontrada.");
            }
        } else {
            req.setAttribute("mensagem_erro", "Código incorreto.");
        }
        req.getRequestDispatcher("excluirPessoa.jsp").forward(req, resp);
    }

}
