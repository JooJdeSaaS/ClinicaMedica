package com.mack.clinica.controller;

import com.mack.clinica.model.CRUDPacientesDAO;
import com.mack.clinica.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/cadastroDePacientes")
public class CadastroDePacientesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("nome") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String oper = request.getParameter("crudOperation");
        request.setAttribute("crudOperation", oper);


        String realPathBase = request.getServletContext().getRealPath("/");
        CRUDPacientesDAO dao = new CRUDPacientesDAO(realPathBase);


        List<Usuario> usuarios = dao.listarUsuarios();
        request.setAttribute("listaDeUsuarios", usuarios);


        request.getRequestDispatcher("/cadastro_de_pacientes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {


            request.getRequestDispatcher("/cadastro_de_pacientes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
