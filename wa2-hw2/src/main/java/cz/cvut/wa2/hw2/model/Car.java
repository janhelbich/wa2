package cz.cvut.wa2.hw2.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "car_type")
@DiscriminatorValue(value = "car")
public class Car extends AbstractEntity {

	private static final long serialVersionUID = -3822216574583075955L;
	
	public Car() {
		super();
	}

	public Car(String brand, String colour, String licencePlate) {
		super();
		this.brand = brand;
		this.colour = colour;
		this.licencePlate = licencePlate;
	}

	public Car(String brand, String colour, String licencePlate, Customer rentBy) {
		super();
		this.brand = brand;
		this.colour = colour;
		this.licencePlate = licencePlate;
		this.rentBy = rentBy;
	}

	@Column(name = "brand")
	protected String brand;

	@Column(name = "colour")
	protected String colour;

	@Column(name = "licence_plate")
	protected String licencePlate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rent_by")
	protected Customer rentBy;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public Customer getRentBy() {
		return rentBy;
	}

	public void setRentBy(Customer rentBy) {
		this.rentBy = rentBy;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (brand != null && !brand.trim().isEmpty())
			result += "brand: " + brand;
		if (colour != null && !colour.trim().isEmpty())
			result += ", colour: " + colour;
		if (licencePlate != null && !licencePlate.trim().isEmpty())
			result += ", licencePlate: " + licencePlate;
		return result;
	}
}