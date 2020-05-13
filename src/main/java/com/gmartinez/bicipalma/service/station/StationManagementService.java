package com.gmartinez.bicipalma.service.station;

import com.gmartinez.bicipalma.domain.station.Station;
import com.gmartinez.bicipalma.domain.usercard.UserCard;
import com.gmartinez.bicipalma.domain.vehicle.Bike;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StationManagementService {

	private static final String THERE_ARE_NOT_BIKES_AVAILABLE = "There are not bikes available.";
	private static final String USER_CARD_ARE_DISABLED = "User card are disabled";
	private AnchorManagementService anchorManagementService;

	public StationManagementService(AnchorManagementService anchorManagementService) {
		this.anchorManagementService = anchorManagementService;
	}


	public int bikeAnchorageFree(Station station) {

		return anchorManagementService.anchorageFree(station.getAnchorages().getBikeAnchorages());
	}


	public Station anchorBikes(Station station, int[] bicyclesCodes) {

		final int freeAnchors = this.bikeAnchorageFree(station);

		if (isEnoughBikeAnchors(freeAnchors, bicyclesCodes)) {

			return station.toBuilder()
				.anchorages(anchorManagementService.anchorBikes(station.getAnchorages(), buildBikes(bicyclesCodes)))
				.toBuild();

		} else {

			log.warn("There are more bikes , {} , to save than free bike anchors {}",
					 bicyclesCodes.length,
					 freeAnchors);
			return station;
		}
	}

	private List<Bike> buildBikes(int[] bicyclesCodes) {

		return Arrays.stream(bicyclesCodes)
			.mapToObj(code -> Bike.builder()
				.id(Integer.toUnsignedLong(code))
				.build())
			.collect(Collectors.toList());
	}

	public Station removeBike(UserCard userCard, Station station) {

		if (isRemoveAvailable(station, userCard)) {

			return station.toBuilder()
				.anchorages(this.anchorManagementService.removeBike(station.getAnchorages(), userCard))
				.toBuild();

		} else {

			log.warn(userCard.isActived()
						 ? THERE_ARE_NOT_BIKES_AVAILABLE
						 : USER_CARD_ARE_DISABLED);

			return station;
		}
	}

	private boolean isRemoveAvailable(Station station, UserCard userCard) {

		return userCard.isActived()
			&& this.anchorManagementService.isThereBikeAvailable(station.getAnchorages().getBikeAnchorages());
	}

	private boolean isEnoughBikeAnchors(int stationFreeAnchors, int[] bicyclesCodes) {
		return stationFreeAnchors >= bicyclesCodes.length;
	}
}
