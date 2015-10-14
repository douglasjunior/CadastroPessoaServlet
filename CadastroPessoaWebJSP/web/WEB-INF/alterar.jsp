<%@page import="br.grupointegrado.cadastroPessoa.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
    Pessoa pessoa = (Pessoa) request.getAttribute("pessoa");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Pessoa</title>
        <style lang="text/css">
            #principal {
                text-align: center;
            }
            .erro {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Alterar Pessoa</h1>
        <div id="principal">
            <% if (mensagemErro != null) { %>
            <p class="erro"><% out.print(mensagemErro); %></p>
            <% } else { %>
            <form method="POST" action="AlterarServlet">
                <input type="hidden" name="id" value="<% out.print(pessoa.getId()); %>" />
                <label>Nome</label>
                <input type="text" name="nome" value="<% out.print(pessoa.getNome()); %>" />
                <br />
                <label>Idade</label>
                <input type="text" name="idade" value="<% out.print(pessoa.getIdade()); %>" />
                <br />
                <label>Time</label>
                <input type="text" name="time" value="<% out.print(pessoa.getTime()); %>" />
                <br />
                <input type="submit" value="Salvar" />
            </form>
            <% }%>
        </div>
        <a href="ConsultaServlet" >Voltar</a>
    </body>
</html>
