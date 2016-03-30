package cz.cvut.wa2.hw3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "materials")
public class Material extends AbstractEntity {
		
	private static final long serialVersionUID = 7991029300091156688L;
	
	@Column(name = "material")
	private String material;

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Material [material=" + material + "]";
	}
	
}
