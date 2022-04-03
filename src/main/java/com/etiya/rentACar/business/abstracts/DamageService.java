package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.damageRequest.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequest.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequest.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.DamageDto;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public interface DamageService {

	DataResult<DamageDto> getById(int id);
	
	DataResult<List<ListDamageDto>> getAll();
	DataResult<List<ListDamageDto>> getByCarId(int carId);
	DataResult<List<ListDamageDto>> getAllPaged(int pageNo, int pageSize);
	DataResult<List<ListDamageDto>> getAllSorted(String option, String field);

	Result add(CreateDamageRequest createCarDamageRequest);
	Result update(UpdateDamageRequest updateCarDamageRequest);
	Result delete(DeleteDamageRequest deleteCarDamageRequest);
	

}
