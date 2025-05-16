package com.mack.clinica.controller;

import com.mack.clinica.model.Consulta;
import com.mack.clinica.model.ConsultaDAO;
import com.mack.clinica.model.UsuarioDAO;
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
        if (!SessionUtil.validar(request, response)) return;

        String realPathBase = request.getServletContext().getRealPath("/");
        ConsultaDAO consultaDAO = new ConsultaDAO(realPathBase);

        // Adiciona médicos e pacientes para os selects
        request.setAttribute("medicos", consultaDAO.listarMedicos());
        request.setAttribute("pacientes", consultaDAO.listarPacientes());

        // Pega os filtros
        String medicoIdParam = request.getParameter("medicoId");
        String pacienteIdParam = request.getParameter("pacienteId");
        String dataParam = request.getParameter("data");

        Integer medicoId = (medicoIdParam != null && !medicoIdParam.isEmpty()) ? Integer.parseInt(medicoIdParam) : null;
        Integer pacienteId = (pacienteIdParam != null && !pacienteIdParam.isEmpty()) ? Integer.parseInt(pacienteIdParam) : null;

        List<Consulta> consultas;
        if (medicoId != null || pacienteId != null || (dataParam != null && !dataParam.isEmpty())) {
            consultas = consultaDAO.buscarConsultasFiltradas(medicoId, pacienteId, dataParam);
        } else {
            consultas = consultaDAO.listarTodasConsultas();
        }

        request.setAttribute("consultas", consultas);
        request.getRequestDispatcher("/consultar_agenda.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String realPathBase = request.getServletContext().getRealPath("/");
        ConsultaDAO dao = new ConsultaDAO(realPathBase);

        if ("cancelar".equals(action)) {
            int consultaId = Integer.parseInt(request.getParameter("consultaId"));
            dao.cancelarConsulta(consultaId);
        }

        doGet(request, response);
    }
}