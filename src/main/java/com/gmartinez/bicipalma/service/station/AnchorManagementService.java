package com.gmartinez.bicipalma.service.station;

import com.gmartinez.bicipalma.domain.station.Anchorage;
import com.gmartinez.bicipalma.domain.station.Anchorages;
import com.gmartinez.bicipalma.domain.station.BikeAnchorage;
import com.gmartinez.bicipalma.domain.usercard.UserCard;
import com.gmartinez.bicipalma.domain.vehicle.Bike;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class AnchorManagementService {

	private RetentionService retentionService;

	public AnchorManagementService(RetentionService retentionService) {
		this.retentionService = retentionService;
	}


	public int anchorageFree(BikeAnchorage[] bikeAnchorages) {

		return getAnchoragesFree(bikeAnchorages)
			.size();
	}

	public boolean isThereBikeAvailable(BikeAnchorage[] bikeAnchorages) {

		return Stream.of(bikeAnchorages)
			.anyMatch(Anchorage::isOccupied);
	}

	private List<BikeAnchorage> getAnchoragesFree(BikeAnchorage[] anchorageList) {

		return Stream.of(anchorageList)
			.filter(bikeAnchorage -> !bikeAnchorage.isOccupied())
			.collect(Collectors.toList());
	}

	public Anchorages anchorBikes(Anchorages anchorages, List<Bike> bikes) {

		final Iterator<Bike> bikeIterator = bikes.iterator();

		List<BikeAnchorage> bikeAnchoragesList = Arrays.asList(anchorages.getBikeAnchorages());

		while (bikeIterator.hasNext()) {

			bikeAnchoragesList = this.anchor(bikeAnchoragesList, bikeIterator.next());
		}

		return anchorages.toBuilder()
			.bikeAnchorages(bikeAnchoragesList.toArray(new BikeAnchorage[0]))
			.toBuild();
	}

	//	private BikeAnchorage[] bikeAnchoragesArrayConverter(Iterator<BikeAnchorage> bikeAnchorageIterator) {
	//		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(bikeAnchorageIterator, Spliterator.ORDERED),
	//									false)
	//			.toArray(BikeAnchorage[]::new);
	//	}


	private List<BikeAnchorage> anchor(List<BikeAnchorage> bikeAnchoragesList, Bike bike) {

		final Optional<BikeAnchorage> first = bikeAnchoragesList.stream()
			.filter(anchorage -> !anchorage.isOccupied())
			.findFirst();

		final BikeAnchorage anchorageUpdated = first
			.map(bikeAnchorage -> retentionService.lock(bikeAnchorage, bike))
			.orElse(null);

		final int indexOf = indexOf(bikeAnchoragesList, anchorageUpdated);

		bikeAnchoragesList.set(indexOf, anchorageUpdated);

		return bikeAnchoragesList;
	}

	private static int indexOf(List<BikeAnchorage> bikeAnchoragesList, BikeAnchorage anchorageUpdated) {
		return bikeAnchoragesList.indexOf(anchorageUpdated);
	}

	public Anchorages removeBike(Anchorages anchorages, UserCard userCard) {

		final List<BikeAnchorage> bikeAnchoragesList = Arrays.asList(anchorages.getBikeAnchorages());

		final BikeAnchorage anchorageUpdated = bikeAnchoragesList.stream()
			.filter(Anchorage::isOccupied)
			.findFirst()
			.map(bikeAnchorage -> retentionService.unlock(bikeAnchorage))
			.orElse(null);

		bikeAnchoragesList.set(indexOf(bikeAnchoragesList, anchorageUpdated), anchorageUpdated);

		return anchorages.toBuilder()
			.bikeAnchorages(bikeAnchoragesList.toArray(new BikeAnchorage[0]))
			.toBuild();
	}
}
