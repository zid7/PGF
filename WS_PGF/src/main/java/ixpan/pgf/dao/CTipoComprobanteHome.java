package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CTipoComprobante.
 * @see ixpan.pgf.dao.CTipoComprobante
 * @author Hibernate Tools
 */
@Stateless
public class CTipoComprobanteHome {

	private static final Log log = LogFactory.getLog(CTipoComprobanteHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CTipoComprobante transientInstance) {
		log.debug("persisting CTipoComprobante instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CTipoComprobante persistentInstance) {
		log.debug("removing CTipoComprobante instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CTipoComprobante merge(CTipoComprobante detachedInstance) {
		log.debug("merging CTipoComprobante instance");
		try {
			CTipoComprobante result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CTipoComprobante findById(int id) {
		log.debug("getting CTipoComprobante instance with id: " + id);
		try {
			CTipoComprobante instance = entityManager.find(CTipoComprobante.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
