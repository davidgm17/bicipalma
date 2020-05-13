package com.gmartinez.bicipalma;

import com.gmartinez.bicipalma.domain.station.Station;
import com.gmartinez.bicipalma.domain.usercard.UserCard;
import com.gmartinez.bicipalma.service.printer.WriterService;
import com.gmartinez.bicipalma.service.station.StationManagementService;
import com.gmartinez.bicipalma.service.usercard.UserCardManagement;
import org.springframework.stereotype.Service;

@Service
public class ExecutionOrder {

	private static final long CARD_ID = 456789L;

	private WriterService writerService;
	private StationManagementService stationManagementService;
	private UserCardManagement userCardManagement;

	public ExecutionOrder(WriterService writerService,
						  StationManagementService stationManagementService,
						  UserCardManagement userCardManagement) {
		this.writerService = writerService;
		this.stationManagementService = stationManagementService;
		this.userCardManagement = userCardManagement;
	}

	public void play() {

		Station station = Station.builder(1, "Manacor", 6)
			.build();

		/**
		 * caso TEST visualizar estado de la station:
		 * muestra id, address, anclaje
		 */

		System.out.println("\n **** caso TEST visualizar estado de la station **** \n");

		this.writerService.status(station);


		/**
		 * caso TEST visualizar anclajes libres
		 */

		System.out.println("\n **** caso TEST visualizar anclajes libres **** \n");

		this.writerService.statusFreeAnchorages(stationManagementService.bikeAnchorageFree(station));

		this.writerService.statusAnchorages(station);

		/**
		 * caso TEST anclar bicicleta(s)
		 */

		System.out.println("\n **** caso TEST anclar bicicleta(s) **** \n");

		int[] bicyclesCodes = {291, 292, 293, 294};

		station = this.stationManagementService.anchorBikes(station, bicyclesCodes);


		this.writerService.statusFreeAnchorages(stationManagementService.bikeAnchorageFree(station));


		/**
		 * Caso TEST consultar bicicletas ancladas
		 */

		System.out.println("\n **** caso TEST consultar bicicletas ancladas **** \n");

		this.writerService.statusAnchorages(station);
		//		station.consultarAnclajes();

		/**
		 * Caso TEST retirar bicicleta
		 */

		System.out.println("\n **** caso TEST retirar bicicleta **** \n");

		UserCard userCard =  UserCard.builder(CARD_ID)
			.actived(true)
			.build();

		this.writerService.status(userCard);

		station = this.stationManagementService.removeBike(userCard,station);

		this.writerService.statusAnchorages(station);

		this.writerService.statusFreeAnchorages(stationManagementService.bikeAnchorageFree(station));


		/**
		 * Caso TEST tarjeta inactiva
		 */

		System.out.println("\n **** caso TEST tarjeta inactiva **** \n");

		userCard = this.userCardManagement.cancelSubscription(userCard);

		this.writerService.status(userCard);

		station = this.stationManagementService.removeBike(userCard,station);

		this.writerService.statusAnchorages(station);

	}


}
