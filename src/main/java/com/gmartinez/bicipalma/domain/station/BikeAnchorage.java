package com.gmartinez.bicipalma.domain.station;

import com.gmartinez.bicipalma.domain.vehicle.Bike;
import lombok.Getter;

@Getter
public class BikeAnchorage extends Anchorage<Bike> {


	BikeAnchorage(long id, boolean occupied, Bike bike) {
		super(id, occupied, bike);
	}

	public static BikeAnchorageBuilder builder(long id) {
		return new BikeAnchorageBuilder(id);
	}

	@Override
	public BikeAnchorageBuilder toBuilder() {

		return new BikeAnchorageBuilder(this.getId())
			.occupied(this.isOccupied())
			.bike(this.getVehicle());
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof BikeAnchorage)) {
			return false;
		}
		final BikeAnchorage other =
			(BikeAnchorage) o;
		if (!other.canEqual((Object) this)) {
			return false;
		}
		return this.getId()== other.getId();
	}

	protected boolean canEqual(final Object other) {
		return other instanceof BikeAnchorage;
	}

	public int hashCode() {
		int result = 1;
		return result;
	}

	public static class BikeAnchorageBuilder extends AnchorageBuilder<Bike> {

		private long id;
		private boolean occupied = false;
		private Bike bike = null;

		BikeAnchorageBuilder(long id) {
			this.id = id;
		}

		public BikeAnchorageBuilder id(long id) {
			this.id = id;
			return this;
		}

		public BikeAnchorageBuilder occupied(boolean occupied) {
			this.occupied = occupied;
			return this;
		}

		public BikeAnchorageBuilder bike(Bike bike) {
			this.bike = bike;
			return this;
		}

		public BikeAnchorage build() {
			return new BikeAnchorage(this.id, this.occupied, this.bike);
		}


	}
}
