package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CItemCaja.
 * @see ixpan.pgf.dao.CItemCaja
 * @author Hibernate Tools
 */
@Stateless
public class CItemCajaHome {

	private static final Log log = LogFactory.getLog(CItemCajaHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CItemCaja transientInstance) {
		log.debug("persisting CItemCaja instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CItemCaja persistentInstance) {
		log.debug("removing CItemCaja instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CItemCaja merge(CItemCaja detachedInstance) {
		log.debug("merging CItemCaja instance");
		try {
			CItemCaja result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CItemCaja findById(int id) {
		log.debug("getting CItemCaja instance with id: " + id);
		try {
			CItemCaja instance = entityManager.find(CItemCaja.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
