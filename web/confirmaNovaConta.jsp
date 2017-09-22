<%-- 
    Document   : confirmaNovaConta
    Created on : 21/09/2017, 22:27:04
    Author     : osniellopesteixeira
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nova Conta</title>
        <% int situacao = (Integer) request.getAttribute("situacao"); %>
    </head>
    <body>
        <% if (situacao == 30000) { %>
        <p>Não foi possível criar esta conta pois o número fornecido coincide com o de outra já existente.</p>
        <%
        } else {
            if (situacao != 1) { %>
            <p>Não foi possível inserir a conta. Erro: <% out.println(situacao); %></p>
            <% } else {%>

        <p>Conta inserida com sucesso!</p>
        <%}
                }%>
        <br>
        <a href="/Projeto-OsniHel/index.html">Voltar ao menu</a>
    </body>
</html>
