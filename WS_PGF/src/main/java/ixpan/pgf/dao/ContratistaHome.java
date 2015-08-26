package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Contratista.
 * @see ixpan.pgf.dao.Contratista
 * @author Hibernate Tools
 */
@Stateless
public class ContratistaHome {

	private static final Log log = LogFactory.getLog(ContratistaHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Contratista transientInstance) {
		log.debug("persisting Contratista instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Contratista persistentInstance) {
		log.debug("removing Contratista instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Contratista merge(Contratista detachedInstance) {
		log.debug("merging Contratista instance");
		try {
			Contratista result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Contratista findById(Integer id) {
		log.debug("getting Contratista instance with id: " + id);
		try {
			Contratista instance = entityManager.find(Contratista.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
