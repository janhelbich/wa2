package cz.cvut.wa2.hw2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumber extends AbstractEntity {

	private static final long serialVersionUID = -4516850857228234309L;

	public PhoneNumber() {
		super();
	}

	public PhoneNumber(String phoneNumber, PhoneType phoneType) {
		super();
		this.phoneNumber = phoneNumber;
		this.phoneType = phoneType;
	}

	public PhoneNumber(String phoneNumber, PhoneType phoneType, Customer owner) {
		super();
		this.phoneNumber = phoneNumber;
		this.phoneType = phoneType;
		this.owner = owner;
	}

	@Column(name = "phone_number")
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "phone_type")
	private PhoneType phoneType;

	@ManyToOne
	@JoinColumn(name = "owner")
	private Customer owner;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((phoneType == null) ? 0 : phoneType.hashCode());
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
		PhoneNumber other = (PhoneNumber) obj;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (phoneType != other.phoneType)
			return false;
		return true;
	}

}