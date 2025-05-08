package com.mack.clinica.controller;

import com.mack.clinica.model.CRUDPacientesDAO;
import com.mack.clinica.model.Usuario;
import com.mack.clinica.controller.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cadastroDeMedicos")
public class CadastroDePacientesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!SessionUtil.validar(request, response)) {return;}

        String oper = request.getParameter("crudOperation");
        request.setAttribute("crudOperation", oper);


        String realPathBase = request.getServletContext().getRealPath("/");
        CRUDPacientesDAO dao = new CRUDPacientesDAO(realPathBase);


        List<Usuario> usuarios = dao.listarUsuarios();
        request.setAttribute("listaDeUsuarios", usuarios);


        request.getRequestDispatcher("/cadastro_de_pacientes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        String oper = request.getParameter("operacao");
        String realPathBase = getServletContext().getRealPath("/");
        CRUDPacientesDAO dao = new CRUDPacientesDAO(realPathBase);

        if ("adicionarUsuario".equals(oper)) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(request.getParameter("nomeCriar"));
            novoUsuario.setCPFformatado(request.getParameter("cpfCriar"));
            novoUsuario.setCelularFormatado(request.getParameter("celularCriar"));
            novoUsuario.setEmail(request.getParameter("emailCriar"));
            String senha = request.getParameter("senhaCriar");

            dao.criarUsuario(novoUsuario, senha);

            response.sendRedirect("cadastroDePacientes?crudOperation=mostrar");
            return;
        }

            // 1) Buscar para atualizar: carrega o usuário e reexibe o JSP em modo "atualizar"
        if ("buscarAtualizar".equals(oper)) {
            String campo = request.getParameter("campoBusca");
            String valor = request.getParameter("valorBusca");

            // Remove tudo que não for número se o campo for "cpf" ou "celular"
            if ("cpf".equals(campo) || "celular".equals(campo)) {
                valor = valor.replaceAll("\\D", "");
            }
            Usuario u = dao.buscarPorCampo(campo, valor);

            request.setAttribute("usuarioEncontrado", u);
            request.setAttribute("crudOperation", "atualizar");
            request.getRequestDispatcher("/cadastro_de_pacientes.jsp")
                    .forward(request, response);
            return;
        }

        // 2) Confirmar atualização: pega novos valores, executa update e redireciona
        if ("confirmarAtualizar".equals(oper)) {
            Usuario u = new Usuario();
            u.setId(Integer.parseInt(request.getParameter("id")));
            u.setNome(request.getParameter("nomeAtualizar"));
            u.setCPFformatado(request.getParameter("cpfAtualizar"));
            u.setCelularFormatado(request.getParameter("celularAtualizar"));
            u.setEmail(request.getParameter("emailAtualizar"));
            String senha = request.getParameter("senhaAtualizar");
            dao.atualizarUsuario(u,senha);

            // após atualizar, volta para a listagem
            response.sendRedirect("cadastroDePacientes?crudOperation=mostrar");
            return;
        }

        // 3) Buscar para deletar: carrega o usuário e reexibe o JSP em modo "deletar"
        if ("buscarDeletar".equals(oper)) {
            String campo = request.getParameter("campoDeletar");
            String valor = request.getParameter("valorDeletar");

            if ("cpf".equals(campo) || "celular".equals(campo)) {
                valor = valor.replaceAll("\\D", "");
            }
            Usuario u = dao.buscarPorCampo(campo, valor);

            request.setAttribute("usuarioEncontrado", u);
            request.setAttribute("crudOperation", "deletar");
            request.getRequestDispatcher("/cadastro_de_pacientes.jsp")
                    .forward(request, response);
            return;
        }

        // 4) Confirmar deleção: remove o usuário pelo ID e redireciona
        if ("confirmarDeletar".equals(oper)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deletarUsuario(id);

            response.sendRedirect("cadastroDePacientes?crudOperation=mostrar");
            return;
        }

        // Qualquer outra operação, apenas volte à tela inicial
        response.sendRedirect("cadastroDePacientes");


            request.getRequestDispatcher("/cadastro_de_pacientes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
