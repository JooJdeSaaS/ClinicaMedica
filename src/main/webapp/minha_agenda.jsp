<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Painel do Paciente</title>
    <!-- Importa o CSS externo -->
    <link rel="stylesheet" href="/css/meu_cadastro.css">
</head>
<body>

<!-- Menu de Navegação -->
<div class="navbar">
    <div class="nav-links">
        <a href="paciente_dashboard">Home</a>
        <a href="agendarConsulta">Agendamento de Consultas</a>
        <a href="minhaAgenda">Minha Agenda</a>
        <a href="meuCadastro">Meu Cadastro</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
</div>

<!-- Conteúdo principal -->
<h2>Consultas Agendadas</h2>

<c:choose>
    <c:when test="${not empty consultas}">
        <c:forEach var="consulta" items="${consultas}">
            <div class="bloco-consulta">
                <p><strong>ID:</strong> ${consulta.id}</p>
                <p><strong>ID do Paciente:</strong> ${consulta.pacienteId}</p>
                <p><strong>ID do Profissional:</strong> ${consulta.profissionalId}</p>
                <p><strong>Data e Hora:</strong> ${consulta.dataHora}</p>
                <p><strong>Status:</strong> ${consulta.status}</p>
                <p><strong>Observações:</strong> ${consulta.observacoes}</p>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>Você não tem nenhuma consulta agendada.</p>
    </c:otherwise>
</c:choose>
</body>
</html>