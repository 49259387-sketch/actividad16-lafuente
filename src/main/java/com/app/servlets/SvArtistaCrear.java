package com.app.servlets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "SvArtistaCrear", urlPatterns = {"/SvArtistaCrear"})
public class SvArtistaCrear extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/SvArtistaListar");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String generoMusical = request.getParameter("generoMusical");
        String nacionalidad = request.getParameter("nacionalidad");
        String edadStr = request.getParameter("edad");
        String fechaStr = request.getParameter("fechaNacimiento"); // formato yyyy-MM-dd

        try {
            int edad = Integer.parseInt(edadStr.trim());
            Date fechaNacimiento = (fechaStr == null || fechaStr.isBlank())
                    ? null
                    : Date.valueOf(LocalDate.parse(fechaStr.trim()));

            Artista artista = new Artista(nombre, generoMusical, nacionalidad, edad, fechaNacimiento);
            control.crearArtista(artista);

            response.sendRedirect(request.getContextPath() + "/SvArtistaListar");
        } catch (NumberFormatException | NullPointerException e) {
            redirigirConError(request, response, "La edad debe ser un número válido.");
        } catch (java.time.format.DateTimeParseException e) {
            redirigirConError(request, response, "La fecha de nacimiento no tiene un formato válido.");
        } catch (Exception e) {
            redirigirConError(request, response, e.getMessage());
        }
    }

    private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        String msg = URLEncoder.encode(mensaje == null ? "Error al crear el artista." : mensaje,
                StandardCharsets.UTF_8);
        response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error=" + msg);
    }
    
    
    
}