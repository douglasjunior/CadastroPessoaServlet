<%@page import="br.grupointegrado.cadastroPessoa.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Pessoa pessoa = (Pessoa) request.getAttribute("pessoa");
    String mensagemErro = (String) request.getAttribute("mensagem_erro");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar pessoas</title>
        <script type="text/javascript">
            function validaFormulario() {
                var nome = this.formulario.nome.value;
                if (nome == null || nome.length == 0) {
                    alert("Preencha o nome da pessoa.");
                    return false;
                }
                var idade = this.formulario.idade.value;
                if (idade == null || idade.length == 0) {
                    alert("Preencha a idade da pessoa.");
                    return false;
                }
                var time = this.formulario.time.value;
                if (time == null || time.length == 0) {
                    alert("Preencha o time da pessoa.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <% if (mensagemErro == null) { %>
        <h2>Alterar pessoas</h2><br />
        <form name="formulario" method="POST" action="AlterarPessoaServlet" onsubmit="return validaFormulario();">
            Código: <input type="text" name="codigo" value="<%=pessoa.getCodigo()%>" readonly="readonly" /><br /> 
            Nome: <input type="text" name="nome" value="<%=pessoa.getNome()%>" /><br /> 
            Idade: <input type="text" name="idade" value="<%=pessoa.getIdade()%>" /><br /> 
            Time: <input type="text" name="time" value="<%=pessoa.getTime()%>" /><br /> 
            <input type="submit" value="Salvar" /> <br /><br /> 
        </form>
        <% } else {%>
        <h2><%=mensagemErro%></h2> 
        <% }%>
    </body>
</html>
