package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Direccion.
 * @see ixpan.pgf.dao.Direccion
 * @author Hibernate Tools
 */
@Stateless
public class DireccionHome {

	private static final Log log = LogFactory.getLog(DireccionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Direccion transientInstance) {
		log.debug("persisting Direccion instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Direccion persistentInstance) {
		log.debug("removing Direccion instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Direccion merge(Direccion detachedInstance) {
		log.debug("merging Direccion instance");
		try {
			Direccion result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Direccion findById(DireccionId id) {
		log.debug("getting Direccion instance with id: " + id);
		try {
			Direccion instance = entityManager.find(Direccion.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
