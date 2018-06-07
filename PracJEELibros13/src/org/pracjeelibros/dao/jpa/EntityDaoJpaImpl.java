package org.pracjeelibros.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.pracjeelibros.dao.EntityDao;
import org.pracjeelibros.dao.JpaHelper;
import org.pracjeelibros.model.Libro;

public class EntityDaoJpaImpl<T, Id extends Serializable> implements EntityDao<T, Id> {

	private static final Logger log = Logger.getLogger(EntityDaoJpaImpl.class);
	private Class<T> claseDePersistencia;

	@SuppressWarnings("unchecked")
	public EntityDaoJpaImpl() {
		this.claseDePersistencia =
				(Class<T>) ((ParameterizedType) getClass()
								.getGenericSuperclass())
									.getActualTypeArguments()[0];
	}

	@Override
	public T buscarPorClave(Id id) {
		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		T objeto = null;
		try {
			objeto = (T) manager.find(claseDePersistencia, id);
		} finally {
			manager.close();
		}
		return objeto;
	}

	@Override
	public List<T> buscarTodos() {
		final String QRY = "select o from " + claseDePersistencia.getSimpleName() + " o";
		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		List<T> listaDeObjetos = null;
		try {
			TypedQuery<T> consulta = manager.createQuery(QRY, claseDePersistencia);
			listaDeObjetos = consulta.getResultList();
		} finally {
			manager.close();
		}
		return listaDeObjetos;
	}

	@Override
	public void insertar(T t) {
		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.merge(t);
			tx.commit();
		} catch (PersistenceException e) {
			log.error(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Override
	public void actualizar(T t) {
		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.merge(t);
			tx.commit();
		} catch (PersistenceException e) {
			log.error(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Override
	public void borrar(T t) {
		EntityManagerFactory factoriaSession = JpaHelper.getJpaFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.remove(manager.merge(t));
			tx.commit();
		} catch (PersistenceException e) {
			log.error(e.getMessage());
		} finally {
			manager.close();
		}
	}

}