<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
int numero = 5;
%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Meu Primeiro JSP</title>
    </head>
    <body>
        <h1>Meu Primeiro JSP</h1>
        <p>
            <%
                numero = 10;
                for (int i = 0; i < 10; i++) {
                    out.println(i + "<br />");
                }
            %>
        </p>
    </body>
</html>
