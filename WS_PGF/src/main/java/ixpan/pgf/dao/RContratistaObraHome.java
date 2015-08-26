package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RContratistaObra.
 * @see ixpan.pgf.dao.RContratistaObra
 * @author Hibernate Tools
 */
@Stateless
public class RContratistaObraHome {

	private static final Log log = LogFactory.getLog(RContratistaObraHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RContratistaObra transientInstance) {
		log.debug("persisting RContratistaObra instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RContratistaObra persistentInstance) {
		log.debug("removing RContratistaObra instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RContratistaObra merge(RContratistaObra detachedInstance) {
		log.debug("merging RContratistaObra instance");
		try {
			RContratistaObra result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RContratistaObra findById(RContratistaObraId id) {
		log.debug("getting RContratistaObra instance with id: " + id);
		try {
			RContratistaObra instance = entityManager.find(RContratistaObra.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
