package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CStatusPresupuesto.
 * @see ixpan.pgf.dao.CStatusPresupuesto
 * @author Hibernate Tools
 */
@Stateless
public class CStatusPresupuestoHome {

	private static final Log log = LogFactory.getLog(CStatusPresupuestoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CStatusPresupuesto transientInstance) {
		log.debug("persisting CStatusPresupuesto instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CStatusPresupuesto persistentInstance) {
		log.debug("removing CStatusPresupuesto instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CStatusPresupuesto merge(CStatusPresupuesto detachedInstance) {
		log.debug("merging CStatusPresupuesto instance");
		try {
			CStatusPresupuesto result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CStatusPresupuesto findById(int id) {
		log.debug("getting CStatusPresupuesto instance with id: " + id);
		try {
			CStatusPresupuesto instance = entityManager.find(CStatusPresupuesto.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
