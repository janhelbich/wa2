package org.wa2.hw1.model;

public class Customer extends AbstractEntity {

	private static final long serialVersionUID = 4328553744259312889L;

	private String firstName;

	private String surname;

	private int age;

	private Car car;
	
	@Override
	public Customer shallowClone() {
		Customer clone = new Customer();
		clone.setId(id);
		return clone;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", surname=" + surname
				+ ", age=" + age + ", car=" + car + "]";
	}

}