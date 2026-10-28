package com.app.persistencia;

import com.app.logica.Artista;
import com.app.logica.Cancion;
import java.util.List;


public class ControladoraPersistencia {
	// Artista: Creamos una instancia del controlador que sabe cómo hablar con la
	// base de datos
	ArtistaJpaController artistaJpa = new ArtistaJpaController();

	// Cancion: Creamos una instancia del controlador que sabe cómo hablar con la
	// base de datos
	// INSTANCIAR OBJETO DE CANCIONJPACONTROLER AL IGUAL QUE SE HIZO CON ARTISTA
	CancionJpaController cancionJpa = new CancionJpaController();
	
	// Este método es el que se usa desde otras capas para crear un ARTISTA
	public void crearArtista(Artista artista) {
		// Delegamos la tarea al controlador especializado
		artistaJpa.crear(artista);
	}
	
// AL IGUAL QUE CON ARTISTA, GENERAR EL MÉTODO “crearCancion()”
	
	public void crearCancion(Cancion cancion) {
		cancionJpa.crear(cancion);
	}
	
	// BUSCAR UN ARTISTA
	public Artista buscarArtista(int id) {
	return artistaJpa.buscarUno(id);
	}
	// BUSCAR TODOS LOS ARTISTAS
	public List<Artista> buscarTodosLosArtistas() {
	return artistaJpa.buscarTodos();
	}
	
	// BUSCAR UNA CANCION
		public Cancion buscarCancion(int id) {
		return cancionJpa.buscarUno(id);
		}
		// BUSCAR TODOS LOS ARTISTAS
		public List<Cancion> buscarTodosLosCanciones() {
		return cancionJpa.buscarTodos();
		}
	
		// EDITAR UN ARTISTA
		public void editarArtista(Artista artista) {
			artistaJpa.editar(artista);
		}

		// ELIMINAR UN ARTISTA
		public void eliminarArtista(int id) {
			artistaJpa.eliminar(id);
		}

		// EDITAR UNA CANCION
		public void editarCancion(Cancion cancion) {
			cancionJpa.editar(cancion);
		}

		// ELIMINAR UNA CANCION
		public void eliminarCancion(int id) {
			cancionJpa.eliminar(id);
		}
		
}


