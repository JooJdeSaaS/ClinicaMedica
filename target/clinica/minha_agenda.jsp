<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mack.clinica.model.Consulta" %>
<%
    List<Consulta> consultas = (List<Consulta>) request.getAttribute("consultas");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Minha Agenda</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; }
        .container { background: white; padding: 20px; border-radius: 8px; max-width: 800px; margin: auto; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 10px; border-bottom: 1px solid #ccc; }
        th { background-color: #f0f0f0; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Consultas Agendadas</h2>

        <% if (consultas != null && !consultas.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Profissional ID</th>
                        <th>Data e Hora</th>
                        <th>Status</th>
                        <th>Observações</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Consulta c : consultas) { %>
                        <tr>
                            <td><%= c.getId() %></td>
                            <td><%= c.getProfissionalId() %></td>
                            <td><%= c.getDataHora() %></td>
                            <td><%= c.getStatus() %></td>
                            <td><%= c.getObservacoes() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>Nenhuma consulta encontrada.</p>
        <% } %>
    </div>
</body>
</html>
