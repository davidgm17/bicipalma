package com.gmartinez.bicipalma.domain.vehicle;

import lombok.Getter;

@Getter
public class Bike extends Vehicle {

	Bike(long id) {
		super(id);
	}

	public static BikeBuilder builder() {
		return new BikeBuilder();
	}

	public static class BikeBuilder {

		private long id;

		BikeBuilder() {
		}

		public BikeBuilder id(long id) {
			this.id = id;
			return this;
		}

		public Bike build() {
			return new Bike(id);
		}

	}
}
