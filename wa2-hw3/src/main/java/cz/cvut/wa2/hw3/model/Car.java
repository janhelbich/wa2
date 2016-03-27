package cz.cvut.wa2.hw3.model;

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

/**
 * @author j
 *
 */
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

	public Car(String colour, String licencePlate) {
		super();
		this.colour = colour;
		this.licencePlate = licencePlate;
	}

	public Car(Brand brand, String colour, String licencePlate, Customer rentBy) {
		super();
		this.brand = brand;
		this.colour = colour;
		this.licencePlate = licencePlate;
		this.rentBy = rentBy;
	}

	@Column(name = "colour")
	protected String colour;

	@Column(name = "licence_plate")
	protected String licencePlate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rent_by")
	protected Customer rentBy;

	// jake je auto znacky chceme vedet vzdy hned
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand")
	protected Brand brand;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
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
		return "Car [colour=" + colour + ", licencePlate=" + licencePlate + ", id=" + id + "]";
	}

	public String printFull() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Car [colour=");
		sb.append(colour);
		sb.append(", licencePlate=");
		sb.append(licencePlate);
		sb.append(", id=");
		sb.append(id);
		sb.append(", brand=(");
		sb.append(brand.getId());
		sb.append(", ");
		sb.append(brand.getBrandName());
		sb.append(", suppliers={");
		brand.getSuppliers().forEach(supplier -> {
			sb.append("(name=");
			sb.append(supplier.getSupplierName());
			sb.append("), ");
		});

		return sb.append("})]").toString();
	}

}