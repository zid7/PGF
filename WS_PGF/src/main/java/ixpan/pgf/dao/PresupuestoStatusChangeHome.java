package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PresupuestoStatusChange.
 * @see ixpan.pgf.dao.PresupuestoStatusChange
 * @author Hibernate Tools
 */
@Stateless
public class PresupuestoStatusChangeHome {

	private static final Log log = LogFactory.getLog(PresupuestoStatusChangeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(PresupuestoStatusChange transientInstance) {
		log.debug("persisting PresupuestoStatusChange instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(PresupuestoStatusChange persistentInstance) {
		log.debug("removing PresupuestoStatusChange instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public PresupuestoStatusChange merge(PresupuestoStatusChange detachedInstance) {
		log.debug("merging PresupuestoStatusChange instance");
		try {
			PresupuestoStatusChange result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PresupuestoStatusChange findById(int id) {
		log.debug("getting PresupuestoStatusChange instance with id: " + id);
		try {
			PresupuestoStatusChange instance = entityManager.find(PresupuestoStatusChange.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
