package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvArtistaEliminar")
public class SvArtistaEliminar extends HttpServlet {

	private ControladoraLogica control = new ControladoraLogica();

	// POST: recibe el id, da de baja y redirige al listado.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			control.eliminarArtista(id);
			response.sendRedirect(request.getContextPath() + "/SvArtistaListar");
		} catch (Exception e) {
			String msg = (e instanceof NumberFormatException) ? "El id del artista no es válido." : e.getMessage();
			response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error="
					+ URLEncoder.encode(msg, StandardCharsets.UTF_8));
		}
	}
}