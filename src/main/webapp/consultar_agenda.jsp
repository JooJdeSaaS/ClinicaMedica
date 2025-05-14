<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.mack.clinica.model.Consulta" %>
<%@ page import="com.mack.clinica.model.Usuario" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Consultar Agenda</title>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #f5f5f5;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
        }

        form.filtro-form {
            text-align: center;
            margin: 20px auto;
        }

        .cancelar-btn {
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }

        .cancelar-btn:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>

<div class="navbar">
    <div class="nav-links">
        <a href="admin_dashboard">Home</a>
        <a href="cadastroDePacientes">Cadastro de Pacientes</a>
        <a href="cadastroDeMedicos">Cadastro de Médicos</a>
        <a href="consultarAgenda">Consultar Agenda</a>
        <a href="fichaClinica">Ficha Clínica</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
</div>

<div class="content">
    <h1>Agenda de Consultas</h1>

    <form class="filtro-form" method="get" action="consultarAgenda">
        <label for="medico">Médico:</label>
        <select name="medicoId" id="medico">
            <option value="">Todos</option>
            <%
                List<Usuario> medicos = (List<Usuario>) request.getAttribute("medicos");
                if (medicos != null) {
                    for (Usuario medico : medicos) {
            %>
            <option value="<%= medico.getId() %>"><%= medico.getNome() %></option>
            <%
                    }
                }
            %>
        </select>
        <label for="paciente">Paciente:</label>
        <select name="pacienteId" id="paciente">
            <option value="">Todos</option>
            <%
                List<Usuario> pacientes = (List<Usuario>) request.getAttribute("pacientes");
                if (pacientes != null) {
                    for (Usuario p : pacientes) {
            %>
            <option value="<%= p.getId() %>"><%= p.getNome() %></option>
            <%
                    }
                }
            %>
        </select>

        <label for="data">Data:</label>
        <input type="date" name="data" id="data">
        <button type="submit">Filtrar</button>
    </form>

    <%
        List<Consulta> consultas = (List<Consulta>) request.getAttribute("consultas");
        if (consultas != null && !consultas.isEmpty()) {
    %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Paciente</th>
            <th>Médico</th>
            <th>Data e Hora</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for (Consulta c : consultas) { %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getNomePaciente() %></td>
            <td><%= c.getNomeProfissional() %></td>
            <td><%= c.getDataHora() %></td>
            <td><%= c.getStatus() %></td>
            <td>
                <% if (!"cancelada".equalsIgnoreCase(c.getStatus())) { %>
                <form method="post" action="consultarAgenda">
                    <input type="hidden" name="action" value="cancelar">
                    <input type="hidden" name="consultaId" value="<%= c.getId() %>">
                    <button class="cancelar-btn" type="submit">Cancelar</button>
                </form>
                <% } else { %>
                <em>Cancelada</em>
                <% } %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <%
    } else {
    %>
    <p style="text-align: center;">Nenhuma consulta encontrada.</p>
    <%
        }
    %>
</div>

</body>
</html>