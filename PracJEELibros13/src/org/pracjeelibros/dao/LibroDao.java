package org.pracjeelibros.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.pracjeelibros.JpaHelper;
import org.pracjeelibros.model.Libro;

public class LibroDao {
	private static final Logger log = Logger.getLogger(LibroDao.class);

	public void insertar(Libro libro) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		try {
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
			manager.persist(libro);
			tx.commit();
		} catch (PersistenceException e) {
			log.error("Error al insertar libro", e);
		} finally {
			manager.close();
		}
	}

	public void actualizar(Libro libro) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		try {
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
			manager.merge(libro);
			tx.commit();
		} catch (PersistenceException e) {
			log.error("Error al actualizar el libro", e);
		} finally {
			manager.close();
		}
	}

	public void borrar(Libro libro) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		try {
			EntityTransaction tx = null;
			tx = manager.getTransaction();
			tx.begin();
			manager.remove(manager.merge(libro));
			tx.commit();
		} catch (PersistenceException e) {
			log.error("Error al borrar el libro", e);
		} finally {
			manager.close();
		}
	}

	public List<Libro> buscarTodos() {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery("SELECT lib FROM Libro lib JOIN " + "FETCH lib.categoria",
				Libro.class);
		List<Libro> listaDeLibros = Collections.emptyList();
		listaDeLibros = consulta.getResultList();
		manager.close();

		return listaDeLibros;
	}

	public Libro buscarPorClave(String isbn) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager
				.createQuery("Select lib from Libro lib " + "JOIN FETCH lib.categoria where lib.isbn=?1", Libro.class);

		consulta.setParameter(1, isbn);
		Libro libro = consulta.getSingleResult();
		manager.close();

		return libro;

	}

	public List<Libro> buscarPorCategoria(int categoria) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery("Select lib from Libro lib where lib.categoria=?1",
				Libro.class);

		consulta.setParameter(1, new CategoriaDao().buscarById(categoria));
		List<Libro> listaDeLibros = Collections.emptyList();
		;
		listaDeLibros = consulta.getResultList();

		manager.close();
		return listaDeLibros;
	}

}
