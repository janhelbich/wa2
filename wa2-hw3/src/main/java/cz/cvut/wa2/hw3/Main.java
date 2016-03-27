package cz.cvut.wa2.hw3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.wa2.hw3.service.CarStoreService;
import cz.cvut.wa2.hw3.service.GenericStoreService;

public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	
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
		GenericStoreService service = new GenericStoreService();
		service.initSessionFactory();
		
		printMsg("666");
		
		CarStoreService carStoreService = new CarStoreService();
		System.out.println(carStoreService.findFull(1L).printFull());
		System.out.println(carStoreService.findFull(2L).printFull());
		System.out.println(carStoreService.findFull(3L).printFull());
	}

	private static void printMsg(String msg) {
		System.out.println("--------------------------------------------------");
		System.out.println("--------------------------------------------------");
		System.out.println(msg);
		System.out.println("--------------------------------------------------");
		System.out.println("--------------------------------------------------");
	}
	
	
	public static void log(String msg) {
		logger.info(msg);
	}
}
