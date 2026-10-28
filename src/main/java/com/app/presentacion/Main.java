package com.app.presentacion;

import com.app.logica.Artista;
import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
	public static void main(String[] args) {
	// INSTANCIAR DOS ARTISTAS
	
		
	// INSTANCIAR DOS CANCIONES
	
		
		ControladoraLogica conLog = new ControladoraLogica();
		try {
		// CREAR AL MENOS DOS ARTISTAS, Y DOS CANCIONES
			// INSTANCIAR DOS ARTISTAS
			Calendar cal1 = new GregorianCalendar(1972, Calendar.AUGUST, 9);
			Date fechaLeo = cal1.getTime();
			
			Calendar cal2 = new GregorianCalendar(2009, Calendar.DECEMBER, 11);
			Date fechaChepe = cal2.getTime();
			
			Artista ar1 = new Artista("Leo", "Bachata", "Peruano", 45, fechaLeo);
			conLog.crearArtista(ar1);//se lo da a la controladora logica
			
			Artista ar2 = new Artista("Chepe", "Deto´o", "Argentino", 18, fechaChepe);
			conLog.crearArtista(ar2);
			
			Cancion c1 = new Cancion("Volve tilin", 3.23f, 2010);
			conLog.crearCancion(c1);
			
			Cancion c2 = new Cancion("Musa", 3.40f, 2022);
			conLog.crearCancion(c2);
			
			
			
		} catch (Exception e) {
			System.out.println("No se pudo crear el elemento: " + e.getMessage());
		}
		
		// ----- LECTURA DE ARTISTAS -----
		// ----- LECTURA DE ARTISTAS -----
		System.out.println("=== TODOS LOS ARTISTAS ===");
		List<Artista> artistas = conLog.listarArtistas();
		for (Artista a : artistas) {
		System.out.println(a.toString());
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 1 ===");
		Artista encontrado = conLog.buscarUnArtista(1);
		if (encontrado != null) {
		System.out.println(encontrado);
		} else {
		System.out.println("No existe un artista con ese id");
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 999 ===");
		Artista noExiste = conLog.buscarUnArtista(999);
		if (noExiste != null) {
		System.out.println(noExiste);
		} else {
		System.out.println("No existe un artista con ese id");
		}
		

		// ----- LECTURA DE CANCION -----
		System.out.println("=== TODOS LOS ARTISTAS ===");
		List<Cancion> cancion = conLog.listarCanciones();
		for (Cancion c : cancion) {
		System.out.println(c.toString());
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 1 ===");
		Cancion encontrada= conLog.buscarUnaCancion(1);
		if (encontrada != null) {
		System.out.println(encontrada);
		} else {
		System.out.println("No existe un artista con ese id");
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 999 ===");
		Cancion noExiste2 = conLog.buscarUnaCancion(67);
		if (noExiste2 != null) {
		System.out.println(noExiste2);
		} else {
		System.out.println("No existe una cancion con ese id");
		}
		
	}
}


