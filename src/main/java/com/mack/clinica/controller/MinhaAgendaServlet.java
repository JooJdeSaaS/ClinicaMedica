package com.mack.clinica.controller;

import java.io.IOException;
import java.util.List;


import com.mack.clinica.model.Consulta;
import com.mack.clinica.model.ConsultaDAO;


import com.mack.clinica.controller.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/minhaAgenda")
public class MinhaAgendaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!SessionUtil.validar(request, response)) {return;}
        HttpSession session = request.getSession();
        String realPathBase = request.getServletContext().getRealPath("/");

        // Obtém o ID do paciente da sessão
        Integer pacienteId = (Integer) session.getAttribute("id");


        // Instancia o DAO e busca as consultas do paciente
        ConsultaDAO dao = new ConsultaDAO(realPathBase);
        List<Consulta> consultas = dao.buscarConsultasPorPacienteId(pacienteId);

        // Define as consultas como atributo e encaminha para o JSP
        request.setAttribute("consultas", consultas);
        request.getRequestDispatcher("/minha_agenda.jsp").forward(request, response);
    }

}
