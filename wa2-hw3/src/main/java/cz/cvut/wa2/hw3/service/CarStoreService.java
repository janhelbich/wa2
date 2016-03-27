package cz.cvut.wa2.hw3.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import cz.cvut.wa2.hw3.model.Car;

public class CarStoreService extends GenericStoreService {

	public List<Car> findAllCarsFull() {
		Session session = openSession();
		
		@SuppressWarnings("unchecked")
		List<Car> result = session.createCriteria(Car.class)
			.setFetchMode("rentBy", FetchMode.JOIN)
			.setFetchMode("brand", FetchMode.JOIN)
			.list();
		
		session.close();
		
		return result;

	}
	
	public Car findFull(Long id) {
		Session session = openSession();
		
		Car result = (Car) findFullCriteria(session, Car.class, id).uniqueResult();
		
		session.close();
		return result;
	}
	
	protected Criteria findFullCriteria(Session session, Class<?> clazz, Long id) {
		return session.createCriteria(clazz)
			.createAlias("brand", "b")
			.createAlias("b.suppliers", "suppls", JoinType.LEFT_OUTER_JOIN)
			.setFetchMode("rentBy", FetchMode.JOIN)
			.setFetchMode("b", FetchMode.JOIN)
			.setFetchMode("suppls", FetchMode.JOIN)
			.add(Restrictions.eq("id", id));
	}
}
