<%@page import="java.util.List"%>
<%@page import="br.grupointegrado.cadastroPessoa.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Pessoa> pessoas = (List<Pessoa>) request.getAttribute("pessoas");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de Pessoas</title>
    </head>
    <body>
        <h1>Consulta de Pessoas</h1>
        <br />
        <form method="GET" action="ConsultaServlet">
            <label>Busca</label>
            <input type="text" name="busca" value="" />
            <input type="submit" value="Buscar" />
        </form>
        <br />
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Idade</th>
                <th>Time</th>
                <th>Opções</th>
            </tr>
            <!-- preencher tabela -->
            <% for (Pessoa p : pessoas) { %>

            <tr>
                <td><% out.print(p.getNome()); %></td>
                <td><% out.print(p.getIdade()); %></td>
                <td><% out.print(p.getTime()); %></td>
                <td>
                    <a href="ExcluirServlet?id=<% out.print(p.getId()); %>" > Excluir </a>
                    <a href="AlterarServlet?id=<% out.print(p.getId()); %>" > Alterar </a>  
                </td>
            </tr>

            <% } %>
        </table>
    </body>
</html>
