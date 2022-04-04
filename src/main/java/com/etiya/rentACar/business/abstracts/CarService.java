package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.damageRequest.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;


public interface CarService {
	
	CarDto getById(int id);
	DataResult<List<ListCarDto>> getAll();
	DataResult<List<ListCarDto>> getAllByModelYear(double modelYear);
	DataResult<List<ListCarDto>> getAllPaged(int pageNo,int pageSize);
	DataResult<List<ListCarDto>> getAllSorted();
	DataResult<List<ListCarDto>> getAllById(int id);

	DataResult<CarDto> updateCarState(UpdateCarStatusRequest updateCarStatusRequest);
	Result add(CreateCarRequest createCarRequest);


	Result delete(DeleteCarRequest deleteCarRequest);
	Result updateMaintenanceStatus(int id);
	void checkIfCarAvaible(int id);


	DataResult<CarDto> getByCarId(int id);

	Result update(UpdateCarRequest updateCarRequest);


	void updateCarStatusToAdd(int id);

	void checkIfCarAvailable(int id);

	DataResult<List<ListCarDto>> getAllByCity(String city);

	CarDto getCarKilometer(int id);
	void setCarKilometer(CreateRentalRequest createRentalRequest);
}
