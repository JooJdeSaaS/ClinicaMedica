package com.mack.clinica.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class SessionUtil {
    public static boolean validar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("nome") == null) {
            response.sendRedirect("index.jsp");
            return false;
        }
        return true;
    }
}
