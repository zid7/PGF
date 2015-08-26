package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ItemsPresupuesto.
 * @see ixpan.pgf.dao.ItemsPresupuesto
 * @author Hibernate Tools
 */
@Stateless
public class ItemsPresupuestoHome {

	private static final Log log = LogFactory.getLog(ItemsPresupuestoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ItemsPresupuesto transientInstance) {
		log.debug("persisting ItemsPresupuesto instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ItemsPresupuesto persistentInstance) {
		log.debug("removing ItemsPresupuesto instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ItemsPresupuesto merge(ItemsPresupuesto detachedInstance) {
		log.debug("merging ItemsPresupuesto instance");
		try {
			ItemsPresupuesto result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemsPresupuesto findById(int id) {
		log.debug("getting ItemsPresupuesto instance with id: " + id);
		try {
			ItemsPresupuesto instance = entityManager.find(ItemsPresupuesto.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
