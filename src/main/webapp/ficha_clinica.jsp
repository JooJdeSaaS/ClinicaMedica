<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Painel do Administrador</title>
    <link rel="stylesheet" href="/css/style.css">
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

<h2>Ficha Clínica</h2>

<form action="fichaClinica" method="post">



    <label for="profissional_id">Selecionar Médico:</label>
    <select name="profissional_id" id="profissional_id" required>
        <option value="">-- Selecione um médico --</option>
        <c:forEach var="medico" items="${medicos}">
            <option value="${medico.id}">${medico.nome}</option>
        </c:forEach>
    </select>
    <label>Data:</label>
    <input type="date" name="data"><br><br>



    <label>Anotacoes Medicas:</label>
    <input type="text" name="anotacoes_medicas"><br><br>

    <label>Prescricoes:</label>
    <input type="text" name="prescricoes"><br><br>

    <input type="submit" value="Enviar">
</form>

</body>
</html>
