<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String usuario = (String) request.getAttribute("usuario");
    String senha = (String) request.getAttribute("senha");
    String mensagemErro = (String) request.getAttribute("mensagem_erro");

    String salvarLogin = "";
    if (usuario != null) {
        salvarLogin = "checked=\"checked\"";
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Entre com os dados de Login.</h1>
        <br />
        <% if (mensagemErro != null) {%>
        <p style="color:red"><% out.print(mensagemErro); %></p>
        <% }%>
        <br />
        <form method="POST" action="LoginServlet">
            <label>Usuário</label>
            <input type="text" name="usuario" value="<% out.print(usuario); %>" />
            <br />
            <label>Senha</label>
            <input type="password" name="senha" value="<% out.print(senha); %>" />
            <br />
            <input type="checkbox" name="salvarLogin"  <% out.print(salvarLogin); %>  />
            <label>Salvar login.</label>
            <br />
            <input type="submit" value="Entrar" />
        </form>
    </body>
</html>
