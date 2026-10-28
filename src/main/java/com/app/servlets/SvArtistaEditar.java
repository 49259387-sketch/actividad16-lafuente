package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvArtistaEditar")
public class SvArtistaEditar extends HttpServlet {

	private ControladoraLogica control = new ControladoraLogica();

	// GET: recibe el id, guarda el artista en el atributo "artista" y hace forward al JSP de edición.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Artista art = control.buscarUnArtista(id);

			// Si no existe, volvemos al listado con un error
			if (art == null) {
				redirigirConError(request, response, "El artista no existe.");
				return;
			}

			request.setAttribute("artista", art);
			request.getRequestDispatcher("/WEB-INF/vistas/editarArtista.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			redirigirConError(request, response, "El id del artista no es válido.");
		}
	}

	// POST: recibe el id y los datos, guarda los cambios y redirige al listado.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));

			// Traemos el artista existente y le pisamos los datos nuevos
			Artista art = control.buscarUnArtista(id);
			if (art == null) {
				redirigirConError(request, response, "El artista no existe.");
				return;
			}

			art.setNombre(request.getParameter("nombre"));
			art.setGeneroMusical(request.getParameter("generoMusical"));
			art.setEdad(Integer.parseInt(request.getParameter("edad")));

			control.editarArtista(art);
			response.sendRedirect(request.getContextPath() + "/SvArtistaListar");
		} catch (NumberFormatException e) {
			redirigirConError(request, response, "Los datos numéricos no son válidos.");
		} catch (Exception e) {
			redirigirConError(request, response, e.getMessage());
		}
	}

	private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String msg)
			throws IOException {
		response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error="
				+ URLEncoder.encode(msg, StandardCharsets.UTF_8));
	}
}