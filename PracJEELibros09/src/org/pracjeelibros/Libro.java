package org.pracjeelibros;


import java.util.List;

import org.apache.log4j.Logger;

public class Libro {

	private String isbn, titulo, categoria;

	public Libro() {

	}

	public Libro(String isbn, String titulo, String categoria) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = categoria;
	}

	public static List<String> buscarTodasLasCategorias() {
		String consultaSQL = "select distinct categoria from libros";
		DataBaseHelper<String> helper = new DataBaseHelper<>();

		List<String> listaCat = helper.seleccionarRegistros(consultaSQL, String.class);

		return listaCat;
	}

	public void insertar() {
		DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();

		String consultaSQL = "insert into libros (isbn,titulo,categoria) values ";
		consultaSQL += "('" + this.isbn + "','" + this.titulo + "','" + this.categoria + "')";

		db.modificarRegistro(consultaSQL);
	}

	public void actualizar() {
		String consultaSQL = "update libros set titulo='" + this.titulo + "',categoria='" + categoria + "' where isbn='"
				+ this.isbn + "'";
		DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
		helper.modificarRegistro(consultaSQL);
	}

	public void borrar() {
		
		final Logger log = Logger.getLogger(getClass().getName());
		
		String consultaSQL = "delete from libros where isbn='" + this.isbn + "'";
		DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
		int resultado = helper.modificarRegistro(consultaSQL);
		
		if(resultado == 1) {
			log.info("Se ha eliminado el libro: " + this.isbn);
		} else {
			log.warn("No se pudo eliminar el libro: " + this.isbn);
		}
		
	}
	@Override
	public String toString() {
		return "[isbn=" + isbn + ", titulo=" + titulo + ", categoria=" + categoria + "]";
	}

	public static List<Libro> buscarTodos() throws DataBaseException {
		String consultaSQL = "select * from libros";
		DataBaseHelper<Libro> helper = new DataBaseHelper<>();

		List<Libro> libros = helper.seleccionarRegistros(consultaSQL, Libro.class);

		return libros;
	}

	public static Libro buscarPorClave(String isbn) {
		String consultaSQL = "select * from libros where isbn='" + isbn + "'";
		DataBaseHelper<Libro> helper = new DataBaseHelper<>();

		List<Libro> libros = helper.seleccionarRegistros(consultaSQL, Libro.class);

		return libros.get(0);
	}

	public static List<Libro> buscarPorCategoria(String cat) {
		String consultaSQL = "select isbn,titulo,categoria from libros where categoria='" + cat + "'";
		DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
		List<Libro> listaDeLibros = helper.seleccionarRegistros(consultaSQL, Libro.class);
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

}
