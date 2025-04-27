<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Painel do Paciente</title>
    <!-- Importa o CSS externo -->
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

    <!-- Menu de Navegação -->
    <div class="navbar">
        <div class="nav-links">
            <a href="paciente_dashboard">Home</a>
            <a href="agendarConsulta">Agendamento de Consultas</a>
            <a href="minha_agenda.jsp">Minha Agenda</a>
            <a href="meu_cadastro.jsp">Meu Cadastro</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>

    <!-- Conteúdo principal -->
<!-- TODO verificar se a unica maneira de passar as informações é pelo jeito que o chat passou ${sessionScope.nome} -->
    <div class="login-container">
        <h2>Trocar informações</h2>
        <form method="post" action="loginAction">

            <label for="nome">Nome:</label>
            <p>${sessionScope.nome}</p>
            <p> trocar para:</p>
            <input type="text" id="nomenovo" name="nomenova" required>

            <label for="telefone">Telefone:</label>
            <p>${sessionScope.telefone}</p>
            <p> trocar para:</p>
            <input type="text" id="telefone" name="telefone" required>

            <label for="CPF">CPF:</label>
            <p>${sessionScope.cpf}</p>
            <p> trocar para:</p>
            <input type="text" id="CPF" name="CPF" required>

            <label for="email">E-mail:</label>
            <p> email da pessoa</p>
            <p> trocar para:</p>
            <input type="text" id="emailnovo" name="emailnova" required>

            <label for="senha">Senha:</label>
            <p> Senha da pessoa</p>
            <p> trocar para:</p>
            <input type="text" id="senhanova" name="senhanova" required>

            <button type="submit">Mudar</button>
        </form>
    </div>

</body>
</html>
