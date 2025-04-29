package com.mack.clinica.controller;

import com.mack.clinica.model.AgendarConsultaDAO;
import com.mack.clinica.model.Consulta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/minha-agenda")
public class MinhaAgendaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String realPathBase = getServletContext().getRealPath("/");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int pacienteId = Integer.parseInt(idParam);
                AgendarConsultaDAO dao = new AgendarConsultaDAO(realPathBase);
                List<Consulta> consultas = dao.listarConsultasDoPaciente(pacienteId);

                request.setAttribute("consultas", consultas);
                request.getRequestDispatcher("/agenda.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("erro", "ID inválido.");
                request.getRequestDispatcher("/erro.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("erro", "ID do paciente não fornecido.");
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }
}
