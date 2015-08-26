package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Obra.
 * @see ixpan.pgf.dao.Obra
 * @author Hibernate Tools
 */
@Stateless
public class ObraHome {

	private static final Log log = LogFactory.getLog(ObraHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Obra transientInstance) {
		log.debug("persisting Obra instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Obra persistentInstance) {
		log.debug("removing Obra instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Obra merge(Obra detachedInstance) {
		log.debug("merging Obra instance");
		try {
			Obra result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Obra findById(int id) {
		log.debug("getting Obra instance with id: " + id);
		try {
			Obra instance = entityManager.find(Obra.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
