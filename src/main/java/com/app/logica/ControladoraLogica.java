package com.app.logica;

import java.util.List;

import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

	// La controladora de persistencia se encarga de hablar con la BD.
	// Desde aquí la usamos como "puente" para guardar datos.
	ControladoraPersistencia controlPersis = new ControladoraPersistencia();

	// ----- ARTISTA -----
	// Método de la capa lógica para crear un artista.
	// Antes de guardarlo en la BD, se hacen validaciones.
	public void crearArtista(Artista art) throws Exception {
		// Validación: el artista debe tener un nombre
		if (art.getNombre() == null || art.getNombre().isEmpty()) {
			throw new Exception("El artista debe tener un nombre.");
		}
		// Validación: el artista debe tener un género musical
		if (art.getGeneroMusical() == null || art.getGeneroMusical().isEmpty()) {
			throw new Exception("El artista debe tener un género musical.");
		}
		// Validación: la edad debe ser positiva
		if (art.getEdad() <= 0) {
			throw new Exception("La edad del artista debe ser mayor a 0.");
		}

		// AGREGAR AL MENOS UNA VALIDACIÓN MAS
		// NUEVA VALIDACIÓN: Fecha de nacimiento no nula y en el pasado/presente
      
		
		// Si pasa todas las validaciones, recién ahí se manda a la persistencia
		controlPersis.crearArtista(art);
	}

	// ----- CANCIÓN -----
	// Método de la capa lógica para crear una canción.
	// Se valida que tenga datos coherentes antes de persistirla.
	public void crearCancion(Cancion can) throws Exception {
		// Validación: el título no puede estar vacío
		if (can.getTitulo() == null || can.getTitulo().isEmpty()) {
			throw new Exception("La canción debe tener un título.");
		}
		// Validación: la duración debe ser mayor a cero
		if (can.getDuracion() <= 0) {
			throw new Exception("La duración debe ser mayor a 0 segundos.");
		}
		// Validación: el año debe tener sentido
		if (can.getAnio() < 1900) {
			throw new Exception("El año de la canción debe ser válido.");
		}
		
		// AGREGAR AL MENOS UNA VALIDACIÓN MAS
		
		
		// Si pasa todas las validaciones, se guarda en la base de datos
		controlPersis.crearCancion(can);
	}
	
	// BUSCAR UN ARTISTA por su id
	public Artista buscarUnArtista(int id) {
	return controlPersis.buscarArtista(id);
	}
	// LISTAR TODOS LOS ARTISTAS
	public List<Artista> listarArtistas() {
	return controlPersis.buscarTodosLosArtistas();
	}
	
	// BUSCAR UNA CANCION por su id
	public Cancion buscarUnaCancion(int id) {
	return controlPersis.buscarCancion(id);
	}
	// LISTAR TODOS LOS CANCIONES
	public List<Cancion> listarCanciones() {
	return controlPersis.buscarTodosLosCanciones();
	}
	
	// ----- VALIDACIONES (las mismas que en el alta) -----
	private void validarArtista(Artista art) throws Exception {
		if (art.getNombre() == null || art.getNombre().isEmpty()) {
			throw new Exception("El artista debe tener un nombre.");
		}
		if (art.getGeneroMusical() == null || art.getGeneroMusical().isEmpty()) {
			throw new Exception("El artista debe tener un género musical.");
		}
		if (art.getEdad() <= 0) {
			throw new Exception("La edad del artista debe ser mayor a 0.");
		}
		// Acá va también la validación extra que agregues en crearArtista
	}

	private void validarCancion(Cancion can) throws Exception {
		if (can.getTitulo() == null || can.getTitulo().isEmpty()) {
			throw new Exception("La canción debe tener un título.");
		}
		if (can.getDuracion() <= 0) {
			throw new Exception("La duración debe ser mayor a 0 segundos.");
		}
		if (can.getAnio() < 1900) {
			throw new Exception("El año de la canción debe ser válido.");
		}
		// Acá va también la validación extra que agregues en crearCancion
	}

	// ----- EDITAR / ELIMINAR ARTISTA -----
	public void editarArtista(Artista art) throws Exception {
		// Antes de editar, validamos que el artista exista
		if (controlPersis.buscarArtista(art.getId()) == null) {
			throw new Exception("El artista que querés editar no existe.");
		}
		// Mismas validaciones que en el alta
		validarArtista(art);
		controlPersis.editarArtista(art);
	}

	public void eliminarArtista(int id) throws Exception {
		// Antes de eliminar, validamos que exista
		if (controlPersis.buscarArtista(id) == null) {
			throw new Exception("El artista que querés eliminar no existe.");
		}
		controlPersis.eliminarArtista(id);
	}

	// ----- EDITAR / ELIMINAR CANCION -----
	public void editarCancion(Cancion can) throws Exception {
		if (controlPersis.buscarCancion(can.getId()) == null) {
			throw new Exception("La canción que querés editar no existe.");
		}
		validarCancion(can);
		controlPersis.editarCancion(can);
	}

	public void eliminarCancion(int id) throws Exception {
		if (controlPersis.buscarCancion(id) == null) {
			throw new Exception("La canción que querés eliminar no existe.");
		}
		controlPersis.eliminarCancion(id);
	}
	
}



