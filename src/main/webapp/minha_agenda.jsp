<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.mack.clinica.model.AgendarConsultaDAO, com.mack.clinica.model.Consulta" %>

<%
    String pacienteIdStr = request.getParameter("pacienteId");
    int pacienteId = 0;
    List<Consulta> consultas = new ArrayList<>();

    if (pacienteIdStr != null && !pacienteIdStr.isEmpty()) {
        try {
            pacienteId = Integer.parseInt(pacienteIdStr);

            String realPathBase = application.getRealPath("/WEB-INF/db.db");
            AgendarConsultaDAO dao = new AgendarConsultaDAO(realPathBase);
            consultas = dao.listarConsultasDoPaciente(pacienteId);
        } catch (Exception e) {
            out.println("<p class='erro'>Erro ao buscar consultas: " + e.getMessage() + "</p>");
        }
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Minha Agenda</title>
    <link rel="stylesheet" href="/css/style.css">
    <div class="navbar">
        <div class="nav-links">
            <a href="paciente_dashboard">Home</a>
            <a href="agendarConsulta">Agendamento de Consultas</a>
            <a href="minha_agenda.jsp">Minha Agenda</a>
            <a href="meu_cadastro.jsp">Meu Cadastro</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>
</head>
<body>

<main class="container">
    <section class="section">
        <h1>Minha Agenda</h1>

        <% if (consultas.isEmpty()) { %>
            <p>Nenhuma consulta agendada para o paciente ID <%= pacienteId %>.</p>
        <% } else { %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Data e Hora</th>
                        <th>Status</th>
                        <th>Observações</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Consulta consulta : consultas) { %>
                        <tr>
                            <td><%= consulta.getId() %></td>
                            <td><%= consulta.getDataHora() %></td>
                            <td><%= consulta.getStatus() %></td>
                            <td><%= consulta.getObservacoes() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>

    </section>
</main>

</body>
</html>
