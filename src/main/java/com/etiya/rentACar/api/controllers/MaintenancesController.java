package com.etiya.rentACar.api.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.business.requests.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;

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
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateMaintenanceRequest updateMaintenanceRequest) {
		return this.maintenanceService.update(updateMaintenanceRequest);
	}


	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteMaintenanceRequest deleteMaintenanceRequest) {
		return this.maintenanceService.delete(deleteMaintenanceRequest);
	}
	
	
}

