package Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JpaUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JpaUtil.class);
	private static final String persistenceUnit = "progetto_settimanale_3";
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
	protected static final EntityManager em = emf.createEntityManager();
	protected static final EntityTransaction t = em.getTransaction();


	private static final EntityManagerFactory entityManagerFactory;

	static {
		try {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("progetto_settimanale_3");
		} catch (Throwable ex) {
			logger.error("Initial EntityManagerFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

}
