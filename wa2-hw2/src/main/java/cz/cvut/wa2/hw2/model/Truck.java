package cz.cvut.wa2.hw2.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Range;

@Entity
public class Truck extends Car {

	private static final long serialVersionUID = -1208299969306079526L;

	public Truck() {
		super();
	}

	public Truck(String brand, String colour, String licencePlate, Float maxKgLoad) {
		super();
		this.brand = brand;
		this.colour = colour;
		this.licencePlate = licencePlate;
		this.maxKgLoad = maxKgLoad;
	}

	public Truck(String brand, String colour, String licencePlate, Float maxKgLoad, Customer rentBy) {
		super();
		this.brand = brand;
		this.colour = colour;
		this.licencePlate = licencePlate;
		this.maxKgLoad = maxKgLoad;
		this.rentBy = rentBy;
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
