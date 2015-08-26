package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ItemAutorizados.
 * @see ixpan.pgf.dao.ItemAutorizados
 * @author Hibernate Tools
 */
@Stateless
public class ItemAutorizadosHome {

	private static final Log log = LogFactory.getLog(ItemAutorizadosHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ItemAutorizados transientInstance) {
		log.debug("persisting ItemAutorizados instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ItemAutorizados persistentInstance) {
		log.debug("removing ItemAutorizados instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ItemAutorizados merge(ItemAutorizados detachedInstance) {
		log.debug("merging ItemAutorizados instance");
		try {
			ItemAutorizados result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemAutorizados findById(ItemAutorizadosId id) {
		log.debug("getting ItemAutorizados instance with id: " + id);
		try {
			ItemAutorizados instance = entityManager.find(ItemAutorizados.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
