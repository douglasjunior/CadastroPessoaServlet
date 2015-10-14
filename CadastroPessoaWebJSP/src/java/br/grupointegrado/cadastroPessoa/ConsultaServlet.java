package br.grupointegrado.cadastroPessoa;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultaServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String busca = req.getParameter("busca");
        PessoaDao dao = new PessoaDao();
        List<Pessoa> pessoas = dao.getPessoasPorNome(busca);
        
        req.setAttribute("pessoas", pessoas);
        req.getRequestDispatcher("/WEB-INF/consulta.jsp").forward(req, resp);
    }
    
}
