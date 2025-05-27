package com.mack.clinica.controller;

import com.mack.clinica.controller.SessionUtil;
import com.mack.clinica.model.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.mack.clinica.model.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet("/meuCadastro")
public class MeuCadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.validar(request, response)) {return;}

        HttpSession session = request.getSession();
        request.setAttribute("nome", session.getAttribute("nome"));
        request.setAttribute("email", session.getAttribute("email"));
        request.setAttribute("celular", session.getAttribute("celular"));
        request.setAttribute("cpf", session.getAttribute("cpf"));
        request.getRequestDispatcher("/meu_cadastro.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            int usuarioId = (int) session.getAttribute("id");

            String nomeParam    = request.getParameter("nome");
            String celularParam = request.getParameter("celular");
            String cpfParam     = request.getParameter("CPF");
            String emailParam   = request.getParameter("emailnovo");

            String nomeAtual    = (String) session.getAttribute("nome");
            String celularAtual = (String) session.getAttribute("celular");
            String cpfAtual     = (String) session.getAttribute("cpf");
            String emailAtual   = (String) session.getAttribute("email");

            String nome    = (nomeParam    == null || nomeParam.isEmpty())    ? nomeAtual    : nomeParam;
            String celular = (celularParam == null || celularParam.isEmpty()) ? celularAtual : celularParam;
            String cpf     = (cpfParam     == null || cpfParam.isEmpty())     ? cpfAtual     : cpfParam;
            String email   = (emailParam   == null || emailParam.isEmpty())   ? emailAtual   : emailParam;


            Usuario usuario = new Usuario();
            usuario.setId(usuarioId);
            usuario.setNome(nome);
            usuario.setCelularFormatado(celular);
            usuario.setCPFformatado(cpf);
            usuario.setEmail(email);

            String realPathBase = request.getServletContext().getRealPath("/");
            UsuarioDAO usuarioDAO = new UsuarioDAO(realPathBase);

            boolean ok = usuarioDAO.atualizarUsuario(usuario);
            if (ok) {
                // 5. Atualiza também os atributos na sessão
                session.setAttribute("nome", nome);
                session.setAttribute("celular", celular);
                session.setAttribute("cpf", cpf);
                session.setAttribute("email", email);

                request.setAttribute("sujeito", "Informações");
                request.setAttribute("verbo", "mudadas");
                request.setAttribute("redirect", "paciente_dashboard.jsp");
                request.getRequestDispatcher("/mensagem_sucesso.jsp").forward(request, response);
            }else {
                // Em caso de erro, você pode encaminhar para uma página de erro ou reenviar com msg
                request.setAttribute("texto", "atualizar suas informações");
                request.setAttribute("redirect", "paciente_dashboard");
                request.getRequestDispatcher("/mensagem_erro.jsp").forward(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
