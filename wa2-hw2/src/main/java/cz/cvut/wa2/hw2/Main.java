package cz.cvut.wa2.hw2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cz.cvut.wa2.hw2.model.Address;
import cz.cvut.wa2.hw2.model.Car;
import cz.cvut.wa2.hw2.model.Customer;
import cz.cvut.wa2.hw2.model.PhoneNumber;
import cz.cvut.wa2.hw2.model.PhoneType;
import cz.cvut.wa2.hw2.model.Truck;
import cz.cvut.wa2.hw2.service.CustomerStoreService;
import cz.cvut.wa2.hw2.service.GenericStoreService;

public class Main {
	
	public static void main(String[] args) {
		try {
			method();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// automaticky se zbavim otevrene SessionFactory
		Runtime.getRuntime().gc();
		Runtime.getRuntime().runFinalization();
	}

	private static void method() {
		final GenericStoreService service = new GenericStoreService();
		
		
		Customer customer = new Customer("Test1", "Test1");
		
		Set<Address> addresses = new HashSet<>(Arrays.asList(
				new Address("mesto", "ulice1", "123", customer), 
				new Address("vesnice", "ulice2", "321", customer)));
		
		Set<PhoneNumber> phones = new HashSet<>(Arrays.asList(
				new PhoneNumber("123456789", PhoneType.MOBILE, customer),
				new PhoneNumber("987654321", PhoneType.PERSONAL, customer)));
		
		customer.setAddresses(addresses);
		customer.setPhones(phones);
		
		service.save(customer);
		
		printMsg("after first customer save");
		
		// zajimave je, ze pokud bych pouzil puvodni entitu, Session#merge mi zkopiruje
		// i vsechny navazane vztahy, tj. zavola select pres vsechny 'phones' a 'addresses'
		customer = service.find(Customer.class, customer.getId());
		System.out.println(customer);
		customer.setFirstName("Upraveny Test1");
		service.save(customer);

		printMsg("after customer update");
		
		customer = service.find(Customer.class, customer.getId());
		System.out.println(customer);
		
		Car car = new Car("Skoda", "red", "abc123", customer);
		service.save(car);
		
		printMsg("after car save");
		
		car.setBrand("BMW");
		service.save(car);
		
		printMsg("after car update");
		
		Truck truck = new Truck("Tatra", "red", "abc124", 20000F, customer);
		service.save(truck);
		
		printMsg("after truck save");
		
		
		customer = new CustomerStoreService().findFull(customer.getId());
		System.out.println(customer.getRentedCars());
		
		
		printMsg("finished");
	}

	private static void printMsg(String msg) {
		System.out.println("--------------------------------------------------");
		System.out.println("--------------------------------------------------");
		System.out.println(msg);
		System.out.println("--------------------------------------------------");
		System.out.println("--------------------------------------------------");
	}
}
