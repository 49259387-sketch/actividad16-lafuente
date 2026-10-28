package com.app.persistencia;

import com.app.logica.Artista;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ArtistaJpaController {
	
	
	
	// "Fábrica" de conexiones (EntityManagerFactory).
	// Sirve para crear EntityManager, que son los que realmente hablan con la BD.
	private EntityManagerFactory emf = null;

	// En el constructor decimos con qué unidad de persistencia vamos a trabajar.
	// Esa unidad está definida en persistence.xml con el nombre "ejemplojpa".
	public ArtistaJpaController() {
		emf = Persistence.createEntityManagerFactory("ejemplojpa"); // ahi va el nombre de la unidad de persistencia
	}

	// Método que crea y devuelve un nuevo "EntityManager".
	// Pensalo como: "dame un empleado que se conecte a la base para trabajar".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Método para guardar un Artista en la base de datos.
	public void crear(Artista art) {
		EntityManager em = null;
		try {
			// Abrimos la conexión (pedimos un empleado).
			em = getEntityManager();
			
			// Iniciamos una transacción: empieza la "promesa".
			em.getTransaction().begin();
			
			// Guardamos el objeto en la base (pero todavía no está confirmado).
			em.persist(art);
			
			// Confirmamos la transacción: ahora sí el objeto queda guardado.
			em.getTransaction().commit();
		} finally {
			// Cerramos la conexión (el empleado ya no hace falta).
			if (em != null) {
				em.close();
			}
		}
	}
	
	// BUSCAR UNO: busca un artista por su id.
	// No hace falta transacción porque no modifica nada en la base.
	public Artista buscarUno(int id) {
	EntityManager em = getEntityManager();
	try {
	// find busca por clave primaria. Si no existe, devuelve null.
	return em.find(Artista.class, id);
	} finally {
	em.close();
	}
	}
	
	// BUSCAR TODOS: devuelve una lista con todos los artistas.
	public List<Artista> buscarTodos() {
	EntityManager em = getEntityManager();
	try {
	// Consulta JPQL: "traeme todos los objetos Artista"
	return em.createQuery("SELECT a FROM Artista a", Artista.class)
	.getResultList();
	} finally {
	em.close();
	}
	}
	
	// EDITAR: actualiza un artista que ya existe en la base.
	public void editar(Artista art) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();

			// merge: si el objeto ya existe (mismo id), actualiza sus datos en la base.
			em.merge(art);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// ELIMINAR: borra un artista por su id.
	public void eliminar(int id) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();

			// Primero lo buscamos: para borrar, JPA necesita el objeto, no solo el id.
			Artista art = em.find(Artista.class, id);

			// Si existe, lo borramos. Si no existe, no hacemos nada.
			if (art != null) {
				em.remove(art);
			}

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
}


