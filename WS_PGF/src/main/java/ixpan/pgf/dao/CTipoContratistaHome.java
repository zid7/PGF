package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CTipoContratista.
 * @see ixpan.pgf.dao.CTipoContratista
 * @author Hibernate Tools
 */
@Stateless
public class CTipoContratistaHome {

	private static final Log log = LogFactory.getLog(CTipoContratistaHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CTipoContratista transientInstance) {
		log.debug("persisting CTipoContratista instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CTipoContratista persistentInstance) {
		log.debug("removing CTipoContratista instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CTipoContratista merge(CTipoContratista detachedInstance) {
		log.debug("merging CTipoContratista instance");
		try {
			CTipoContratista result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CTipoContratista findById(Integer id) {
		log.debug("getting CTipoContratista instance with id: " + id);
		try {
			CTipoContratista instance = entityManager.find(CTipoContratista.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
