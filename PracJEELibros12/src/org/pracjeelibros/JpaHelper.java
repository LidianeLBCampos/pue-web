package org.pracjeelibros;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class JpaHelper {

	private static final Logger log = Logger.getLogger(JpaHelper.class);
	private static final EntityManagerFactory emf = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			return Persistence.createEntityManagerFactory("praclibrosjeeorm");
		} catch (Throwable ex) {
			log.error("Error creando factoria JPA", ex);
			throw new RuntimeException("Error creando factoria JPA", ex);
		}
	}

	public static EntityManagerFactory getJpaFactory() {
		return emf;
	}
}
