package com.mack.clinica.controller;

import com.mack.clinica.model.AgendarConsultaDAO;
import com.mack.clinica.model.FichaClinicaDAO;
import com.mack.clinica.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

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

        String dbPath = getServletContext().getRealPath("/");

        // Carregar lista de médicos
        AgendarConsultaDAO dao = new AgendarConsultaDAO(dbPath);
        List<Usuario> medicos = dao.listarMedicos();
        request.setAttribute("medicos", medicos);

        request.getRequestDispatcher("/ficha_clinica.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String paciente_id = request.getParameter("paciente_id");
            String profissional_id = request.getParameter("profissional_id");
            String anotacoes_medicas = request.getParameter("anotacoes_medicas");
            String prescricoes = request.getParameter("prescricoes");
            String data = request.getParameter("data");

            String dbPath = getServletContext().getRealPath("/");

            Integer b = Integer.parseInt(paciente_id);
            Integer c = Integer.parseInt(profissional_id);

            FichaClinicaDAO dao = new FichaClinicaDAO(dbPath);
            dao.salvarFichaClinica(b, c, anotacoes_medicas, prescricoes, data);

            response.sendRedirect("admin_dashboard.jsp");

        } catch (Exception e) {
            System.out.println("Erro no servlet da ficha clínica");
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?msg=erro");
        }
    }
}
