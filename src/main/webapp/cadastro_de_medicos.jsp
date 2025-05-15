<%@ page import="com.mack.clinica.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>cadastroDeMedicos</title>
    <link rel="stylesheet" href="/css/cadastro_de_pacientes.css">
<body>
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

<div class="container">
    <div class="card">
        <h2>Criar, Ler, Atualizar, Deletar Medicos</h2>

        <%-- JSP Form for operation selection --%>
            <%
            String selectedOperation = (String) request.getAttribute("crudOperation");
            if (selectedOperation == null) {
                selectedOperation = request.getParameter("crudOperation");
            }
            if (selectedOperation == null) {
                selectedOperation = "";
            }
            request.setAttribute("selectedOperation", selectedOperation);
        %>


        <form id="operationForm" method="post" action="cadastroDeMedicos">
            <input type="hidden" name="operacao" value="selecionarOperacao"/>
            <div class="form-group">
                <label for="crudOperation">Selecionar Operação:</label>
                <select name="crudOperation" id="crudOperation" onchange="this.form.submit()">
                    <option value=""   ${selectedOperation.isEmpty()? 'selected':''}>Selecionar Operação</option>
                    <option value="criar"    ${selectedOperation=='criar'   ? 'selected':''}>Criar</option>
                    <option value="mostrar"  ${selectedOperation=='mostrar' ? 'selected':''}>Mostrar</option>
                    <option value="atualizar"${selectedOperation=='atualizar'? 'selected':''}>Atualizar</option>
                    <option value="deletar"  ${selectedOperation=='deletar' ? 'selected':''}>Deletar</option>
                </select>
            </div>
        </form>



        <%-- Display different sections based on selection --%>
        <div class="section">
            <c:if test="${selectedOperation == 'criar'}">
                <div class="create-section">
                    <form method="post" action="cadastroDeMedicos">
                        <input type="hidden" name="operacao" value="adicionarUsuario"/>

                        <div class="form-group">
                            <label for="nomeCriar">Nome:</label>
                            <input type="text" id="nomeCriar" name="nomeCriar" required/>
                        </div>
                        <div class="form-group">
                            <label for="emailCriar">Email:</label>
                            <input type="email" id="emailCriar" name="emailCriar" required/>
                        </div>
                        <div class="form-group">
                            <label for="cpfCriar">CPF:</label>
                            <input type="text" id="cpfCriar" name="cpfCriar" required/>
                        </div>
                        <div class="form-group">
                            <label for="celularCriar">Celular:</label>
                            <input type="text" id="celularCriar" name="celularCriar" required/>
                        </div>
                        <div class="form-group">
                            <label for="senhaCriar">Senha:</label>
                            <input type="password" id="senhaCriar" name="senhaCriar" required/>
                        </div>

                        <!-- aqui: tipo submit! -->
                        <button type="submit" class="criarusuario">Criar Usuário</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${selectedOperation == 'mostrar'}">
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
            </c:if>
            <c:if test="${selectedOperation == 'atualizar'}">
                <%
                    // Recebe o campo escolhido para buscar o usuário
                    String campoBusca = request.getParameter("campoBusca");
                    if (campoBusca == null) {
                        campoBusca = ""; // evita NullPointerException
                    }
                %>
                <%-- 1) Formulário de busca --%>
                <form method="post" action="cadastroDeMedicos">
                    <input type="hidden" name="operacao" value="buscarAtualizar"/>
                    <div class="form-group">
                        <label for="campoBusca">Buscar usuário por:</label>
                        <select id="campoBusca" name="campoBusca">
                            <option value="email"   ${param.campoBusca eq 'email'   ? 'selected' : ''}>Email</option>
                            <option value="cpf"     ${param.campoBusca eq 'cpf'     ? 'selected' : ''}>CPF</option>
                            <option value="celular" ${param.campoBusca eq 'celular' ? 'selected' : ''}>Celular</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="valorBusca">Valor:</label>
                        <input type="text" id="valorBusca" name="valorBusca" value="${param.valorBusca}" required/>
                    </div>
                    <button type="submit">Buscar para Atualizar</button>
                </form>

                <%-- 2) Só exibe se o servlet devolveu um usuário encontrado --%>
                <c:if test="${not empty usuarioEncontrado}">
                    <hr/>
                    <h4>Atualizar usuário: ${usuarioEncontrado.nome}</h4>
                    <form method="post" action="cadastroDeMedicos">
                        <input type="hidden" name="operacao" value="confirmarAtualizar"/>
                        <input type="hidden" name="id"        value="${usuarioEncontrado.id}"/>

                        <div class="form-group">
                            <label for="nomeAtualizar">Nome:</label>
                            <input type="text" id="nomeAtualizar" name="nomeAtualizar"
                                   value="${usuarioEncontrado.nome}" />
                        </div>

                        <div class="form-group">
                            <label for="cpfAtualizar">CPF:</label>
                            <input type="text" id="cpfAtualizar" name="cpfAtualizar"
                                   value="${usuarioEncontrado.CPF}" />
                        </div>

                        <div class="form-group">
                            <label for="celularAtualizar">Celular:</label>
                            <input type="text" id="celularAtualizar" name="celularAtualizar"
                                   value="${usuarioEncontrado.celular}" />
                        </div>

                        <div class="form-group">
                            <label for="emailAtualizar">Email:</label>
                            <input type="email" id="emailAtualizar" name="emailAtualizar"
                                   value="${usuarioEncontrado.email}" />
                        </div>


                        <div class="form-group">
                            <label for="senhaAtualizar">Senha:</label>
                            <input type="password" id="senhaAtualizar" name="senhaAtualizar" />
                        </div>

                        <button type="submit">Confirmar Atualização</button>
                    </form>
                </c:if>
            </c:if>
            <c:if test="${selectedOperation == 'deletar'}">
                <div class="delete-section">
                    <!-- 1) Buscar usuário para deletar -->
                    <form method="post" action="cadastroDeMedicos">
                        <input type="hidden" name="operacao" value="buscarDeletar"/>
                        <label for="campoDeletar">Buscar por:</label>
                        <select id="campoDeletar" name="campoDeletar">
                            <option value="email">Email</option>
                            <option value="cpf">CPF</option>
                            <option value="celular">Celular</option>
                        </select>
                        <input type="text" name="valorDeletar" required/>
                        <button type="submit">Buscar para Deletar</button>
                    </form>

                    <!-- 2) Só aparece depois que você carregar o usuário -->
                    <c:if test="${not empty usuarioEncontrado}">
                        <hr/>
                        <p>Tem certeza que quer deletar: <strong>${usuarioEncontrado.nome}</strong>?</p>
                        <form method="post" action="cadastroDeMedicos">
                            <input type="hidden" name="operacao" value="confirmarDeletar"/>
                            <input type="hidden" name="id"        value="${usuarioEncontrado.id}"/>
                            <button type="submit">Confirmar Deleção</button>
                        </form>
                    </c:if>
                </div>
            </c:if>
        </div>
</body>
</html>