package org.pracjeelibros.model;

import java.util.List;

import org.pracjeelibros.DataBaseException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pracjeelibros.HibernateHelper;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Libro {
	
	private String isbn;
	private String titulo; 
	private String categoria;

	public Libro() {

	}

	public Libro(String isbn, String titulo, String categoria) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = categoria;
	}

	@SuppressWarnings("unchecked")
	public static List<String> buscarTodasLasCategorias() {
		// String consultaSQL = "select distinct categoria from libros";
		// DataBaseHelper<String> helper = new DataBaseHelper<>();
		// List<String> listaCat = helper.seleccionarRegistros(consultaSQL,
		// String.class);
		// return listaCat;
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		String consulta = "select distinct libro.categoria from Libro libro";
		List<String> listaDeCategorias = session.createQuery(consulta).list();
		session.close();
		return listaDeCategorias;
	}

	public void insertar() {
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}

	public void actualizar() {
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		session.beginTransaction();
		session.saveOrUpdate(this);
		session.getTransaction().commit();
	}

	public void borrar() {
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		session.beginTransaction();
		session.delete(this);
		session.getTransaction().commit();

		// final Logger log = Logger.getLogger(getClass().getName());
		//
		// String consultaSQL = "delete from libros where isbn='" + this.isbn + "'";
		// DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
		// int resultado = helper.modificarRegistro(consultaSQL);
		//
		// if (resultado == 1) {
		// log.info("Se ha eliminado el libro: " + this.isbn);
		// } else {
		// log.warn("No se pudo eliminar el libro: " + this.isbn);
		// }

	}

	@SuppressWarnings("unchecked")
	public static List<Libro> buscarTodos() throws DataBaseException {
		// String consultaSQL = "select * from libros";
		// DataBaseHelper<Libro> helper = new DataBaseHelper<>();
		// List<Libro> libros = helper.seleccionarRegistros(consultaSQL, Libro.class);
		// return libros;
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		List<Libro> listaDeLibros = session.createQuery("from Libro libro").list();
		session.close();
		return listaDeLibros;
	}

	public static Libro buscarPorClave(String isbn) {
		// String consultaSQL = "select * from libros where isbn='" + isbn + "'";
		// DataBaseHelper<Libro> helper = new DataBaseHelper<>();
		// List<Libro> libros = helper.seleccionarRegistros(consultaSQL, Libro.class);
		// return libros.get(0);
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		Libro libro = (Libro) session.get(Libro.class, isbn);
		session.close();
		return libro;
	}

	@SuppressWarnings("unchecked")
	public static List<Libro> buscarPorCategoria(String cat) {
		// String consultaSQL = "select isbn,titulo,categoria from libros where
		// categoria='" + cat + "'";
		// DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
		// List<Libro> listaDeLibros = helper.seleccionarRegistros(consultaSQL,
		// Libro.class);
		// return listaDeLibros;
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		Query consulta = session.createQuery("from Libro libro where libro.categoria=:categoria");
		consulta.setString("categoria", cat);
		List<Libro> listaDeLibros = consulta.list();
		session.close();
		return listaDeLibros;

	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCategoria(String categoria) {
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
