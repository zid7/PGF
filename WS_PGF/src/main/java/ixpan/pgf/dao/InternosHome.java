package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Internos.
 * @see ixpan.pgf.dao.Internos
 * @author Hibernate Tools
 */
@Stateless
public class InternosHome {

	private static final Log log = LogFactory.getLog(InternosHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Interno transientInstance) {
		log.debug("persisting Internos instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Interno persistentInstance) {
		log.debug("removing Internos instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Interno merge(Interno detachedInstance) {
		log.debug("merging Internos instance");
		try {
			Interno result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Interno findById(int id) {
		log.debug("getting Internos instance with id: " + id);
		try {
			Interno instance = entityManager.find(Interno.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
