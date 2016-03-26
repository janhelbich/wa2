package org.wa2.hw1.model;

public class Car extends AbstractEntity {

	private static final long serialVersionUID = -6603945109543566473L;

	protected String color;

	protected String brand;

	protected String licencePlate;

	public Car() {
		super();
	}

	public Car(Long id) {
		super(id);
	}

	@Override
	public Car shallowClone() {
		return new Car(id);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	@Override
	public String toString() {
		return "Car [color=" + color + ", brand=" + brand + ", licencePlate="
				+ licencePlate + ", id=" + id + "]";
	}

}