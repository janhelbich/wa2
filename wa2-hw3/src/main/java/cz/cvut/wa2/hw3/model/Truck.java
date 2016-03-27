package cz.cvut.wa2.hw3.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Range;

@Entity
public class Truck extends Car {

	private static final long serialVersionUID = -1208299969306079526L;

	public Truck() {
		super();
	}

	public Truck(String colour, String licencePlate, Float maxKgLoad) {
		super(colour, licencePlate);
		this.maxKgLoad = maxKgLoad;
	}

	public Truck(Brand brand, String colour, String licencePlate, Float maxKgLoad, Customer rentBy) {
		super(brand, colour, licencePlate, rentBy);
		this.maxKgLoad = maxKgLoad;
	}

	@Range(min = 0, max = 50000)
	@Column(name = "max_kg_load")
	protected Float maxKgLoad;

	public Float getMaxKgLoad() {
		return maxKgLoad;
	}

	public void setMaxKgLoad(Float maxKgLoad) {
		this.maxKgLoad = maxKgLoad;
	}

}
