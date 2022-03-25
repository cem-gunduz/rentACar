package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;




public interface MaintenanceService {
	void add(CreateMaintenanceRequest createMaintenanceRequest);

	List<ListMaintenanceDto> getAll();
	
	List<ListMaintenanceDto> getByCarId(int id);
	List<ListMaintenanceDto>getByStateId(int id);
}
