package cz.cvut.wa2.hw3.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "brands")
public class Brand extends AbstractEntity {
	
	private static final long serialVersionUID = 7609616400163765935L;
	
	@NotBlank
	@Column(name = "brand_name")
	private String brandName;
	
	@ManyToMany
	@JoinTable(name = "brands_suppliers",
			joinColumns = @JoinColumn(name = "brand_id"),
			inverseJoinColumns = @JoinColumn(name = "supplier_id"))
	private List<Supplier> suppliers;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

}
