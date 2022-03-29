package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.damageRequest.CreateDamageRequest;
import com.etiya.rentACar.business.requests.damageRequest.DeleteDamageRequest;
import com.etiya.rentACar.business.requests.damageRequest.UpdateDamageRequest;
import com.etiya.rentACar.business.responses.damageResponses.DamageDto;
import com.etiya.rentACar.business.responses.damageResponses.ListDamageDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.DamageDao;
import com.etiya.rentACar.entities.Damage;

@Service
public class DamageManager implements DamageService{

	private DamageDao damageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	
	
	public DamageManager(DamageDao damageDao, ModelMapperService modelMapperService,CarService carService) {
		this.damageDao = damageDao;
		this.modelMapperService = modelMapperService;
		this.carService=carService;
	}



	@Override
	public DamageDto getById(int id) {
		Damage result= this.damageDao.getById(id);
		
		DamageDto  response=this.modelMapperService.forDto().map(result, DamageDto.class);
		
		return response;
	}



	@Override
	public List<ListDamageDto> getAll() {
		
		List<Damage> result=this.damageDao.findAll();
		List<ListDamageDto>response=result.stream().map(carDamage->this.modelMapperService.forDto()
				.map(carDamage, ListDamageDto.class)).collect(Collectors.toList());
		
		return response;
	}



	@Override
	public List<ListDamageDto> getByCarId(int carId) {
		List<Damage> result=this.damageDao.getAllByCarId(carId);
		
		List<ListDamageDto>response=result.stream().map(carDamage->this.modelMapperService.forDto()
				.map(carDamage, ListDamageDto.class)).collect(Collectors.toList());
		
		return response;
	}



	@Override
	public void add(CreateDamageRequest createCarDamageRequest) {
	
		
		
		Damage carDamage= this.modelMapperService.forRequest().map(createCarDamageRequest, Damage.class);
		this.damageDao.save(carDamage);
	}



	@Override
	public void update(UpdateDamageRequest updateDamageRequest) {
		
		checkIfCarExist(updateDamageRequest.getCarId());
		
		Damage carDamage= this.modelMapperService.forRequest().map(updateDamageRequest, Damage.class);
		this.damageDao.save(carDamage);
		
		
	}



	@Override
	public void delete(DeleteDamageRequest deleteDamageRequest) {
		
		
		this.damageDao.deleteById(deleteDamageRequest.getId());
		
	}
	
	public void checkIfCarExist(int id) {
		if(this.carService.getById(id)==null) {
			throw new BusinessException("Böyle bir araba mevcut değil");
		}
	}



	  @Override
	    public List<ListDamageDto> getAllPaged(int pageNo, int pageSize) {
	        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
	        List<Damage> damages = damageDao.findAll(pageable).getContent();
	        List<ListDamageDto> response = damages.stream()
	                .map(cardamage -> modelMapperService.forDto().map(cardamage, ListDamageDto.class))
	                .collect(Collectors.toList());
	        return response;
	    }

	    @Override
	    public List<ListDamageDto> getAllSorted(String option, String field) {
	        Sort sort = Sort.by(Sort.Direction.valueOf(option), field);
	        List<Damage> damages = damageDao.findAll(sort);
	        List<ListDamageDto> response = damages.stream()
	                .map(damage -> modelMapperService.forDto().map(damage, ListDamageDto.class))
	                .collect(Collectors.toList());
	        return response;
	    }

}
