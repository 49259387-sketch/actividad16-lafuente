package com.app.servlets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SvCancionCrear", urlPatterns = {"/SvCancionCrear"})
public class SvCancionCrear extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/SvCancionListar");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String duracionStr = request.getParameter("duracion");
        String anioStr = request.getParameter("anio");

        try {
            float duracion = Float.parseFloat(duracionStr.trim());
            int anio = Integer.parseInt(anioStr.trim());

            Cancion cancion = new Cancion(titulo, duracion, anio);
            control.crearCancion(cancion);

            response.sendRedirect(request.getContextPath() + "/SvCancionListar");
        } catch (NumberFormatException | NullPointerException e) {
            redirigirConError(request, response, "La duración y el año deben ser números válidos.");
        } catch (Exception e) {
            redirigirConError(request, response, e.getMessage());
        }
    }

    private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        String msg = URLEncoder.encode(mensaje == null ? "Error al crear la canción." : mensaje,
                StandardCharsets.UTF_8);
        response.sendRedirect(request.getContextPath() + "/SvCancionListar?error=" + msg);
    }
}