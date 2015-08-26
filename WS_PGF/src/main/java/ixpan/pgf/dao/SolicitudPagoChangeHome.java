package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SolicitudPagoChange.
 * @see ixpan.pgf.dao.SolicitudPagoChange
 * @author Hibernate Tools
 */
@Stateless
public class SolicitudPagoChangeHome {

	private static final Log log = LogFactory.getLog(SolicitudPagoChangeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SolicitudPagoChange transientInstance) {
		log.debug("persisting SolicitudPagoChange instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SolicitudPagoChange persistentInstance) {
		log.debug("removing SolicitudPagoChange instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SolicitudPagoChange merge(SolicitudPagoChange detachedInstance) {
		log.debug("merging SolicitudPagoChange instance");
		try {
			SolicitudPagoChange result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SolicitudPagoChange findById(SolicitudPagoChangeId id) {
		log.debug("getting SolicitudPagoChange instance with id: " + id);
		try {
			SolicitudPagoChange instance = entityManager.find(SolicitudPagoChange.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
