package com.gmartinez.bicipalma.service.printer;

import com.gmartinez.bicipalma.domain.station.Anchorage;
import com.gmartinez.bicipalma.domain.station.BikeAnchorage;
import com.gmartinez.bicipalma.domain.station.Station;
import com.gmartinez.bicipalma.domain.usercard.UserCard;
import com.gmartinez.bicipalma.domain.vehicle.Vehicle;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WriterService {

	public void statusAnchorage(BikeAnchorage bikeAnchorage) {

		log.info("occupied: {}", Boolean.toString(bikeAnchorage.isOccupied()));
	}

	public void status(Station station) {
		log.info("STATION --> id: {}, "
					 + "address: {}, "
					 + "numeroAnclajes: {}.",
				 station.getId(),
				 station.getAddress(),
				 station.getAnchorages().getBikeAnchorages().length);
	}

	public void statusAnchorages(Station station) {

		Stream.of(station.getAnchorages().getBikeAnchorages())
			.forEach(this::printStatus);
	}

	private void printStatus(BikeAnchorage anchorage) {

		log.info("BikeAnchorage {}  {}", anchorage.getId(), anchorage.isOccupied()
			? "Bike : " + anchorage.getVehicle().getId()
			: "free");
	}

	public void statusFreeAnchorages(int totalFreeAnchorages) {

		log.info("Free anchorages : {}", totalFreeAnchorages);
	}

	public void lockInfo(Vehicle vehicle, Anchorage anchorage) {
		log.info("Bike  {} anchored in the {} position. ",
				 vehicle.getId(),
				 anchorage.getId());
	}

	public void unlockInfo(Vehicle vehicle, Anchorage anchorage) {
		log.info("Bike  {} unhooked in the {} position. ",
				 vehicle.getId(),
				 anchorage.getId());
	}

	public void status(UserCard usercard) {
		log.info("UserCard number : {}  .Status : {}",
				 usercard.getId()
			, usercard.isActived()
					 ? "Activated"
					 : "Disabled");
	}

}
