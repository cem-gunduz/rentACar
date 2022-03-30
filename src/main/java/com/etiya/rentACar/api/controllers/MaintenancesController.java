package com.etiya.rentACar.api.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {
	private MaintenanceService maintenanceService;

	public MaintenancesController(MaintenanceService maintenanceService) {
		super();
		this.maintenanceService = maintenanceService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListMaintenanceDto>> getAll() {
		return maintenanceService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateMaintenanceRequest createMaintenanceRequest) {
		return this.maintenanceService.add(createMaintenanceRequest);
	}

	@GetMapping("/getByCarId")
	public DataResult<List<ListMaintenanceDto>> getByCarId(@RequestParam("carId") int id) {
		return maintenanceService.getByCarId(id);
	}

	
	
}

