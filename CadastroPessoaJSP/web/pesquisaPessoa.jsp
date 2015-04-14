<%@page import="br.grupointegrado.cadastroPessoa.Pessoa"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Pessoas</title>
        <script>
            function excluirPessoa(var codigo){
                
            }
        </script>
    </head>
    <body>
        <h2>Pesquisa de Pessoas</h2>
        <form method="GET" action="PesquisaPessoaServlet" >
            Nome: <input type="text" name="nome" value="" />
            <input type="submit" value="Pesquisar" /> <br /><br />
        </form>

        <%
            List<Pessoa> encontradas
                    = (List<Pessoa>) request.getAttribute("encontradas");
            if (encontradas != null && !encontradas.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Idade</th>
                <th>Time</th>
                <th></th>
            </tr>
            <%
                for (Pessoa pessoa : encontradas) {
            %>
            <tr>
                <td><%=pessoa.getCodigo()%></td>
                <td><%=pessoa.getNome()%></td>
                <td><%=pessoa.getIdade()%></td>
                <td><%=pessoa.getTime()%></td>
                <td>
                    <a href="AlterarPessoaServlet?codigo=<%=pessoa.getCodigo()%>" >Alterar</a><span> </span>
                    <a href="#" onclick="excluirPessoa(<%=pessoa.getCodigo()%>)" >Excluir</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>
    </body>
</html>
