<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excluir Pessoa</title>
    </head>
    <body>
        <p>
            <% out.print(mensagemErro);%>
        </p>
        <br />
        <a href="ConsultaServlet">Voltar</a>
    </body>
</html>
