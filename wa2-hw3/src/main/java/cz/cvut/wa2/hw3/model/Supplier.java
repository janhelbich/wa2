package cz.cvut.wa2.hw3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "suppliers")
public class Supplier extends AbstractEntity {

	private static final long serialVersionUID = 5162830735590186775L;

	@NotBlank
	@Column(name = "supplier_name")
	private String supplierName;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
