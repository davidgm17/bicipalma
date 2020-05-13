package com.gmartinez.bicipalma.domain.vehicle;

import com.gmartinez.bicipalma.domain.common.UniqueModel;

public class Vehicle implements UniqueModel {

	private final long id;

	Vehicle(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

}
