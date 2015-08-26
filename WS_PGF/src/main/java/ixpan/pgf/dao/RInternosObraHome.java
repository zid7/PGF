package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RInternosObra.
 * @see ixpan.pgf.dao.RInternosObra
 * @author Hibernate Tools
 */
@Stateless
public class RInternosObraHome {

	private static final Log log = LogFactory.getLog(RInternosObraHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RInternosObra transientInstance) {
		log.debug("persisting RInternosObra instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RInternosObra persistentInstance) {
		log.debug("removing RInternosObra instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RInternosObra merge(RInternosObra detachedInstance) {
		log.debug("merging RInternosObra instance");
		try {
			RInternosObra result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RInternosObra findById(int id) {
		log.debug("getting RInternosObra instance with id: " + id);
		try {
			RInternosObra instance = entityManager.find(RInternosObra.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
