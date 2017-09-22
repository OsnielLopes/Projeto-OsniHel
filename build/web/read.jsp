<%--
Document : index
Created on : 07/04/2014, 20:49:49
--%>
<%@page import="dao.Conta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8"> <title>JSP Page</title>
    </head>
    <body>
        <h1>Contas</h1> 
        <% List<Conta> contas
                    = (List<Conta>) request.getAttribute("lista_contas");
        %>
        <%if (contas.size() > 0) { %>
        <table>
            <% for (Conta c : contas) {%> <tr>
                <td><%=c.getNumero()%></td>
                <td><%=c.getSaldo()%></td> </tr>
                <%}%>
        </table>
        <%}%>
    </body>
</html>