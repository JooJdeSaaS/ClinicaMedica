<%@ page import="com.mack.clinica.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>cadastroDePacientes</title>
<body>
<div class="container">
    <div class="card">
        <h2>Criar, Ler, Atualizar, Deletar usuarios</h2>

        <%-- JSP Form for operation selection --%>
        <%
            String selectedOperation = request.getParameter("crudOperation");
            if (selectedOperation == null) {
                selectedOperation = "";
            }
        %>

        <form method="get" action="cadastroDePacientes">
            <div class="form-group">
                <label for="crudOperation">Selecionar Operação:</label>
                <select name="crudOperation" id="crudOperation" onchange="this.form.submit()">
                    <option value="" <%= selectedOperation.isEmpty() ? "selected" : "" %>>Selecionar Operação</option>
                    <option value="criar" <%= "criar".equals(selectedOperation) ? "selected" : "" %>>Criar</option>
                    <option value="mostrar" <%= "mostrar".equals(selectedOperation) ? "selected" : "" %>>Mostrar</option>
                    <option value="atualizar" <%= "atualizar".equals(selectedOperation) ? "selected" : "" %>>Atualizar</option>
                    <option value="deletar" <%= "deletar".equals(selectedOperation) ? "selected" : "" %>>Deletar</option>
                </select>
            </div>
        </form>


    <%-- Display different sections based on selection --%>
        <div class="section">
            <% if ("criar".equals(selectedOperation)) { %>
            <div class="create-section">
                <form action="#" method="post">
                    <div class="form-group">
                        <label for="nomecriar">Nome:</label>
                        <input type="text" id="nomecriar" name="nomecriar">
                    </div>
                    <div class="form-group">
                        <label for="emailcriar">Email:</label>
                        <input type="text" id="emailcriar" name="emailcriar">
                    </div>
                    <div class="form-group">
                        <label for="cpfcriar">CPF:</label>
                        <input type="text" id="cpfcriar" name="cpfcriar">
                    </div>
                    <div class="form-group">
                        <label for="celularcriar">Celular:</label>
                        <input type="text" id="celularcriar" name="celularcriar">
                    </div>
                    <div class="form-group">
                        <label for="senhacriar">Senha:</label>
                        <input type="text" id="senhacriar" name="senhacriar">
                    </div>
                    <button type="button" class="criarusuario">Criar Usuario </button>
                </form>
            </div>
            <% } else if ("mostrar".equals(selectedOperation)) { %>
            <div class="read-section">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Email</th>
                        <th>Celular</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="usuario" items="${listaDeUsuarios}">
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nome}</td>
                            <td>${usuario.CPF}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.celular}</td>
                        </tr>
                    </c:forEach>
                    <!-- Se a lista estiver vazia, exiba uma linha de “nenhum registro”: -->
                    <c:if test="${empty listaDeUsuarios}">
                        <tr>
                            <td colspan="5">Nenhum usuário encontrado.</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <% } else if ("atualizar".equals(selectedOperation)) { %>
            <div class="update-section">
                <%
                    // Recebe o campo escolhido para buscar o usuário
                    String campoBusca = request.getParameter("campoBusca");
                    if (campoBusca == null) {
                        campoBusca = ""; // evita NullPointerException
                    }
                %>

                <form action="#" method="post">
                    <input type="hidden" name="operacao" value="atualizar">

                    <!-- 1) Campo para selecionar por qual campo buscar -->
                    <div class="form-group">
                        <label for="campoBusca">Buscar usuário por:</label>
                        <select id="campoBusca" name="campoBusca" onchange="this.form.submit()">
                            <option value="" <%= campoBusca.isEmpty() ? "selected" : "" %>>Selecione um campo</option>
                            <option value="email" <%= "email".equals(campoBusca) ? "selected" : "" %>>Email</option>
                            <option value="cpf" <%= "cpf".equals(campoBusca) ? "selected" : "" %>>CPF</option>
                            <option value="celular" <%= "celular".equals(campoBusca) ? "selected" : "" %>>Celular</option>
                        </select>
                    </div>

                    <!-- 2) Campo para inserir o valor de busca -->
                    <% if (!campoBusca.isEmpty()) { %>
                    <div class="form-group">
                        <label for="valorBusca">Digite o <%= campoBusca %> para buscar:</label>
                        <input type="text" id="valorBusca" name="valorBusca" required>
                    </div>

                    <!-- 3) Campos para atualização (todos exceto o campo de busca) -->
                    <h4>Novos dados:</h4>


                    <div class="form-group">
                        <label for="nomeAtualizar">Novo Nome:</label>
                        <input type="text" id="nomeAtualizar" name="nomeAtualizar">
                    </div>

                    <% if (!"cpf".equals(campoBusca)) { %>
                    <div class="form-group">
                        <label for="cpfAtualizar">Novo CPF:</label>
                        <input type="text" id="cpfAtualizar" name="cpfAtualizar">
                    </div>
                    <% } %>

                    <% if (!"celular".equals(campoBusca)) { %>
                    <div class="form-group">
                        <label for="celularAtualizar">Novo Celular:</label>
                        <input type="text" id="celularAtualizar" name="celularAtualizar">
                    </div>
                    <% } %>

                    <% if (!"email".equals(campoBusca)) { %>
                    <div class="form-group">
                        <label for="emailAtualizar">Novo Email:</label>
                        <input type="email" id="emailAtualizar" name="emailAtualizar">
                    </div>
                    <% } %>

                    <div class="form-group">
                        <label for="senhaAtualizar">Nova Senha:</label>
                        <input type="password" id="senhaAtualizar" name="senhaAtualizar">
                    </div>

                    <!-- 4) Botão de submit -->
                    <button type="submit" class="btn-update">Atualizar Usuário</button>
                    <% } %>
                </form>
            </div>
            <% } else if ("deletar".equals(selectedOperation)) { %>
            <div class="delete-section">

                <%
                    // Recebe o campo escolhido para buscar o usuário
                    String campoDeletar = request.getParameter("campoDeletar");
                    if (campoDeletar == null) {
                        campoDeletar = ""; // evita NullPointerException
                    }
                %>

                <form action="#" method="post">
                    <input type="hidden" name="operacao" value="atualizar">

                    <!-- 1) Campo para selecionar por qual campo buscar -->
                    <div class="form-group">
                        <label for="campoDeletar">Buscar usuário por:</label>
                        <select id="campoDeletar" name="campoDeletar" onchange="this.form.submit()">
                            <option value="" <%= campoDeletar.isEmpty() ? "selected" : "" %>>Selecione um campo</option>
                            <option value="email" <%= "email".equals(campoDeletar) ? "selected" : "" %>>Email</option>
                            <option value="cpf" <%= "cpf".equals(campoDeletar) ? "selected" : "" %>>CPF</option>
                            <option value="celular" <%= "celular".equals(campoDeletar) ? "selected" : "" %>>Celular</option>
                        </select>
                    </div>

                    <!-- 2) Campo para inserir o valor de busca -->
                        <% if (!campoDeletar.isEmpty()) { %>
                    <div class="form-group">
                        <label for="valorDeletar">Digite o <%= campoDeletar %> para buscar:</label>
                        <input type="text" id="valorDeletar" name="valorDeletar" required>
                    </div>
                <div class="warning">
                    <p><strong>Aviso:</strong> Essa ação não pode ser revertida.</p>
                    <p>Você tem certeza que deseja deletar esse usuario?</p>
                </div>
                <button type="button" class="btn-delete">Deletar Usuario</button>
            </div>
            <% } }%>
        </div>


        <%-- In a real application, you would process form submissions here --%>
        <%--
        <%
            // Process create form submission
            if ("POST".equalsIgnoreCase(request.getMethod()) && "create".equals(selectedOperation)) {
                String itemName = request.getParameter("itemName");
                String itemDescription = request.getParameter("itemDescription");

                // Insert into database code would go here
                // ...

                // Redirect or show success message
            }

            // Similar processing for update and delete operations
        %>
        --%>
    </div>
</div>
</body>
</html>