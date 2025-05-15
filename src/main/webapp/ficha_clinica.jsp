<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mack.clinica.model.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Painel do Administrador</title>
    <link rel="stylesheet" href="/css/ficha_clinica.css">
</head>
<body>

<!-- Menu de Navegação -->
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

<div class="form-container">
    <h2>Ficha Clínica</h2>
    <form action="fichaClinica" method="post">
        <div class="form-group">
            <label for="profissional_id">Profissional:</label>
            <select id="profissional_id" name="profissional_id" required>
                <option value="">Selecione o médico</option>
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
        </div>

        <div class="form-group">
            <label for="paciente_id">CPF do paciente:</label>
            <input type="number" name="paciente_id" id="paciente_id" required>
        </div>

        <div class="form-group">
            <label for="data">Data:</label>
            <input type="date" name="data" id="data" required>
        </div>

        <div class="form-group">
            <label for="anotacoes_medicas">Anotações Médicas:</label>
            <input type="text" name="anotacoes_medicas" id="anotacoes_medicas">
        </div>

        <div class="form-group">
            <label for="prescricoes">Prescrições:</label>
            <input type="text" name="prescricoes" id="prescricoes">
        </div>

        <input type="submit" value="Enviar">
    </form>
</div>

</body>
</html>
