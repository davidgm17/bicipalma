package com.gmartinez.bicipalma.domain.station;

import com.gmartinez.bicipalma.domain.common.UniqueModel;
import lombok.Getter;

@Getter
public class Anchorage<Vehicle> implements UniqueModel, UpdateAnchorage<Vehicle> {

	private long id;
	private boolean occupied = false;
	private Vehicle vehicle;

	Anchorage(long id, boolean occupied, Vehicle vehicle) {
		this.id = id;
		this.occupied = occupied;
		this.vehicle = vehicle;
	}

	public static <Vehicle> AnchorageBuilder<Vehicle> builder(int id) {
		return new AnchorageBuilder<Vehicle>()
			.id((long) id);
	}

	@Override
	public AnchorageBuilder<Vehicle> toBuilder() {

		return new AnchorageBuilder<Vehicle>()
			.id(this.id)
			.occupied(this.occupied)
			.objectAnchored(this.vehicle);
	}

	public static class AnchorageBuilder<Vehicle> {

		private long id;
		private boolean occupied;
		private Vehicle vehicle;

		AnchorageBuilder() {
		}

		public AnchorageBuilder<Vehicle> id(long id) {
			this.id = id;
			return this;
		}

		public AnchorageBuilder<Vehicle> occupied(boolean occupied) {
			this.occupied = occupied;
			return this;
		}

		public AnchorageBuilder<Vehicle> objectAnchored(Vehicle vehicle) {
			this.vehicle = vehicle;
			return this;
		}

		public Anchorage<Vehicle> build() {
			return new Anchorage<Vehicle>(id, occupied, vehicle);
		}

	}
}
