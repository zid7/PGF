package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CRolObra.
 * @see ixpan.pgf.dao.CRolObra
 * @author Hibernate Tools
 */
@Stateless
public class CRolObraHome {

	private static final Log log = LogFactory.getLog(CRolObraHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CRolObra transientInstance) {
		log.debug("persisting CRolObra instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CRolObra persistentInstance) {
		log.debug("removing CRolObra instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CRolObra merge(CRolObra detachedInstance) {
		log.debug("merging CRolObra instance");
		try {
			CRolObra result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CRolObra findById(int id) {
		log.debug("getting CRolObra instance with id: " + id);
		try {
			CRolObra instance = entityManager.find(CRolObra.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
