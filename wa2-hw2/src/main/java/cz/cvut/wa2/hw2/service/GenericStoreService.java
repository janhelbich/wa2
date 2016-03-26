package cz.cvut.wa2.hw2.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.wa2.hw2.model.AbstractEntity;

public class GenericStoreService {
	
	private static final Logger logger = LoggerFactory.getLogger(GenericStoreService.class);

	protected SessionFactory sessionFactory;

	public void save(AbstractEntity e) {
		Session session = openSession();
		Transaction tx = session.beginTransaction();
		try {
			if (e.getId() == null) {
				session.persist(e);
			} else {
				session.merge(e);
			}
			tx.commit();
			session.close();
			
		} catch (Exception ex) {
			tx.rollback();
			session.close();
			throw ex;
		}
	}

	public void delete(AbstractEntity e) {
		Session session = openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(e);
			tx.commit();
			session.close();
		} catch (Exception ex) {
			tx.rollback();
			session.close();
			throw ex;
		}
	}

	public <E> E find(Class<E> clazz, Long id) {
		Session session = openSession();
		E elem = session.get(clazz, id);
		session.close();

		return elem;
	}

	protected SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			initSessionFactory();
		}

		return sessionFactory;
	}
	
	protected Session openSession() {
		return getSessionFactory().openSession();
	}

	private void initSessionFactory() {

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		try {
			this.sessionFactory = new MetadataSources(registry)
					.buildMetadata()
					.buildSessionFactory();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			StandardServiceRegistryBuilder.destroy(registry);
			throw e;
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
		super.finalize();
	}
	
}
