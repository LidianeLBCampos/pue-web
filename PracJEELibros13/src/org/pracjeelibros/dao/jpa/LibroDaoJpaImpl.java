package org.pracjeelibros.dao.jpa;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.pracjeelibros.dao.JpaHelper;
import org.pracjeelibros.dao.LibroDao;
import org.pracjeelibros.model.Categoria;
import org.pracjeelibros.model.Libro;

@SuppressWarnings("rawtypes")
public class LibroDaoJpaImpl extends EntityDaoJpaImpl implements LibroDao {
	private static final Logger log = Logger.getLogger(LibroDaoJpaImpl.class);

	@Override
	public List<Libro> buscarPorIdCategoria(int categoria) {
		final String QRY = "Select lib from Libro lib where lib.categoria=?1";
		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery(QRY, Libro.class);
		consulta.setParameter(1, new CategoriaDaoJpaImpl().buscarPorClave(categoria));
		List<Libro> listaDeLibros = null;
		try {
			listaDeLibros = consulta.getResultList();
		} catch (Exception e) {
			listaDeLibros = Collections.emptyList();
		} finally {
			manager.close();
		}
		return listaDeLibros;
	}
}
