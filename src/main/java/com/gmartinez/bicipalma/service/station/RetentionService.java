package com.gmartinez.bicipalma.service.station;

import com.gmartinez.bicipalma.domain.station.BikeAnchorage;
import com.gmartinez.bicipalma.domain.vehicle.Bike;
import com.gmartinez.bicipalma.service.printer.WriterService;
import org.springframework.stereotype.Component;

@Component
public class RetentionService {

	private WriterService writerService;

	public RetentionService(WriterService writerService) {
		this.writerService = writerService;
	}

	public BikeAnchorage lock(BikeAnchorage anchorage, Bike vehicle) {

		this.writerService.lockInfo(vehicle, anchorage);

		final BikeAnchorage bikeAnchorageUpdated = anchorage.toBuilder()
			.bike(vehicle)
			.occupied(true)
			.build();
		return bikeAnchorageUpdated;
	}

	public BikeAnchorage unlock(BikeAnchorage anchorage) {

		this.writerService.unlockInfo(anchorage.getVehicle(), anchorage);

		return  anchorage.toBuilder()
			.occupied(false)
			.bike(null)
			.build();
	}

}
