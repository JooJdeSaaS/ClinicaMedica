<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.mack.clinica.model.Consulta" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Minha Agenda</title>
    <!-- Importa o CSS externo -->
    <link rel="stylesheet" href="/css/minha_agenda.css">
</head>
<body>
<video autoplay muted loop id="bg-video">
    <source src="subway.mp4" type="video/mp4">
</video>
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
<div class="content-container">
    <h2>Consultas Agendadas</h2>

    <c:choose>
        <c:when test="${not empty consultas}">
            <div class="consultas-grid">
                <c:forEach var="consulta" items="${consultas}">
                    <div class="bloco-consulta">
                        <div class="consulta-header">
                            <div class="consulta-id">Consulta #${consulta.id}</div>
                            <div class="consulta-status">${consulta.status}</div>
                        </div>
                        <div class="consulta-body">
                            <table class="consulta-table">
                                <tr>
                                    <td class="info-label">Paciente:</td>
                                    <td class="info-value">${sessionScope.nome}</td>
                                </tr>
                                <tr>
                                    <td class="info-label">Profissional:</td>
                                    <td class="info-value">${consulta.nomeProfissional}</td>
                                </tr>
                                <tr>
                                    <td class="info-label">Data/Hora:</td>
                                    <td class="info-value">${consulta.dataHora}</td>
                                </tr>
                            </table>
                        </div>
                        <div class="consulta-footer">
                            <div class="observacoes-label">Observações:</div>
                            <div class="observacoes-content">${consulta.observacoes}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="no-consultas">
                <p>Você não tem nenhuma consulta agendada.</p>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>