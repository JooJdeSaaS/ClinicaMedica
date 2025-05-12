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
    <style>
        .form-container {
            background-color: white;
            padding: 30px;
            max-width: 600px;
            margin: 120px auto 40px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            animation: fadeIn 0.5s ease forwards;
        }

        .form-container h2 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: 600;
            margin-bottom: 6px;
            color: #444;
        }

        input[type="text"],
        input[type="date"],
        input[type="number"],
        select {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 15px;
        }

        input[type="submit"] {
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 600;
            transition: background 0.3s ease;
            width: 100%;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background: linear-gradient(to right, #5a67d8, #6b46c1);
        }
    </style>
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
