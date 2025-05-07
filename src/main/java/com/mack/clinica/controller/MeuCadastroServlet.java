package com.mack.clinica.controller;

import com.mack.clinica.model.AgendarConsultaDAO;
import com.mack.clinica.controller.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/meuCadastro")
public class MeuCadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtém o caminho real do projeto
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


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
