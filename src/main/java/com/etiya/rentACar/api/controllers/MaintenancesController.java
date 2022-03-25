package com.etiya.rentACar.api.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;


@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {
	private MaintenanceService maintenanceService;

	public MaintenancesController(MaintenanceService maintenanceService) {
		super();
		this.maintenanceService = maintenanceService;
	}

	@GetMapping("/getAll")
	public List<ListMaintenanceDto> getAll() {
		return maintenanceService.getAll();
	}

	@PostMapping("/add")
	public void add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
		this.maintenanceService.add(createMaintenanceRequest);
	}

	@GetMapping("/getByCarId")
	public List<ListMaintenanceDto> getByCarId(@RequestParam("carId") int id) {
		return maintenanceService.getByCarId(id);
	}
	@GetMapping("/getbystateid")
    public List<ListMaintenanceDto> getByStateId(@RequestParam("stateId") int id){
        return this.maintenanceService.getByStateId(id);
    }
}

