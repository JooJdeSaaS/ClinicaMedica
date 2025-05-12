package com.mack.clinica.controller;

import com.mack.clinica.controller.SessionUtil;
import com.mack.clinica.model.CRUDMedicosDAO;
import com.mack.clinica.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/cadastroDeMedicos")
public class CadastroDeMedicosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!SessionUtil.validar(request, response)) {return;}

        String realPathBase = request.getServletContext().getRealPath("/");
        CRUDMedicosDAO dao = new CRUDMedicosDAO(realPathBase);

        List<Usuario> usuarios = dao.listarUsuarios();
        request.setAttribute("listaDeUsuarios", usuarios);

        request.getRequestDispatcher("/cadastro_de_medicos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String realPathBase = getServletContext().getRealPath("/");
            CRUDMedicosDAO dao = new CRUDMedicosDAO(realPathBase);

            String operacao = request.getParameter("operacao");

            // captura a operação de visão (criar/mostrar/atualizar/deletar) e se for ler, manda a lista
            if ("selecionarOperacao".equals(operacao)) {
                String escolha = request.getParameter("crudOperation");
                request.setAttribute("crudOperation", escolha);

                if ("mostrar".equals(escolha)) {
                    List<Usuario> usuarios = dao.listarUsuarios();
                    request.setAttribute("listaDeUsuarios", usuarios);
                }

                request.getRequestDispatcher("/cadastro_de_medicos.jsp").forward(request, response);

                return;
            }

            //Criar Usuario
            if ("adicionarUsuario".equals(operacao)) {
                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(request.getParameter("nomeCriar"));
                novoUsuario.setCPFformatado(request.getParameter("cpfCriar"));
                novoUsuario.setCelularFormatado(request.getParameter("celularCriar"));
                novoUsuario.setEmail(request.getParameter("emailCriar"));
                String senha = request.getParameter("senhaCriar");

                boolean tudoCerto = dao.criarUsuario(novoUsuario, senha);

                if(tudoCerto) {
                    request.setAttribute("sujeito", "Usuario");
                    request.setAttribute("verbo", "adicionado");
                    request.setAttribute("redirect", "cadastroDeMedicos?crudOperation=mostrar");
                    request.getRequestDispatcher("/mensagem_sucesso.jsp").forward(request, response);

                    return;
                }

                request.setAttribute("texto", "criar um novo usuario");
                request.setAttribute("redirect", "/admin_dashboard");
                response.sendRedirect("/mensagem_erro.jsp");

                return;
            }

            //Recebe o valor para pesquisar o usuario
            if ("buscarAtualizar".equals(operacao) || "buscarDeletar".equals(operacao)) {

                String campo = "buscarAtualizar".equals(operacao)
                        ? request.getParameter("campoBusca")
                        : request.getParameter("campoDeletar");
                String valor = "buscarAtualizar".equals(operacao)
                        ? request.getParameter("valorBusca")
                        : request.getParameter("valorDeletar");

                if ("cpf".equals(campo) || "celular".equals(campo)) {
                    valor = valor.replaceAll("\\D", "");
                }

                Usuario usuarioEncontrado = dao.buscarPorCampo(campo, valor);

                request.setAttribute("usuarioEncontrado", usuarioEncontrado);
                request.setAttribute("crudOperation", "buscarAtualizar".equals(operacao) ? "atualizar" : "deletar");

                request.getRequestDispatcher("/cadastro_de_medicos.jsp").forward(request, response);

                return;
            }

            //Salva as atualizações do usuario
            if ("confirmarAtualizar".equals(operacao)) {
                Usuario u = new Usuario();
                u.setId(Integer.parseInt(request.getParameter("id")));
                u.setNome(request.getParameter("nomeAtualizar"));
                u.setCPFformatado(request.getParameter("cpfAtualizar"));
                u.setCelularFormatado(request.getParameter("celularAtualizar"));//celular bugado
                u.setEmail(request.getParameter("emailAtualizar"));
                String senha = request.getParameter("senhaAtualizar");

                boolean tudoCerto = dao.atualizarUsuario(u,senha);
                System.out.println(tudoCerto);

                if(tudoCerto) {
                    request.setAttribute("sujeito", "Usuario");
                    request.setAttribute("verbo", "atualizado");
                    request.setAttribute("redirect", "cadastroDeMedicos?crudOperation=mostrar");
                    request.getRequestDispatcher("/mensagem_sucesso.jsp").forward(request, response);
                    return;
                }

                request.setAttribute("texto", "atualizar o usuario");
                request.setAttribute("redirect", "/admin_dashboard");
                response.sendRedirect("/mensagem_erro.jsp");

                return;
            }

            //Deleta o usuario
            if ("confirmarDeletar".equals(operacao)) {
                int id = Integer.parseInt(request.getParameter("id"));

                boolean tudoCerto = dao.deletarUsuario(id);

                if(tudoCerto) {
                    request.setAttribute("sujeito", "Usuario");
                    request.setAttribute("verbo", "deletado");
                    request.setAttribute("redirect", "cadastroDeMedicos?crudOperation=mostrar");
                    request.getRequestDispatcher("/mensagem_sucesso.jsp").forward(request, response);
                    return;
                }

                request.setAttribute("texto", "deletar o usuario");
                request.setAttribute("redirect", "/admin_dashboard");
                response.sendRedirect("/mensagem_erro.jsp");

                return;
            }

            // Qualquer outra operação, apenas volte à tela inicial
            response.sendRedirect("cadastroDeMedicos");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
