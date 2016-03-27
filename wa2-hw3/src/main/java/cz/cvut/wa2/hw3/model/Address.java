package cz.cvut.wa2.hw3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address extends AbstractEntity {

	private static final long serialVersionUID = -2027895788257636474L;

	public Address() {
		super();
	}

	public Address(String city, String street, String houseNumber) {
		super();
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public Address(String city, String street, String houseNumber, Customer resident) {
		super();
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.resident = resident;
	}

	@Column
	private String city;

	@Column
	private String street;

	@Column(name = "house_number")
	private String houseNumber;

	@ManyToOne
	@JoinColumn(name = "resident")
	private Customer resident;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Customer getResident() {
		return resident;
	}

	public void setResident(Customer resident) {
		this.resident = resident;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (city != null && !city.trim().isEmpty())
			result += "city: " + city;
		if (street != null && !street.trim().isEmpty())
			result += ", street: " + street;
		if (houseNumber != null && !houseNumber.trim().isEmpty())
			result += ", houseNumber: " + houseNumber;
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((resident == null) ? 0 : resident.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (resident == null) {
			if (other.resident != null)
				return false;
		} else if (!resident.equals(other.resident))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

}