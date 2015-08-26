package ixpan.pgf.dao;
// Generated Aug 5, 2015 8:14:13 AM by Hibernate Tools 3.4.0.CR1
import ixpan.pgf.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Banco.
 * @see ixpan.pgf.dao.Banco
 * @author Hibernate Tools
 */
@Stateless
public class BancoHome {

	private static final Log log = LogFactory.getLog(BancoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Banco transientInstance) {
		log.debug("persisting Banco instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Banco persistentInstance) {
		log.debug("removing Banco instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Banco merge(Banco detachedInstance) {
		log.debug("merging Banco instance");
		try {
			Banco result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Banco findById(int id) {
		log.debug("getting Banco instance with id: " + id);
		try {
			Banco instance = entityManager.find(Banco.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
