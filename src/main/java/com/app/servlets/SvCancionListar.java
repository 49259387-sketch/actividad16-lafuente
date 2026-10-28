package com.app.servlets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvCancionListar", urlPatterns = {"/SvCancionListar"})
public class SvCancionListar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cancion> canciones = control.listarCanciones();
        request.setAttribute("canciones", canciones);
        request.getRequestDispatcher("/WEB-INF/vistas/canciones.jsp").forward(request, response);
    }
}