package org.wa2.hw1.model;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 7349722927011305799L;

	protected Long id;

	public AbstractEntity() {
	}
	
	public abstract AbstractEntity shallowClone();

	public AbstractEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
