package com.mack.clinica.controller;

import com.mack.clinica.controller.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/cadastroDeMedicos")
public class CadastroDeMedicosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.validar(request, response)) {return;}

        request.getRequestDispatcher("/cadastro_de_medicos.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {



        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
