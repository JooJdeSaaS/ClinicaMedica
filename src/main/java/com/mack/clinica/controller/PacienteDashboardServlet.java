package com.mack.clinica.controller;

import java.io.IOException;

import com.mack.clinica.controller.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/paciente_dashboard")
public class PacienteDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!SessionUtil.validar(request, response)) {return;}

        request.getRequestDispatcher("/paciente_dashboard.jsp").forward(request, response);
    }
}

