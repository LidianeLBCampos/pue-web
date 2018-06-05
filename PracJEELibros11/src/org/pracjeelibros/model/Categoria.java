package org.pracjeelibros.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pracjeelibros.HibernateHelper;

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	private Integer id;
	private String descripcion;
	
	@OneToMany
	@JoinColumn(name="categoria")
	private List<Libro> libros;
	
	public Integer getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@SuppressWarnings("unchecked")
	public static List<Categoria> buscarTodasLasCategorias() {
	SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
	Session session = factoriaSession.openSession();
	String consulta = "From Categoria categoria";
	List<Categoria> listaDeCategorias = session.createQuery(consulta).list();
	session.close();
	return listaDeCategorias;
	}

	public static Categoria buscarCategoria(int id) {
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		Categoria categoria = (Categoria) session.get(Categoria.class, new Integer(id));
		session.close();
		return categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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

		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descripcion=" + descripcion + ", libros=" + libros + "]";
	}
	
	
}