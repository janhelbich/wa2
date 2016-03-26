package org.wa2.hw1.model;

public class Truck extends Car {

	private static final long serialVersionUID = 8050778668996407607L;

	private float maximumLoadKg;

	public Truck() {
		super();
	}

	public Truck(Long id) {
		super(id);
	}

	@Override
	public Car shallowClone() {
		return new Truck(id);
	}

	public float getMaximumLoadKg() {
		return maximumLoadKg;
	}

	public void setMaximumLoadKg(float maximumLoadKg) {
		this.maximumLoadKg = maximumLoadKg;
	}

	@Override
	public String toString() {
		return "Truck [maximumLoadKg=" + maximumLoadKg + ", color=" + color
				+ ", brand=" + brand + ", licencePlate=" + licencePlate
				+ ", id=" + id + "]";
	}

}