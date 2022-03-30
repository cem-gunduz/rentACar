package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.carDamageRequest.DeleteCarDamageRequest;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStateRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.Car;


public interface CarService {
	
	CarDto getById(int id);
	DataResult<List<ListCarDto>> getAll();
	DataResult<List<ListCarDto>> getAllByModelYear(double modelYear);
	DataResult<List<ListCarDto>> getAllPaged(int pageNo,int pageSize);
	DataResult<List<ListCarDto>> getAllSorted();
	DataResult<ListCarDto> getAllById(int id);
	
	Result updateCarState(UpdateCarStateRequest updateCarStateRequest);
	Result add(CreateCarRequest createCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	Result delete(DeleteCarDamageRequest deleteCarDamageRequest);
	Result updateMaintenanceStatus(int id);
	void checkIfCarAvaible(int id);
	
	
}
