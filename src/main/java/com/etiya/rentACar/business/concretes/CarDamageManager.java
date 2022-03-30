package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarDamageService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.etiya.rentACar.business.requests.carDamageRequest.DeleteCarDamageRequest;
import com.etiya.rentACar.business.requests.carDamageRequest.UpdateCarDamageRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.business.responses.carDamageResponses.CarDamageDto;
import com.etiya.rentACar.business.responses.carDamageResponses.ListCarDamageDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CarDamageDao;
import com.etiya.rentACar.entities.CarDamage;

@Service
public class CarDamageManager implements CarDamageService{

	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	
	
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService,CarService carService) {
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
		this.carService=carService;
	}



	@Override
	public DataResult<CarDamageDto> getById(int id) {
			CarDamage result= this.carDamageDao.getById(id);

			CarDamageDto  response=this.modelMapperService.forDto().map(result, CarDamageDto.class);

			return new SuccessDataResult<CarDamageDto>(response);
	}



	@Override
	public DataResult<List<ListCarDamageDto>> getAll() {
		
		List<CarDamage> result=this.carDamageDao.findAll();
		List<ListCarDamageDto>response=result.stream().map(carDamage->this.modelMapperService.forDto()
				.map(carDamage, ListCarDamageDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDamageDto>>(response);
	}



	@Override
	public DataResult<List<ListCarDamageDto>> getByCarId(int carId) {
		List<CarDamage> result=this.carDamageDao.getAllByCarId(carId);
		
		List<ListCarDamageDto>response=result.stream().map(carDamage->this.modelMapperService.forDto()
				.map(carDamage, ListCarDamageDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDamageDto>>(response);	}



	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
	
		CarDamage carDamage= this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);
		return new SuccessResult("BRAND_ADDED");

	}



	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		
		checkIfCarExist(updateCarDamageRequest.getCarId());
		
		CarDamage carDamage= this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);
		
		return new SuccessResult("BRAND_UPDATE");

	}



	@Override
	public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
		
		
		this.carDamageDao.deleteById(deleteCarDamageRequest.getId());
		return new SuccessResult("BRAND_DELETED");
		
	}
	
	public void checkIfCarExist(int id) {
		if(this.carService.getById(id)==null) {
			throw new BusinessException(BusinessMessages.CarDamageMessage.CAR_NOT_EXISTS);
		}
	}



	  @Override
	    public DataResult<List<ListCarDamageDto>> getAllPaged(int pageNo, int pageSize) {
	        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
	        List<CarDamage> damages = carDamageDao.findAll(pageable).getContent();
	        List<ListCarDamageDto> response = damages.stream()
	                .map(cardamage -> modelMapperService.forDto().map(cardamage, ListCarDamageDto.class))
	                .collect(Collectors.toList());
	        return new SuccessDataResult<List<ListCarDamageDto>>(response);	
	    }

	    @Override
	    public DataResult<List<ListCarDamageDto>> getAllSorted(String option, String field) {
	        Sort sort = Sort.by(Sort.Direction.valueOf(option), field);
	        List<CarDamage> damages = carDamageDao.findAll(sort);
	        List<ListCarDamageDto> response = damages.stream()
	                .map(damage -> modelMapperService.forDto().map(damage, ListCarDamageDto.class))
	                .collect(Collectors.toList());
	        return new SuccessDataResult<List<ListCarDamageDto>>(response);		    }

}
