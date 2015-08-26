package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CRolUsuario.
 * @see ixpan.pgf.dao.CRolUsuario
 * @author Hibernate Tools
 */
@Stateless
public class CRolUsuarioHome {

	private static final Log log = LogFactory.getLog(CRolUsuarioHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CRolUsuario transientInstance) {
		log.debug("persisting CRolUsuario instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CRolUsuario persistentInstance) {
		log.debug("removing CRolUsuario instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CRolUsuario merge(CRolUsuario detachedInstance) {
		log.debug("merging CRolUsuario instance");
		try {
			CRolUsuario result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CRolUsuario findById(Integer id) {
		log.debug("getting CRolUsuario instance with id: " + id);
		try {
			CRolUsuario instance = entityManager.find(CRolUsuario.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
