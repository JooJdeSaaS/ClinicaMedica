package com.mack.clinica.controller;

import java.io.IOException;
import java.util.List;

import com.mack.clinica.model.AgendarConsultaDAO;
import com.mack.clinica.model.Usuario;

import com.mack.clinica.controller.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/agendarConsulta")
public class AgendarConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.validar(request, response)) {return;}

        String realPathBase = request.getServletContext().getRealPath("/");
        AgendarConsultaDAO dao = new AgendarConsultaDAO(realPathBase);

        List<Usuario> medicos = dao.listarMedicos();
        request.setAttribute("medicos", medicos);

        request.getRequestDispatcher("/agendar_consulta.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!SessionUtil.validar(request, response)) {return;}

            int pacienteId = (Integer) request.getSession().getAttribute("id");
            int profissionalId = Integer.parseInt(request.getParameter("profissionalId"));
            String dataHora = request.getParameter("dataHora");

            String realPathBase = request.getServletContext().getRealPath("/");
            AgendarConsultaDAO dao = new AgendarConsultaDAO(realPathBase);

            boolean sucesso = dao.agendarConsulta(pacienteId, profissionalId, dataHora);
            if (sucesso) {
                // apresenta o pop-up de sucesso e mensagem_sucesso.jsp redireciona para o painel do paciente
                request.setAttribute("sujeito", "Consulta");
                request.setAttribute("verbo", "agendada");
                request.setAttribute("redirect", "paciente_dashboard");
                request.getRequestDispatcher("/mensagem_sucesso.jsp").forward(request, response);

            } else {
                request.setAttribute("texto", "deletar o usuario");
                request.setAttribute("redirect", "paciente_dashboard");
                request.getRequestDispatcher("/mensagem_erro.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
