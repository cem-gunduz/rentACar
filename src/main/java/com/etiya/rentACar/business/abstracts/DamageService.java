package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.requests.damageRequest.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequest.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequest.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.business.responses.damageResponses.DamageDto;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.dataAccess.abstracts.DamageDao;

public interface DamageService {

	DamageDto getById(int id);
	
	List<ListDamageDto> getAll();
	List<ListDamageDto> getByCarId(int carId);
	
	void add(CreateDamageRequest createDamageRequest);
	void update(UpdateDamageRequest updateDamageRequest);
	void delete(DeleteDamageRequest deleteDamageRequest);
	

	List<ListDamageDto> getAllPaged(int pageNo,int pageSize);
	List<ListDamageDto> getAllSorted(String option,String field);
}
