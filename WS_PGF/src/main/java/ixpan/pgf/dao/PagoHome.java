package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Pago.
 * @see ixpan.pgf.dao.Pago
 * @author Hibernate Tools
 */
@Stateless
public class PagoHome {

	private static final Log log = LogFactory.getLog(PagoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Pago transientInstance) {
		log.debug("persisting Pago instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Pago persistentInstance) {
		log.debug("removing Pago instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Pago merge(Pago detachedInstance) {
		log.debug("merging Pago instance");
		try {
			Pago result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Pago findById(PagoId id) {
		log.debug("getting Pago instance with id: " + id);
		try {
			Pago instance = entityManager.find(Pago.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
