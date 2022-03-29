package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;


public interface CarService {
	
	CarDto getById(int id);
	List<ListCarDto> getAll();
	List<ListCarDto> getAllByModelYear(double modelYear);
	List<ListCarDto> getAllPaged(int pageNo,int pageSize);
	List<ListCarDto> getAllSorted();
	ListCarDto getAllById(int id);
	
	void add(CreateCarRequest createCarRequest);
	void update(UpdateCarRequest updateCarRequest);
	void delete(DeleteCarRequest deleteCarRequest);
	void updateMaintenanceStatus(int id);
	
	
}
