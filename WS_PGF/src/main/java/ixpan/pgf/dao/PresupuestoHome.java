package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Presupuesto.
 * @see ixpan.pgf.dao.Presupuesto
 * @author Hibernate Tools
 */
@Stateless
public class PresupuestoHome {

	private static final Log log = LogFactory.getLog(PresupuestoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Presupuesto transientInstance) {
		log.debug("persisting Presupuesto instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Presupuesto persistentInstance) {
		log.debug("removing Presupuesto instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Presupuesto merge(Presupuesto detachedInstance) {
		log.debug("merging Presupuesto instance");
		try {
			Presupuesto result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Presupuesto findById(PresupuestoId id) {
		log.debug("getting Presupuesto instance with id: " + id);
		try {
			Presupuesto instance = entityManager.find(Presupuesto.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
