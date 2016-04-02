package cz.cvut.wa2.hw3.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.wa2.hw3.model.Truck;

public class TruckStoreService extends GenericStoreService {
	
	protected static final Logger logger = LoggerFactory.getLogger(TruckStoreService.class);

	public List<Truck> listAllTrucksFull() {
		Session session = openSession();
		
		@SuppressWarnings("unchecked")
		List<Truck> result = session.createCriteria(Truck.class)
			.setFetchMode("rentBy", FetchMode.JOIN)
			.setFetchMode("brand", FetchMode.JOIN)
			.list();
		
		session.close();
		
		return result;

	}
	
	public Truck findFull(Long id) {
		Session session = openSession();
				
		Truck truck = (Truck) session.createCriteria(Truck.class)
				.setFetchMode("rentBy", FetchMode.JOIN)
				.setFetchMode("brand", FetchMode.JOIN)
				.setFetchMode("brand.suppliers", FetchMode.JOIN)
				.setFetchMode("brand.suppliers.materials", FetchMode.JOIN)
				.add(Restrictions.eq("id", id))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.uniqueResult();
		
		if (truck == null) {
			session.close();
			return null;
		}
		
//		Hibernate.initialize(car.getBrand().getSuppliers());
//		for (Supplier supplier : car.getBrand().getSuppliers()) {
//			Hibernate.initialize(supplier.getMaterials());
//		}
		
		session.close();
		return truck;
	}
	
}
