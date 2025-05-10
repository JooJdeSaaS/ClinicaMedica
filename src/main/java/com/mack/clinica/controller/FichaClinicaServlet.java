package com.mack.clinica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/fichaClinica")
public class FichaClinicaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("nome") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        request.getRequestDispatcher("/ficha_clinica.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // pega os dado
            String nome = request.getParameter("nome");
            String anotacoes = request.getParameter("anotacoes");
            String prescricao = request.getParameter("prescricao");

            // guarda os dado
            request.setAttribute("nome", nome);
            request.setAttribute("anotacoes", anotacoes);
            request.setAttribute("sintomas", prescricao);

            // faz voltar pra home ao enviar
            response.sendRedirect("admin_dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
