package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carDamageRequest.DeleteCarDamageRequest;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStateRequest;
import com.etiya.rentACar.business.responses.carDamageResponses.ListCarDamageDto;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;
import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CarDao;
import com.etiya.rentACar.entities.Brand;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.CarDamage;
import com.etiya.rentACar.entities.CarStates;
import com.etiya.rentACar.entities.Color;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;

	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {

		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		if(createCarRequest.getState()==CarStates.UnderMaintenance ) {
			throw new BusinessException(BusinessMessages.CarMessage.CAR_MAINTENANCE);
		}
		
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);// ilgili alanları mapler
		this.carDao.save(car);
		return new SuccessResult("BRAND_ADDED");

	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		List<Car> cars = this.carDao.findAll();
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(response);	

	}

	@Override
	public DataResult<List<ListCarDto>> getAllByModelYear(double modelYear) {
		List<Car> cars = this.carDao.getByModelYear(modelYear);
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		 return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> cars = this.carDao.findAll(pageable).getContent();
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		 return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC, "modelYear");
		List<Car> cars = this.carDao.findAll(sort);
		List<ListCarDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		 return new SuccessDataResult<List<ListCarDto>>(response);
	}
	 

	@Override
	public CarDto getById(int id) {
		
		Car car=this.carDao.getById(id);
		
		CarDto carDto = this.modelMapperService.forRequest().map(car, CarDto.class);
		 return carDto;
	}

	@Override
	public DataResult<ListCarDto> getAllById(int id) {
		
		return null;
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		checkIfCarExist(updateCarRequest.getId());
		
		Car car= this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("BRAND_UPDATED");
	}

	@Override
	public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
		this.carDao.deleteById(deleteCarDamageRequest.getId());
		return new SuccessResult("BRAND_DELETED");
		
		
	}
	
	public void checkIfCarExist(int id) {
		if(this.carDao.getById(id)==null) {
			throw new BusinessException(BusinessMessages.CarMessage.CAR_EXISTS);
		}
	}

	@Override
	public Result updateMaintenanceStatus(int id) {
		Car car = carDao.getById(id);
        car.setCarState(CarStates.UnderMaintenance);
        carDao.save(car);
        
        return new SuccessResult("BRAND_UPDATED");
		
	}
	
	public Result updateCarState(UpdateCarStateRequest updateCarStateRequest) {
		Car car = carDao.getById(updateCarStateRequest.getId());
		car.setCarState(updateCarStateRequest.getCarState());
		this.carDao.save(car);
		return new SuccessResult("STATE_UPDATED");
	}
	
	public void checkIfCarAvaible(int id) {
		Car car = carDao.getById(id);
		if(car.getCarState() == CarStates.UnderMaintenance || car.getCarState() == CarStates.Rented) {
			throw new BusinessException(BusinessMessages.CarStateMessage.CAR_NOT_AVAILABLE);
		} 
	} 
	
	
	
	


}
