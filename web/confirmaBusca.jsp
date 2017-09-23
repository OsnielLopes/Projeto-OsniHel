<%-- 
    Document   : confirmaBusca
    Created on : 22/09/2017, 19:17:03
    Author     : osniellopesteixeira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado da Busca</title>
        <% String conta =(String) request.getAttribute("conta");
        String saldo =(String) request.getAttribute("saldo");
        %>
    </head>
    <body>
        <h3>Resultado da busca:</h3>
        <% if (conta.isEmpty() || saldo.isEmpty() || conta.equals("0") || saldo == null) { %>
        <p>Conta não encontrada.</p>
        <%
        } else {  %>
        <p>Conta: <% out.println(conta); %></p>
        <p>Saldo <% out.println(saldo); %></p>
        <%}%>
        <br>
        <a href="/Projeto-OsniHel/index.html">Voltar ao menu</a>
    </body>
</html>
