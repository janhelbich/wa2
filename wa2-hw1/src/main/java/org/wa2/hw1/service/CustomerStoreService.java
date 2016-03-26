package org.wa2.hw1.service;

import org.wa2.hw1.model.Customer;

public class CustomerStoreService extends GenericStoreService {

	public void save(Customer cust) {
		if (cust.getCar() != null) {
			cust.setCar(cust.getCar().shallowClone());
		}
		super.save(cust);
	}

	public Customer find(Long id) {
		Customer cust = super.find(Customer.class, id);
		if (cust.getCar() != null) {
			GenericStoreService carService = new GenericStoreService();
			cust.setCar(carService.find(cust.getCar().getClass(), cust.getCar().getId()));
		}

		return cust;
	}

}
