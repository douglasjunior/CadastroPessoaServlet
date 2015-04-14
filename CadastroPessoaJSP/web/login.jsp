<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String usuario = "";
    String senha = "";
    if (request.getCookies() != null){
        for (Cookie c : request.getCookies()) {
            if ("usuario".equals(c.getName())) {
                usuario = c.getValue();
            }
            if ("senha".equals(c.getName())) {
                senha = c.getValue();
            }
        }
    }
    %>
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
            Usuário: <input type="text" name="usuario" value="<%=usuario%>" /><br /><br />
            Senha: <input type="password" name="senha" value="<%=senha%>" /><br /><br />
            Salvar dados: <input type="checkbox" name="salvarDados" <%= usuario != null ? "checked" : "" %> /><br /><br />
            <input type="submit" value="Entrar" />
        </form>
    </body>
</html>
