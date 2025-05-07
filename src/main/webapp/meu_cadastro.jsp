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
<div class="cadastro-container">
    <h2>Trocar informações</h2>
    <form method="post" action="meuCadastro">

        <div class="form-group">
            <% String nome = (String) session.getAttribute("nome"); %>
            <label for="nomenovo">Nome:</label>
            <div class="current-value"> <%= nome %> </div>
            <p class="change-label">Trocar para:</p>
            <input type="text" id="nomenovo" name="nome" value="${sessionScope.nome}" required>
        </div>

        <div class="form-group">
            <!--TODO Porra professor, celular como float foi foda-->
            <%
                String celular = (String) session.getAttribute("celular");
            %>
            <label for="celularnovo">Celular:</label>
            <div class="current-value"><%= celular %></div>
            <p class="change-label">Trocar para:</p>
            <input type="text" id="celularnovo" name="celular" value="<%= celular %>" required>
        </div>

        <div class="form-group">
            <%
                String CPF = (String) session.getAttribute("cpf");
            %>
            <label for="CPF">CPF:</label>
            <div class="current-value"><%= CPF %></div>
            <p class="change-label">Trocar para:</p>
            <input type="text" id="CPF" name="CPF" value="<%= CPF %>" required>
        </div>

        <div class="form-group">
            <%
                String email = (String) session.getAttribute("email");
            %>
            <label for="emailnovo">E-mail:</label>
            <div class="current-value"><%= email %></div>
            <p class="change-label">Trocar para:</p>
            <input type="text" id="emailnovo" name="emailnovo" value="<%= email %>" required>
        </div>

        <button type="submit">Mudar</button>
    </form>
</div>

</body>
</html>