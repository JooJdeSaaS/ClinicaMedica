<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
    <label>Nome:</label>
    <input type="text" name="nome"><br><br>

    <label>Idade:</label>
    <input type="number" name="idade"><br><br>

    <label>Sintomas:</label><br>
    <textarea name="sintomas" rows="5" cols="30"></textarea><br><br>

    <input type="submit" value="Enviar">
</form>

</body>
</html>
