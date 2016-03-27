package cz.cvut.wa2.hw3.service;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cz.cvut.wa2.hw3.model.Customer;

public class CustomerStoreService extends GenericStoreService {

	// vybleje kompletni objekt Customer se vsemi vazbami pres jedinou query
	public Customer findFull(Long id) {
		Session session = openSession();
		Customer result = (Customer) session.createCriteria(Customer.class)
			.add(Restrictions.eq("id", id))
			.setFetchMode("addresses", FetchMode.JOIN)
			.setFetchMode("phones", FetchMode.JOIN)
			.setFetchMode("rentedCars", FetchMode.JOIN)
			.uniqueResult();
		session.close();
		
		return result;
	}
	
	
}
