package org.pracjeelibros.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.pracjeelibros.dao.jpa.CategoriaDaoJpaImpl;

@Entity
@Table(name = "libros")
public class Libro {

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
		this.categoria = new CategoriaDaoJpaImpl().buscarPorClave(categoria);
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