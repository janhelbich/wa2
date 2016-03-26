package org.wa2.hw1;

import org.wa2.hw1.model.Car;
import org.wa2.hw1.model.Customer;
import org.wa2.hw1.model.Truck;
import org.wa2.hw1.service.CustomerStoreService;
import org.wa2.hw1.service.GenericStoreService;

public class Main {

	public static void main(String[] args) {
		Car car = new Car();
		car.setBrand("Skoda");
		car.setColor("blue");
		car.setLicencePlate("abcd1234");

		GenericStoreService carService = new GenericStoreService();
		carService.save(car);

		Truck truck = new Truck();
		truck.setBrand("Skoda");
		truck.setColor("blue");
		truck.setLicencePlate("abcd1234");
		truck.setMaximumLoadKg(12345);

		carService.save(truck);

		Customer customer = new Customer();
		customer.setAge(20);
		customer.setCar(car);
		customer.setFirstName("Jan");
		customer.setSurname("Helbich");

		CustomerStoreService customerService = new CustomerStoreService();
		customerService.save(customer);

		Customer read1 = customerService.find(customer.getId());
		System.out.println(read1);

		read1.setCar(truck);
		customerService.save(read1);

		Customer read2 = customerService.find(customer.getId());
		System.out.println(read2);
	}
}
