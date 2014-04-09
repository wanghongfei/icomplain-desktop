package cn.neo.icomplain.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A persistence utility class.
 * @author whf
 *
 */
public class DbManager {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	private static EntityManager em = emf.createEntityManager();
	
	public static EntityManager getEntityManager() {
		return em;
	}
	
	/**
	 * Persist the specified entity to database.
	 * @param o The entity you will persist.
	 */
	public static void persist(Object o) {
		em.getTransaction().begin();
		
		em.persist(o);
		em.flush();
		
		em.getTransaction().commit();
	}
}
