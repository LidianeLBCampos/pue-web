package org.pracjeelibros.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.pracjeelibros.JpaHelper;
import org.pracjeelibros.model.Categoria;

public class CategoriaDao {

	public List<Categoria> buscarTodas() {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Categoria> consulta = manager.createQuery("SELECT cat FROM Categoria cat", Categoria.class);
		List<Categoria> listaDeCategorias = consulta.getResultList();
		manager.close();

		return listaDeCategorias;
	}

	public Categoria buscarById(int id) {

		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Categoria> consulta = manager.createQuery("Select cat FROM Categoria cat where cat.id=?1",
				Categoria.class);
		consulta.setParameter(1, id);
		Categoria categoria = consulta.getSingleResult();
		manager.close();

		return categoria;
	}

}
