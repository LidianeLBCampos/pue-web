package org.pracjeelibros.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.pracjeelibros.JpaHelper;

@Entity
@Table(name = "libros")
public class Libro {

	private static final Logger log = Logger.getLogger(Libro.class);
	@Id
	private String isbn;
	private String titulo;

	@ManyToOne
	@JoinColumn(name = "categoria")
	private Categoria categoria;

	public Libro() {
	}

	public Libro(String isbn, String titulo, int categoria) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = Categoria.buscarById(categoria);
	}

	public void insertar() {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		try {
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
			manager.persist(this);
			tx.commit();
		} catch (PersistenceException e) {
			log.error("Error al insertar libro", e);
		} finally {
			manager.close();
		}
	}

	public void actualizar() {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		try {
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
			manager.merge(this);
			tx.commit();
		} catch (PersistenceException e) {
			log.error("Error al actualizar el libro", e);
		} finally {
			manager.close();
		}
	}

	public void borrar() {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		try {
			EntityTransaction tx = null;
			tx = manager.getTransaction();
			tx.begin();
			manager.remove(manager.merge(this));
			tx.commit();
		} catch (PersistenceException e) {
			log.error("Error al borrar el libro", e);
		} finally {
			manager.close();
		}
	}

	public static List<Libro> buscarTodos() {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery("SELECT lib FROM Libro lib JOIN " + "FETCH lib.categoria",
				Libro.class);
		List<Libro> listaDeLibros = Collections.emptyList();
		listaDeLibros = consulta.getResultList();
		manager.close();

		return listaDeLibros;
	}

	public static Libro buscarPorClave(String isbn) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager
				.createQuery("Select lib from Libro lib " + "JOIN FETCH lib.categoria where lib.isbn=?1", Libro.class);

		consulta.setParameter(1, isbn);
		Libro libro = consulta.getSingleResult();
		manager.close();

		return libro;

	}

	public static List<Libro> buscarPorCategoria(int categoria) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery("Select lib from Libro lib where lib.categoria=?1",
				Libro.class);

		consulta.setParameter(1, Categoria.buscarById(categoria));
		List<Libro> listaDeLibros = Collections.emptyList();
		;
		listaDeLibros = consulta.getResultList();

		manager.close();
		return listaDeLibros;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[isbn=" + isbn + ", titulo=" + titulo + ", categoria=" + categoria + "]";
	}
}