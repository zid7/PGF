package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CTipoPresupuesto.
 * @see ixpan.pgf.dao.CTipoPresupuesto
 * @author Hibernate Tools
 */
@Stateless
public class CTipoPresupuestoHome {

	private static final Log log = LogFactory.getLog(CTipoPresupuestoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CTipoPresupuesto transientInstance) {
		log.debug("persisting CTipoPresupuesto instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CTipoPresupuesto persistentInstance) {
		log.debug("removing CTipoPresupuesto instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CTipoPresupuesto merge(CTipoPresupuesto detachedInstance) {
		log.debug("merging CTipoPresupuesto instance");
		try {
			CTipoPresupuesto result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CTipoPresupuesto findById(int id) {
		log.debug("getting CTipoPresupuesto instance with id: " + id);
		try {
			CTipoPresupuesto instance = entityManager.find(CTipoPresupuesto.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
