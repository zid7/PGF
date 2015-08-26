package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CTipoDireccion.
 * @see ixpan.pgf.dao.CTipoDireccion
 * @author Hibernate Tools
 */
@Stateless
public class CTipoDireccionHome {

	private static final Log log = LogFactory.getLog(CTipoDireccionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CTipoDireccion transientInstance) {
		log.debug("persisting CTipoDireccion instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CTipoDireccion persistentInstance) {
		log.debug("removing CTipoDireccion instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CTipoDireccion merge(CTipoDireccion detachedInstance) {
		log.debug("merging CTipoDireccion instance");
		try {
			CTipoDireccion result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CTipoDireccion findById(int id) {
		log.debug("getting CTipoDireccion instance with id: " + id);
		try {
			CTipoDireccion instance = entityManager.find(CTipoDireccion.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
