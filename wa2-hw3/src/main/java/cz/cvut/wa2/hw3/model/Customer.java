package cz.cvut.wa2.hw3.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity {

	private static final long serialVersionUID = -8091060273552873682L;

	public Customer() {
		super();
	}

	public Customer(String firstName, String surname) {
		super();
		this.firstName = firstName;
		this.surname = surname;
	}

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "surname")
	private String surname;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true,
		cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.DETACH })
	private Set<PhoneNumber> phones;

	@OneToMany(mappedBy = "resident", fetch = FetchType.LAZY, orphanRemoval = true,
			cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.DETACH })
	private Set<Address> addresses;
	
	@OneToMany(mappedBy = "rentBy", fetch = FetchType.LAZY)
	private Set<Car> rentedCars;

	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
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

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public Set<Car> getRentedCars() {
		return rentedCars;
	}

	public void setRentedCars(Set<Car> rentedCars) {
		this.rentedCars = rentedCars;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", surname=" + surname + ", id=" + id + "]";
	}

}