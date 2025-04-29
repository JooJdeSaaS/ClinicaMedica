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
        <a href="minha_agenda.jsp">Minha Agenda</a>
        <a href="meu_cadastro.jsp">Meu Cadastro</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
</div>

<!-- Conteúdo principal -->
<div class="cadastro-container">
    <h2>Trocar informações</h2>
    <form method="post" action="meuCadastro">

       

        <button type="submit">Mudar</button>
    </form>
</div>

</body>
</html>