package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CStastusSolicitudPago.
 * @see ixpan.pgf.dao.CStastusSolicitudPago
 * @author Hibernate Tools
 */
@Stateless
public class CStastusSolicitudPagoHome {

	private static final Log log = LogFactory.getLog(CStastusSolicitudPagoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CStastusSolicitudPago transientInstance) {
		log.debug("persisting CStastusSolicitudPago instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CStastusSolicitudPago persistentInstance) {
		log.debug("removing CStastusSolicitudPago instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CStastusSolicitudPago merge(CStastusSolicitudPago detachedInstance) {
		log.debug("merging CStastusSolicitudPago instance");
		try {
			CStastusSolicitudPago result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CStastusSolicitudPago findById(Integer id) {
		log.debug("getting CStastusSolicitudPago instance with id: " + id);
		try {
			CStastusSolicitudPago instance = entityManager.find(CStastusSolicitudPago.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
