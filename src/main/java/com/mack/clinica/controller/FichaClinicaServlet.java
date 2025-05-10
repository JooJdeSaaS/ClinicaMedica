package com.mack.clinica.controller;

import com.mack.clinica.model.FichaClinicaDAO;
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
            String paciente_id = request.getParameter("nome");
            String anotacoes_medicas = request.getParameter("anotacoes");
            String prescricoes = request.getParameter("prescricao");

            // Caminho absoluto para o banco SQLite dentro de /WEB-INF
            String dbPath = getServletContext().getRealPath("/WEB-INF/db.db");

            FichaClinicaDAO dao = new FichaClinicaDAO(dbPath);
            dao.salvarFichaClinica(paciente_id, anotacoes_medicas, prescricoes);

            response.sendRedirect("admin_dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }
}
