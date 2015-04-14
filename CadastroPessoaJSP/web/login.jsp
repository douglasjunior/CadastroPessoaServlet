<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h2>Informe os dados de Login</h2>
        <%
            String mensagemErro = (String) request.getAttribute("mensagem_erro");
            if (mensagemErro != null) {
        %>
        <p style="color:red"><%=mensagemErro%></p>
        <%
            }
        %>
        <form method="POST" action="LoginServlet" >
            Usuário: <input type="text" name="usuario" value="" /><br /><br />
            Senha: <input type="password" name="senha" value="" /><br /><br />
            Salvar dados: <input type="checkbox" name="salvarDados" /><br /><br />
            <input type="submit" value="Entrar" />
        </form>
    </body>
</html>
