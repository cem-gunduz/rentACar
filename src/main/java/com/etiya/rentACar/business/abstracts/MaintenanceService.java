package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.requests.brandRequests.DeleteBrandRequest;
import com.etiya.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;





public interface MaintenanceService {
	
	
	List<ListMaintenanceDto> getAll();
	List<ListMaintenanceDto> getByCarId(int id);
	
	void add(CreateMaintenanceRequest createMaintenanceRequest);
	void update(UpdateMaintenanceRequest updateMaintenanceRequest);
	void delete(DeleteMaintenanceRequest deleteMaintenanceRequest);
	

	
}
