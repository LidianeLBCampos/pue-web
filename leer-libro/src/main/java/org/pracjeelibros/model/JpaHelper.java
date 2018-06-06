package org.pracjeelibros.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaHelper {

	private static final EntityManagerFactory emf = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			return Persistence.createEntityManagerFactory("praclibrosjeeorm");
		} catch (Throwable ex) {
			System.out.println("Erros creando factoria JPA" + ex.getMessage());
			throw new RuntimeException("Error creando factoria JPA", ex);
		}
	}

	public static EntityManagerFactory getJpaFactory() {
		return emf;
	}
}
