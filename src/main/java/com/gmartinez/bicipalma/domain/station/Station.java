package com.gmartinez.bicipalma.domain.station;

import com.gmartinez.bicipalma.domain.common.UniqueModel;
import lombok.Getter;

@Getter
public class Station implements UniqueModel {

	private final long id;
	private final String address;
	private final Anchorages anchorages;

	private Station(long id, String address, int totalAnchores) {
		this.id = id;
		this.address = address;
		this.anchorages = Anchorages.builder()
			.numAnclajes(totalAnchores)
			.build();
	}

	private Station(long id, String address, Anchorages anchorages) {
		this.id = id;
		this.address = address;
		this.anchorages = anchorages;
	}

	public static StationBuilder builder(long id, String address, int numAnclajes) {

		return new StationBuilder()
			.id(id)
			.address(address)
			.numAnclajes(numAnclajes);
	}


	public StationBuilder toBuilder() {

		return new StationBuilder()
			.id(this.id)
			.address(this.address)
			.anchorages(this.anchorages);
	}

	public static class StationBuilder {

		private long id;
		private String direccion;
		private int numAnclajes;
		private Anchorages anchorages;

		StationBuilder() {
		}

		public StationBuilder id(long id) {
			this.id = id;
			return this;
		}

		public StationBuilder address(String direccion) {
			this.direccion = direccion;
			return this;
		}

		public StationBuilder numAnclajes(int numAnclajes) {
			this.numAnclajes = numAnclajes;
			return this;
		}

		public StationBuilder anchorages(Anchorages anchorages) {
			this.anchorages = anchorages;
			return this;
		}

		public Station build() {
			return new Station(id, direccion, numAnclajes);
		}

		/**
		 * This method is to re-build this station
		 */
		public Station toBuild() {
			return new Station(id, direccion, anchorages);
		}

	}
}
