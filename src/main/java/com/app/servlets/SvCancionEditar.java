package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvCancionEditar")
public class SvCancionEditar extends HttpServlet {

	private ControladoraLogica control = new ControladoraLogica();

	// GET: recibe el id, guarda la canción en el atributo "cancion" y hace forward al JSP de edición.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Cancion can = control.buscarUnaCancion(id);

			if (can == null) {
				redirigirConError(request, response, "La canción no existe.");
				return;
			}

			request.setAttribute("cancion", can);
			request.getRequestDispatcher("/WEB-INF/vistas/editarCancion.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			redirigirConError(request, response, "El id de la canción no es válido.");
		}
	}

	// POST: recibe el id y los datos, guarda los cambios y redirige al listado.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));

			Cancion can = control.buscarUnaCancion(id);
			if (can == null) {
				redirigirConError(request, response, "La canción no existe.");
				return;
			}

			can.setTitulo(request.getParameter("titulo"));
			can.setDuracion(Integer.parseInt(request.getParameter("duracion")));
			can.setAnio(Integer.parseInt(request.getParameter("anio")));

			control.editarCancion(can);
			response.sendRedirect(request.getContextPath() + "/SvCancionListar");
		} catch (NumberFormatException e) {
			redirigirConError(request, response, "Los datos numéricos no son válidos.");
		} catch (Exception e) {
			redirigirConError(request, response, e.getMessage());
		}
	}

	private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String msg)
			throws IOException {
		response.sendRedirect(request.getContextPath() + "/SvCancionListar?error="
				+ URLEncoder.encode(msg, StandardCharsets.UTF_8));
	}
}