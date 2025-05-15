package com.mack.clinica.controller;


import com.mack.clinica.model.ProntuariosDAO;
import com.mack.clinica.model.Usuario;
import com.mack.clinica.model.UsuarioDAO;
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

        if (!SessionUtil.validar(request, response)) {return;}

        String realPathBase = request.getServletContext().getRealPath("/");

        // Carregar lista de médicos
        UsuarioDAO dao = new UsuarioDAO(realPathBase);
        List<Usuario> medicos = dao.listarUsuarios("medico");
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

            String realPathBase = request.getServletContext().getRealPath("/");

            int id_paciente = Integer.parseInt(paciente_id);
            int id_medico = Integer.parseInt(profissional_id);

            ProntuariosDAO dao = new ProntuariosDAO(realPathBase);
            dao.salvarFichaClinica(id_paciente, id_medico, anotacoes_medicas, prescricoes, data);

            response.sendRedirect("admin_dashboard.jsp");

        } catch (Exception e) {
            System.out.println("Erro no servlet da ficha clínica");
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?msg=erro");
        }
    }
}
