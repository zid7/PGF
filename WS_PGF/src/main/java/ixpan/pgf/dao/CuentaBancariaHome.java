package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CuentaBancaria.
 * @see ixpan.pgf.dao.CuentaBancaria
 * @author Hibernate Tools
 */
@Stateless
public class CuentaBancariaHome {

	private static final Log log = LogFactory.getLog(CuentaBancariaHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CuentaBancaria transientInstance) {
		log.debug("persisting CuentaBancaria instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CuentaBancaria persistentInstance) {
		log.debug("removing CuentaBancaria instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CuentaBancaria merge(CuentaBancaria detachedInstance) {
		log.debug("merging CuentaBancaria instance");
		try {
			CuentaBancaria result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CuentaBancaria findById(CuentaBancariaId id) {
		log.debug("getting CuentaBancaria instance with id: " + id);
		try {
			CuentaBancaria instance = entityManager.find(CuentaBancaria.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
