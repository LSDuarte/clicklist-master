package com.clicklist.util;

import javax.inject.Named;

import com.clicklist.model.entity.Sexo;

@Named
public class FabricaEnum {

	public Sexo[] getlistarSexo() {
		return Sexo.values();
	}
}