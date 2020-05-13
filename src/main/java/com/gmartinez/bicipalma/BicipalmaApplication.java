package com.gmartinez.bicipalma;

import com.gmartinez.bicipalma.service.printer.WriterService;
import com.gmartinez.bicipalma.service.station.AnchorManagementService;
import com.gmartinez.bicipalma.service.station.RetentionService;
import com.gmartinez.bicipalma.service.station.StationManagementService;
import com.gmartinez.bicipalma.service.usercard.UserCardManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BicipalmaApplication {

	public static void main(String[] args) {

		SpringApplication.run(BicipalmaApplication.class, args);

		WriterService writerService = new WriterService();
		RetentionService retentionService = new RetentionService(writerService);
		AnchorManagementService anchorManagementService = new AnchorManagementService(retentionService);
		StationManagementService stationManagementService = new StationManagementService(anchorManagementService);
		UserCardManagement userCardManagement = new UserCardManagement();

		ExecutionOrder executionOrder = new ExecutionOrder(writerService, stationManagementService, userCardManagement);

		executionOrder.play();
	}

}
