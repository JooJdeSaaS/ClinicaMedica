package com.mack.clinica.controller;

import com.mack.clinica.model.AgendarConsultaDAO;
import com.mack.clinica.model.Consulta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/consultarAgenda")
public class ConsultarAgendaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("nome") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String path = getServletContext().getRealPath("/");
        AgendarConsultaDAO dao = new AgendarConsultaDAO(path);

        // Adiciona médicos e pacientes para os selects
        request.setAttribute("medicos", dao.listarMedicos());
        request.setAttribute("pacientes", dao.listarPacientes());

        // Pega os filtros
        String medicoIdParam = request.getParameter("medicoId");
        String pacienteIdParam = request.getParameter("pacienteId");
        String dataParam = request.getParameter("data");

        Integer medicoId = null;
        Integer pacienteId = null;

        if (medicoIdParam != null && !medicoIdParam.isEmpty()) {
            medicoId = Integer.parseInt(medicoIdParam);
        }

        if (pacienteIdParam != null && !pacienteIdParam.isEmpty()) {
            pacienteId = Integer.parseInt(pacienteIdParam);
        }

        List<Consulta> consultas;
        if (medicoId != null || pacienteId != null || (dataParam != null && !dataParam.isEmpty())) {
            consultas = dao.buscarConsultasFiltradas(medicoId, dataParam);
        } else {
            consultas = dao.buscarTodasConsultas();
        }

        request.setAttribute("consultas", consultas);
        request.getRequestDispatcher("/consultar_agenda.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String path = getServletContext().getRealPath("/");
        AgendarConsultaDAO dao = new AgendarConsultaDAO(path);

        if ("cancelar".equals(action)) {
            int consultaId = Integer.parseInt(request.getParameter("consultaId"));
            dao.cancelarConsulta(consultaId);
        }

        doGet(request, response);
    }
}
