package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.etiya.rentACar.business.requests.carDamageRequest.DeleteCarDamageRequest;
import com.etiya.rentACar.business.requests.carDamageRequest.UpdateCarDamageRequest;
import com.etiya.rentACar.business.responses.carDamageResponses.CarDamageDto;
import com.etiya.rentACar.business.responses.carDamageResponses.ListCarDamageDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.dataAccess.abstracts.CarDamageDao;

public interface CarDamageService {

	DataResult<CarDamageDto> getById(int id);
	
	DataResult<List<ListCarDamageDto>> getAll();
	DataResult<List<ListCarDamageDto>> getByCarId(int carId);
	
	Result add(CreateCarDamageRequest createCarDamageRequest);
	Result update(UpdateCarDamageRequest updateCarDamageRequest);
	Result delete(DeleteCarDamageRequest deleteCarDamageRequest);
	

	DataResult<List<ListCarDamageDto>> getAllPaged(int pageNo,int pageSize);
	DataResult<List<ListCarDamageDto>> getAllSorted(String option,String field);
}
