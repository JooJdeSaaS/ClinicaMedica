<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Painel do Administrador</title>
    <!-- Importa o CSS externo -->
    <link rel="stylesheet" href="/css/paciente_dashboard.css">
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

    <!-- Conteúdo principal -->
    <div class="content">
        <h1>Painel do Administrador</h1>
        <p>Bem-vindo ao painel administrativo. Aqui você poderá gerenciar pacientes e consultas.</p>
    </div>

</body>
</html>

