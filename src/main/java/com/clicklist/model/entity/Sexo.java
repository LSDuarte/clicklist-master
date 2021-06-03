package com.clicklist.model.entity;

import com.clicklist.model.EnumModel;

public enum Sexo implements EnumModel {

	MASCULINO("Masculino"), 
	FEMININO("Feminino");


	private String label;

	public void setLabel(String label) {
		this.label = label;
	}

	private Sexo(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public boolean isMasculino() {
		return this == MASCULINO;
	}

	public boolean isFeminino() {
		return this == FEMININO;
	}
	
}