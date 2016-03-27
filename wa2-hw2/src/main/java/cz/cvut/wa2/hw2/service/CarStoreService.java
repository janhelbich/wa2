package cz.cvut.wa2.hw2.service;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;

import cz.cvut.wa2.hw2.model.Car;

public class CarStoreService extends GenericStoreService {

	public List<Car> findAllCarsFull() {
		Session session = openSession();
		
		@SuppressWarnings("unchecked")
		List<Car> result = session.createCriteria(Car.class)
			.setFetchMode("rentBy", FetchMode.JOIN)
			.list();
		
		session.close();
		
		return result;

	}
}
