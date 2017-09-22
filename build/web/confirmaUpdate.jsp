<%-- 
    Document   : confirmaUpdate
    Created on : 21/09/2017, 22:35:08
    Author     : osniellopesteixeira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualiza Conta</title>
        <% int situacao = (Integer) request.getAttribute("situacao"); %>
    </head>
    <body>
        
        <% if (situacao == 0) { %>
        <p>Não foi possível atualizar esta conta pois o número fornecido não coincide o de nenhuma existente.</p>
        <%
        } else {
            if (situacao != 1) { %>
        <p>Não foi possível atualizar a conta. Erro: <% out.println(situacao); %></p>
        <% } else {%>

        <p>Conta atualizada com sucesso!</p>
        <%}
            }%>
        <br>
        <a href="/Projeto-OsniHel/index.html">Voltar ao menu</a>
    </body>
</html>
