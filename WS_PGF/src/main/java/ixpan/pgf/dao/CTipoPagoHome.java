package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CTipoPago.
 * @see ixpan.pgf.dao.CTipoPago
 * @author Hibernate Tools
 */
@Stateless
public class CTipoPagoHome {

	private static final Log log = LogFactory.getLog(CTipoPagoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CTipoPago transientInstance) {
		log.debug("persisting CTipoPago instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CTipoPago persistentInstance) {
		log.debug("removing CTipoPago instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CTipoPago merge(CTipoPago detachedInstance) {
		log.debug("merging CTipoPago instance");
		try {
			CTipoPago result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CTipoPago findById(int id) {
		log.debug("getting CTipoPago instance with id: " + id);
		try {
			CTipoPago instance = entityManager.find(CTipoPago.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
