package com.clicklist.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

//É uma classe abstrata que deverá ser extendida por qualquer entidade.

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Long getId();

	@Override
	public boolean equals(Object obj) {
		boolean result = obj != null;
		result = result && getClass().isInstance(obj);
		result = result && hashCode() > 0;
		result = result && obj.hashCode() > 0;
		result = result && hashCode() == obj.hashCode();
		return result;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : super.hashCode();
	}

}
