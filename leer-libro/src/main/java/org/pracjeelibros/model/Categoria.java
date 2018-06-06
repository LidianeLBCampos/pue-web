package org.pracjeelibros.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	private Integer id;
	private String descripcion;

	@OneToMany
	@JoinColumn(name = "categoria")
	private List<Libro> libros;

	public Categoria() {

	}
	
	public Categoria(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public static List<Categoria> buscarTodas() {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Categoria> consulta = manager.createQuery("SELECT cat FROM Categoria cat", Categoria.class);
		List<Categoria> listaDeCategorias = consulta.getResultList();
		manager.close();

		return listaDeCategorias;
	}

	public static Categoria buscarById(int id) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Categoria> consulta = manager.createQuery("Select cat FROM Categoria cat where cat.id=?1",
				Categoria.class);
		consulta.setParameter(1, id);
		Categoria categoria = consulta.getSingleResult();
		manager.close();

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

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

}