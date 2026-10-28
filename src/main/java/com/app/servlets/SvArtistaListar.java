package com.app.servlets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvArtistaListar", urlPatterns = {"/SvArtistaListar"})
public class SvArtistaListar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Artista> artistas = control.listarArtistas();
        request.setAttribute("artistas", artistas);
        request.getRequestDispatcher("/WEB-INF/vistas/artistas.jsp").forward(request, response);
    }
}