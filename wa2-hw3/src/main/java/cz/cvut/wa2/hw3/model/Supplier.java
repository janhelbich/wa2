package cz.cvut.wa2.hw3.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "suppliers")
public class Supplier extends AbstractEntity {

	private static final long serialVersionUID = 5162830735590186775L;

	@NotBlank
	@Column(name = "supplier_name")
	private String supplierName;

	@ManyToMany
	@JoinTable(name = "suppliers_materials", 
		joinColumns = @JoinColumn(name = "supplier_id"), 
		inverseJoinColumns = @JoinColumn(name = "material_id"))
	private Set<Material> materials;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Set<Material> getMaterials() {
		if (this.materials == null) {
			this.materials = new HashSet<>();
		}
		return materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}

}
