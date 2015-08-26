package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CajaChica.
 * @see ixpan.pgf.dao.CajaChica
 * @author Hibernate Tools
 */
@Stateless
public class CajaChicaHome {

	private static final Log log = LogFactory.getLog(CajaChicaHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CajaChica transientInstance) {
		log.debug("persisting CajaChica instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CajaChica persistentInstance) {
		log.debug("removing CajaChica instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CajaChica merge(CajaChica detachedInstance) {
		log.debug("merging CajaChica instance");
		try {
			CajaChica result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CajaChica findById(CajaChicaId id) {
		log.debug("getting CajaChica instance with id: " + id);
		try {
			CajaChica instance = entityManager.find(CajaChica.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
