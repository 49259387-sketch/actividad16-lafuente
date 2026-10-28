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

@WebServlet("/SvCancionEliminar")
public class SvCancionEliminar extends HttpServlet {

	private ControladoraLogica control = new ControladoraLogica();

	// POST: recibe el id, da de baja y redirige al listado.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			control.eliminarCancion(id);
			response.sendRedirect(request.getContextPath() + "/SvCancionListar");
		} catch (Exception e) {
			String msg = (e instanceof NumberFormatException) ? "El id de la canción no es válido." : e.getMessage();
			response.sendRedirect(request.getContextPath() + "/SvCancionListar?error="
					+ URLEncoder.encode(msg, StandardCharsets.UTF_8));
		}
	}
}