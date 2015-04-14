package br.grupointegrado.cadastroPessoa;


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

        List<Pessoa> encontradas = Pessoas.getPessoa(paramNome);

        if ("true".equals(paramMobile)) {
            imprimirMobile(resp, encontradas);
        } else {
            req.setAttribute("encontradas", encontradas);
            req.getRequestDispatcher("pesquisaPessoa.jsp").forward(req, resp);
        }
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
