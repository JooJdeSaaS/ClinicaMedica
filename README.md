# Projeto de Programção Orientada a Objeto 
## Site clinica 

## 👥 Equipe de Desenvolvimento
- João de Sá Calvano Bezerra - RA: 10436734
- Ian Erichsen Pacher de Araujo -  RA: 10427607
- Gustavo Ebeling de Almeida -  RA: 10436455


# 🏥 Sistema de Agendamento e Gestão de Consultas Médicas de uma clinica

Projeto final da disciplina de **Programação Orientada a Objetos com Java**, desenvolvido pelos alunos de Engenharia da Computação da **Universidade Presbiteriana Mackenzie**.

## 📋 Descrição

Este sistema web permite que pacientes agendem consultas médicas online, visualizem seus dados pessoais e histórico de consultas. Administradores podem gerenciar usuários (pacientes e médicos), acompanhar a agenda e acessar prontuários.

---

## 🎯 Objetivos do Projeto

- Desenvolver uma aplicação web utilizando Java, Servlets, JSP e HTML/CSS.
- Utilizar **POO** (Programação Orientada a Objetos) com boas práticas.
- Controle de sessões com `HttpSession` e rotas protegidas por autenticação.

---

## 🗂️ Estrutura do Projeto

clinica/ <br />
├── pom.xml <br />
├── db.db <br />
├── src/ <br />
│&nbsp;&nbsp;&nbsp;&nbsp;└── main/ <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── java/ <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── com/mack/clinica/ <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── controller/       # Servlets <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── model/            # Classes de domínio e DAOs <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── util/             # Conexão com o banco <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── webapp/                   # .jsp <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── login.jsp  <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── paciente_dashboard.jsp <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── admin_dashboard.jsp <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── ... <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── css/ <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── styles.css <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── ... <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── WEB-INF/ <br />
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── web.xml <br />




---

## 🔐 Usuários de Teste

| Tipo         | Email                       | Senha     |
|--------------|-----------------------------|-----------|
| Administrador| admin@mackenzie.br          | admin123  |
| Paciente     | james.brown@soul.com        | 123       |

---

## ✅ Funcionalidades Implementadas

### Pacientes
- Visualizar e editar dados pessoais
- Visualizar agendamentos do usuario

### Administradores
- CRUD de pacientes
- CRUD de médicos
- Visualização de agenda (com filtros)
- Ficha clínica (prontuário)

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 11+
- Apache Maven
- Qualquer IDE (desenvolvido em IntelliJ)
- (Opcional) Apache Tomcat 9

### Configuração Inicial

1. Clone o repositório:
   ```bash
   git clone https://github.com/JooJdeSaaS/ClinicaMedica
   cd seu-repo
   
### Compile e instale as dependências:


mvn clean install
#### Rode o servidor Jetty:

mvn jetty:run

### Acesse a aplicação em:


http://localhost:8080/