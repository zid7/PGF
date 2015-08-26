package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SolicitudPago.
 * @see ixpan.pgf.dao.SolicitudPago
 * @author Hibernate Tools
 */
@Stateless
public class SolicitudPagoHome {

	private static final Log log = LogFactory.getLog(SolicitudPagoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SolicitudPago transientInstance) {
		log.debug("persisting SolicitudPago instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SolicitudPago persistentInstance) {
		log.debug("removing SolicitudPago instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SolicitudPago merge(SolicitudPago detachedInstance) {
		log.debug("merging SolicitudPago instance");
		try {
			SolicitudPago result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SolicitudPago findById(int id) {
		log.debug("getting SolicitudPago instance with id: " + id);
		try {
			SolicitudPago instance = entityManager.find(SolicitudPago.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
