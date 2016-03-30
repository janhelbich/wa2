package cz.cvut.wa2.hw3.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.wa2.hw3.model.Car;
import cz.cvut.wa2.hw3.model.Supplier;

public class CarStoreService extends GenericStoreService {
	
	protected static final Logger logger = LoggerFactory.getLogger(CarStoreService.class);

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
		
		Car car = (Car) session.createCriteria(Car.class)
				.createAlias("brand", "b")
				.setFetchMode("rentBy", FetchMode.JOIN)
				.setFetchMode("b", FetchMode.JOIN)
				.add(Restrictions.eq("id", id))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.uniqueResult();
		
		if (car == null) {
			session.close();
			return null;
		}
		
		Hibernate.initialize(car.getBrand().getSuppliers());
		for (Supplier supplier : car.getBrand().getSuppliers()) {
			Hibernate.initialize(supplier.getMaterials());
		}
		session.close();
		return car;
	}
	
}
