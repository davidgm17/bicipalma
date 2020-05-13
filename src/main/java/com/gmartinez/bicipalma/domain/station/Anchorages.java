package com.gmartinez.bicipalma.domain.station;

import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Anchorages {

	private final BikeAnchorage[] bikeAnchorages;


	private Anchorages(int numAnclajes) {
		this.bikeAnchorages = new BikeAnchorage[numAnclajes];
		crearAnclajes();
	}

	public static AnchoragesBuilder builder() {
		return new AnchoragesBuilder();
	}

	private void crearAnclajes() {

		IntStream.range(0, bikeAnchorages.length)
			.forEach(position -> bikeAnchorages[position] = BikeAnchorage.builder((long) position).build());
	}

	public AnchoragesBuilder toBuilder() {
		return new AnchoragesBuilder().bikeAnchorages(this.bikeAnchorages);
	}


	public static class AnchoragesBuilder {

		private int numAnclajes;
		private BikeAnchorage[] bikeAnchorages;

		AnchoragesBuilder() {
		}

		public AnchoragesBuilder numAnclajes(int numAnclajes) {
			this.numAnclajes = numAnclajes;
			return this;
		}

		public Anchorages build() {
			return new Anchorages(numAnclajes);
		}

		public Anchorages toBuild(){
			return new Anchorages(bikeAnchorages);
		}

		public AnchoragesBuilder bikeAnchorages(BikeAnchorage[] bikeAnchorages) {
			this.bikeAnchorages = bikeAnchorages;
			return this;
		}
	}
}